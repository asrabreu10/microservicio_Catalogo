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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.api.microservicio_catalogo.dto.ComentarioRequest;
import com.api.microservicio_catalogo.dto.ComentarioResponse;
import com.api.microservicio_catalogo.service.ComentarioService;

import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/comentarios")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})

@Tag(name = "Comentarios", description = "Proporciona métodos para administrar comentarios") 

public class ComentariosController {
     private final ComentarioService comentarioService;
     
    @GetMapping
    @Operation(summary = "Obtener todos los comentarios registrados")
    public List<ComentarioResponse> obtenerTodosLosComentarios() {
        return comentarioService.findAll();
    }


    @GetMapping("/{id}")
    @Operation(summary = "Obtener un comentario por su ID")
    @ApiResponse(responseCode = "404", description = "Comentario no encontrado")
    public ComentarioResponse obtenerComentarioPorId(@PathVariable Long id) {
        return comentarioService.findById(id);
    }
    
    @PostMapping
    @Operation(summary = "Crear un nuevo comentario")
    public ResponseEntity<ComentarioResponse> crearComentario(@Valid @RequestBody ComentarioRequest request) {
        ComentarioResponse nuevoComentario = comentarioService.create(request);
        return ResponseEntity
                .created(URI.create("/api/v1/comentarios/" + nuevoComentario.getIdComentario()))
                .body(nuevoComentario);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un comentario por su ID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarComentario(@PathVariable Long id) {
        comentarioService.delete(id);
    }

    @Operation(summary = "Buscar comentarios por autor (búsqueda parcial e insensible a mayúsculas/minúsculas)")
    @GetMapping("/search/autor/{autor}")
    public ResponseEntity<List<ComentarioResponse>> findCommentsByAuthorLike(@PathVariable String autor) {
        List<ComentarioResponse> comentarios = comentarioService.findCommentsByAutor(autor);
        return ResponseEntity.ok(comentarios);
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar comentarios que contengan una palabra clave")
    public List<ComentarioResponse> buscarComentariosPorPalabraClave(@RequestParam String keyword) {
        return comentarioService.findByKeyword(keyword);
    }

    @GetMapping(value = "pagination", params = { "page", "pageSize" })
    @Operation(summary = "obtener todos los comentarios con paginación")	
	public List<ComentarioResponse> getAllPaginated(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
		List<ComentarioResponse> students = comentarioService.getAll(page, pageSize);
		return students;
	}
}
