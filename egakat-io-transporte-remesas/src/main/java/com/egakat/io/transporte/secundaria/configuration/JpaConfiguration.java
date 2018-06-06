package com.egakat.io.transporte.secundaria.configuration;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import lombok.val;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = JpaConfiguration.PACKAGES_TO_SCAN_FOR_REPOSITORIES)
public class JpaConfiguration {

	static final String PACKAGES_TO_SCAN_FOR_REPOSITORIES = "com.egakat.io.transporte.repository";

	static final String[] PACKAGES_TO_SCAN_FOR_ENTITIES = { "com.egakat.io.transporte.domain",
			"com.egakat.core.data.jpa.converters" };

	protected String[] getPackagesToScanForEntities() {
		return PACKAGES_TO_SCAN_FOR_ENTITIES;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			DataSource dataSource) {
		val result = builder.dataSource(dataSource).packages(getPackagesToScanForEntities()).build();
		return result;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		val result = new JpaTransactionManager(entityManagerFactory);
		return result;
	}
}
