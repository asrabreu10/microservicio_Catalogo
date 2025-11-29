package com.api.microservicio_catalogo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.microservicio_catalogo.model.Cajero;

public interface CajeroRepository extends JpaRepository<Cajero, Integer> {
// Buscar un cajero por su nombre (unique constraint)
    @Query(value = "SELECT * FROM cajeros WHERE _nombre = :nombre", nativeQuery = true)
    List<Cajero> findCajeroByNombre(@Param("nombre") String nombre);

    // Buscar cajeros por turno (ENUM)
    @Query(value = "SELECT * FROM cajeros WHERE _turno = :turno", nativeQuery = true)
    List<Cajero> findCajerosByTurno(@Param("turno") String turno);
}



