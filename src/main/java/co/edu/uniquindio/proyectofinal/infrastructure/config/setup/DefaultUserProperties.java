package co.edu.uniquindio.proyectofinal.infrastructure.config.setup;

import co.edu.uniquindio.proyectofinal.infrastructure.security.entity.RolSeguridadEnum;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "default-users")
public record DefaultUserProperties(List<DefaultUser> users) {

    public record DefaultUser(String username, String password, RolSeguridadEnum role) {
    }
}
