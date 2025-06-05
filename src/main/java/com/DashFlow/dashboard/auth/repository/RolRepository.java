package com.DashFlow.dashboard.auth.repository;

import com.DashFlow.dashboard.auth.models.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Rol findByNombreRol(String nombreRol);
}