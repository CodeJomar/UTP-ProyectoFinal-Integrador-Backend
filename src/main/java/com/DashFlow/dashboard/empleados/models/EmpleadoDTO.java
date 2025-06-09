package com.DashFlow.dashboard.empleados.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EmpleadoDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private LocalDate fechaContratacion;
    private LocalDate fechaDespido;
    private BigDecimal salario;
    private EstadoEmpleado estado;
    private Departamento departamento;
    private String puesto;
    private Long rolId;
    
    // Constructor vacío
    public EmpleadoDTO() {
    }
    
    // Constructor completo
    public EmpleadoDTO(Long id, String nombre, String apellido, String email,
                       LocalDate fechaContratacion, LocalDate fechaDespido,
                       BigDecimal salario, EstadoEmpleado estado,
                       Departamento departamento, String puesto, Long rolId) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fechaContratacion = fechaContratacion;
        this.fechaDespido = fechaDespido;
        this.salario = salario;
        this.estado = estado;
        this.departamento = departamento;
        this.puesto = puesto;
        this.rolId = rolId;
    }
    
    // Getters y Setters
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
    
    public String getApellido() {
        return apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
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
    
    public EstadoEmpleado getEstado() {
        return estado;
    }
    
    public void setEstado(EstadoEmpleado estado) {
        this.estado = estado;
    }
    
    public Departamento getDepartamento() {
        return departamento;
    }
    
    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
    
    public String getPuesto() {
        return puesto;
    }
    
    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
    
    public Long getRolId() {
        return rolId;
    }
    
    public void setRolId(Long rolId) {
        this.rolId = rolId;
    }
    
    // Método para obtener nombre completo
    public String getNombreCompleto() {
        return nombre + " " + (apellido != null ? apellido : "");
    }
}
