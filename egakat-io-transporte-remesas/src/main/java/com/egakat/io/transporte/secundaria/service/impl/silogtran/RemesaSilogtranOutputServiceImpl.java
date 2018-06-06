package com.egakat.io.transporte.secundaria.service.impl.silogtran;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egakat.integration.core.files.service.impl.flat.ArchivoPlanoOutputServiceImpl;
import com.egakat.integration.files.repository.MensajeRepository;
import com.egakat.io.transporte.domain.silogtran.RemesaSilogtran;
import com.egakat.io.transporte.repository.silogtran.RemesaSilogtranRepository;
import com.egakat.io.transporte.secundaria.service.api.silogtran.RemesaSilogtranOutputService;

@Service
public class RemesaSilogtranOutputServiceImpl extends ArchivoPlanoOutputServiceImpl<RemesaSilogtran>
		implements RemesaSilogtranOutputService {

	private static final String TIPO_ARCHIVO_CODIGO = "REMESAS_SILOGTRAN";

	@Autowired
	private RemesaSilogtranRepository repository;

	@Override
	public String getTipoArchivoCodigo() {
		return TIPO_ARCHIVO_CODIGO;
	}

	@Override
	protected MensajeRepository<RemesaSilogtran> getRepository() {
		return repository;
	}


}
