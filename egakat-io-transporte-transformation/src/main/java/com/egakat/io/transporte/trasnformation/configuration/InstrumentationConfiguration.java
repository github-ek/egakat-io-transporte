package com.egakat.io.transporte.trasnformation.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.EnableLoadTimeWeaving.AspectJWeaving;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;

@Configuration
@EnableLoadTimeWeaving(aspectjWeaving = AspectJWeaving.ENABLED)
public class InstrumentationConfiguration {

	@Bean
	public InstrumentationLoadTimeWeaver loadTimeWeaver() throws Throwable {
		InstrumentationLoadTimeWeaver loadTimeWeaver = new InstrumentationLoadTimeWeaver();
		return loadTimeWeaver;
	}
}
