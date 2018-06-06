package com.egakat.io.transporte.trasnformation.tasks;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.egakat.io.transporte.trasnformation.service.api.programaciones.ProgramacionRutaManualTransformationService;
import com.egakat.io.transporte.trasnformation.service.api.programaciones.ProgramacionRutaTourSolverTransformationService;
import com.egakat.io.transporte.trasnformation.service.api.programaciones.RemesaProgramadaTransformationService;

import lombok.val;

@Component
public class Task {
	@Autowired
	private RemesaProgramadaTransformationService remesaProgramadaTransformationService;

	@Autowired
	private ProgramacionRutaManualTransformationService programacionRutaManualTransformationService;

	@Autowired
	private ProgramacionRutaTourSolverTransformationService programacionRutaTourSolverTransformationService;

	@Scheduled(cron = "30 */1 * * * ?")
	public void run() {
		// @formatter:off
		val services = Arrays.asList(
				remesaProgramadaTransformationService, 
				programacionRutaTourSolverTransformationService, 
				programacionRutaManualTransformationService);
		// @formatter:on

		services.stream().forEach(service -> {
			val archivos = service.getArchivosPendientes();
			archivos.stream().forEach(archivoId -> {
				try {
					service.transformar(archivoId);
				} catch (RuntimeException e) {
					e.printStackTrace();
				}
			});
		});
	}
}
