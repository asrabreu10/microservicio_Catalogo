package com.api.microservicio_catalogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.api.microservicio_catalogo.model.Inventario;
import java.util.Optional;

public interface InventarioRepository extends JpaRepository<Inventario, Integer> {
    // Encontrar el registro de inventario por el ID de producto asociado
    @Query(value = "SELECT * FROM inventarios WHERE _id_producto = :idProducto", nativeQuery = true)
    Optional<Inventario> findInventoryByProductId(@Param("idProducto") Integer idProducto);
}