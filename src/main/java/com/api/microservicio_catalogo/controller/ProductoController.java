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
import com.api.microservicio_catalogo.dto.ProductoRequest;
import com.api.microservicio_catalogo.dto.ProductoResponse;
import com.api.microservicio_catalogo.service.ProductoService;

import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/productos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT })

@Tag(name = "Productos", description = "Proporciona métodos para administrar productos") 
public class ProductoController {
    private final ProductoService productoService;


    @GetMapping
    @Operation(summary = "Obtener todos los productos registrados")
    public List<ProductoResponse> obtenerTodosLosProductos() {
        return productoService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un producto por su ID")
    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    public ProductoResponse obtenerProductoPorId(@PathVariable Integer id) {
        return productoService.findById(id);
    }

    @GetMapping("/categoria/{idCategoria}")
    @Operation(summary = "Obtener productos filtrados por ID de categoría")
    public List<ProductoResponse> obtenerProductosPorCategoria(@PathVariable Integer idCategoria) {
        return productoService.findByCategoria(idCategoria);
    }

    @PostMapping
    @Operation(summary = "Crear un producto")
    public ResponseEntity<ProductoResponse> crearProducto(@Valid @RequestBody ProductoRequest request) {
        ProductoResponse nuevoProducto = productoService.create(request);
        return ResponseEntity
                .created(URI.create("/api/v1/productos/" + nuevoProducto.getIdProducto()))
                .body(nuevoProducto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar la información de un producto existente")
    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    public ProductoResponse actualizarProducto(@PathVariable Integer id, @Valid @RequestBody ProductoRequest request) {
        return productoService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un producto por su ID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarProducto(@PathVariable Integer id) {
        productoService.delete(id);
    }

    @GetMapping("/stock-bajo")
    @Operation(summary = "Obtener productos con stock menor o igual a la cantidad mínima especificada")
    public List<ProductoResponse> obtenerProductosConStockBajo(
            @RequestParam(defaultValue = "10") Integer cantidadMinima) {
        return productoService.findProductsWithLowStock(cantidadMinima);
    }

    @GetMapping(value = "pagination", params = { "page", "pageSize" })
    @Operation(summary = "Obtener todos los productos con paginación")
    public List<ProductoResponse> getAllPaginated(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        List<ProductoResponse> students = productoService.findAll(page, pageSize);
        return students;
    }

    
}
