package com.uniquindio.ecommerce.domain.valueobject;

import java.util.List;

public record ResultadoPaginado<T>(
        List<T> contenido,
        long totalElementos,
        int totalPaginas,
        int paginaActual,
        int tamanioPagina
) {
    public ResultadoPaginado {
        contenido = List.copyOf(contenido);
    }
}