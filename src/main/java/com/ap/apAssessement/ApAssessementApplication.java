package com.ap.apAssessement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.ap.database.model")
public class ApAssessementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApAssessementApplication.class, args);
	}

}
