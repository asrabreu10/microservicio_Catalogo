package com.api.microservicio_catalogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.api.microservicio_catalogo.model.DetalleVenta;
import java.util.List;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Integer> {
    // Obtener todos los detalles de una venta específica
    @Query(value = "SELECT * FROM detalle_ventas WHERE _id_venta = :idVenta", nativeQuery = true)
    List<DetalleVenta> findDetailsBySaleId(@Param("idVenta") Integer idVenta);
    
    // Obtener detalles donde se vendió un producto específico
    @Query(value = "SELECT * FROM detalle_ventas WHERE _id_producto = :idProducto", nativeQuery = true)
    List<DetalleVenta> findDetailsByProductId(@Param("idProducto") Integer idProducto);
}