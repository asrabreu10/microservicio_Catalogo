package com.api.microservicio_catalogo.repository;

import com.api.microservicio_catalogo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

import java.util.Optional;
//import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    /*
     * // Buscar un usuario por su email (unique constraint)
     * 
     * @Query(value = "SELECT * FROM usuarios WHERE _email = :email", nativeQuery =
     * true)
     * Optional<Usuario> findUserByEmail(@Param("email") String email);
     * 
     * // Buscar usuarios por su rol (ENUM)
     * 
     * @Query(value = "SELECT * FROM usuarios WHERE _rol = :rol", nativeQuery =
     * true)
     * List<Usuario> findUsersByRol(@Param("rol") String rol);
     * 
     */
    public Optional<Usuario> findByEmail(String email);
}
