package com.api.microservicio_catalogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.api.microservicio_catalogo.model.MetodosPago;
import java.util.Optional;

public interface MetodoPagoRepository extends JpaRepository<MetodosPago, Integer> {
    
    @Query(value = "SELECT * FROM metodo_pagos WHERE _tipo_pago = :tipoPago", nativeQuery = true)
    Optional<MetodosPago> findPaymentMethodByType(@Param("tipoPago") String tipoPago);
}