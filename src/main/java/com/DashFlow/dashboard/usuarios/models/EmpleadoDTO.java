package com.DashFlow.dashboard.usuarios.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EmpleadoDTO {
    
    private String nombre;
    private String rol; // GERENTE o EMPLEADO
    
    private LocalDate fechaContratacion;
    private LocalDate fechaDespido;
    private BigDecimal salario;
    
    // (opcional) solo si lo necesitás internamente
    private String email;
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getRol() {
        return rol;
    }
    
    public void setRol(String rol) {
        this.rol = rol;
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
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
}
