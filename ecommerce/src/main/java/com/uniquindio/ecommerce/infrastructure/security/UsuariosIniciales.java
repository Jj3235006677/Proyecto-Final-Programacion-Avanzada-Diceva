
package com.uniquindio.ecommerce.infrastructure.security;

import com.uniquindio.ecommerce.infrastructure.Persistence.jpa.dataRepository.UsuarioJpaDataRepository;
import com.uniquindio.ecommerce.infrastructure.Persistence.jpa.entity.UsuarioEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UsuariosIniciales implements CommandLineRunner {

    private record Semilla(
            String id,
            String email,
            String cedula,
            String clave,
            RolSeguridad rol
    ) {}

    private final UsuarioJpaDataRepository repository;
    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) {

        List<Semilla> semillas = List.of(
                new Semilla(
                        "admin",
                        "admin@diceva.com",
                        "900000001",
                        "c",
                        RolSeguridad.ADMIN
                ),
                new Semilla(
                        "ana",
                        "ana@diceva.com",
                        "900000002",
                        "Ana123!",
                        RolSeguridad.USER
                ),
                new Semilla(
                        "bruno",
                        "bruno@diceva.com",
                        "900000003",
                        "Bruno123!",
                        RolSeguridad.USER
                ),
                new Semilla(
                        "carla",
                        "carla@diceva.com",
                        "900000004",
                        "Carla123!",
                        RolSeguridad.USER
                ),
                new Semilla(
                        "diego",
                        "diego@diceva.com",
                        "900000005",
                        "Diego123!",
                        RolSeguridad.USER
                ),
                new Semilla(
                        "elena",
                        "elena@diceva.com",
                        "900000006",
                        "Elena123!",
                        RolSeguridad.USER
                )
        );

        for (Semilla semilla : semillas) {

            boolean existe = repository
                    .findByEmail(semilla.email())
                    .isPresent();

            if (!existe) {

                UsuarioEntity usuario = new UsuarioEntity(
                        semilla.id(),
                        semilla.email(),
                        semilla.cedula(),
                        encoder.encode(semilla.clave()),
                        semilla.rol()
                );

                repository.save(usuario);

                System.out.println(
                        "Usuario inicial creado: "
                                + semilla.email()
                                + " - Rol: "
                                + semilla.rol()
                );
            }
        }
    }
}
