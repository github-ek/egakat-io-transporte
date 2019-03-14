package com.egakat.io.transporte.components.decorators.programaciones;

import java.util.stream.Collectors;

import com.egakat.integration.commons.archivos.dto.EtlRequestDto;
import com.egakat.integration.core.files.components.decorators.Decorator;
import com.egakat.io.transporte.domain.programaciones.ProgramacionRutaTourSolver;

import lombok.val;

public class FiltroProgramacionRutaTourSolverDecorator extends Decorator<ProgramacionRutaTourSolver,Long> {

	public FiltroProgramacionRutaTourSolverDecorator(Decorator<ProgramacionRutaTourSolver,Long> inner) {
		super(inner);
	}

	@Override
	public EtlRequestDto<ProgramacionRutaTourSolver,Long> transformar(EtlRequestDto<ProgramacionRutaTourSolver,Long> archivoDTO) {
		val result = super.transformar(archivoDTO);

		// @formatter:off
		val registros = result
				.getRegistros()
				.stream()
				.filter(a -> !a.getDatos().get(ProgramacionRutaTourSolver.IDENTIFICADOR_ORDEN).isEmpty())
				.collect(Collectors.toList());

		result.getRegistros().clear();
		result.getRegistros().addAll(registros);
		// @formatter:on

		return result;
	}
}
