package com.DashFlow.dashboard.cuenta.service;

import com.DashFlow.dashboard.auth.models.Usuario;
import com.DashFlow.dashboard.auth.repository.UsuarioRepository;
import com.DashFlow.dashboard.cuenta.dto.PersonaDTO;
import com.DashFlow.dashboard.cuenta.model.Persona;
import com.DashFlow.dashboard.cuenta.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonaService {
    
    @Autowired
    private PersonaRepository personaRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Transactional(readOnly = true)
    public PersonaDTO obtenerPersonaUsuarioActual() {
        Usuario usuario = obtenerUsuarioAutenticado();
        Persona persona = usuario.getPersona();
        
        if (persona == null) {
            // Si no hay persona asociada, crear un DTO vacío
            return new PersonaDTO();
        }
        
        return convertirADTO(persona);
    }
    
    @Transactional
    public PersonaDTO actualizarPersona(PersonaDTO personaDTO) {
        Usuario usuario = obtenerUsuarioAutenticado();
        Persona persona = usuario.getPersona();
        
        if (persona == null) {
            persona = new Persona();
            usuario.setPersona(persona);
        }
        
        // Actualizar los datos de la persona
        persona.setApellido(personaDTO.getApellido());
        persona.setCelular(personaDTO.getCelular());
        persona.setDireccion(personaDTO.getDireccion());
        persona.setCiudad(personaDTO.getCiudad());
        persona.setBiografia(personaDTO.getBiografia());
        
        persona = personaRepository.save(persona);
        usuarioRepository.save(usuario);
        
        return convertirADTO(persona);
    }
    
    private Usuario obtenerUsuarioAutenticado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return usuarioRepository.findByEmail(email);
    }
    
    private PersonaDTO convertirADTO(Persona persona) {
        PersonaDTO dto = new PersonaDTO();
        dto.setId(persona.getId());
        dto.setApellido(persona.getApellido());
        dto.setCelular(persona.getCelular());
        dto.setDireccion(persona.getDireccion());
        dto.setCiudad(persona.getCiudad());
        dto.setBiografia(persona.getBiografia());
        return dto;
    }
}