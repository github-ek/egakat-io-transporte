package com.egakat.io.transporte.trasnformation.service.impl.programaciones;

import static com.egakat.integration.files.enums.EstadoRegistroType.DESCARTADO;
import static com.egakat.integration.files.enums.EstadoRegistroType.ERROR_VALIDACION;
import static com.egakat.io.transporte.domain.programaciones.RemesaProgramada.CLIENTE_IDENTIFICACION;
import static com.egakat.io.transporte.domain.programaciones.RemesaProgramada.NUMERO_SOLICITUD;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egakat.econnect.maestros.client.service.api.lookup.LookUpService;
import com.egakat.integration.core.transformation.service.impl.TransformationServiceImpl;
import com.egakat.integration.files.dto.ArchivoErrorDto;
import com.egakat.integration.files.dto.CampoDto;
import com.egakat.io.transporte.domain.programaciones.RemesaProgramada;
import com.egakat.io.transporte.repository.programaciones.RemesaProgramadaRepository;
import com.egakat.io.transporte.trasnformation.service.api.programaciones.RemesaProgramadaTransformationService;

import lombok.val;

@Service
public class RemesaProgramadaTansformationServiceimpl extends TransformationServiceImpl<RemesaProgramada>
		implements RemesaProgramadaTransformationService {

	@Autowired
	private RemesaProgramadaRepository repository;

	@Override
	protected RemesaProgramadaRepository getRepository() {
		return repository;
	}

	@Autowired
	private LookUpService lookUpService;
	
	@Override
	public void cacheEvict() {
		lookUpService.cacheEvict();
	}

	@Override
	protected void translateField(RemesaProgramada registro, CampoDto campo, String value) {
		switch (campo.getCodigo()) {
		case RemesaProgramada.CLIENTE_IDENTIFICACION:
			translateCliente(registro, value);
			break;
		case RemesaProgramada.NUMERO_SOLICITUD:
			// TODO
		default:
		}
	}

	private void translateCliente(RemesaProgramada registro, String value) {
		registro.setIdCliente(null);
		val id = lookUpService.findClienteIdByNumeroIndentificacion(value);
		registro.setIdCliente(id);
	}

	@Override
	protected boolean beforeValidateRow(RemesaProgramada registro, List<ArchivoErrorDto> errores,
			List<CampoDto> campos) {
		boolean result = super.beforeValidateRow(registro, errores, campos);

		val placa = registro.getPlacaProgramada();

		if (placa.startsWith("CLI-REC-")) {
			registro.setEstado(DESCARTADO);
		}

		//CASO PARTICULAR DE GWS YA SE VA Y NO BME VOY A COMPLICAR CON ESTA EMPANADA
		if (placa.startsWith("BMW347")) {
			registro.setEstado(DESCARTADO);
		}

		return result;
	}

	@Override
	protected boolean validateRow(RemesaProgramada registro, List<ArchivoErrorDto> errores, List<CampoDto> campos) {
		boolean result = super.validateRow(registro, errores, campos);
		boolean success = true;
		val idArchivo = registro.getIdArchivo();
		val numeroLinea = registro.getNumeroLinea();
		val datos = registro.toString();

		val identificacion = registro.getClienteIdentificacion();
		val numeroSolicitud = registro.getNumeroSolicitud();
		val placa = registro.getPlacaProgramada();
		val paqueteadora = registro.getPaqueteadoraCodigo();

		if ((identificacion.isEmpty() && !numeroSolicitud.isEmpty())
				|| (!identificacion.isEmpty() && numeroSolicitud.isEmpty())) {

			String mensaje = "Los campos %s y %s deben ser ambos diferentes de vacio o ambos vacios";
			mensaje = String.format(mensaje, CLIENTE_IDENTIFICACION, NUMERO_SOLICITUD);
			errores.add(ArchivoErrorDto.error(idArchivo, mensaje, numeroLinea, datos));
			success = false;
		}

		if (!identificacion.isEmpty() && registro.getIdCliente() == null) {
			String mensaje = "Este campo no admite el valor [%s].";
			mensaje = String.format(mensaje, identificacion);
			errores.add(ArchivoErrorDto.error(idArchivo, mensaje, numeroLinea, datos));
			success = false;
		}

		if (placa.startsWith("PAQUETEO") && paqueteadora.isEmpty()) {
			// String mensaje = "Cuando la placa corresponda a una operación de paqueteo, se
			// debe suministrar un código de paqueteadora";
			// errores.add(ArchivoErrorDto.error(idArchivo, mensaje, numeroLinea, datos));
			// success = false;
		}

		if (placa.startsWith("TACTIC_")) {
			//String mensaje = "No se permite el uso de placas genéricas. Si aún no dispone de la placa real, es preferible que excluya estas remesas y las programe cuando tenga el dato real";
			//errores.add(ArchivoErrorDto.error(idArchivo, mensaje, numeroLinea, datos));
			//success = false;
		}

		if (!success) {
			registro.setEstado(ERROR_VALIDACION);
			result = false;
		}

		return result;
	}
}