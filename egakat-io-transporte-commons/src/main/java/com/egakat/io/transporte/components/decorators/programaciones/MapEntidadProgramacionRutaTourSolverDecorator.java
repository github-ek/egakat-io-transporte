package com.egakat.io.transporte.components.decorators.programaciones;

import java.time.LocalDate;
import java.time.LocalTime;

import com.egakat.integration.core.files.components.decorators.Decorator;
import com.egakat.integration.core.files.components.decorators.MapEntidadDecorator;
import com.egakat.integration.files.dto.EtlRequestDto;
import com.egakat.integration.files.dto.RegistroDto;
import com.egakat.integration.files.enums.EstadoRegistroType;
import com.egakat.io.transporte.domain.programaciones.ProgramacionRutaTourSolver;

import lombok.val;

public class MapEntidadProgramacionRutaTourSolverDecorator extends MapEntidadDecorator<ProgramacionRutaTourSolver, Long> {

	public MapEntidadProgramacionRutaTourSolverDecorator(Decorator<ProgramacionRutaTourSolver, Long> inner) {
		super(inner);
	}

	@Override
	protected ProgramacionRutaTourSolver map(EtlRequestDto<ProgramacionRutaTourSolver, Long> archivo,
			RegistroDto<ProgramacionRutaTourSolver, Long> registro) {

		Integer secuencia = getInteger(archivo, registro,ProgramacionRutaTourSolver.SECUENCIA);
		LocalDate fechaEstimada = getLocalDate(archivo, registro, ProgramacionRutaTourSolver.FECHA_ESTIMADA);
		LocalTime horaEstimada = getLocalTimeFromBigDecimal(archivo, registro, ProgramacionRutaTourSolver.HORA_ESTIMADA);

		// @formatter:off
		val result = ProgramacionRutaTourSolver.builder()
				.idArchivo(archivo.getArchivo().getId())
				.estado(EstadoRegistroType.ESTRUCTURA_VALIDA)
				.numeroLinea(registro.getNumeroLinea())
				.version(0)
				
				.placa(getString(archivo, registro,ProgramacionRutaTourSolver.PLACA))
				.secuencia(secuencia)
				.clienteCodigo(getString(archivo, registro,ProgramacionRutaTourSolver.CLIENTE_CODIGO))
				.identificadorOrden(getString(archivo, registro,ProgramacionRutaTourSolver.IDENTIFICADOR_ORDEN))
				.fechaEstimada(fechaEstimada)
				.horaEstimada(horaEstimada)
				.build();
		// @formatter:on

		return result;
	}
}