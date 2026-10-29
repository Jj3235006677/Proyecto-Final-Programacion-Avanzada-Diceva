# Diceva · Marketplace de juegos de mesa

**Programación Avanzada · Universidad del Quindío · Entrega 2: Backend completo**

**Nicho:** Venta de juegos de mesa
**Integrantes:** Juan Pablo Buitrago

## Cómo ejecutar

Requisitos: Java 21.

    ./gradlew bootRun

- Swagger: http://localhost:8080/swagger-ui.html (botón **Authorize** para pegar el token)
- Consola H2: http://localhost:8080/h2-console
  (JDBC URL `jdbc:h2:mem:ecommercedb`, usuario `sa`, sin clave)
- Pruebas: `./gradlew test`

La base de datos es H2 en memoria y los datos de prueba se cargan al arrancar.

## Usuarios de prueba

| Correo | Contraseña | Rol |
|---|---|---|
| admin@diceva.com | c | ADMIN |
| ana@diceva.com | Ana123! | USER |
| bruno@diceva.com | Bruno123! | USER |
| carla@diceva.com | Carla123! | USER |
| diego@diceva.com | Diego123! | USER |
| elena@diceva.com | Elena123! | USER |

## Endpoints principales

| Método | Ruta | Descripción | Acceso |
|---|---|---|---|
| POST | /api/auth/registro | Registrar usuario | Público |
| POST | /api/auth/login | Iniciar sesión (devuelve JWT) | Público |
| GET | /api/juegoMesa | Listado paginado con búsqueda y filtro | Público |
| GET | /api/juegoMesa/{id} | Consultar un juego | Público |
| GET | /api/juegoMesa/ranking | Ranking de juegos más vendidos | Público |
| POST | /api/juegoMesa | Crear juego | ADMIN |
| PUT | /api/juegoMesa/{id}/eliminacion-logica | Eliminar juego (soft delete) | ADMIN |
| POST | /api/compras | Crear compra | Autenticado |
| GET | /api/compras/{id} | Consultar compra | Autenticado |
| PATCH | /api/compras/agregarJuego | Agregar juego a una compra | Autenticado |
| PATCH | /api/compras/{id}/confirmar | Confirmar compra | ADMIN |
| PATCH | /api/compras/{id}/reembolsar | Solicitar reembolso | Autenticado |
| POST | /api/Comentario | Comentar un juego | Autenticado |
| GET | /api/Comentario/{nombreJuego} | Ver comentarios de un juego | Público |

Sin token responde 401; con token pero sin el rol correcto responde 403.

## Correcciones de la Entrega 1

| Observación que nos dieron | Qué cambiamos | Dónde se ve (archivo o rama) |
|---|---|---|
| Quitar las validaciones del constructor principal | Sacamos las validaciones del constructor y las pusimos en un método aparte | `JuegoMesa.java` (método `validarDatosJuego`) |
| El estado del juego era solo activo o inactivo | Agregamos los estados PREVENTA, DISPONIBLE y AGOTADO, con transiciones permitidas | `EstadoJuego.java` |
| Precio congelado | Pendiente: no alcanzamos a corregirla en esta entrega | `Compra.java` (hoy guarda solo `precioTotal`) |
