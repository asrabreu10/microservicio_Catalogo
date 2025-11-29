package com.api.microservicio_catalogo.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.api.microservicio_catalogo.dto.DetalleVentaRequest;
import com.api.microservicio_catalogo.dto.VentaRequest;
import com.api.microservicio_catalogo.dto.VentaResponse;
import com.api.microservicio_catalogo.mapper.DetalleVentaMapper;
import com.api.microservicio_catalogo.mapper.VentaMapper;
import com.api.microservicio_catalogo.model.DetalleVenta;
import com.api.microservicio_catalogo.model.Producto;
import com.api.microservicio_catalogo.model.Venta;
import com.api.microservicio_catalogo.repository.DetalleVentaRepository;
import com.api.microservicio_catalogo.repository.ProductoRepository;
import com.api.microservicio_catalogo.repository.VentaRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VentaServiceImpl implements VentaService {

    private final VentaRepository ventaRepository;
    private final DetalleVentaRepository detalleVentaRepository;
    private final ProductoRepository productoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<VentaResponse> findAll() {
        return ventaRepository.findAll().stream()
                .map(VentaMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public VentaResponse findById(Integer idVenta) {
        Venta venta = ventaRepository.findById(idVenta)
                .orElseThrow(() -> new EntityNotFoundException("Venta no encontrada: " + idVenta));
        return VentaMapper.toResponse(venta);
    }

    @Override
    @Transactional // **¡TRANSACCIÓN CRÍTICA DE NEGOCIO!**
    public VentaResponse createSale(VentaRequest req) {
        // 1. Calcular el total de la venta a partir de los detalles para validación.
        BigDecimal calculatedTotal = req.getDetalles().stream()
                .map(d -> d.getPrecioUnitario().multiply(new BigDecimal(d.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (calculatedTotal.compareTo(req.getTotal()) != 0) {
            throw new IllegalArgumentException("El total de la venta ($" + req.getTotal() +
                    ") es inconsistente con los detalles ($" + calculatedTotal + ").");
        }

        // 2. Crear y guardar el registro principal de la Venta
        Venta venta = VentaMapper.toEntity(req);
        Venta savedVenta = ventaRepository.save(venta);
        //Integer idVenta = savedVenta.getIdVenta();

        // 3. Procesar, guardar Detalles y descontar Stock
        for (DetalleVentaRequest detalleReq : req.getDetalles()) {
            Producto producto = productoRepository.findById(detalleReq.getIdProducto())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Producto no encontrado en stock: " + detalleReq.getIdProducto()));

            // Validar Stock
            if (producto.getStock() < detalleReq.getCantidad()) {
                throw new IllegalArgumentException("Stock insuficiente (" + producto.getStock() +
                        ") para el producto: " + producto.getNombreProducto() +
                        ". Se solicitan: " + detalleReq.getCantidad());
            }

            // Descontar Stock y guardar el producto
            producto.setStock(producto.getStock() - detalleReq.getCantidad());
            productoRepository.save(producto);

            // Guardar Detalle de Venta
            DetalleVenta detalle = DetalleVentaMapper.toEntity(detalleReq);
            //detalle.setIdVenta(idVenta);
            detalle.setVenta(savedVenta);
            detalleVentaRepository.save(detalle);
        }

        return VentaMapper.toResponse(savedVenta);
    }

    @Override
    @Transactional
    public void delete(Integer idVenta) {
        if (!ventaRepository.existsById(idVenta)) {
            throw new EntityNotFoundException("Venta no encontrada: " + idVenta);
        }
        // NOTA: Se asume que en un sistema real, la eliminación de una venta implica
        // reponer el stock,
        // pero por simplicidad de este ejercicio, solo se elimina.
        ventaRepository.deleteById(idVenta);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VentaResponse> findByCajero(Integer idCajero) {
        return ventaRepository.findSalesByCajeroId(idCajero).stream()
                .map(VentaMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<VentaResponse> findSalesByDateRange(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return ventaRepository.findSalesByDateRange(fechaInicio, fechaFin).stream()
                .map(VentaMapper::toResponse)
                .toList();
    }

    @Override
    public List<VentaResponse> getAll(int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<Venta> venta = ventaRepository.findAll(pageReq);
        return venta.getContent().stream()
                .map(VentaMapper::toResponse)
                .toList();
    }
}
