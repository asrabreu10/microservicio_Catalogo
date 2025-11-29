package com.api.microservicio_catalogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.microservicio_catalogo.model.Producto;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
  // Buscar productos cuyo nombre contenga una cadena
    @Query(value = "SELECT * FROM productos WHERE LOWER(_nombre_producto) LIKE CONCAT('%', LOWER(:nombre), '%')", nativeQuery = true)
    List<Producto> findProductsByNameLike(@Param("nombre") String nombre);

    // Buscar productos con stock bajo (ej. stock < cantidadMinima)
    @Query(value = "SELECT * FROM productos WHERE _stock < :cantidadMinima", nativeQuery = true)
    List<Producto> findProductsWithLowStock(@Param("cantidadMinima") Integer cantidadMinima);

    //List<Producto> findProductsByIdCategoria(Integer idCategoria);
    
    List<Producto> findByCategoria_IdCategoria(Integer idCategoria);
}
