package com.egakat.io.transporte.components.decorators.programaciones;

import java.time.LocalDate;

import com.egakat.integration.commons.archivos.dto.EtlRequestDto;
import com.egakat.integration.commons.archivos.dto.RegistroDto;
import com.egakat.integration.commons.archivos.enums.EstadoRegistroType;
import com.egakat.integration.core.files.components.decorators.Decorator;
import com.egakat.integration.core.files.components.decorators.MapEntidadDecorator;
import com.egakat.io.transporte.domain.programaciones.ProgramacionRutaManual;
import com.egakat.io.transporte.domain.programaciones.ProgramacionRutaTourSolver;

import lombok.val;

public class MapEntidadProgramacionRutaManualDecorator extends MapEntidadDecorator<ProgramacionRutaManual, Long> {

	public MapEntidadProgramacionRutaManualDecorator(Decorator<ProgramacionRutaManual, Long> inner) {
		super(inner);
	}

	@Override
	protected ProgramacionRutaManual map(EtlRequestDto<ProgramacionRutaManual, Long> archivo,
			RegistroDto<ProgramacionRutaManual, Long> registro) {

		LocalDate fecha = getLocalDate(archivo, registro, ProgramacionRutaManual.FECHA_ESTIMADA);
		Integer secuencia = getInteger(archivo, registro, ProgramacionRutaTourSolver.SECUENCIA);

		// @formatter:off
		val result = new ProgramacionRutaManual();

		result.setIdArchivo(archivo.getArchivo().getId());
		result.setEstado(EstadoRegistroType.ESTRUCTURA_VALIDA);
		result.setNumeroLinea(registro.getNumeroLinea());
		result.setVersion(0);
				
		result.setPlaca(getString(archivo, registro,ProgramacionRutaTourSolver.SECUENCIA));
		result.setSecuencia(secuencia);
		result.setClienteCodigo(getString(archivo, registro,ProgramacionRutaManual.CLIENTE_CODIGO));
		result.setNumeroSolicitud(getString(archivo, registro,ProgramacionRutaManual.NUMERO_SOLICITUD));
		result.setFechaEstimada(fecha);
		// @formatter:on

		return result;
	}
}