package com.DashFlow.dashboard.auth.services;

import com.DashFlow.dashboard.auth.models.Rol;
import com.DashFlow.dashboard.auth.models.Usuario;
import com.DashFlow.dashboard.auth.dto.UsuarioRegistroDTO;
import com.DashFlow.dashboard.auth.repository.RolRepository;
import com.DashFlow.dashboard.auth.repository.UsuarioRepository;
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
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    public Usuario guardar(UsuarioRegistroDTO registroDTO) {
        Rol rolAdmin = rolRepo.findByNombreRol("ADMIN");
        
        Usuario usuario = new Usuario(
            registroDTO.getNombre(),
            registroDTO.getEmail(),
            passwordEncoder.encode(registroDTO.getClave()),
            rolAdmin
        );
        return usuarioRepo.save(usuario);
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepo.findByEmail(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario o password inválidos");
        }
        Collection<? extends GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + usuario.getRol().getNombreRol())
        );
        return new User(usuario.getEmail(), usuario.getClave(), authorities);
    }
}
