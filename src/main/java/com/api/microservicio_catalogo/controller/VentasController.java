package com.api.microservicio_catalogo.controller;

import java.net.URI;
import java.time.LocalDateTime;
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
import com.api.microservicio_catalogo.dto.VentaRequest;
import com.api.microservicio_catalogo.dto.VentaResponse;
import com.api.microservicio_catalogo.service.VentaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/ventas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE })

@Tag(name = "Ventas", description = "Proporciona métodos para administrar ventas") 
public class VentasController {
    

    private final VentaService ventaService;



    @GetMapping
    @Operation(summary = "Obtener todas las ventas registradas")
    public List<VentaResponse> obtenerTodasLasVentas() {
        return ventaService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una venta por su ID")
    @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    public VentaResponse obtenerVentaPorId(@PathVariable Integer id) {
        return ventaService.findById(id);
    }

    @GetMapping("/cajero/{idCajero}")
    @Operation(summary = "Obtener todas las ventas realizadas por un cajero específico")
    public List<VentaResponse> obtenerVentasPorCajero(@PathVariable Integer idCajero) {
        return ventaService.findByCajero(idCajero);
    }

    @GetMapping("/rango-fecha")
    @Operation(summary = "Obtener ventas dentro de un rango de fecha y hora")
    public List<VentaResponse> obtenerVentasPorRangoDeFecha(
            @RequestParam LocalDateTime inicio,
            @RequestParam LocalDateTime fin) {
        return ventaService.findSalesByDateRange(inicio, fin);
    }

    @PostMapping
    @Operation(summary = "Registrar una nueva venta completa (incluye detalles y actualización de stock)")
    public ResponseEntity<VentaResponse> crearVenta(@Valid @RequestBody VentaRequest request) {
        VentaResponse nuevaVenta = ventaService.createSale(request);
        return ResponseEntity
                .created(URI.create("/api/v1/ventas/" + nuevaVenta.getIdVenta()))
                .body(nuevaVenta);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una venta por su ID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarVenta(@PathVariable Integer id) {
        ventaService.delete(id);
    }

    @GetMapping(value = "pagination", params = { "page", "pageSize" })
    @Operation(summary = "Obtener todos los ventas con paginación")
    public List<VentaResponse> getAllPaginated(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        List<VentaResponse> students = ventaService.getAll(page, pageSize);
        return students;
    }
}
