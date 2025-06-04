package com.DashFlow.dashboard.usuarios.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "COLABORADORES")
public class Colaborador {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_colaborador;
    
    @Column(name = "fecha_contratacion")
    private LocalDate fechaContratacion;
    
    @Column(name = "fecha_despido")
    private LocalDate fechaDespido;
    
    private BigDecimal salario;
    
    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private Usuario usuario;
    
    public Colaborador() {
    }
    
    public Colaborador(Long id_colaborador, LocalDate fechaContratacion, LocalDate fechaDespido, BigDecimal salario, Usuario usuario) {
        this.id_colaborador = id_colaborador;
        this.fechaContratacion = fechaContratacion;
        this.fechaDespido = fechaDespido;
        this.salario = salario;
        this.usuario = usuario;
    }
    
    public Long getId_colaborador() {
        return id_colaborador;
    }
    
    public void setId_colaborador(Long id_colaborador) {
        this.id_colaborador = id_colaborador;
    }
    
    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }
    
    public void setFechaContratacion(LocalDate fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }
    
    public LocalDate getFechaDespido() {
        return fechaDespido;
    }
    
    public void setFechaDespido(LocalDate fechaDespido) {
        this.fechaDespido = fechaDespido;
    }
    
    public BigDecimal getSalario() {
        return salario;
    }
    
    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
