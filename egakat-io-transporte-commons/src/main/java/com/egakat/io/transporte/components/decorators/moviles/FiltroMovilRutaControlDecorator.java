package com.egakat.io.transporte.components.decorators.moviles;

import java.util.stream.Collectors;

import com.egakat.integration.commons.archivos.dto.EtlRequestDto;
import com.egakat.integration.core.files.components.decorators.Decorator;
import com.egakat.io.transporte.domain.moviles.MovilRutaControl;

import lombok.val;

public class FiltroMovilRutaControlDecorator extends Decorator<MovilRutaControl,Long> {


	public FiltroMovilRutaControlDecorator(Decorator<MovilRutaControl,Long> inner) {
		super(inner);
	}

	@Override
	public EtlRequestDto<MovilRutaControl,Long> transformar(EtlRequestDto<MovilRutaControl,Long> archivoDTO) {
		final val result = super.transformar(archivoDTO);

		// @formatter:off
		val registros = result
				.getRegistros()
				.stream()
				.filter(a -> a.getDatos().get(MovilRutaControl.MOVIL).startsWith("TACTIC"))
				.collect(Collectors.toList());
		result.getRegistros().clear();
		result.getRegistros().addAll(registros);
		// @formatter:on

		return result;
	}
}
