package com.egakat.io.transporte.trasnformation.service.impl.programaciones;

import static com.egakat.io.transporte.domain.programaciones.ProgramacionRutaTourSolver.CLIENTE_CODIGO;
import static com.egakat.io.transporte.domain.programaciones.ProgramacionRutaTourSolver.IDENTIFICADOR_ORDEN;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egakat.econnect.maestros.client.service.api.lookup.LookUpService;
import com.egakat.integration.core.transformation.service.impl.TransformationServiceImpl;
import com.egakat.integration.files.dto.CampoDto;
import com.egakat.io.transporte.domain.programaciones.ProgramacionRutaTourSolver;
import com.egakat.io.transporte.repository.programaciones.ProgramacionRutaTourSolverRepository;
import com.egakat.io.transporte.trasnformation.service.api.programaciones.ProgramacionRutaTourSolverTransformationService;

import lombok.val;

@Service
public class ProgramacionRutaTourSolverTansformationServiceimpl
		extends TransformationServiceImpl<ProgramacionRutaTourSolver>
		implements ProgramacionRutaTourSolverTransformationService {

	@Autowired
	private ProgramacionRutaTourSolverRepository repository;

	@Override
	protected ProgramacionRutaTourSolverRepository getRepository() {
		return repository;
	}

	@Autowired
	private LookUpService lookUpService;

	@Override
	public void cacheEvict() {
		lookUpService.cacheEvict();
	}

	@Override
	protected void translateField(ProgramacionRutaTourSolver registro, CampoDto campo, String value) {
		switch (campo.getCodigo()) {
		case CLIENTE_CODIGO:
			translateCliente(registro, value);
			break;
		case IDENTIFICADOR_ORDEN:
			translateOrden(registro, value);
			break;
		default:
		}
	}

	private void translateCliente(ProgramacionRutaTourSolver registro, String value) {
		registro.setIdCliente(null);
		val id = lookUpService.findClienteIdByCodigo(value);
		registro.setIdCliente(id);
	}

	private void translateOrden(ProgramacionRutaTourSolver registro, String value) {
		registro.setIdSolicitud(null);
		val id = new Long(0);// lookUpService.findClienteIdByClienteNumeroIndentificacion(value);
		registro.setIdSolicitud(id);
	}
}