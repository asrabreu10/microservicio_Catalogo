package com.api.microservicio_catalogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.microservicio_catalogo.model.Role;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    public Optional<Role> findByAuthority(String role);
}
