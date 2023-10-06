package com.example.equipos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * Mapeamos el Main para que coja el conector de la BBDD
 * 
 * @autor Javier
 * 
 * @see com.example.controller
 * @see com.example.services
 * @see com.example.model
 * @see com.example.dao
 * 
 * @version 1.0
 */
@EntityScan(basePackages = "com.example.model")
@EnableJpaRepositories(basePackages = "com.example.dao")
@SpringBootApplication(scanBasePackages={"com.example.controller", "com.example.services"})
public class EquiposApplication {

	public static void main(String[] args) {
		SpringApplication.run(EquiposApplication.class, args);
	}

	/**
	 * Confituramos la clase RestTemplate con @Bean para poder usarla en EjemplarServiceImpl
	 */
	@Bean
	public RestTemplate template() {
		return new RestTemplate();
	}

}
