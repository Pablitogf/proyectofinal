package co.edu.uniquindio.proyectofinal.infrastructure.persistence.jpa.repository;

import co.edu.uniquindio.proyectofinal.domain.model.entity.Usuario;
import co.edu.uniquindio.proyectofinal.domain.model.repository.UsuarioRepositorio;
import co.edu.uniquindio.proyectofinal.infrastructure.persistence.jpa.entity.UsuarioEntity;
import co.edu.uniquindio.proyectofinal.infrastructure.persistence.jpa.mapper.UsuarioMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepositorioJpa implements UsuarioRepositorio {

    private final UsuarioJpaDataRepository dataRepository;

    public UsuarioRepositorioJpa(UsuarioJpaDataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        UsuarioEntity entity = UsuarioMapper.toEntity(usuario);
        UsuarioEntity saved = dataRepository.save(entity);
        return UsuarioMapper.toDomain(saved);
    }

    @Override
    public Optional<Usuario> buscarPorId(String id) {
        return dataRepository.findById(id)
                .map(UsuarioMapper::toDomain);
    }

    @Override
    public List<Usuario> listar() {
        return dataRepository.findAll()
                .stream()
                .map(UsuarioMapper::toDomain)
                .toList();
    }
}
