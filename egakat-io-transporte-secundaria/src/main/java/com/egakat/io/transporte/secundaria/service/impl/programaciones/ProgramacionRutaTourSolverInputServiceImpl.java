package com.egakat.io.transporte.secundaria.service.impl.programaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egakat.integration.core.files.components.decorators.Decorator;
import com.egakat.integration.core.files.service.impl.ArchivoExcelInputServiceImpl;
import com.egakat.io.transporte.components.decorators.programaciones.FiltroProgramacionRutaTourSolverDecorator;
import com.egakat.io.transporte.components.decorators.programaciones.MapEntidadProgramacionRutaTourSolverDecorator;
import com.egakat.io.transporte.domain.programaciones.ProgramacionRutaTourSolver;
import com.egakat.io.transporte.repository.programaciones.ProgramacionRutaTourSolverRepository;
import com.egakat.io.transporte.secundaria.service.api.programaciones.ProgramacionRutaTourSolverInputService;

@Service
public class ProgramacionRutaTourSolverInputServiceImpl extends ArchivoExcelInputServiceImpl<ProgramacionRutaTourSolver>  implements ProgramacionRutaTourSolverInputService{

	private static final String TIPO_ARCHIVO_CODIGO = "PROGRAMACION_RUTA_TOURSOLVER";

	private static final String WORKSHEET_NAME = "Informe";

	@Autowired
	private ProgramacionRutaTourSolverRepository repository;

	@Override
	public String getTipoArchivoCodigo() {
		return TIPO_ARCHIVO_CODIGO;
	}

	@Override
	protected String getWorkSheetName() {
		return WORKSHEET_NAME;
	}

	@Override
	protected ProgramacionRutaTourSolverRepository getRepository() {
		return repository;
	}

	@Override
	protected Decorator<ProgramacionRutaTourSolver, Long> getFiltrarRegistrosDecorator(Decorator<ProgramacionRutaTourSolver, Long> inner) {
		return new FiltroProgramacionRutaTourSolverDecorator(inner);
	}

	@Override
	protected Decorator<ProgramacionRutaTourSolver, Long> getMapEntidadDecorator(Decorator<ProgramacionRutaTourSolver, Long> inner) {
		return new MapEntidadProgramacionRutaTourSolverDecorator(inner);
	}
}
