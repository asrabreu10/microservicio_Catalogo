package com.api.microservicio_catalogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.microservicio_catalogo.model.Proveedor;
import java.util.Optional;
import java.util.List;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {
// Buscar por el nombre de la empresa
    @Query(value = "SELECT * FROM proveedores WHERE LOWER(_nombre_empresa) = LOWER(:nombreEmpresa)", nativeQuery = true)
    Optional<Proveedor> findProviderByCompanyName(@Param("nombreEmpresa") String nombreEmpresa);

    // Buscar proveedores que se encuentran en un código postal específico
    @Query(value = "SELECT p.* FROM proveedores p JOIN direcciones d ON p._id_direccion = d._id_direccion WHERE d._codigo_postal = :codigoPostal", nativeQuery = true)
    List<Proveedor> findProvidersByPostalCode(@Param("codigoPostal") String codigoPostal);

}