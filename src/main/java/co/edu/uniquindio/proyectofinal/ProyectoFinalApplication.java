package co.edu.uniquindio.proyectofinal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProyectoFinalApplication {

	public static void main(String[] args) {
		// SpringApplication.run lanza el contexto de Spring,
		// escanea las anotaciones (@Service, @Repository, @RestController)
		// e inyecta las dependencias automáticamente.
		SpringApplication.run(ProyectoFinalApplication.class, args);
	}
}