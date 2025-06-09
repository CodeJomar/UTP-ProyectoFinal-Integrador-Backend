package com.DashFlow.dashboard.controller;

import com.DashFlow.dashboard.auth.dto.UsuarioInfoDTO;
import com.DashFlow.dashboard.auth.models.Usuario;
import com.DashFlow.dashboard.auth.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @ModelAttribute("usuarioInfo")
    public UsuarioInfoDTO addUsuarioInfo() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            
            if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
                String email = auth.getName();
                Usuario usuario = usuarioRepository.findByEmail(email);
                
                if (usuario != null) {
                    String rolNombre = usuario.getRol() != null ? usuario.getRol().getNombreRol() : "Usuario";
                    return new UsuarioInfoDTO(usuario.getNombre(), usuario.getEmail(), rolNombre);
                }
            }
        } catch (Exception e) {
            // Log del error si es necesario
            System.err.println("Error obteniendo información del usuario: " + e.getMessage());
        }
        
        return new UsuarioInfoDTO("Usuario", "email@ejemplo.com", "Usuario");
    }
}
