package com.egakat.io.transporte.secundaria.service.impl.programaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egakat.integration.core.files.components.decorators.Decorator;
import com.egakat.integration.core.files.service.impl.ArchivoExcelInputServiceImpl;
import com.egakat.io.transporte.components.decorators.programaciones.MapEntidadProgramacionRutaManualDecorator;
import com.egakat.io.transporte.domain.programaciones.ProgramacionRutaManual;
import com.egakat.io.transporte.repository.programaciones.ProgramacionRutaManualRepository;
import com.egakat.io.transporte.secundaria.service.api.programaciones.ProgramacionRutaManualInputService;

@Component
public class ProgramacionRutaManualInputServiceImpl extends ArchivoExcelInputServiceImpl<ProgramacionRutaManual>  implements ProgramacionRutaManualInputService{
	
	private static final String TIPO_ARCHIVO_CODIGO = "PROGRAMACION_RUTA_MANUAL";

	private static final String WORKSHEET_NAME = "PROGRAMACIONES";
	
	@Autowired
	private ProgramacionRutaManualRepository repository;

	@Override
	public String getTipoArchivoCodigo() {
		return TIPO_ARCHIVO_CODIGO;
	}
	
	@Override
	protected String getWorkSheetName() {
		return WORKSHEET_NAME;
	}

	@Override
	protected ProgramacionRutaManualRepository getRepository() {
		return repository;
	}

	@Override
	protected Decorator<ProgramacionRutaManual, Long> getMapEntidadDecorator(Decorator<ProgramacionRutaManual, Long> inner) {
		return new MapEntidadProgramacionRutaManualDecorator(inner);
	}
}