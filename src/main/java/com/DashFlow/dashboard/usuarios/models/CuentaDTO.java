package com.DashFlow.dashboard.usuarios.models;

public class CuentaDTO {
    
    private String nombre;
    private String email;
    
    // Info personal (desde InformacionUsuario)
    private String apellido;
    private String celular;
    private String direccion;
    private String ciudad;
    private String organizacion;
    private String biografia;
    
    // Campos de cambio de clave (solo para actualizar)
    private String nuevaClave;
    private String confirmarClave;
    
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
    
    public String getOrganizacion() {
        return organizacion;
    }
    
    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
    }
    
    public String getBiografia() {
        return biografia;
    }
    
    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }
    
    public String getNuevaClave() {
        return nuevaClave;
    }
    
    public void setNuevaClave(String nuevaClave) {
        this.nuevaClave = nuevaClave;
    }
    
    public String getConfirmarClave() {
        return confirmarClave;
    }
    
    public void setConfirmarClave(String confirmarClave) {
        this.confirmarClave = confirmarClave;
    }
}
