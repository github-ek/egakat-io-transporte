package com.egakat.io.transporte.secundaria.service.impl.programaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egakat.integration.core.files.components.decorators.Decorator;
import com.egakat.integration.core.files.components.decorators.IncluirEncabezadoDecorator;
import com.egakat.integration.core.files.components.readers.ExcelWorkSheetReader;
import com.egakat.integration.core.files.components.readers.Reader;
import com.egakat.integration.core.files.service.impl.ArchivoExcelInputServiceImpl;
import com.egakat.io.transporte.components.decorators.programaciones.CamposRemesaProgramadaDecorator;
import com.egakat.io.transporte.components.decorators.programaciones.MapEntidadRemesaProgramadaDecorator;
import com.egakat.io.transporte.domain.programaciones.RemesaProgramada;
import com.egakat.io.transporte.repository.programaciones.RemesaProgramadaRepository;
import com.egakat.io.transporte.secundaria.service.api.programaciones.RemesaProgramadaRutaControlInputService;

import lombok.val;

@Service
@Transactional(readOnly = true)
public class RemesaProgramadaRutaControlInputServiceImpl extends ArchivoExcelInputServiceImpl<RemesaProgramada>
		implements RemesaProgramadaRutaControlInputService {

	private static final String TIPO_ARCHIVO_CODIGO = "REMESAS_PROGRAMADAS_RUTACONTROL";

	private static final String WORKSHEET_NAME = "Resumen_Diario";

	@Autowired
	private RemesaProgramadaRepository repository;

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
	protected RemesaProgramadaRepository getRepository() {
		return repository;
	}

	@Override
	protected Decorator<RemesaProgramada, Long> getIncluirEncabezadoDecorator(Decorator<RemesaProgramada, Long> inner) {
		return new IncluirEncabezadoDecorator<>(inner);
	}

	@Override
	protected Decorator<RemesaProgramada, Long> getEnriquecerCamposDecorator(Decorator<RemesaProgramada, Long> inner) {
		return new CamposRemesaProgramadaDecorator(inner);
	}

	@Override
	protected Decorator<RemesaProgramada, Long> getMapEntidadDecorator(Decorator<RemesaProgramada, Long> inner) {
		return new MapEntidadRemesaProgramadaDecorator(inner);
	}
}
