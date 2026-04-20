package co.edu.uniquindio.proyectofinal.domain.model.repository;

import co.edu.uniquindio.proyectofinal.domain.model.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepositorio {

    Usuario guardar(Usuario usuario);

    Optional<Usuario> buscarPorId(String id);

    List<Usuario> listar();
}