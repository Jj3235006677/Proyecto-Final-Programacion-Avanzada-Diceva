
package com.uniquindio.ecommerce.infrastructure.Persistence.jpa.repositoryJpa;

import com.uniquindio.ecommerce.domain.entity.Usuario;
import com.uniquindio.ecommerce.domain.repository.UsuarioRepository;
import com.uniquindio.ecommerce.infrastructure.Persistence.jpa.Mapper.UsuarioPesistenceMapper;
import com.uniquindio.ecommerce.infrastructure.Persistence.jpa.dataRepository.UsuarioJpaDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UsuarioReposritoryJpa implements UsuarioRepository {

    private final UsuarioJpaDataRepository usuarioJpaDataRepository;
    private final UsuarioPesistenceMapper usuarioPesistenceMapper;

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioJpaDataRepository.findByEmail(email)
                .map(usuarioPesistenceMapper::toDomain);
    }

    @Override
    public Optional<Usuario> buscarPorCedula(String cedula) {
        return usuarioJpaDataRepository.findByCedula(cedula)
                .map(usuarioPesistenceMapper::toDomain);
    }

    @Override
    public void guardar(Usuario usuario) {
        var usuarioEntity = usuarioPesistenceMapper.toEntity(usuario);
        usuarioJpaDataRepository.save(usuarioEntity);
    }
}
