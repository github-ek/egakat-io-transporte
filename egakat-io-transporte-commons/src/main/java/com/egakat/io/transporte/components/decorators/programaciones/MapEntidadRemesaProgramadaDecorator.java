package com.egakat.io.transporte.components.decorators.programaciones;

import java.time.LocalDate;

import com.egakat.integration.core.files.components.decorators.Decorator;
import com.egakat.integration.core.files.components.decorators.MapEntidadDecorator;
import com.egakat.integration.files.dto.EtlRequestDto;
import com.egakat.integration.files.dto.RegistroDto;
import com.egakat.integration.files.enums.EstadoRegistroType;
import com.egakat.io.transporte.domain.programaciones.RemesaProgramada;

import lombok.val;

public class MapEntidadRemesaProgramadaDecorator extends MapEntidadDecorator<RemesaProgramada, Long> {

	public MapEntidadRemesaProgramadaDecorator(Decorator<RemesaProgramada, Long> inner) {
		super(inner);
	}

	@Override
	protected RemesaProgramada map(EtlRequestDto<RemesaProgramada, Long> archivo,
			RegistroDto<RemesaProgramada,Long> registro) {

		LocalDate fechaProgramada = getLocalDate(archivo, registro, RemesaProgramada.FECHA_PROGRAMADA);
		boolean esServicioDedicado = false;
		String paqueteadoraCodigo = getString(archivo, registro,RemesaProgramada.PAQUETEADORA_CODIGO);
		
		if("DEDICADO".equals(paqueteadoraCodigo)) {
			paqueteadoraCodigo = "";
			esServicioDedicado = true;
		}
		
		// @formatter:off
		val result = RemesaProgramada.builder()
				.idArchivo(archivo.getArchivo().getId())
				.estado(EstadoRegistroType.ESTRUCTURA_VALIDA)
				.numeroLinea(registro.getNumeroLinea())
				.version(0)
				
				.placaProgramada(getString(archivo, registro,RemesaProgramada.PLACA_PROGRAMADA))
				.fechaProgramada(fechaProgramada)
				.clienteIdentificacion(getString(archivo, registro,RemesaProgramada.CLIENTE_IDENTIFICACION))
				.numeroSolicitud(getString(archivo, registro,RemesaProgramada.NUMERO_SOLICITUD))
				.servicioDedicado(esServicioDedicado)
				.paqueteadoraCodigo(paqueteadoraCodigo)
				.build();
		// @formatter:on

		return result;
	}
}