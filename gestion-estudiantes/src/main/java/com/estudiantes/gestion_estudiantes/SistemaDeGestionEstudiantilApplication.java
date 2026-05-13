package com.estudiantes.gestion_estudiantes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
public class SistemaDeGestionEstudiantilApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaDeGestionEstudiantilApplication.class, args);
	}

}
