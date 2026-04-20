package co.edu.uniquindio.proyectofinal.infrastructure.persistence.jpa.mapper;

import co.edu.uniquindio.proyectofinal.domain.model.entity.Usuario;
import co.edu.uniquindio.proyectofinal.domain.model.valueobject.Email;
import co.edu.uniquindio.proyectofinal.domain.model.valueobject.TipoUser;
import co.edu.uniquindio.proyectofinal.infrastructure.persistence.jpa.entity.UsuarioEntity;

public class UsuarioMapper {

    public static UsuarioEntity toEntity(Usuario u) {
        UsuarioEntity e = new UsuarioEntity();

        e.setId(u.getId());
        e.setNombre(u.getNombre());
        e.setEmail(u.getEmail().getValor());
        e.setRolUser(u.getRolUser().name());

        return e;
    }

    public static Usuario toDomain(UsuarioEntity e) {
        return Usuario.reconstruirDesdeDB(
                e.getId(),
                e.getNombre(),
                new Email(e.getEmail()),
                TipoUser.valueOf(e.getRolUser())
        );
    }
}