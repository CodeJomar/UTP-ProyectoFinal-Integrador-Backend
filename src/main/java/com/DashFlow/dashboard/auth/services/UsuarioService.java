package com.DashFlow.dashboard.auth.services;

import com.DashFlow.dashboard.auth.models.Rol;
import com.DashFlow.dashboard.auth.models.Usuario;
import com.DashFlow.dashboard.auth.dto.UsuarioRegistroDTO;
import com.DashFlow.dashboard.auth.repository.RolRepository;
import com.DashFlow.dashboard.auth.repository.UsuarioRepository;
import com.DashFlow.dashboard.cuenta.model.Persona;
import com.DashFlow.dashboard.cuenta.repository.PersonaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class UsuarioService implements UserDetailsService {
    
    @Autowired
    private UsuarioRepository usuarioRepo;
    
    @Autowired
    private RolRepository rolRepo;
    
    // Cambio aquí
    @Autowired
    private PersonaRepository personaRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    
    @Transactional // Anotación agregada
    public Usuario guardar(UsuarioRegistroDTO registroDTO) {
        Rol rolAdmin = rolRepo.findByNombreRol("ADMIN");
        
        // Cambio aquí con el crear una Persona con el nombre de usuario
        Persona persona = new Persona();
        persona = personaRepository.save(persona);
        
        Usuario usuario = new Usuario(
            registroDTO.getNombre(),
            registroDTO.getEmail(),
            passwordEncoder.encode(registroDTO.getClave()),
            rolAdmin,
            persona
        );
        return usuarioRepo.save(usuario);
    }
    
    @Transactional
    public Usuario guardarDesdeEmpleado(String nombre, String email, String clave, Rol rol) {
        if (usuarioRepo.findByEmail(email) != null) {
            throw new RuntimeException("Ya existe un usuario con el correo: " + email);
        }
        
        Persona persona = new Persona();
        personaRepository.save(persona);
        
        Usuario usuario = new Usuario(nombre, email, passwordEncoder.encode(clave), rol, persona);
        return usuarioRepo.save(usuario);
    }
    
    
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepo.findByEmail(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario o password inválidos");
        }
        Collection<? extends GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + usuario.getRol().getNombreRol()) // ROLE_ADMIN
        );
        return new User(usuario.getEmail(), usuario.getClave(), authorities);
    }
}
