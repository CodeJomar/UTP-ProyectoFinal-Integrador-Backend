package com.DashFlow.dashboard.pedidos.repositories;

import com.DashFlow.dashboard.auth.models.Usuario;
import com.DashFlow.dashboard.pedidos.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByCreadoPor(Usuario creadoPor);
}
