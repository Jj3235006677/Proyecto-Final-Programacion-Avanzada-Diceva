# Mapeo Dominio ->API - Compra
|Operacion del dominio |Metodo HTTP| Endpoint|
|---|---|---|
|Compra.realizar(...)|POST|/compras|



Compra.realizarCompra(UUID id,
List<UUID> idListaJuegosMesa,
UUID cedulaUsuario,
Precio precioTotal,
LocalDateTime fechaCompra)