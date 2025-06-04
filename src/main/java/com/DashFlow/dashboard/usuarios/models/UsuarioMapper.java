package com.DashFlow.dashboard.usuarios.models;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {
    
    public static CuentaDTO toCuentaDTO(Usuario usuario) {
        CuentaDTO dto = new CuentaDTO();
        dto.setNombre(usuario.getNombre());
        dto.setEmail(usuario.getEmail());
        
        InformacionUsuario info = usuario.getInformacion();
        if (info != null) {
            dto.setApellido(info.getApellido());
            dto.setCelular(info.getCelular());
            dto.setDireccion(info.getDireccion());
            dto.setCiudad(info.getCiudad());
            dto.setOrganizacion(info.getOrganizacion());
            dto.setBiografia(info.getBiografia());
        }
        
        return dto;
    }
    
    public static EmpleadoDTO toEmpleadoDTO(Usuario usuario) {
        EmpleadoDTO dto = new EmpleadoDTO();
        
        dto.setNombre(usuario.getNombre());
        dto.setRol(usuario.getRol().getNombreRol());
        dto.setEmail(usuario.getEmail()); // opcional
        
        if (usuario.getColaborador() != null) {
            dto.setFechaContratacion(usuario.getColaborador().getFechaContratacion());
            dto.setFechaDespido(usuario.getColaborador().getFechaDespido());
            dto.setSalario(usuario.getColaborador().getSalario());
        }
        
        return dto;
    }
    
    public static List<EmpleadoDTO> toEmpleadoDTOList(List<Usuario> colaboradores) {
        return colaboradores.stream()
                .map(UsuarioMapper::toEmpleadoDTO)
                .collect(Collectors.toList());
    }
}
