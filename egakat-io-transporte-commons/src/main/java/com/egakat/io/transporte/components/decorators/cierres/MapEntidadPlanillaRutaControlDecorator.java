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

import com.egakat.integration.commons.archivos.dto.EtlRequestDto;
import com.egakat.integration.commons.archivos.dto.RegistroDto;
import com.egakat.integration.commons.archivos.enums.EstadoRegistroType;
import com.egakat.integration.core.files.components.decorators.Decorator;
import com.egakat.integration.core.files.components.decorators.MapEntidadDecorator;
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
		val result = new PlanillaRutaControl();
		result.setIdArchivo(archivo.getArchivo().getId());
		result.setEstado(EstadoRegistroType.ESTRUCTURA_VALIDA);
		result.setNumeroLinea(registro.getNumeroLinea());
		result.setVersion(0);
		
		result.setPlacaProgramada(getString(archivo, registro,PLACA_PROGRAMADA));
		result.setFechaProgramada(fechaProgramada);
		result.setClienteIdentificacion(getString(archivo, registro,CLIENTE_IDENTIFICACION));
		result.setNumeroSolicitud(getString(archivo, registro,NUMERO_SOLICITUD));
		result.setEstadoCodigoAlterno(getString(archivo, registro,ESTADO_CODIGO_ALTERNO));
		result.setCausalNovedadCodigoAlterno(getString(archivo, registro,CAUSAL_NOVEDAD_CODIGO_ALTERNO));
		result.setFechaHoraInicio(fechaHoraInicio);
		result.setFechaHoraFin(fechaHoraFin);
		result.setObservaciones(getString(archivo, registro,OBSERVACION));
		// @formatter:on

		return result;
	}
}