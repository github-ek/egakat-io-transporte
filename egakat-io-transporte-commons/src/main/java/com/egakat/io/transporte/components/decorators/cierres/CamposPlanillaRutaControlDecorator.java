package com.egakat.io.transporte.components.decorators.cierres;

import com.egakat.integration.commons.archivos.dto.EtlRequestDto;
import com.egakat.integration.commons.archivos.dto.RegistroDto;
import com.egakat.integration.core.files.components.decorators.Decorator;
import com.egakat.io.transporte.domain.cierres.PlanillaRutaControl;

import lombok.val;

public class CamposPlanillaRutaControlDecorator extends Decorator<PlanillaRutaControl, Long> {

	public CamposPlanillaRutaControlDecorator(Decorator<PlanillaRutaControl, Long> inner) {
		super(inner);
	}

	@Override
	public EtlRequestDto<PlanillaRutaControl, Long> transformar(EtlRequestDto<PlanillaRutaControl, Long> archivo) {
		val result = super.transformar(archivo);

		for (val registro : result.getRegistros()) {
			reemplazarValoresNull(registro);
		}

		return result;
	}

	protected void reemplazarValoresNull(RegistroDto<PlanillaRutaControl, Long> registro) {
		// @formatter:off
		registro
		.getDatos()
		.entrySet()
		.stream()
		.filter(a -> a.getValue().equals("NULL"))
		.forEach(a -> a.setValue(""));
		// @formatter:on
	}
}