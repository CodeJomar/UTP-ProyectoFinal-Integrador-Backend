package com.DashFlow.dashboard.empleados.repositories;

import com.DashFlow.dashboard.auth.models.Usuario;
import com.DashFlow.dashboard.empleados.models.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    List<Empleado> findByJefe(Usuario jefe);
}
