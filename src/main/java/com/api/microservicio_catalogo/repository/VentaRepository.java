package com.api.microservicio_catalogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.api.microservicio_catalogo.model.Venta;
import java.time.LocalDateTime;
import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Integer> {
    // Buscar ventas realizadas por un cajero
    @Query(value = "SELECT * FROM ventas WHERE _id_cajero = :idCajero", nativeQuery = true)
    List<Venta> findSalesByCajeroId(@Param("idCajero") Integer idCajero);

    // Buscar ventas en un rango de fechas
    @Query(value = "SELECT * FROM ventas WHERE _fecha_venta BETWEEN :fechaInicio AND :fechaFin", nativeQuery = true)
    List<Venta> findSalesByDateRange(@Param("fechaInicio") LocalDateTime fechaInicio, @Param("fechaFin") LocalDateTime fechaFin);
}
