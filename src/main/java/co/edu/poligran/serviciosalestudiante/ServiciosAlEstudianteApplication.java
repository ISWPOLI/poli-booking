package co.edu.poligran.serviciosalestudiante;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ServiciosAlEstudianteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiciosAlEstudianteApplication.class, args);
	}
}
