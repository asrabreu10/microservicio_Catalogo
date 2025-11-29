package com.api.microservicio_catalogo.repository;

import com.api.microservicio_catalogo.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login, Integer> {
// Buscar credenciales por el username (unique constraint)
    @Query(value = "SELECT * FROM login WHERE _username = :username", nativeQuery = true)
    Optional<Login> findLoginByUsername(@Param("username") String username);

    // Buscar credenciales por el ID de usuario asociado (1:1 relación conceptual)
    @Query(value = "SELECT * FROM login WHERE _id_usuario = :idUsuario", nativeQuery = true)
    Optional<Login> findLoginByIdUsuario(@Param("idUsuario") Integer idUsuario);
}
