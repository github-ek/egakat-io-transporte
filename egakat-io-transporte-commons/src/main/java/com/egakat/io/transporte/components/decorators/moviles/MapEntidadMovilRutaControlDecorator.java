package com.egakat.io.transporte.components.decorators.moviles;

import com.egakat.integration.commons.archivos.dto.EtlRequestDto;
import com.egakat.integration.commons.archivos.dto.RegistroDto;
import com.egakat.integration.commons.archivos.enums.EstadoRegistroType;
import com.egakat.integration.core.files.components.decorators.Decorator;
import com.egakat.integration.core.files.components.decorators.MapEntidadDecorator;
import com.egakat.io.transporte.domain.moviles.MovilRutaControl;

import lombok.val;

public class MapEntidadMovilRutaControlDecorator extends MapEntidadDecorator<MovilRutaControl, Long> {

	public MapEntidadMovilRutaControlDecorator(Decorator<MovilRutaControl, Long> inner) {
		super(inner);
	}

	@Override
	protected MovilRutaControl map(EtlRequestDto<MovilRutaControl, Long> archivo,
			RegistroDto<MovilRutaControl, Long> registro) {

		// @formatter:off
		val result = new MovilRutaControl();

		result.setIdArchivo(archivo.getArchivo().getId());
		result.setEstado(EstadoRegistroType.ESTRUCTURA_VALIDA);
		result.setNumeroLinea(registro.getNumeroLinea());
		result.setVersion(0);
				
		result.setMovil(getString(archivo,registro,MovilRutaControl.MOVIL));
		result.setPlaca(getString(archivo,registro,MovilRutaControl.PLACA));

		// @formatter:on

		return result;
	}
}