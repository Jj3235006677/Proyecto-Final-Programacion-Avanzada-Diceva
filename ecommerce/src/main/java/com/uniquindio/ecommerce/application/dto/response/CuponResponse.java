package com.uniquindio.ecommerce.application.dto.response;

import java.time.LocalDate;

public record CuponResponse(
        String codigo,
        int porcentaje,
        LocalDate fechaVencimiento
) {
}