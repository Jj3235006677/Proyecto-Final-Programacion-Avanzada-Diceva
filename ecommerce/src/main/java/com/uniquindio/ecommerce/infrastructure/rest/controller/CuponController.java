package com.uniquindio.ecommerce.infrastructure.rest.controller;

import com.uniquindio.ecommerce.application.UseCase.CuponUseCase.ConsultarCuponUseCase;
import com.uniquindio.ecommerce.application.dto.response.CuponResponse;
import com.uniquindio.ecommerce.domain.entity.Cupon;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cupones")
public class CuponController {

    private final ConsultarCuponUseCase consultarCuponUseCase;

    public CuponController(ConsultarCuponUseCase consultarCuponUseCase) {
        this.consultarCuponUseCase = consultarCuponUseCase;
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<CuponResponse> consultar(@PathVariable String codigo) {

        Cupon cupon = consultarCuponUseCase.ejecutar(codigo);

        return ResponseEntity.ok(new CuponResponse(
                cupon.getCodigo(),
                cupon.getPorcentaje(),
                cupon.getFechaVencimiento()
        ));
    }
}