package com.DashFlow.dashboard.auth.models;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String clave;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rol_id", referencedColumnName = "id", nullable = false)
    private Rol rol;
    
    public Usuario() {
    }
    
    public Usuario(Long id, String nombre, String email, String clave, Rol rol) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.clave = clave;
        this.rol = rol;
    }
    
    public Usuario(String nombre, String email, String clave, Rol rol) {
        this.nombre = nombre;
        this.email = email;
        this.clave = clave;
        this.rol = rol;
    }
    
    public Long getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getClave() {
        return clave;
    }
    
    public Rol getRol() {
        return rol;
    }
}
