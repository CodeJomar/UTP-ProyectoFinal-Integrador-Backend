package com.DashFlow.dashboard.auth.models;

import com.DashFlow.dashboard.cuenta.model.Persona;
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
    
    // Inicio de los Cambios
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    private Persona persona;
    
    // Constructores existentes
    
    // Nuevo constructor con persona
    public Usuario(String nombre, String email, String clave, Rol rol, Persona persona) {
        this.nombre = nombre;
        this.email = email;
        this.clave = clave;
        this.rol = rol;
        this.persona = persona;
    }
    
    // Getters y setters existentes
    
    public Persona getPersona() {
        return persona;
    }
    
    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    
    // Fin de los cambios
    
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
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setClave(String clave) {
        this.clave = clave;
    }
    
    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
