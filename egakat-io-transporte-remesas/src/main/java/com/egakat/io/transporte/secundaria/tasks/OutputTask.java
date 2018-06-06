package com.egakat.io.transporte.secundaria.tasks;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.egakat.econnect.maestros.client.service.api.BodegaLocalService;
import com.egakat.integration.files.enums.EstadoMensajeType;
import com.egakat.io.transporte.domain.silogtran.RemesaSilogtran;
import com.egakat.io.transporte.repository.silogtran.RemesaSilogtranRepository;
import com.egakat.io.transporte.secundaria.service.api.silogtran.RemesaSilogtranOutputService;

import lombok.val;

@Component
public class OutputTask {

	// -----------------------------------------------------------------------------------------------------------------------------------------------
	// -- SILOGTRAN
	// -----------------------------------------------------------------------------------------------------------------------------------------------
	@Autowired
	private RemesaSilogtranOutputService remesaSilogtranOutputService;

	@Autowired
	private RemesaSilogtranRepository repository;

	@Autowired
	private BodegaLocalService bodegaLocalService;

	@Transactional(readOnly = false)
	//@Scheduled(cron = "*/5 * * * * ?")
	public void run() {
		// @formatter:off
		val services = Arrays.asList(
				remesaSilogtranOutputService
				);
		// @formatter:on

		services.stream().forEach(service -> {
			try {
				System.out.println();
				System.out.println(LocalDateTime.now());
				val map = service.getMensajesPendientes().stream()
						.collect(groupingBy(RemesaSilogtran::correlacion, toList()));

				map.keySet().stream().sorted().forEach(key -> {

					Comparator<RemesaSilogtran> comparator = (a, b) -> a.getFechaCompromiso()
							.compareTo(b.getFechaCompromiso());
					comparator = comparator.thenComparing((a, b) -> a.getPlacaRemesa().compareTo(b.getPlacaRemesa()));
					comparator = comparator
							.thenComparing((a, b) -> a.getNumeroSolicitud().compareTo(b.getNumeroSolicitud()));

					val mensajes = map.get(key).stream().sorted(comparator).collect(toList());

					val archivo = getNombreArchivo(mensajes).replaceAll(" ", "_");

					val c = ';';
					val sb = new StringBuilder();
					val now = LocalDateTime.now();
					for (val mensaje : mensajes) {
						val td = mensaje.getTipoDocumento();
						val nd = mensaje.getNumeroSolicitud();
						val encabezado = sanitize(mensaje.linea(c));

						val lineas = mensaje.getLineas().stream()
								.sorted((a, b) -> Integer.compare(a.getNumeroLinea(), b.getNumeroLinea()))
								.collect(toList());
						for (val linea : lineas) {
							val t = String.format(encabezado, String.format(sanitize(linea.linea(c)), td, nd));
							sb.append(t);
						}

						mensaje.setEstado(EstadoMensajeType.ENVIADO);
						mensaje.setFechaEnvio(now);
					}

					Path file = Paths.get("C:/a/20180323/" + archivo);
					System.out.println("-----------------------------------------------------------------------");
					System.out.println(String.format("--- %s", file.toAbsolutePath()));
					System.out.println("----------------- ------------------------------------------------------");

					try (BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {
						writer.write(sb.toString());
						repository.saveAll(mensajes);
						repository.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
				});

				System.out.println(LocalDateTime.now());
				System.out.println();

				// service.enviar(ids);
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		});
	}

	private String getNombreArchivo(List<RemesaSilogtran> mensajes) {
		val mensaje = mensajes.get(0);
		val bodega = bodegaLocalService.findOneById(mensaje.getIdBodega());
		val linea = mensaje.getLineaNegocioCodigo();
		val tipoRemesa = mensaje.getTipoRemesa();
		val bodegaCodigo = sanitize(bodega.getNombre());
		val fechaCompromiso = mensaje.getFechaCompromiso().substring(0, 7);

		val result = String.format("%s-%s-%s-%s-%s.csv", LocalDate.now(), linea, tipoRemesa, bodegaCodigo,
				fechaCompromiso);
		return result;
	}

	private String sanitize(String s) {
		val sb = new StringBuilder(s.length());

		val array = s.toCharArray();
		for (int i = 0; i < array.length; i++) {
			char c = array[i];
			if (c == '%' && i < array.length - 1) {
				if (array[i + 1] != 's') {
					sb.append("%");
				}
			}

			sb.append(c);
		}

		return sb.toString();
	}
}
