package com.egakat.io.transporte.secundaria.service.impl.moviles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egakat.integration.core.files.components.decorators.Decorator;
import com.egakat.integration.core.files.service.impl.excel.ArchivoExcelInputServiceImpl;
import com.egakat.io.transporte.components.decorators.moviles.FiltroMovilRutaControlDecorator;
import com.egakat.io.transporte.components.decorators.moviles.MapEntidadMovilRutaControlDecorator;
import com.egakat.io.transporte.domain.moviles.MovilRutaControl;
import com.egakat.io.transporte.repository.moviles.MovilRutaControlRepository;

@Component
public class MovilesRutaControlInputServiceImpl extends ArchivoExcelInputServiceImpl<MovilRutaControl> {

	private static final String TIPO_ARCHIVO_CODIGO = "RUTACONTROL_MOVILES";

	private static final String WORKSHEET_NAME = "0";

	@Autowired
	private MovilRutaControlRepository repository;

	@Override
	public String getTipoArchivoCodigo() {
		return TIPO_ARCHIVO_CODIGO;
	}

	@Override
	protected String getWorkSheetName() {
		return WORKSHEET_NAME;
	}

	@Override
	protected MovilRutaControlRepository getRepository() {
		return repository;
	}

	@Override
	protected Decorator<MovilRutaControl, Long> getFiltrarRegistrosDecorator(Decorator<MovilRutaControl, Long> inner) {
		return new FiltroMovilRutaControlDecorator(inner);
	}

	@Override
	protected Decorator<MovilRutaControl, Long> getMapEntidadDecorator(Decorator<MovilRutaControl, Long> inner) {
		return new MapEntidadMovilRutaControlDecorator(inner);
	}
}