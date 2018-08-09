package com.egakat.io.transporte.secundaria.service.impl.cierres;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egakat.integration.core.files.components.decorators.Decorator;
import com.egakat.integration.core.files.components.decorators.IncluirEncabezadoDecorator;
import com.egakat.integration.core.files.components.readers.ExcelWorkSheetReader;
import com.egakat.integration.core.files.components.readers.Reader;
import com.egakat.integration.core.files.service.impl.excel.ArchivoExcelInputServiceImpl;
import com.egakat.io.transporte.components.decorators.cierres.CamposPlanillaRutaControlDecorator;
import com.egakat.io.transporte.components.decorators.cierres.MapEntidadPlanillaRutaControlDecorator;
import com.egakat.io.transporte.domain.cierres.PlanillaRutaControl;
import com.egakat.io.transporte.repository.cierres.PlanillaRutaControlRepository;
import com.egakat.io.transporte.secundaria.service.api.cierres.PlanillaRutaControlInputService;

import lombok.val;

@Service
@Transactional(readOnly = true)
public class PlanillaRutaControlInputServiceImpl extends ArchivoExcelInputServiceImpl<PlanillaRutaControl>
		implements PlanillaRutaControlInputService {

	private static final String TIPO_ARCHIVO_CODIGO = "PLANILLA_RUTACONTROL";

	private static final String WORKSHEET_NAME = "Resumen_Diario";

	@Autowired
	private PlanillaRutaControlRepository repository;

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
		reader.setRowOffset(6);
		reader.setColOffset(1);
		reader.setLastCellNum(20);
		return reader;
	}

	@Override
	protected PlanillaRutaControlRepository getRepository() {
		return repository;
	}

	@Override
	protected Decorator<PlanillaRutaControl, Long> getIncluirEncabezadoDecorator(Decorator<PlanillaRutaControl, Long> inner) {
		return new IncluirEncabezadoDecorator<>(inner);
	}

	@Override
	protected Decorator<PlanillaRutaControl, Long> getEnriquecerCamposDecorator(Decorator<PlanillaRutaControl, Long> inner) {
		return new CamposPlanillaRutaControlDecorator(inner);
	}

	@Override
	protected Decorator<PlanillaRutaControl, Long> getMapEntidadDecorator(Decorator<PlanillaRutaControl, Long> inner) {
		return new MapEntidadPlanillaRutaControlDecorator(inner);
	}
}
