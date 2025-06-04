package com.DashFlow.dashboard.usuarios.services;

import com.DashFlow.dashboard.usuarios.models.Rol;
import com.DashFlow.dashboard.usuarios.models.Usuario;
import com.DashFlow.dashboard.usuarios.repositories.RolRepository;
import com.DashFlow.dashboard.usuarios.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepo;
    
    @Autowired
    private RolRepository roleRepo;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public void registrarComoAdmin(Usuario usuario) {
        Rol rolAdmin = roleRepo.findByNombreRol("ADMIN")
                .orElseThrow(() -> new RuntimeException("Rol ADMIN no existe"));
        
        usuario.setClave(passwordEncoder.encode(usuario.getClave()));
        usuario.setRol(rolAdmin);
        usuarioRepo.save(usuario);
    }
}
