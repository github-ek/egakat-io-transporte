package com.egakat.io.transporte.components.decorators.programaciones;

import java.time.LocalDate;
import java.time.LocalTime;

import com.egakat.integration.commons.archivos.dto.EtlRequestDto;
import com.egakat.integration.commons.archivos.dto.RegistroDto;
import com.egakat.integration.commons.archivos.enums.EstadoRegistroType;
import com.egakat.integration.core.files.components.decorators.Decorator;
import com.egakat.integration.core.files.components.decorators.MapEntidadDecorator;
import com.egakat.io.transporte.domain.programaciones.ProgramacionRutaTourSolver;

import lombok.val;

public class MapEntidadProgramacionRutaTourSolverDecorator
		extends MapEntidadDecorator<ProgramacionRutaTourSolver, Long> {

	public MapEntidadProgramacionRutaTourSolverDecorator(Decorator<ProgramacionRutaTourSolver, Long> inner) {
		super(inner);
	}

	@Override
	protected ProgramacionRutaTourSolver map(EtlRequestDto<ProgramacionRutaTourSolver, Long> archivo,
			RegistroDto<ProgramacionRutaTourSolver, Long> registro) {

		Integer secuencia = getInteger(archivo, registro, ProgramacionRutaTourSolver.SECUENCIA);
		LocalDate fechaEstimada = getLocalDate(archivo, registro, ProgramacionRutaTourSolver.FECHA_ESTIMADA);
		LocalTime horaEstimada = getLocalTimeFromBigDecimal(archivo, registro,
				ProgramacionRutaTourSolver.HORA_ESTIMADA);

		val result = new ProgramacionRutaTourSolver();

		result.setIdArchivo(archivo.getArchivo().getId());
		result.setEstado(EstadoRegistroType.ESTRUCTURA_VALIDA);
		result.setNumeroLinea(registro.getNumeroLinea());
		result.setVersion(0);

		result.setPlaca(getString(archivo, registro, ProgramacionRutaTourSolver.PLACA));
		result.setSecuencia(secuencia);
		result.setClienteCodigo(getString(archivo, registro, ProgramacionRutaTourSolver.CLIENTE_CODIGO));
		result.setIdentificadorOrden(getString(archivo, registro, ProgramacionRutaTourSolver.IDENTIFICADOR_ORDEN));
		result.setFechaEstimada(fechaEstimada);
		result.setHoraEstimada(horaEstimada);

		return result;
	}
}