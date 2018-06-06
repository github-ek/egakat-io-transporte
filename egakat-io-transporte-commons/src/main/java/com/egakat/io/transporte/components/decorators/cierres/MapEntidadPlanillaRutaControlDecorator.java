package com.egakat.io.transporte.components.decorators.cierres;

import static com.egakat.io.transporte.domain.cierres.PlanillaRutaControl.CAUSAL_NOVEDAD_CODIGO_ALTERNO;
import static com.egakat.io.transporte.domain.cierres.PlanillaRutaControl.CLIENTE_IDENTIFICACION;
import static com.egakat.io.transporte.domain.cierres.PlanillaRutaControl.ESTADO_CODIGO_ALTERNO;
import static com.egakat.io.transporte.domain.cierres.PlanillaRutaControl.FECHA_HORA_FIN;
import static com.egakat.io.transporte.domain.cierres.PlanillaRutaControl.FECHA_HORA_INICIO;
import static com.egakat.io.transporte.domain.cierres.PlanillaRutaControl.FECHA_PROGRAMADA;
import static com.egakat.io.transporte.domain.cierres.PlanillaRutaControl.NUMERO_SOLICITUD;
import static com.egakat.io.transporte.domain.cierres.PlanillaRutaControl.OBSERVACION;
import static com.egakat.io.transporte.domain.cierres.PlanillaRutaControl.PLACA_PROGRAMADA;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.egakat.integration.core.files.components.decorators.Decorator;
import com.egakat.integration.core.files.components.decorators.MapEntidadDecorator;
import com.egakat.integration.files.dto.EtlRequestDto;
import com.egakat.integration.files.dto.RegistroDto;
import com.egakat.integration.files.enums.EstadoRegistroType;
import com.egakat.io.transporte.domain.cierres.PlanillaRutaControl;

import lombok.val;

public class MapEntidadPlanillaRutaControlDecorator extends MapEntidadDecorator<PlanillaRutaControl, Long> {

	public MapEntidadPlanillaRutaControlDecorator(Decorator<PlanillaRutaControl, Long> inner) {
		super(inner);
	}

	@Override
	protected PlanillaRutaControl map(EtlRequestDto<PlanillaRutaControl, Long> archivo,
			RegistroDto<PlanillaRutaControl, Long> registro) {

		LocalDate fechaProgramada = getLocalDate(archivo, registro, FECHA_PROGRAMADA);
		LocalDateTime fechaHoraInicio = getLocalDateTime(archivo, registro, FECHA_HORA_INICIO);
		LocalDateTime fechaHoraFin = getLocalDateTime(archivo, registro, FECHA_HORA_FIN);

		
		// @formatter:off
		val result = PlanillaRutaControl.builder()
				.idArchivo(archivo.getArchivo().getId())
				.estado(EstadoRegistroType.ESTRUCTURA_VALIDA)
				.numeroLinea(registro.getNumeroLinea())
				.version(0)
				
				.placaProgramada(getString(archivo, registro,PLACA_PROGRAMADA))
				.fechaProgramada(fechaProgramada)
				.clienteIdentificacion(getString(archivo, registro,CLIENTE_IDENTIFICACION))
				.numeroSolicitud(getString(archivo, registro,NUMERO_SOLICITUD))
				.estadoCodigoAlterno(getString(archivo, registro,ESTADO_CODIGO_ALTERNO))
				.causalNovedadCodigoAlterno(getString(archivo, registro,CAUSAL_NOVEDAD_CODIGO_ALTERNO))
				.fechaHoraInicio(fechaHoraInicio)
				.fechaHoraFin(fechaHoraFin)
				.observaciones(getString(archivo, registro,OBSERVACION))
				.build();
		// @formatter:on

		return result;
	}
}