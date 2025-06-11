package com.DashFlow.dashboard.productos.services;

import com.DashFlow.dashboard.auth.models.Usuario;
import com.DashFlow.dashboard.auth.repository.UsuarioRepository;
import com.DashFlow.dashboard.productos.models.Producto;
import com.DashFlow.dashboard.productos.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    
    @Autowired
    private ProductoRepository productoRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    /**
     * Guardar producto creado por el admin logueado
     */
    public void guardarProducto(Producto producto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario admin = usuarioRepository.findByEmail(auth.getName());
        
        producto.setCreadoPor(admin);
        productoRepository.save(producto);
    }
    
    /**
     * Obtener todos los productos del admin logueado
     */
    public List<Producto> listarProductosDelAdminActual() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario admin = usuarioRepository.findByEmail(auth.getName());
        
        return productoRepository.findByCreadoPor(admin);
    }
    
    public Optional<Producto> buscarPorId(Long id) {
        return productoRepository.findById(id);
    }
    
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}
