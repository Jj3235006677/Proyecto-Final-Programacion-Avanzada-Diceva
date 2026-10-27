
package com.uniquindio.ecommerce.application.dto.request;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record RealizarCompraRequest(
        @NotNull(message = "La cédula del usuario es obligatoria")
        UUID cedulaUsuario
) {
}
