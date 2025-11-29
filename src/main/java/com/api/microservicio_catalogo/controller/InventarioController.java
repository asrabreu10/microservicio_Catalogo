package com.api.microservicio_catalogo.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.api.microservicio_catalogo.dto.InventarioRequest;
import com.api.microservicio_catalogo.dto.InventarioResponse;
import com.api.microservicio_catalogo.service.InventarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/inventario")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})

@Tag(name = "Inventarios", description = "Proporciona métodos para administrar inventarios") 

public class InventarioController {
    private final InventarioService inventarioService;

    
    @GetMapping("/{idProducto}")
    @Operation(summary = "Obtener el registro de inventario para un producto específico")
    @ApiResponse(responseCode = "404", description = "Inventario/Producto no encontrado")
    public InventarioResponse obtenerInventarioPorProducto(@PathVariable Integer idProducto) {
        return inventarioService.findByProductId(idProducto);
    }

    @PutMapping("/actualizar")
    @Operation(summary = "Crear o actualizar el stock de un producto en el inventario")
    public ResponseEntity<InventarioResponse> actualizarStock(@Valid @RequestBody InventarioRequest request) {
        InventarioResponse inventarioActualizado = inventarioService.createOrUpdate(request);
        return ResponseEntity.ok(inventarioActualizado); 
    }

    @GetMapping(value = "pagination", params = { "page", "pageSize" })
    @Operation(summary = "Get all students with pagination")	
	public List<InventarioResponse> getAllPaginated(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
		List<InventarioResponse> students = inventarioService.getAll(page, pageSize);
		return students;
	}
}
