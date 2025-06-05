package com.DashFlow.dashboard.auth.models;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Rol {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nombre_rol", nullable = false, unique = true)
    private String nombreRol;
    
    public Rol() {}
    
    public Rol(Long id, String nombreRol) {
        this.id = id;
        this.nombreRol = nombreRol;
    }
    
    public Rol(String nombreRol) {
        this.nombreRol = nombreRol;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNombreRol() {
        return nombreRol;
    }
    
    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }
}
