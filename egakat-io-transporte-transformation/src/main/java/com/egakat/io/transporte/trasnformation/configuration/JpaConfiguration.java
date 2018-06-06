package com.egakat.io.transporte.trasnformation.configuration;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.egakat.io.transporte.trasnformation.Application;

import lombok.val;

@Configuration
@PropertySource("classpath:" + Application.SPRING_CONFIG_NAME_APPLICATION + ".properties")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = JpaConfiguration.PACKAGES_TO_SCAN_FOR_REPOSITORIES)
public class JpaConfiguration {

	static final String PACKAGES_TO_SCAN_FOR_REPOSITORIES = "com.egakat.etl.repository";

	static final String[] PACKAGES_TO_SCAN_FOR_ENTITIES = { "com.egakat.etl.domain",
			"com.egakat.core.data.jpa.converters" };

	protected String[] getPackagesToScanForEntities() {
		return PACKAGES_TO_SCAN_FOR_ENTITIES;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		val result = new JpaTransactionManager(entityManagerFactory);
		return result;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			DataSource dataSource) {
		val result = builder.dataSource(dataSource).packages(getPackagesToScanForEntities()).build();
		return result;
	}
}
