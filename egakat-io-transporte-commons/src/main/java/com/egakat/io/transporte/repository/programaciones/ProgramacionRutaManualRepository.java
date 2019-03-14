package com.egakat.io.transporte.repository.programaciones;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.egakat.integration.commons.archivos.enums.EstadoRegistroType;
import com.egakat.integration.commons.archivos.repository.RegistroRepository;
import com.egakat.io.transporte.domain.programaciones.ProgramacionRutaManual;

public interface ProgramacionRutaManualRepository extends RegistroRepository<ProgramacionRutaManual> {

	@Override
	@Query("SELECT DISTINCT a.idArchivo FROM ProgramacionRutaManual a WHERE a.estado IN (:estados) ORDER BY a.idArchivo")
	List<Long> findAllArchivoIdByEstadoIn(@Param("estados") List<EstadoRegistroType> estados);
}
