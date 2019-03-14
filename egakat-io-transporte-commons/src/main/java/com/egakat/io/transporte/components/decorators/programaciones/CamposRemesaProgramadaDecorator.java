package com.egakat.io.transporte.components.decorators.programaciones;

import com.egakat.integration.commons.archivos.dto.EtlRequestDto;
import com.egakat.integration.commons.archivos.dto.RegistroDto;
import com.egakat.integration.core.files.components.decorators.Decorator;
import com.egakat.io.transporte.domain.programaciones.RemesaProgramada;

import lombok.val;

public class CamposRemesaProgramadaDecorator extends Decorator<RemesaProgramada, Long> {

	public CamposRemesaProgramadaDecorator(Decorator<RemesaProgramada, Long> inner) {
		super(inner);
	}

	@Override
	public EtlRequestDto<RemesaProgramada, Long> transformar(EtlRequestDto<RemesaProgramada, Long> archivo) {
		val result = super.transformar(archivo);

		for (val registro : result.getRegistros()) {
			reemplazarValoresNull(registro);
		}

		return result;
	}

	protected void reemplazarValoresNull(RegistroDto<RemesaProgramada, Long> registro) {
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
