package com.DashFlow.dashboard.pedidos.services;

import com.DashFlow.dashboard.auth.models.Usuario;
import com.DashFlow.dashboard.auth.repository.UsuarioRepository;
import com.DashFlow.dashboard.pedidos.models.Pedido;
import com.DashFlow.dashboard.pedidos.repositories.PedidoRepository;
import com.DashFlow.dashboard.productos.models.Producto;
import com.DashFlow.dashboard.productos.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private ProductoRepository productoRepository;
    
    public void guardarPedido(Pedido pedido) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioRepository.findByEmail(auth.getName());
        pedido.setCreadoPor(usuario);
        
        Producto producto = productoRepository.findById(pedido.getProducto().getId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        
        pedido.setProducto(producto);
        pedido.setMontoTotal(producto.getPrecioVenta().multiply(BigDecimal.valueOf(pedido.getCantidad())));
        
        if (pedido.getId() != null) {
            Pedido original = pedidoRepository.findById(pedido.getId())
                    .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
            
            pedido.setFechaPedido(original.getFechaPedido());
        } else {
            pedido.setFechaPedido(LocalDate.now());
        }
        
        pedidoRepository.save(pedido);
    }
    
    
    public List<Pedido> listarPedidosDelUsuarioActual() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioRepository.findByEmail(auth.getName());
        return pedidoRepository.findByCreadoPor(usuario);
    }
    
    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }
    
    public void eliminarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }
}
