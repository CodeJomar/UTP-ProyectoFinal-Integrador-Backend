package com.DashFlow.dashboard.productos.repositories;

import com.DashFlow.dashboard.auth.models.Usuario;
import com.DashFlow.dashboard.productos.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByCreadoPor(Usuario creadoPor);
}
