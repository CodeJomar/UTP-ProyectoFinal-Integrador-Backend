package com.DashFlow.dashboard.empleados.repositories;

import com.DashFlow.dashboard.auth.models.Usuario;
import com.DashFlow.dashboard.empleados.models.Departamento;
import com.DashFlow.dashboard.empleados.models.Empleado;
import com.DashFlow.dashboard.empleados.models.EstadoEmpleado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    
    // Buscar empleados por propietario (admin)
    List<Empleado> findByOwner(Usuario owner);
    Page<Empleado> findByOwner(Usuario owner, Pageable pageable);
    
    // Buscar por usuario empleado
    Optional<Empleado> findByEmpleado(Usuario empleado);
    
    // Buscar por estado
    List<Empleado> findByEstado(EstadoEmpleado estado);
    Page<Empleado> findByOwnerAndEstado(Usuario owner, EstadoEmpleado estado, Pageable pageable);
    
    // Buscar por departamento
    List<Empleado> findByDepartamento(Departamento departamento);
    Page<Empleado> findByOwnerAndDepartamento(Usuario owner, Departamento departamento, Pageable pageable);
    
    // Buscar por estado y departamento
    Page<Empleado> findByOwnerAndEstadoAndDepartamento(Usuario owner, EstadoEmpleado estado,
                                                       Departamento departamento, Pageable pageable);
    
    // Buscar por término (nombre o apellido del usuario asociado)
    @Query("SELECT e FROM Empleado e JOIN e.empleado u JOIN u.persona p " +
            "WHERE e.owner = :owner AND " +
            "(LOWER(u.nombre) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
            "LOWER(p.apellido) LIKE LOWER(CONCAT('%', :termino, '%')))")
    Page<Empleado> buscarPorTermino(@Param("owner") Usuario owner,
                                    @Param("termino") String termino,
                                    Pageable pageable);
    
    // Contar empleados por departamento
    @Query("SELECT e.departamento, COUNT(e) FROM Empleado e WHERE e.owner = :owner GROUP BY e.departamento")
    List<Object[]> contarPorDepartamento(@Param("owner") Usuario owner);
    
    // Contar empleados por estado
    @Query("SELECT e.estado, COUNT(e) FROM Empleado e WHERE e.owner = :owner GROUP BY e.estado")
    List<Object[]> contarPorEstado(@Param("owner") Usuario owner);
    
    // Buscar empleados con contratos por vencer en los próximos días
    @Query("SELECT e FROM Empleado e WHERE e.owner = :owner AND " +
            "e.fechaDespido BETWEEN :fechaInicio AND :fechaFin")
    List<Empleado> buscarContratosPorVencer(@Param("owner") Usuario owner,
                                            @Param("fechaInicio") LocalDate fechaInicio,
                                            @Param("fechaFin") LocalDate fechaFin);
    
    // Verificar si existe un empleado con el mismo correo
    boolean existsByEmpleadoEmail(String email);
}