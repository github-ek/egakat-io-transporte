package com.egakat.io.transporte.components.decorators.programaciones;

import java.time.LocalDate;

import com.egakat.integration.commons.archivos.dto.EtlRequestDto;
import com.egakat.integration.commons.archivos.dto.RegistroDto;
import com.egakat.integration.commons.archivos.enums.EstadoRegistroType;
import com.egakat.integration.core.files.components.decorators.Decorator;
import com.egakat.integration.core.files.components.decorators.MapEntidadDecorator;
import com.egakat.io.transporte.domain.programaciones.RemesaProgramada;

import lombok.val;

public class MapEntidadRemesaProgramadaDecorator extends MapEntidadDecorator<RemesaProgramada, Long> {

	public MapEntidadRemesaProgramadaDecorator(Decorator<RemesaProgramada, Long> inner) {
		super(inner);
	}

	@Override
	protected RemesaProgramada map(EtlRequestDto<RemesaProgramada, Long> archivo,
			RegistroDto<RemesaProgramada, Long> registro) {

		LocalDate fechaProgramada = getLocalDate(archivo, registro, RemesaProgramada.FECHA_PROGRAMADA);
		boolean esServicioDedicado = false;
		String paqueteadoraCodigo = getString(archivo, registro, RemesaProgramada.PAQUETEADORA_CODIGO);

		if ("DEDICADO".equals(paqueteadoraCodigo)) {
			paqueteadoraCodigo = "";
			esServicioDedicado = true;
		}

		val result = new RemesaProgramada();

		result.setIdArchivo(archivo.getArchivo().getId());
		result.setEstado(EstadoRegistroType.ESTRUCTURA_VALIDA);
		result.setNumeroLinea(registro.getNumeroLinea());
		result.setVersion(0);

		result.setPlacaProgramada(getString(archivo, registro, RemesaProgramada.PLACA_PROGRAMADA));
		result.setFechaProgramada(fechaProgramada);
		result.setClienteIdentificacion(getString(archivo, registro, RemesaProgramada.CLIENTE_IDENTIFICACION));
		result.setNumeroSolicitud(getString(archivo, registro, RemesaProgramada.NUMERO_SOLICITUD));
		result.setServicioDedicado(esServicioDedicado);
		result.setPaqueteadoraCodigo(paqueteadoraCodigo);

		return result;
	}
}