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
import com.api.microservicio_catalogo.dto.ClienteRequest;
import com.api.microservicio_catalogo.dto.ClienteResponse;
import com.api.microservicio_catalogo.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/clientes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class ClienteController {
    private final ClienteService clienteService;

    @GetMapping
    @Operation(summary = "Obtener todos los clientes registrados")
    public List<ClienteResponse> obtenerTodosLosClientes() {
        return clienteService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un cliente por su ID")
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    public ClienteResponse obtenerClientePorId(@PathVariable Integer id) {
        return clienteService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo registro de cliente")
    public ResponseEntity<ClienteResponse> crearCliente(@Valid @RequestBody ClienteRequest request) {
        ClienteResponse nuevoCliente = clienteService.create(request);
        return ResponseEntity
                .created(URI.create("/api/v1/clientes/" + nuevoCliente.getIdCliente()))
                .body(nuevoCliente);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar la información de un cliente existente")
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    public ClienteResponse actualizarCliente(@PathVariable Integer id, @Valid @RequestBody ClienteRequest request) {
        return clienteService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un cliente por su ID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarCliente(@PathVariable Integer id) {
        clienteService.delete(id);
    }

       @GetMapping("/buscar")
    @Operation(summary = "Obtener clientes por nombre o apellido")
    public List<ClienteResponse> buscarPorNombreOApellido(
            @RequestParam(required = false) String nombre, 
            @RequestParam(required = false) String apellido) {
        return clienteService.findClienteByNombre(nombre, apellido);
    }

    @Operation(summary = "Obtener cajeros por ciudad")
    @GetMapping("/search/ciudad/{ciudad}")
    public List<ClienteResponse> findClienteByCiudad(@PathVariable String ciudad) {
        return clienteService.findClienteByCiudad(ciudad);
    }


    @GetMapping(value = "pagination", params = { "page", "pageSize" })
    @Operation(summary = "Get all customers with pagination")
    public List<ClienteResponse> findAll(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        if (page < 0 || pageSize < 0 || (page == 0 && pageSize == 0)) {
            throw new IllegalArgumentException(
                    "Invalid pagination parameters: page and pageSize cannot be negative and cannot both be 0.");
        }
        return clienteService.findAll(page, pageSize);
    }

 
}
