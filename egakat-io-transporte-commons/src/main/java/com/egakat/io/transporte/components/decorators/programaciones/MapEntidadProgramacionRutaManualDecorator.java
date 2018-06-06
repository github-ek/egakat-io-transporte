package com.egakat.io.transporte.components.decorators.programaciones;

import java.time.LocalDate;

import com.egakat.integration.core.files.components.decorators.Decorator;
import com.egakat.integration.core.files.components.decorators.MapEntidadDecorator;
import com.egakat.integration.files.dto.EtlRequestDto;
import com.egakat.integration.files.dto.RegistroDto;
import com.egakat.integration.files.enums.EstadoRegistroType;
import com.egakat.io.transporte.domain.programaciones.ProgramacionRutaManual;
import com.egakat.io.transporte.domain.programaciones.ProgramacionRutaTourSolver;

import lombok.val;

public class MapEntidadProgramacionRutaManualDecorator extends MapEntidadDecorator<ProgramacionRutaManual, Long> {

	public MapEntidadProgramacionRutaManualDecorator(Decorator<ProgramacionRutaManual, Long> inner) {
		super(inner);
	}

	@Override
	protected ProgramacionRutaManual map(EtlRequestDto<ProgramacionRutaManual, Long> archivo, RegistroDto<ProgramacionRutaManual, Long> registro) {

		LocalDate fecha = getLocalDate(archivo, registro, ProgramacionRutaManual.FECHA_ESTIMADA);
		Integer secuencia = getInteger(archivo, registro,ProgramacionRutaTourSolver.SECUENCIA);

		// @formatter:off
		val result = ProgramacionRutaManual.builder()
				.idArchivo(archivo.getArchivo().getId())
				.estado(EstadoRegistroType.ESTRUCTURA_VALIDA)
				.numeroLinea(registro.getNumeroLinea())
				.version(0)
				
				.placa(getString(archivo, registro,ProgramacionRutaTourSolver.SECUENCIA))
				.secuencia(secuencia)
				.clienteCodigo(getString(archivo, registro,ProgramacionRutaManual.CLIENTE_CODIGO))
				.numeroSolicitud(getString(archivo, registro,ProgramacionRutaManual.NUMERO_SOLICITUD))
				.fechaEstimada(fecha)
				.build();
		// @formatter:on

		return result;
	}
}