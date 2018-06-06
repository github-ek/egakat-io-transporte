package com.egakat.io.transporte.secundaria.service.impl.programaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egakat.integration.core.files.components.decorators.Decorator;
import com.egakat.integration.core.files.components.decorators.IncluirEncabezadoDecorator;
import com.egakat.integration.core.files.service.impl.excel.ArchivoExcelInputServiceImpl;
import com.egakat.io.transporte.domain.programaciones.ConsultaSolicitud;
import com.egakat.io.transporte.repository.programaciones.ConsultaSolicitudRepository;
import com.egakat.io.transporte.secundaria.service.api.programaciones.ConsultaSolicitudInputService;

@Component
public class ConsultaSolicitudInputServiceImpl extends ArchivoExcelInputServiceImpl<ConsultaSolicitud>  implements ConsultaSolicitudInputService{
	
	private static final String TIPO_ARCHIVO_CODIGO = "PROGRAMACION_RUTA_MANUAL";

	@Autowired
	private ConsultaSolicitudRepository repository;

	@Override
	public String getTipoArchivoCodigo() {
		return TIPO_ARCHIVO_CODIGO;
	}

	@Override
	protected ConsultaSolicitudRepository getRepository() {
		return repository;
	}

	@Override
	protected Decorator<ConsultaSolicitud, Long> getIncluirEncabezadoDecorator(Decorator<ConsultaSolicitud, Long> inner) {
		return new IncluirEncabezadoDecorator<>(inner);
	}

	@Override
	protected Decorator<ConsultaSolicitud, Long> getMapEntidadDecorator(Decorator<ConsultaSolicitud, Long> inner) {
		return null;//new MapEntidadProgramacionRutaManualDecorator(inner);
	}

	@Override
	protected String getWorkSheetName() {
		// TODO Auto-generated method stub
		return null;
	}
}