
package com.uniquindio.ecommerce.infrastructure.security;

import com.uniquindio.ecommerce.domain.valueobject.TipoUsuario;
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
    public void run(String... args) {

        List<Semilla> semillas = List.of(
                new Semilla("admin", "Administrador", "DICEVA",
                        "admin@diceva.com", "900000001",
                        "c", TipoUsuario.ADMIN),

                new Semilla("ana", "Ana", "García",
                        "ana@diceva.com", "900000002",
                        "Ana123!", TipoUsuario.USER),

                new Semilla("bruno", "Bruno", "López",
                        "bruno@diceva.com", "900000003",
                        "Bruno123!", TipoUsuario.USER),

                new Semilla("carla", "Carla", "Martínez",
                        "carla@diceva.com", "900000004",
                        "Carla123!", TipoUsuario.USER),

                new Semilla("diego", "Diego", "Ramírez",
                        "diego@diceva.com", "900000005",
                        "Diego123!", TipoUsuario.USER),

                new Semilla("elena", "Elena", "Torres",
                        "elena@diceva.com", "900000006",
                        "Elena123!", TipoUsuario.USER)
        );

        for (Semilla semilla : semillas) {

            boolean existe = repository
                    .findByEmail(semilla.email())
                    .isPresent();

            if (!existe) {

                UsuarioEntity usuario = new UsuarioEntity(
                        semilla.id(),
                        semilla.nombre(),
                        semilla.apellido(),
                        semilla.email(),
                        semilla.cedula(),
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
