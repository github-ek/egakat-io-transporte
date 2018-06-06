package com.egakat.io.transporte.repository.cierres;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.egakat.integration.files.enums.EstadoRegistroType;
import com.egakat.integration.files.repository.RegistroRepository;
import com.egakat.io.transporte.domain.cierres.PlanillaRutaControl;

public interface PlanillaRutaControlRepository extends RegistroRepository<PlanillaRutaControl> {

	@Override
	@Query("SELECT DISTINCT a.idArchivo FROM PlanillaRutaControl a WHERE a.estado IN (:estados) ORDER BY a.idArchivo")
	List<Long> findAllArchivoIdByEstadoIn(@Param("estados") List<EstadoRegistroType> estados);
}
