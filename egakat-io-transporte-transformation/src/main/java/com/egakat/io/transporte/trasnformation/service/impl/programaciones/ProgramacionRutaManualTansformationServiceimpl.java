package com.egakat.io.transporte.trasnformation.service.impl.programaciones;

import static com.egakat.io.transporte.domain.programaciones.ProgramacionRutaManual.CLIENTE_CODIGO;
import static com.egakat.io.transporte.domain.programaciones.ProgramacionRutaManual.NUMERO_SOLICITUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egakat.econnect.maestros.client.service.api.lookup.LookUpService;
import com.egakat.integration.core.transformation.service.impl.TransformationServiceImpl;
import com.egakat.integration.files.dto.CampoDto;
import com.egakat.io.transporte.domain.programaciones.ProgramacionRutaManual;
import com.egakat.io.transporte.repository.programaciones.ProgramacionRutaManualRepository;
import com.egakat.io.transporte.trasnformation.service.api.programaciones.ProgramacionRutaManualTransformationService;

import lombok.val;

@Service
public class ProgramacionRutaManualTansformationServiceimpl extends TransformationServiceImpl<ProgramacionRutaManual>
		implements ProgramacionRutaManualTransformationService {

	@Autowired
	private ProgramacionRutaManualRepository repository;

	@Override
	protected ProgramacionRutaManualRepository getRepository() {
		return repository;
	}

	@Autowired
	private LookUpService lookUpService;

	@Override
	protected void translateField(ProgramacionRutaManual registro, CampoDto campo, String value) {
		switch (campo.getCodigo()) {
		case CLIENTE_CODIGO:
			translateCliente(registro, value);
			break;
		case NUMERO_SOLICITUD:
			translateOrden(registro, value);
			break;
		default:
		}
	}

	private void translateCliente(ProgramacionRutaManual registro, String value) {
		registro.setIdCliente(null);
		val id = lookUpService.findClienteIdByCodigo(value);
		registro.setIdCliente(id);
	}

	private void translateOrden(ProgramacionRutaManual registro, String value) {
		registro.setIdSolicitud(null);
		val id = new Long(0);// lookUpService.findClienteIdByClienteNumeroIndentificacion(value);
		registro.setIdSolicitud(id);
	}
}