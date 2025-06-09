package com.DashFlow.dashboard.cuenta.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "personas")
public class Persona implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "apellido")
    private String apellido;
    
    @Column(name = "celular")
    private String celular;
    
    @Column(name = "direccion")
    private String direccion;
    
    @Column(name = "ciudad")
    private String ciudad;
    
    @Column(name = "biografia", columnDefinition = "TEXT")
    private String biografia;
    
    // Constructor vacío requerido por JPA
    public Persona() {
    }
    
    // Constructor completo
    public Persona(String apellido, String celular,
                   String direccion, String ciudad, String biografia) {
        this.apellido = apellido;
        this.celular = celular;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.biografia = biografia;
    }
    
    // Getters y setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getApellido() {
        return apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public String getCelular() {
        return celular;
    }
    
    public void setCelular(String celular) {
        this.celular = celular;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public String getCiudad() {
        return ciudad;
    }
    
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
    public String getBiografia() {
        return biografia;
    }
    
    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }
    
    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", apellido='" + apellido + '\'' +
                ", celular='" + celular + '\'' +
                ", direccion='" + direccion + '\'' +
                ", ciudad='" + ciudad + '\'' +
                '}';
    }
}