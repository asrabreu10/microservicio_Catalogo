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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.api.microservicio_catalogo.dto.CajeroRequest;
import com.api.microservicio_catalogo.dto.CajeroResponse;
import com.api.microservicio_catalogo.service.CajeroService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/cajeros")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT })

@Tag(name = "Cajero", description = "Proporciona métodos para administrar cajeros") 

public class CajeroController {

    private final CajeroService cajeroService;


        @GetMapping
    @Operation(summary = "Obtener todos los cajeros registrados")
    @ApiResponse(responseCode = "200", description = "Lista de cajeros", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CajeroResponse.class))) })
    public List<CajeroResponse> obtenerTodosLosCajeros() {
        return cajeroService.findAll();
    }
    
 
    @GetMapping("/{id}")
    @Operation(summary = "Obtener un cajero por su ID")
    @ApiResponse(responseCode = "200", description = "Cajero encontrado")
    @ApiResponse(responseCode = "404", description = "Cajero no encontrado")
    public CajeroResponse obtenerCajeroPorId(@PathVariable Integer id) {
        return cajeroService.findById(id);
    }


    @PostMapping
    @Operation(summary = "Crear un nuevo registro de cajero")
    public ResponseEntity<CajeroResponse> crearCajero(@Valid @RequestBody CajeroRequest request) {
        CajeroResponse nuevoCajero = cajeroService.create(request);
        return ResponseEntity
                .created(URI.create("/api/v1/cajeros/" + nuevoCajero.getIdCajero()))
                .body(nuevoCajero);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar la información de un cajero existente")
    @ApiResponse(responseCode = "200", description = "Cajero actualizado")
    @ApiResponse(responseCode = "404", description = "Cajero no encontrado")
    public CajeroResponse actualizarCajero(@PathVariable Integer id, @Valid @RequestBody CajeroRequest request) {
        return cajeroService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un cajero por su ID")
    @ApiResponse(responseCode = "204", description = "Cajero eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Cajero no encontrado")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarCajero(@PathVariable Integer id) {
        cajeroService.delete(id);
    }

    @Operation(summary = "Obtener cajeros por nombre")  
    @GetMapping("/search/nombre/{nombre}")
    public List<CajeroResponse> findCajeroByNombre(@PathVariable String nombre) {
        return cajeroService.findCajeroByNombre(nombre);
    }

    @Operation(summary = "Obtener cajeros por turno")
    @GetMapping("/search/turno/{turno}")
    public List<CajeroResponse> findCajerosByTurno(@PathVariable String turno) {
        return cajeroService.findCajerosByTurno(turno);
    }

    @GetMapping(value = "pagination", params = { "page", "pageSize" })
    @Operation(summary = "obtener todos los Cajeros con paginación")	
	public List<CajeroResponse> getAllPaginated(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
		List<CajeroResponse> students = cajeroService.getAll(page, pageSize);
		return students;
	}
}