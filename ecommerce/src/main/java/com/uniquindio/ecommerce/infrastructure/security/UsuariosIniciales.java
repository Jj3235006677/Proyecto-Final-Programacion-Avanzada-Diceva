
package com.uniquindio.ecommerce.infrastructure.security;

import com.uniquindio.ecommerce.infrastructure.Persistence.jpa.dataRepository.UsuarioJpaDataRepository;
import com.uniquindio.ecommerce.infrastructure.Persistence.jpa.entity.RolSeguridad;
import com.uniquindio.ecommerce.infrastructure.Persistence.jpa.entity.UsuarioEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuariosIniciales implements CommandLineRunner {

    private record Semilla(
            String id,
            String email,
            String clave,
            RolSeguridad rol
    ) {}

    private final UsuarioJpaDataRepository repository;
    private final PasswordEncoder encoder;

    public UsuariosIniciales(
            UsuarioJpaDataRepository repository,
            PasswordEncoder encoder
    ) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) {
        List<Semilla> semillas = List.of(
                new Semilla(
                        "admin",
                        "admin@diceva.com",
                        "Admin123!",
                        RolSeguridad.ADMIN
                ),
                new Semilla(
                        "usuario",
                        "usuario@diceva.com",
                        "Usuario123!",
                        RolSeguridad.USER
                )
        );

        for (Semilla semilla : semillas) {
            if (repository.findByEmail(semilla.email()).isEmpty()) {
                UsuarioEntity usuario = new UsuarioEntity(
                        semilla.id(),
                        semilla.email(),
                        encoder.encode(semilla.clave()),
                        semilla.rol()
                );

                repository.save(usuario);
            }
        }
    }
}
