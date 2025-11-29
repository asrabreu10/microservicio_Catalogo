package com.api.microservicio_catalogo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.microservicio_catalogo.model.Direccion;
public interface DireccionRepository extends JpaRepository<Direccion, Integer> {
    @Query(value = "SELECT * FROM direcciones WHERE LOWER(_ciudad) = LOWER(:ciudad) AND LOWER(_estado) = LOWER(:estado)", nativeQuery = true)
    List<Direccion> findDireccionesByCiudadAndEstado(@Param("ciudad") String ciudad, @Param("estado") String estado);

}

