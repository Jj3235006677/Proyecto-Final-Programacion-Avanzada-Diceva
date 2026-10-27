
package com.uniquindio.ecommerce.infrastructure.Persistence.jpa.Mapper;

import com.uniquindio.ecommerce.domain.entity.Usuario;
import com.uniquindio.ecommerce.infrastructure.Persistence.jpa.entity.UsuarioEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UsuarioPesistenceMapper {

    /**
     * Convierte un usuario del dominio
     * en una entidad que JPA puede guardar.
     */
    public UsuarioEntity toEntity(Usuario usuario) {

        UsuarioEntity entity = new UsuarioEntity();

        entity.setNombre(usuario.getNombre());
        entity.setApellido(usuario.getApellido());
        entity.setEmail(usuario.getEmail());
        entity.setCedula(usuario.getCedula());
        entity.setPassword(usuario.getPassword());
        entity.setTipoUsuario(usuario.getRol());

        return entity;
    }

    /**
     * Reconstruye un usuario del dominio
     * a partir de los datos recuperados de JPA.
     */
    public Usuario toDomain(UsuarioEntity entity) {

        return Usuario.crear(
                entity.getNombre(),
                entity.getApellido(),
                entity.getEmail(),
                entity.getCedula(),
                entity.getPassword(),
                entity.getTipoUsuario()
        );
    }
}
