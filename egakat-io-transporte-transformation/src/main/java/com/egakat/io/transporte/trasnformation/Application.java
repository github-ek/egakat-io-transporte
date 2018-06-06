package com.egakat.io.transporte.trasnformation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = { "com.egakat" })
@EnableScheduling
@EnableAsync
@EnableCaching
public class Application implements CommandLineRunner {

	public static final String SPRING_CONFIG_NAME_APPLICATION = "application-egakat-integration-transformation-secundaria";

	public static void main(String[] args) {
		// @formatter:off
		new SpringApplicationBuilder(Application.class)
		.properties("spring.config.name:"+SPRING_CONFIG_NAME_APPLICATION)
		.build()
		.run(args);
		// @formatter:on
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
