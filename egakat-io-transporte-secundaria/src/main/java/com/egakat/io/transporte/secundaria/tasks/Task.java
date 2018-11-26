package com.egakat.io.transporte.secundaria.tasks;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.egakat.io.transporte.secundaria.service.api.cierres.PlanillaRutaControlInputService;
<<<<<<< HEAD
import com.egakat.io.transporte.secundaria.service.api.programaciones.ConsultaSolicitudesInputService;
=======
import com.egakat.io.transporte.secundaria.service.api.moviles.MovilesRutaControlInputService;
import com.egakat.io.transporte.secundaria.service.api.programaciones.ConsultaSolicitudesInputService;
import com.egakat.io.transporte.secundaria.service.api.programaciones.ProgramacionRutaTourSolverInputService;
>>>>>>> cbef25cf26735d9855674b8af1b7dbd673883b24

import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Task {
	
	@Autowired
	private PlanillaRutaControlInputService planillaRutaControlInputService;

<<<<<<< HEAD
//	@Autowired
//	private MovilesRutaControlInputService movilesRutaControlService;
//
	@Autowired
	private ConsultaSolicitudesInputService consultaSolicitudesService;
//
//	@Autowired
//	private ProgramacionRutaTourSolverInputService programacionRutaTourSolverService;
//
=======
	@Autowired
	private MovilesRutaControlInputService movilesRutaControlService;

	@Autowired
	private ConsultaSolicitudesInputService consultaSolicitudesService;

	@Autowired
	private ProgramacionRutaTourSolverInputService programacionRutaTourSolverService;

>>>>>>> cbef25cf26735d9855674b8af1b7dbd673883b24
//	@Autowired
//	private ProgramacionRutaManualInputService programacionRutaManualService;
//
//	@Autowired
//	private RemesaProgramadaRutaControlInputService remesaProgramadaRutaControlService;

	@Scheduled(cron = "${schedule.start}")
	public void run() {
		// @formatter:off
		val services = Arrays.asList(
				planillaRutaControlInputService,
<<<<<<< HEAD
				/*movilesRutaControlService,*/
				consultaSolicitudesService/*,
				programacionRutaTourSolverService,
				programacionRutaManualService,
				remesaProgramadaRutaControlService*/
=======
				movilesRutaControlService,
				consultaSolicitudesService,
				programacionRutaTourSolverService
				//,
				//programacionRutaManualService,
				//remesaProgramadaRutaControlService
>>>>>>> cbef25cf26735d9855674b8af1b7dbd673883b24
				);
		// @formatter:on

		services.parallelStream().forEach(service -> {
			val archivos = service.getArchivosPendientes();
			archivos.stream().forEach(archivoId -> {
				try {
					service.extraer(archivoId);
				} catch (RuntimeException e) {
					log.error("service.extraer(" + archivoId + ");", e);
				}
			});
		});
	}
}
