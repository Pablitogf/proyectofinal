package co.edu.uniquindio.proyectofinal;

import co.edu.uniquindio.proyectofinal.infrastructure.config.setup.DefaultUserProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(DefaultUserProperties.class)
public class ProyectoFinalApplication {

	public static void main(String[] args) {
		// SpringApplication.run lanza el contexto de Spring,
		// escanea las anotaciones (@Service, @Repository, @RestController)
		// e inyecta las dependencias automáticamente.
		SpringApplication.run(ProyectoFinalApplication.class, args);
	}
}