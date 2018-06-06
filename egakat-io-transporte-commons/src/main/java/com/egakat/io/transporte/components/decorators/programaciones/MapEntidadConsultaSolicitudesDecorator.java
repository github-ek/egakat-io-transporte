package com.egakat.io.transporte.components.decorators.programaciones;

import com.egakat.integration.core.files.components.decorators.Decorator;
import com.egakat.integration.core.files.components.decorators.MapEntidadDecorator;
import com.egakat.integration.files.dto.EtlRequestDto;
import com.egakat.integration.files.dto.RegistroDto;
import com.egakat.integration.files.enums.EstadoRegistroType;
import com.egakat.io.transporte.domain.programaciones.ConsultaSolicitudes;

import lombok.val;

public class MapEntidadConsultaSolicitudesDecorator extends MapEntidadDecorator<ConsultaSolicitudes,Long> {


	public MapEntidadConsultaSolicitudesDecorator(Decorator<ConsultaSolicitudes,Long> inner) {
		super(inner);
	}

	@Override
	protected ConsultaSolicitudes map(EtlRequestDto<ConsultaSolicitudes,Long> archivo, RegistroDto<ConsultaSolicitudes,Long> registro) {

		// @formatter:off
		val result = ConsultaSolicitudes
				.builder()
				.idArchivo(archivo.getArchivo().getId())
				.estado(EstadoRegistroType.ESTRUCTURA_VALIDA)
				.numeroLinea(registro.getNumeroLinea())
				.version(0)
				
				.clienteCodigo(getString(archivo,registro,ConsultaSolicitudes.CLIENTE_CODIGO))
				.numeroSolicitud(getString(archivo,registro,ConsultaSolicitudes.NUMERO_SOLICITUD))
				.build();
		// @formatter:on

		return result;
	}
}