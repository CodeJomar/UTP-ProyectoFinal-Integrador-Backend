package com.DashFlow.dashboard.auth.dto;


public class UsuarioRegistroDTO {
    
    private Long id;
    private String nombre;
    private String email;
    private String clave;
    
    public UsuarioRegistroDTO() {
    }
    
    public UsuarioRegistroDTO(String email) {
        this.email = email;
    }
    
    public UsuarioRegistroDTO(String nombre, String email, String clave) {
        this.nombre = nombre;
        this.email = email;
        this.clave = clave;
    }
    
    public UsuarioRegistroDTO(Long id, String nombre, String email, String clave) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.clave = clave;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getClave() {
        return clave;
    }
    
    public void setClave(String clave) {
        this.clave = clave;
    }
}
