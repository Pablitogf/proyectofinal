package co.edu.uniquindio.proyectofinal.infrastructure.config.setup;

import co.edu.uniquindio.proyectofinal.infrastructure.security.entity.SecurityUserEntity;
import co.edu.uniquindio.proyectofinal.infrastructure.security.repository.SecurityUserJpaDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultUserInitializer implements CommandLineRunner {

    private final DefaultUserProperties props;
    private final SecurityUserJpaDataRepository repository;
    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) {
        if (repository.count() == 0) {
            props.users().forEach(defaultUser -> {
                SecurityUserEntity eu = new SecurityUserEntity();
                eu.setEmail(defaultUser.username());
                eu.setPassword(encoder.encode(defaultUser.password()));
                eu.setRol(defaultUser.role());
                repository.save(eu);
            });
            System.out.println("Security seed data cargado en H2");
        }
    }
}
