package com.api.microservicio_catalogo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.api.microservicio_catalogo.dto.MetodoPagoRequest;
import com.api.microservicio_catalogo.dto.MetodoPagoResponse;
import com.api.microservicio_catalogo.service.MetodoPagoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/metodo_pagos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})

@Tag(name = "MetodosPago", description = "Proporciona métodos para administrar merodos de pago") 
public class MetodoPagoController {
        private final MetodoPagoService metodoPagoService;

    @GetMapping
    @Operation(summary = "Obtener todos los métodos de pago registrados")
    public List<MetodoPagoResponse> obtenerTodosLosMetodos() {
        return metodoPagoService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un método de pago por su ID")
    @ApiResponse(responseCode = "404", description = "Método de pago no encontrado")
    public MetodoPagoResponse obtenerMetodoPorId(@PathVariable Integer id) {
        return metodoPagoService.findById(id);
    }
    
    @GetMapping("/tipo/{tipo}")
    @Operation(summary = "Obtener un método de pago por su tipo (ej: Efectivo, Tarjeta)")
    @ApiResponse(responseCode = "404", description = "Método de pago no encontrado")
    public MetodoPagoResponse obtenerMetodoPorTipo(@PathVariable String tipo) {
        return metodoPagoService.findByTipo(tipo);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo método de pago")
    public ResponseEntity<MetodoPagoResponse> crearMetodo(@Valid @RequestBody MetodoPagoRequest request) {
        MetodoPagoResponse nuevoMetodo = metodoPagoService.create(request);
        return ResponseEntity
                .created(URI.create("/api/v1/metodos-pago/" + nuevoMetodo.getIdMetodoPago()))
                .body(nuevoMetodo);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar la información de un método de pago existente")
    @ApiResponse(responseCode = "404", description = "Método de pago no encontrado")
    public MetodoPagoResponse actualizarMetodo(@PathVariable Integer id, @Valid @RequestBody MetodoPagoRequest request) {
        return metodoPagoService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un método de pago por su ID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarMetodo(@PathVariable Integer id) {
        metodoPagoService.delete(id);
    }



        @GetMapping(value = "pagination", params = { "page", "pageSize" })
    @Operation(summary = "Get all payment methods with pagination")
    public List<MetodoPagoResponse> findAll(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        if (page < 0 || pageSize < 0 || (page == 0 && pageSize == 0)) {
            throw new IllegalArgumentException(
                    "Invalid pagination parameters: page and pageSize cannot be negative and cannot both be 0.");
        }
        return metodoPagoService.getAll(page, pageSize);
    }
}
