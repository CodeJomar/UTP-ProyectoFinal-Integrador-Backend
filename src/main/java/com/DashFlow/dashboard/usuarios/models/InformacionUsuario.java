package com.DashFlow.dashboard.usuarios.models;

import jakarta.persistence.*;

@Entity
@Table(name = "INFORMACION_USUARIOS")
public class InformacionUsuario {
    
    /* ATRIBUTOS */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_informacion;
    
    private String apellido;
    private String celular;
    private String direccion;
    private String ciudad;
    private String organizacion;
    
    @Column(columnDefinition = "TEXT")
    private String biografia;
    
    /* RELACIONES*/
    
    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private Usuario usuario;
    
    public InformacionUsuario() {
    }
    
    public InformacionUsuario(Long id_informacion, String apellido, String celular, String direccion, String ciudad, String organizacion, String biografia, Usuario usuario) {
        this.id_informacion = id_informacion;
        this.apellido = apellido;
        this.celular = celular;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.organizacion = organizacion;
        this.biografia = biografia;
        this.usuario = usuario;
    }
    
    public Long getId_informacion() {
        return id_informacion;
    }
    
    public void setId_informacion(Long id_informacion) {
        this.id_informacion = id_informacion;
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
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
