package com.egakat.io.transporte.secundaria.service.impl.planillas;

import org.springframework.stereotype.Service;

@Service("calPlanillaRutaExtractService")
public class CalPlanillaRutaExtractServiceImpl extends PlanillaRutaExtractServiceImpl{
	
	private static final String TIPO_ARCHIVO_CODIGO = "PLANILLA_RUTA_CAL";
	
	@Override
	public String getTipoArchivoCodigo() {
		return TIPO_ARCHIVO_CODIGO;
	}
}