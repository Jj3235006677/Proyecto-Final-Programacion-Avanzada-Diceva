
package com.uniquindio.ecommerce.application.dto.response;

import java.util.UUID;

public record RealizarComentarioResponse(
        UUID idComentario,
        String mensaje,
        boolean eliminacionLogica
) {
}
