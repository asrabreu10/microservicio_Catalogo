package com.api.microservicio_catalogo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.microservicio_catalogo.dto.DetalleVentaResponse;
import com.api.microservicio_catalogo.service.DetalleVentaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/detalle_ventas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.GET })

@Tag(name = "Detalles", description = "Proporciona métodos para administrar detalles de ventas")


public class DetalleVentaController {
    
    private final DetalleVentaService detalleVentaService;

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un detalle de venta por su ID")
    public DetalleVentaResponse obtenerDetallePorId(@PathVariable Integer id) {
        return detalleVentaService.findById(id);
    }

    @GetMapping("/venta/{idVenta}")
    @Operation(summary = "Obtener todos los detalles de una venta específica")
    public List<DetalleVentaResponse> obtenerDetallesPorVenta(@PathVariable Integer idVenta) {
        return detalleVentaService.findByVentaId(idVenta);
    }

    @GetMapping(value = "pagination", params = { "page", "pageSize" })
    @Operation(summary = "obtener todos los detalles de las ventas con paginación")
    public List<DetalleVentaResponse> getAllPaginated(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        List<DetalleVentaResponse> dtv = detalleVentaService.getAll(page, pageSize);
        return dtv;
    }

}
