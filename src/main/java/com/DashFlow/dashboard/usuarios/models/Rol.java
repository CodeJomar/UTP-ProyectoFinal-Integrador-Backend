package com.DashFlow.dashboard.usuarios.models;


import jakarta.persistence.*;

@Entity
@Table(name = "ROLES")
public class Rol {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_rol;
    
    @Column(name = "nombre_rol", nullable = false, unique = true)
    private String nombreRol;
    
    public Rol() {
    }
    
    public Rol(Long id_rol, String nombreRol) {
        this.id_rol = id_rol;
        this.nombreRol = nombreRol;
    }
    
    public Long getId_rol() {
        return id_rol;
    }
    
    public void setId_rol(Long id_rol) {
        this.id_rol = id_rol;
    }
    
    public String getNombreRol() {
        return nombreRol;
    }
    
    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }
}
