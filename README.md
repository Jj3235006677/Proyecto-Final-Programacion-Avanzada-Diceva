# DICEVA · Marketplace de juegos de mesa (Backend)

**Programación Avanzada · Universidad del Quindío · Entrega 3**

**Nicho:** Venta de juegos de mesa
**Integrantes:** Juan Pablo Buitrago

## Cómo ejecutar

Requisitos: Java 21.

    ./gradlew bootRun

- Swagger: http://localhost:8080/swagger-ui.html (botón **Authorize** para pegar el token)
- Consola H2: http://localhost:8080/h2-console
  (JDBC URL `jdbc:h2:mem:ecommercedb`, usuario `sa`, sin clave)
- Pruebas: `./gradlew test`

La base de datos es H2 en memoria y los datos de prueba se cargan al arrancar. El frontend en Angular está en su propio repositorio.

## Usuarios de prueba

| Correo | Contraseña | Rol |
|---|---|---|
| admin@diceva.com | Admin123! | ADMIN |
| ana@diceva.com | Ana123! | USER |
| bruno@diceva.com | Bruno123! | USER |
| carla@diceva.com | Carla123! | USER |
| diego@diceva.com | Diego123! | USER |
| elena@diceva.com | Elena123! | USER |

## Cupones de prueba

| Código | Descuento | Estado |
|---|---|---|
| DICEVA10 | 10 % | Vigente |
| JUEGANAVIDAD | 20 % | Vigente |
| VIEJO15 | 15 % | Vencido |

## Endpoints principales

| Método | Ruta | Descripción | Acceso |
|---|---|---|---|
| POST | /api/auth/registro | Registrar usuario | Público |
| POST | /api/auth/login | Iniciar sesión (devuelve JWT) | Público |
| GET | /api/juegoMesa | Listado paginado con búsqueda y filtro | Público |
| GET | /api/juegoMesa/{id} | Consultar un juego | Público |
| GET | /api/juegoMesa/ranking | Ranking de juegos más vendidos | Público |
| GET | /api/juegoMesa/aleatorio?jugadores=4 | Recomienda hasta 3 juegos al azar para ese número de jugadores | Público |
| POST | /api/juegoMesa | Crear juego | ADMIN |
| PUT | /api/juegoMesa/{id}/eliminacion-logica | Eliminar juego (soft delete) | ADMIN |
| POST | /api/compras | Crear compra (el comprador sale del token) | Autenticado |
| GET | /api/compras/{id} | Consultar compra | Autenticado |
| PATCH | /api/compras/agregarJuego | Agregar juego a una compra | Autenticado |
| PATCH | /api/compras/{id}/cupon?codigo=DICEVA10 | Aplicar un cupón a una compra propia y pendiente | Autenticado |
| PATCH | /api/compras/{id}/confirmar | Confirmar compra | ADMIN |
| PATCH | /api/compras/{id}/reembolsar | Reembolsar una compra aprobada (con motivo) | Autenticado |
| PATCH | /api/compras/{id}/cancelar | Reembolsar una compra pendiente: el dueño la suya, el ADMIN cualquiera. Devuelve el stock | Autenticado |
| GET | /api/compras/mias | Historial de compras del usuario autenticado | Autenticado |
| GET | /api/admin/compras/pendientes | Compras pendientes de todos los usuarios | ADMIN |
| GET | /api/cupones/{codigo} | Consultar si un cupón existe y está vigente | Autenticado |
| POST | /api/Comentario | Comentar un juego | Autenticado |
| GET | /api/Comentario/{nombreJuego} | Ver comentarios de un juego | Público |

Códigos de error: **401** sin token, **403** sin el rol correcto, **400** datos con formato inválido, **404** recurso inexistente y **422** cuando se rompe una regla de negocio.

## Funcionalidades propias

| Funcionalidad | Reglas de negocio |
|---|---|
| Cupones de descuento | El cupón debe existir y estar vigente; solo un cupón por compra; solo en compras pendientes, con al menos un juego y del mismo usuario. La compra guarda el código y el porcentaje, así el descuento queda congelado aunque el cupón cambie después |
| Lanza los dados | Solo recomienda juegos que sirvan para ese número de jugadores, que no estén eliminados y que tengan stock. Máximo 3, mezclados al azar, priorizando los que no se han mostrado |
| Los más vendidos | Ranking calculado en la base de datos, sin incluir juegos eliminados |

## Correcciones de la Entrega 2

| Observación que nos dieron | Qué cambiamos | Dónde se ve |
|---|---|---|
| Las reglas de negocio respondían 400 | Ahora una regla de negocio incumplida responde **422**; el 400 queda para errores de formato | `GlobalExceptionHandler.java` |
| La entidad `Pedido` no se usaba | Eliminamos `Pedido`, `EstadoPedido` y su prueba | — |

## Correcciones de la Entrega 1

| Observación que nos dieron | Qué cambiamos | Dónde se ve |
|---|---|---|
| Quitar las validaciones del constructor principal | Sacamos las validaciones del constructor y las pusimos en un método aparte | `JuegoMesa.java` (método `validarDatosJuego`) |
| El estado del juego era solo activo o inactivo | Agregamos los estados PREVENTA, DISPONIBLE y AGOTADO, con transiciones permitidas | `EstadoJuego.java` |
| Precio congelado | La compra ahora guarda el precio al que se vendió cada juego. Ejemplo: si Catan se compró a $150.000 y después sube a $180.000, la compra sigue mostrando $150.000. | `DetalleCompra.java`, `Compra.java` (`agregarJuego`), `AgregarJuegoUseCase.java` |
