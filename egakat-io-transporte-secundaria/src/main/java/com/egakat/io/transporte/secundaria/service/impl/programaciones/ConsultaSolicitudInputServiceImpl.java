package com.egakat.io.transporte.secundaria.service.impl.programaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egakat.integration.core.files.components.decorators.Decorator;
import com.egakat.integration.core.files.service.impl.excel.ArchivoExcelInputServiceImpl;
import com.egakat.io.transporte.components.decorators.programaciones.MapEntidadConsultaSolicitudesDecorator;
import com.egakat.io.transporte.domain.programaciones.ConsultaSolicitudes;
import com.egakat.io.transporte.repository.programaciones.ConsultaSolicitudesRepository;
import com.egakat.io.transporte.secundaria.service.api.programaciones.ConsultaSolicitudesInputService;

@Component
public class ConsultaSolicitudInputServiceImpl extends ArchivoExcelInputServiceImpl<ConsultaSolicitudes>  implements ConsultaSolicitudesInputService{
	
	private static final String TIPO_ARCHIVO_CODIGO = "CORTE_DETALLADO_DISTRIBUCION";

	private static final String WORKSHEET_NAME = "SOLICITUDES";
	
	@Autowired
	private ConsultaSolicitudesRepository repository;

	@Override
	public String getTipoArchivoCodigo() {
		return TIPO_ARCHIVO_CODIGO;
	}
	
	@Override
	protected String getWorkSheetName() {
		return WORKSHEET_NAME;
	}

	@Override
	protected ConsultaSolicitudesRepository getRepository() {
		return repository;
	}

	@Override
	protected Decorator<ConsultaSolicitudes, Long> getMapEntidadDecorator(Decorator<ConsultaSolicitudes, Long> inner) {
		return new MapEntidadConsultaSolicitudesDecorator(inner);
	}

}