package com.egakat.io.transporte.secundaria.tasks;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.egakat.io.transporte.secundaria.service.api.cierres.PlanillaRutaControlInputService;
import com.egakat.io.transporte.secundaria.service.api.programaciones.ProgramacionRutaManualInputService;
import com.egakat.io.transporte.secundaria.service.api.programaciones.ProgramacionRutaTourSolverInputService;
import com.egakat.io.transporte.secundaria.service.api.programaciones.RemesaProgramadaRutaControlInputService;

import lombok.val;

@Component
public class InputTask {

	// -----------------------------------------------------------------------------------------------------------------------------------------------
	// -- ENTREGAS
	// -----------------------------------------------------------------------------------------------------------------------------------------------
	@Autowired
	private PlanillaRutaControlInputService planillaRutaControlService;
	
	// -----------------------------------------------------------------------------------------------------------------------------------------------
	// -- PROGRAMACIONES
	// -----------------------------------------------------------------------------------------------------------------------------------------------
	@Autowired
	private ProgramacionRutaTourSolverInputService programacionRutaTourSolverExtractService;

	@Autowired
	private ProgramacionRutaManualInputService programacionRutaManualExtractService;

	@Autowired
	private RemesaProgramadaRutaControlInputService remesaProgramadaRutaControlExtractService;
	
	// -----------------------------------------------------------------------------------------------------------------------------------------------
	// -- RESULTADOS ENTREGAS 
	// -----------------------------------------------------------------------------------------------------------------------------------------------
	@Autowired
	private PlanillaRutaControlInputService planillaRutaControlInputService;


	@Scheduled(cron = "30 * * * * ?")
	public void run() {
		// @formatter:off
		val services = Arrays.asList(
//				planillaRutaControlExtractService,
//				programacionRutaTourSolverExtractService,
//				programacionRutaManualExtractService,
//				remesaProgramadaRutaControlExtractService,
				
				planillaRutaControlInputService
				);
		// @formatter:on

		services.stream().forEach(service -> {
			val archivos = service.getArchivosPendientes();
			archivos.stream().forEach(archivoId -> {
				try {
					service.extraer(archivoId);
				} catch (RuntimeException e) {
					e.printStackTrace();
				}
			});
		});
	}
}
