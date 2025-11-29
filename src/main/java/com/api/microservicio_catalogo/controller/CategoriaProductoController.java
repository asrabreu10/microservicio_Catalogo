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
import com.api.microservicio_catalogo.dto.CategoriaProductoRequest;
import com.api.microservicio_catalogo.dto.CategoriaProductoResponse;
import com.api.microservicio_catalogo.service.CategoriaProductosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/categorias")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})

@Tag(name = "Categoria Productos", description = "Proporciona métodos para administrar categorias de productos") 
public class CategoriaProductoController {
    
private final CategoriaProductosService categoriaService;


    @GetMapping
    @Operation(summary = "Obtener todas las categorías de productos registradas")
    public List<CategoriaProductoResponse> obtenerTodasLasCategorias() {
        return categoriaService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una categoría por su ID")
    @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    public CategoriaProductoResponse obtenerCategoriaPorId(@PathVariable Integer id) {
        return categoriaService.findById(id);
    }
    
    @PostMapping
    @Operation(summary = "Crear un nuevo registro de categoría de producto")
    public ResponseEntity<CategoriaProductoResponse> crearCategoria(@Valid @RequestBody CategoriaProductoRequest request) {
        CategoriaProductoResponse nuevaCategoria = categoriaService.create(request);
        return ResponseEntity
                .created(URI.create("/api/v1/categorias/" + nuevaCategoria.getIdCategoria()))
                .body(nuevaCategoria);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar la información de una categoría existente")
    @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    public CategoriaProductoResponse actualizarCategoria(@PathVariable Integer id, @Valid @RequestBody CategoriaProductoRequest request) {
        return categoriaService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una categoría por su ID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarCategoria(@PathVariable Integer id) {
        categoriaService.delete(id);
    }

    @GetMapping("/nombre/{nombre}")
    @Operation(summary = "Obtener una categoría por su nombre")
    @ApiResponse(responseCode = "404", description = "Categoría no encontrada con ese nombre")
    public CategoriaProductoResponse obtenerCategoriaPorNombre(@PathVariable String nombre) {
        return categoriaService.findCategoryByName(nombre);
    }


    @GetMapping(value = "pagination", params = { "page", "pageSize" })
    @Operation(summary = "obtener todos las categorias con paginación")	
	public List<CategoriaProductoResponse> getAllPaginated(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
		List<CategoriaProductoResponse> students = categoriaService.getAll(page, pageSize);
		return students;
	}
}
