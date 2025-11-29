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

import com.api.microservicio_catalogo.dto.DireccionRequest;
import com.api.microservicio_catalogo.dto.DireccionResponse;
import com.api.microservicio_catalogo.service.DireccionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/direcciones")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT })

public class DireccionController {
    private final DireccionService direccionService;

    @GetMapping
    @Operation(summary = "Obtener todas las direcciones registradas")
    public List<DireccionResponse> obtenerTodasLasDirecciones() {
        return direccionService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una dirección por su ID")
    @ApiResponse(responseCode = "404", description = "Dirección no encontrada")
    public DireccionResponse obtenerDireccionPorId(@PathVariable Integer id) {
        return direccionService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo registro de dirección")
    public ResponseEntity<DireccionResponse> crearDireccion(@Valid @RequestBody DireccionRequest request) {
        DireccionResponse nuevaDireccion = direccionService.create(request);
        return ResponseEntity
                .created(URI.create("/api/v1/direcciones/" + nuevaDireccion.getIdDireccion()))
                .body(nuevaDireccion);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar la información de una dirección existente")
    @ApiResponse(responseCode = "404", description = "Dirección no encontrada")
    public DireccionResponse actualizarDireccion(@PathVariable Integer id,
            @Valid @RequestBody DireccionRequest request) {
        return direccionService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una dirección por su ID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarDireccion(@PathVariable Integer id) {
        direccionService.delete(id);
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar direcciones por ciudad y estado")
    public List<DireccionResponse> buscarPorUbicacion(@RequestParam String ciudad, @RequestParam String estado) {
        return direccionService.findByCiudadAndEstado(ciudad, estado);
    }

    @GetMapping(value = "pagination", params = { "page", "pageSize" })
    @Operation(summary = "Get all addresses with pagination")
    public List<DireccionResponse> findAll(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        if (page < 0 || pageSize < 0 || (page == 0 && pageSize == 0)) {
            throw new IllegalArgumentException(
                    "Invalid pagination parameters: page and pageSize cannot be negative and cannot both be 0.");
        }
        return direccionService.getAll(page, pageSize);
    }

}
