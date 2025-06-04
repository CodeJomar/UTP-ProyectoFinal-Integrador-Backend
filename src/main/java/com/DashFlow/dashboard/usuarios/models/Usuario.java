package com.DashFlow.dashboard.usuarios.models;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USUARIOS")
public class Usuario {
    
    /* ATRIBUTOS */
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;
    
    @Column(nullable = false)
    private String nombre;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String clave;
    
    /* RELACIONES */
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Usuario admin;
    
    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<Usuario> colaboradores = new ArrayList<>();
    
    @OneToOne(mappedBy = "USUARIOS", cascade = CascadeType.ALL, orphanRemoval = true)
    private InformacionUsuario informacion;
    
    @OneToOne(mappedBy = "USUARIOS", cascade = CascadeType.ALL, orphanRemoval = true)
    private Colaborador colaborador;
    
    public Usuario() {
    }
    
    public Usuario(Long id_usuario, String nombre, String email, String clave, Rol rol, Usuario admin, List<Usuario> colaboradores, InformacionUsuario informacion, Colaborador colaborador) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.email = email;
        this.clave = clave;
        this.rol = rol;
        this.admin = admin;
        this.colaboradores = colaboradores;
        this.informacion = informacion;
        this.colaborador = colaborador;
    }
    
    public List<Usuario> getColaboradores() {
        return colaboradores;
    }
    
    public void setColaboradores(List<Usuario> colaboradores) {
        this.colaboradores = colaboradores;
    }
    
    public Long getId_usuario() {
        return id_usuario;
    }
    
    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
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
    
    public Rol getRol() {
        return rol;
    }
    
    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
    public Usuario getAdmin() {
        return admin;
    }
    
    public void setAdmin(Usuario admin) {
        this.admin = admin;
    }
    
    public InformacionUsuario getInformacion() {
        return informacion;
    }
    
    public void setInformacion(InformacionUsuario informacion) {
        this.informacion = informacion;
    }
    
    public Colaborador getColaborador() {
        return colaborador;
    }
    
    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }
}
