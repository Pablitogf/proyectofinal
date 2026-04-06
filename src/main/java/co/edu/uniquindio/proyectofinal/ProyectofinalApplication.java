package co.edu.uniquindio.proyectofinal;

import co.edu.uniquindio.proyectofinal.application.usecase.*;
import co.edu.uniquindio.proyectofinal.domain.entity.Solicitud;
import co.edu.uniquindio.proyectofinal.domain.entity.Usuario;
import co.edu.uniquindio.proyectofinal.domain.repository.SolicitudRepositorio;
import co.edu.uniquindio.proyectofinal.domain.valueobject.*;
import co.edu.uniquindio.proyectofinal.infrastructure.persistence.SolicitudRepositorioMemoria;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ProyectoFinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoFinalApplication.class, args);
	}

	@Bean
	public CommandLineRunner probar() {
		return args -> {

			// 1. Repositorio
			SolicitudRepositorio repo = new SolicitudRepositorioMemoria();

			// 2. Casos de uso
			CrearSolicitudUseCase crear = new CrearSolicitudUseCase(repo);
			AsignarResponsableUseCase asignar = new AsignarResponsableUseCase(repo);
			CambiarEstadoUseCase cambiar = new CambiarEstadoUseCase(repo);
			CerrarSolicitudUseCase cerrar = new CerrarSolicitudUseCase(repo);
			ConsultarSolicitudesPorEstadoUseCase consultar =
					new ConsultarSolicitudesPorEstadoUseCase(repo);

			// 3. Usuarios
			Usuario solicitante = new Usuario(
					"Santiago",
					new Email("santiago@correo.com"),
					TipoUser.ESTUDIANTE
			);

			Usuario responsable = new Usuario(
					"Carlos",
					new Email("carlos@correo.com"),
					TipoUser.DOCENTE
			);

			// 4. Crear solicitud
			Solicitud solicitud = crear.ejecutar(
					"Problema con matrícula en la universidad",
					solicitante
			);

			System.out.println("=== SOLICITUD CREADA ===");
			System.out.println("ID: " + solicitud.getId());

			
			solicitud.clasificar(Prioridad.ALTA, TipoSolicitud.SOPORTE, solicitante);
			repo.guardar(solicitud);

			// 5. Asignar responsable
			asignar.ejecutar(solicitud.getId(), responsable, solicitante);
			System.out.println("RESPONSABLE ASIGNADO");

			// 6. Iniciar atención
			cambiar.iniciarAtencion(solicitud.getId(), responsable);
			System.out.println("EN ATENCION");

			// 7. Finalizar atención
			cambiar.finalizarAtencion(solicitud.getId(), responsable);
			System.out.println("ATENDIDA");

			// 8. Cerrar solicitud
			cerrar.ejecutar(solicitud.getId(), responsable);
			System.out.println("CERRADA");

			// 9. Consultar por estado
			List<Solicitud> cerradas = consultar.ejecutar(EstadoSolicitud.CERRADA);

			System.out.println("\n=== SOLICITUDES CERRADAS ===");
			for (Solicitud s : cerradas) {
				System.out.println("Descripción: " + s.getDescripcion());
			}
		};
	}
}