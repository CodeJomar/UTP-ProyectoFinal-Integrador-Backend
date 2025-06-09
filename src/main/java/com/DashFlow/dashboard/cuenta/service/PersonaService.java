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
    
    /**
     * Obtiene la información de la persona asociada al usuario autenticado
     */
    @Transactional(readOnly = true)
    public PersonaDTO obtenerPersonaUsuarioActual() {
        Usuario usuario = obtenerUsuarioAutenticado();
        Persona persona = usuario.getPersona();
        
        if (persona == null) {
            return new PersonaDTO();
        }
        
        return convertirADTO(persona);
    }
    
    /**
     * Actualiza la información de la persona asociada al usuario autenticado
     */
    @Transactional
    public PersonaDTO actualizarPersona(PersonaDTO personaDTO) {
        Usuario usuario = obtenerUsuarioAutenticado();
        Persona persona = usuario.getPersona();
        
        if (persona == null) {
            // Si no existe una persona asociada, crear una nueva
            persona = new Persona();
            usuario.setPersona(persona);
        }
        
        // Actualizar los datos de la persona
        persona.setApellido(personaDTO.getApellido());
        persona.setCelular(personaDTO.getCelular());
        persona.setDireccion(personaDTO.getDireccion());
        persona.setCiudad(personaDTO.getCiudad());
        persona.setBiografia(personaDTO.getBiografia());
        
        // Guardar la persona
        persona = personaRepository.save(persona);
        
        // Guardar la relación con el usuario
        usuarioRepository.save(usuario);
        
        return convertirADTO(persona);
    }
    
    /**
     * Obtiene el usuario autenticado actualmente
     */
    private Usuario obtenerUsuarioAutenticado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return usuarioRepository.findByEmail(email);
    }
    
    /**
     * Convierte una entidad Persona a un DTO
     */
    private PersonaDTO convertirADTO(Persona persona) {
        return new PersonaDTO(
                persona.getId(),
                persona.getApellido(),
                persona.getCelular(),
                persona.getDireccion(),
                persona.getCiudad(),
                persona.getBiografia()
        );
    }
    
    /**
     * Convierte un DTO a una entidad Persona
     */
    private Persona convertirAEntidad(PersonaDTO dto) {
        Persona persona = new Persona();
        persona.setId(dto.getId());
        persona.setApellido(dto.getApellido());
        persona.setCelular(dto.getCelular());
        persona.setDireccion(dto.getDireccion());
        persona.setCiudad(dto.getCiudad());
        persona.setBiografia(dto.getBiografia());
        return persona;
    }
}