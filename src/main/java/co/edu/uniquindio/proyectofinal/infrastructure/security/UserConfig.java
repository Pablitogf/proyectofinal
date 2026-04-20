package co.edu.uniquindio.proyectofinal.infrastructure.security;

import co.edu.uniquindio.proyectofinal.infrastructure.security.repository.SecurityUserJpaDataRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
public class UserConfig {

    @Bean
    public UserDetailsService userDetailsServiceFromDataBase(SecurityUserJpaDataRepository repository) {
        return username -> repository.findByEmail(username)
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario inexistente en base de datos"));
    }
}
