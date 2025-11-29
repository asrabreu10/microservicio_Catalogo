package com.api.microservicio_catalogo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.microservicio_catalogo.model.CategoriaProducto;
import java.util.Optional;


public interface CategoriaProductoRepository extends JpaRepository<CategoriaProducto, Integer> {
    // Buscar categoría por nombre (not null)
   /* @Query(value = "SELECT _id_categoria, _nombre_categoria, _descripcion FROM categorias_productos WHERE LOWER(_nombre_categoria) = LOWER(:nombreCategoria)", nativeQuery = true)
    Optional<CategoriaProducto> findCategoryByName(@Param("nombreCategoria") String nombreCategoria);

       @Query(value = "SELECT _id_categoria, _nombre_categoria, _descripcion FROM categorias_productos WHERE LOWER(_nombre_categoria) = LOWER(:nombreCategoria)", nativeQuery = true)
    Optional<CategoriaProducto> findCategoryByName(@Param("nombreCategoria") String nombreCategoria);*/

    @Query("SELECT c FROM CategoriaProducto c WHERE LOWER(c.nombreCategoria) = LOWER(:nombreCategoria)")    
    Optional<CategoriaProducto> findCategoryByName(@Param("nombreCategoria") String nombreCategoria);
    
    

}