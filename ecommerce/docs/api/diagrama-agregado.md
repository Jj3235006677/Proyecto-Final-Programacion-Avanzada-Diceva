# Diagramas de Agregados DICEVA

## Agregado Compra

### Raíz del agregado

La raíz del agregado es `Compra`.

`Compra` representa una compra realizada en DICEVA y controla la información relacionada con la transacción.

### Elementos internos

El agregado contiene:

- `Precio`: Value Object utilizado para representar el precio total de la compra.
- `EstadoPago`: Enum que representa el estado del pago.

### Elemento externo

`JuegoMesa` pertenece a otro agregado y posee su propio ciclo de vida.

La entidad `Compra` no contiene directamente objetos `JuegoMesa`. En su lugar, mantiene una lista de identificadores:

`idListaJuegosMesa : List<UUID>`

Por esta razón, la relación entre `Compra` y `JuegoMesa` se realiza mediante una referencia por UUID.

### Invariantes

1. La compra debe tener toda la información obligatoria.
2. La compra debe tener al menos un juego de mesa.
3. Toda compra nueva inicia en estado `PENDIENTE`.

---

## Agregado JuegoMesa

### Raíz del agregado

La raíz del agregado es `JuegoMesa`.

`JuegoMesa` representa el juego de mesa comercializado dentro de DICEVA.

### Elementos internos

El agregado contiene:

- `Precio`: Value Object que representa el precio del juego.
- `RangoJugadores`: Value Object que representa el rango de jugadores.
- `ComplejidadJuego`: Enum que representa el nivel de complejidad del juego.

### Invariantes

1. El stock no puede ser negativo.
2. La edad recomendada no puede ser menor que cero.
3. Los campos obligatorios del juego no pueden estar vacíos.

---

## Relación entre los agregados

DICEVA contiene dos agregados principales:

- `Compra`
- `JuegoMesa`

Cada agregado posee su propia raíz y protege sus propias reglas de dominio.

La relación entre `Compra` y `JuegoMesa` se realiza mediante identificadores `UUID`, evitando que un agregado mantenga directamente la referencia al objeto raíz de otro agregado.