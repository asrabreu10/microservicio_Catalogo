package com.api.microservicio_catalogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.microservicio_catalogo.model.Cliente;
import java.util.List;
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
   // Buscar clientes por una parte de su nombre o apellido
    @Query(value = "SELECT * FROM clientes WHERE LOWER(_nombre) LIKE CONCAT('%', LOWER(:nombre), '%') OR LOWER(_apellido) LIKE CONCAT('%', LOWER(:apellido), '%')", nativeQuery = true)
    List<Cliente> findClientsByNombre(@Param("nombre") String nombre, @Param("apellido") String apellido);

    // Buscar clientes que viven en una ciudad específica (usando JOIN)
    @Query(value = "SELECT c.* FROM clientes c JOIN direcciones d ON c._id_direccion = d._id_direccion WHERE LOWER(d._ciudad) = LOWER(:ciudad)", nativeQuery = true)
    List<Cliente> findClientsByCiudad(@Param("ciudad") String ciudad);
}
