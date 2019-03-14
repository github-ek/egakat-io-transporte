package com.egakat.io.transporte.components.decorators.programaciones;

import com.egakat.integration.commons.archivos.dto.EtlRequestDto;
import com.egakat.integration.commons.archivos.dto.RegistroDto;
import com.egakat.integration.commons.archivos.enums.EstadoRegistroType;
import com.egakat.integration.core.files.components.decorators.Decorator;
import com.egakat.integration.core.files.components.decorators.MapEntidadDecorator;
import com.egakat.io.transporte.domain.programaciones.ConsultaSolicitudes;

import lombok.val;

public class MapEntidadConsultaSolicitudesDecorator extends MapEntidadDecorator<ConsultaSolicitudes, Long> {

	public MapEntidadConsultaSolicitudesDecorator(Decorator<ConsultaSolicitudes, Long> inner) {
		super(inner);
	}

	@Override
	protected ConsultaSolicitudes map(EtlRequestDto<ConsultaSolicitudes, Long> archivo,
			RegistroDto<ConsultaSolicitudes, Long> registro) {

		// @formatter:off
		val result = new  ConsultaSolicitudes();

		result.setIdArchivo(archivo.getArchivo().getId());
		result.setEstado(EstadoRegistroType.ESTRUCTURA_VALIDA);
		result.setNumeroLinea(registro.getNumeroLinea());
		result.setVersion(0);
		
		result.setClienteCodigo(getString(archivo,registro,ConsultaSolicitudes.CLIENTE_CODIGO));
		result.setNumeroSolicitud(getString(archivo,registro,ConsultaSolicitudes.NUMERO_SOLICITUD));
		// @formatter:on

		return result;
	}
}