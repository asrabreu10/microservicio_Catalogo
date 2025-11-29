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

import com.api.microservicio_catalogo.dto.ProveedorRequest;
import com.api.microservicio_catalogo.dto.ProveedorResponse;
import com.api.microservicio_catalogo.service.ProveedorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/proveedores")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})

@Tag(name = "Proveedores", description = "Proporciona métodos para administrar proveedores") 
public class ProveedorController {
 private final ProveedorService proveedorService;

    @GetMapping
    @Operation(summary = "Obtener todos los proveedores registrados")
    public List<ProveedorResponse> obtenerTodosLosProveedores() {
        return proveedorService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un proveedor por su ID")
    @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    public ProveedorResponse obtenerProveedorPorId(@PathVariable Integer id) {
        return proveedorService.findById(id);
    }
    
    @GetMapping("/nombre/{nombreEmpresa}")
    @Operation(summary = "Obtener un proveedor por el nombre de su empresa")
    @ApiResponse(responseCode = "404", description = "Proveedor no encontrado con ese nombre")
    public ProveedorResponse obtenerProveedorPorNombre(@PathVariable String nombreEmpresa) {
        return proveedorService.findByNombreEmpresa(nombreEmpresa);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo registro de proveedor")
    public ResponseEntity<ProveedorResponse> crearProveedor(@Valid @RequestBody ProveedorRequest request) {
        ProveedorResponse nuevoProveedor = proveedorService.create(request);
        return ResponseEntity
                .created(URI.create("/api/v1/proveedores/" + nuevoProveedor.getIdProveedor()))
                .body(nuevoProveedor);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar la información de un proveedor existente")
    @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    public ProveedorResponse actualizarProveedor(@PathVariable Integer id, @Valid @RequestBody ProveedorRequest request) {
        return proveedorService.update(id, request);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un proveedor por su ID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarProveedor(@PathVariable Integer id) {
        proveedorService.delete(id);
    }   

    @GetMapping(value = "pagination", params = { "page", "pageSize" })
    @Operation(summary = "Obtener todos los proveedores con paginación")	
	public List<ProveedorResponse> getAllPaginated(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
		List<ProveedorResponse> students = proveedorService.findAll(page, pageSize);
		return students;
	}
}
