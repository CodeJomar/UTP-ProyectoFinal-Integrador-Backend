package com.DashFlow.dashboard.cuenta.dto;

public class PersonaDTO {
    
    private Long id;
    private String apellido;
    private String celular;
    private String direccion;
    private String ciudad;
    private String biografia;
    
    // Constructor vacío
    public PersonaDTO() {
    }
    
    // Constructor completo
    public PersonaDTO(Long id, String apellido, String celular,
                      String direccion, String ciudad, String biografia) {
        this.id = id;
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

}
