package com.ap.apAssessement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.ap.apAssessement.model")
@EnableJpaRepositories(basePackages = "com.ap.apAssessement.repository")
public class ApAssessementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApAssessementApplication.class, args);
	}

}
