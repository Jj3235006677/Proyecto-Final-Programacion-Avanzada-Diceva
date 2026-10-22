package com.uniquindio.ecommerce.infrastructure.rest.exeption;

import com.uniquindio.ecommerce.domain.exception.ReglaDominioException;

import jakarta.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Reglas de negocio incumplidas: 409 Conflict
    @ExceptionHandler(ReglaDominioException.class)
    public ResponseEntity<Map<String, Object>> manejarReglaDominio(
            ReglaDominioException ex) {

        return construirRespuesta(
                HttpStatus.CONFLICT,
                ex.getMessage()
        );
    }

    // Recurso inexistente: 404 Not Found
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, Object>> manejarNoEncontrado(
            NoSuchElementException ex) {

        String mensaje = ex.getMessage() != null
                ? ex.getMessage()
                : "El recurso solicitado no existe";

        return construirRespuesta(
                HttpStatus.NOT_FOUND,
                mensaje
        );
    }

    // Errores de validación de DTO: 400 Bad Request
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> manejarValidacionDTO(
            MethodArgumentNotValidException ex) {

        String mensaje = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": "
                        + error.getDefaultMessage())
                .findFirst()
                .orElse("Los datos enviados no son válidos");

        return construirRespuesta(
                HttpStatus.BAD_REQUEST,
                mensaje
        );
    }

    // Errores de validación de parámetros: 400 Bad Request
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, Object>> manejarValidacionParametros(
            ConstraintViolationException ex) {

        return construirRespuesta(
                HttpStatus.BAD_REQUEST,
                ex.getMessage()
        );
    }

    // Tipo de parámetro incorrecto: 400 Bad Request
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, Object>> manejarTipoParametro(
            MethodArgumentTypeMismatchException ex) {

        return construirRespuesta(
                HttpStatus.BAD_REQUEST,
                "El parámetro '" + ex.getName()
                        + "' tiene un formato inválido"
        );
    }

    // JSON mal formado: 400 Bad Request
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> manejarJSONInvalido(
            HttpMessageNotReadableException ex) {

        return construirRespuesta(
                HttpStatus.BAD_REQUEST,
                "El cuerpo de la solicitud está vacío o tiene un formato inválido"
        );
    }

    // Construcción uniforme de las respuestas JSON
    private ResponseEntity<Map<String, Object>> construirRespuesta(
            HttpStatus status,
            String mensaje) {

        Map<String, Object> cuerpo = Map.of(
                "timestamp", LocalDateTime.now().toString(),
                "status", status.value(),
                "error", status.getReasonPhrase(),
                "mensaje", mensaje != null
                        ? mensaje
                        : "Se produjo un error en la solicitud"
        );

        return ResponseEntity
                .status(status)
                .body(cuerpo);
    }
}