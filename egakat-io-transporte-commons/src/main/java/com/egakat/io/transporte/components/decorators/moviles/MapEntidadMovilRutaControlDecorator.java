package com.egakat.io.transporte.components.decorators.moviles;

import com.egakat.integration.core.files.components.decorators.Decorator;
import com.egakat.integration.core.files.components.decorators.MapEntidadDecorator;
import com.egakat.integration.files.dto.EtlRequestDto;
import com.egakat.integration.files.dto.RegistroDto;
import com.egakat.integration.files.enums.EstadoRegistroType;
import com.egakat.io.transporte.domain.moviles.MovilRutaControl;

import lombok.val;

public class MapEntidadMovilRutaControlDecorator extends MapEntidadDecorator<MovilRutaControl,Long> {


	public MapEntidadMovilRutaControlDecorator(Decorator<MovilRutaControl,Long> inner) {
		super(inner);
	}

	@Override
	protected MovilRutaControl map(EtlRequestDto<MovilRutaControl,Long> archivo, RegistroDto<MovilRutaControl,Long> registro) {

		// @formatter:off
		val result = MovilRutaControl
				.builder()
				.idArchivo(archivo.getArchivo().getId())
				.estado(EstadoRegistroType.ESTRUCTURA_VALIDA)
				.numeroLinea(registro.getNumeroLinea())
				.version(0)
				
				.movil(getString(archivo,registro,MovilRutaControl.MOVIL))
				.placa(getString(archivo,registro,MovilRutaControl.PLACA))
				.build();
		// @formatter:on

		return result;
	}
}