
package com.uniquindio.ecommerce.infrastructure.security;

import com.uniquindio.ecommerce.domain.valueobject.TipoUsuario;
import com.uniquindio.ecommerce.infrastructure.Persistence.jpa.dataRepository.UsuarioJpaDataRepository;
import com.uniquindio.ecommerce.infrastructure.Persistence.jpa.entity.UsuarioEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UsuariosIniciales implements CommandLineRunner {

    private record Semilla(
            String nombre,
            String apellido,
            String email,
            String cedula,
            String clave,
            TipoUsuario tipoUsuario
    ) {}

    private final UsuarioJpaDataRepository repository;
    private final PasswordEncoder encoder;

    @Override
    @Order(1)
    public void run(String... args) {

        List<Semilla> semillas = List.of(
                new Semilla(
                        "Administrador", "DICEVA",
                        "admin@diceva.com", "900000001",
                        "c", TipoUsuario.ADMIN
                ),
                new Semilla(
                        "Ana", "García",
                        "ana@diceva.com", "900000002",
                        "Ana123!", TipoUsuario.USER
                ),
                new Semilla(
                        "Bruno", "López",
                        "bruno@diceva.com", "900000003",
                        "Bruno123!", TipoUsuario.USER
                ),
                new Semilla(
                        "Carla", "Martínez",
                        "carla@diceva.com", "900000004",
                        "Carla123!", TipoUsuario.USER
                ),
                new Semilla(
                        "Diego", "Ramírez",
                        "diego@diceva.com", "900000005",
                        "Diego123!", TipoUsuario.USER
                ),
                new Semilla(
                        "Elena", "Torres",
                        "elena@diceva.com", "900000006",
                        "Elena123!", TipoUsuario.USER
                )
        );

        for (Semilla semilla : semillas) {

            boolean existe = repository
                    .findByEmail(semilla.email())
                    .isPresent();

            if (!existe) {

                UsuarioEntity usuario = new UsuarioEntity(
                        semilla.cedula(),
                        semilla.nombre(),
                        semilla.apellido(),
                        semilla.email(),
                        encoder.encode(semilla.clave()),
                        semilla.tipoUsuario()
                );

                repository.save(usuario);

                System.out.println(
                        "Usuario inicial creado: "
                                + semilla.email()
                                + " - Rol: "
                                + semilla.tipoUsuario()
                );
            }
        }
    }
}
