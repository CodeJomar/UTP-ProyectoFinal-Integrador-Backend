package com.DashFlow.dashboard.empleados.services;

import com.DashFlow.dashboard.auth.models.Rol;
import com.DashFlow.dashboard.auth.models.Usuario;
import com.DashFlow.dashboard.auth.repository.RolRepository;
import com.DashFlow.dashboard.auth.repository.UsuarioRepository;
import com.DashFlow.dashboard.auth.services.UsuarioService;
import com.DashFlow.dashboard.cuenta.model.Persona;
import com.DashFlow.dashboard.cuenta.repository.PersonaRepository;
import com.DashFlow.dashboard.empleados.models.Empleado;
import com.DashFlow.dashboard.empleados.models.EstadoEmpleado;
import com.DashFlow.dashboard.empleados.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmpleadoService {
    
    @Autowired
    private EmpleadoRepository empleadoRepository;
    
    @Autowired
    private PersonaRepository personaRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    public List<Empleado> listarEmpleadosCreados() {
        return empleadoRepository.findAll();
    }
    
    public List<Empleado> listarEmpleadosDelJefeActual() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Usuario jefe = usuarioRepository.findByEmail(email);
        
        return empleadoRepository.findByJefe(jefe);
    }
    
    
    @Transactional
    public void guardarEmpleado(Empleado empleado) {
        // Si es edición, no tocar datos sensibles
        if (empleado.getId() != null) {
            Empleado original = empleadoRepository.findById(empleado.getId())
                    .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
            
            empleado.setNombre(original.getNombre());
            empleado.setEmail(original.getEmail());
            empleado.setClave(original.getClave());
            empleado.setPersona(original.getPersona());
            empleado.setRol(original.getRol());
        } else {
            // CREACIÓN nueva
            if (usuarioRepository.findByEmail(empleado.getEmail()) != null) {
                throw new RuntimeException("Ya existe un usuario con el correo: " + empleado.getEmail());
            }
            
            Rol rol = rolRepository.findById(empleado.getRol().getId())
                    .orElseThrow(() -> new RuntimeException("Rol inválido"));
            
            // Usar servicio para crear Usuario y Persona correctamente
            Usuario usuario = usuarioService.guardarDesdeEmpleado(
                    empleado.getNombre(),
                    empleado.getEmail(),
                    empleado.getClave(),
                    rol
            );
            
            empleado.setPersona(usuario.getPersona());
            empleado.setRol(rol);
        }
        
        // Asociar jefe actual
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario jefe = usuarioRepository.findByEmail(auth.getName());
        
        empleado.setJefe(jefe);
        
        empleadoRepository.save(empleado);
    }
    
    
    
    
    
    public void eliminarEmpleado(Long id) {
        empleadoRepository.deleteById(id);
    }
    
    public Empleado obtenerEmpleadoPorId(Long id) {
        return empleadoRepository.findById(id).orElse(null);
    }
    
}
