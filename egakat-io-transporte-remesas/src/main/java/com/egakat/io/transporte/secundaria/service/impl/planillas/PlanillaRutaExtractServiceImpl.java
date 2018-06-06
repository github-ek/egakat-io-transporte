package com.egakat.io.transporte.secundaria.service.impl.planillas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egakat.integration.core.files.components.decorators.Decorator;
import com.egakat.integration.core.files.components.decorators.DistinctDecorator;
import com.egakat.integration.core.files.components.readers.ExcelWorkSheetReader;
import com.egakat.integration.core.files.components.readers.Reader;
import com.egakat.integration.core.files.service.impl.excel.ArchivoExcelInputServiceImpl;
import com.egakat.io.transporte.components.decorators.planillas.LimpiarLineasPlanillaRutaDecorator;
import com.egakat.io.transporte.components.decorators.planillas.MapEntidadPlanillaRutaDecorator;
import com.egakat.io.transporte.domain.planillas.PlanillaRuta;
import com.egakat.io.transporte.repository.planillas.PlanillaRutaRepository;
import com.egakat.io.transporte.secundaria.service.api.planillas.PlanillaRutaInputService;

import lombok.val;

@Service("planillaRutaExtractService")
public class PlanillaRutaExtractServiceImpl extends ArchivoExcelInputServiceImpl<PlanillaRuta> implements PlanillaRutaInputService{
	
	private static final String TIPO_ARCHIVO_CODIGO = "PLANILLA_RUTA";
	
	private static final String WORKSHEET_NAME = "0";

	@Autowired
	private PlanillaRutaRepository repository;

	@Override
	public String getTipoArchivoCodigo() {
		return TIPO_ARCHIVO_CODIGO;
	}

	@Override
	protected String getWorkSheetName() {
		return WORKSHEET_NAME;
	}

	@Override
	protected Reader getReader() {
		val reader = (ExcelWorkSheetReader) super.getReader();
		reader.setRowOffset(7);
		reader.setColOffset(1);
		reader.setLastCellNum(15);
		return reader;
	}

	
	@Override
	protected PlanillaRutaRepository getRepository() {
		return repository;
	}
	
	@Override
	protected Decorator<PlanillaRuta, Long> getLimpiarLineasDecorator(Decorator<PlanillaRuta, Long> inner) {
		return new LimpiarLineasPlanillaRutaDecorator(inner);
	}

	@Override
	protected Decorator<PlanillaRuta, Long> getFiltrarRegistrosDecorator(Decorator<PlanillaRuta, Long> inner) {
		return new DistinctDecorator<PlanillaRuta,Long>(inner);
	}

	@Override
	protected Decorator<PlanillaRuta, Long> getMapEntidadDecorator(Decorator<PlanillaRuta, Long> inner) {
		return new MapEntidadPlanillaRutaDecorator(inner);
	}
	
	
}