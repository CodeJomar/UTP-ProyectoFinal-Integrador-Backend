package com.DashFlow.dashboard.empleados.models;

import com.DashFlow.dashboard.auth.models.Usuario;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "empleados")
public class Empleado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private Usuario owner; // El administrador que creó este empleado
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empleado_id")
    private Usuario empleado; // El usuario asociado al empleado (si tiene acceso al sistema)
    
    @Column(name = "fecha_contratacion", nullable = false)
    private LocalDate fechaContratacion;
    
    @Column(name = "fecha_despido")
    private LocalDate fechaDespido;
    
    @Column(name = "salario", precision = 10, scale = 2)
    private BigDecimal salario;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoEmpleado estado;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "departamento", nullable = false)
    private Departamento departamento;
    
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;
    
    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;
    
    // Constructor vacío
    public Empleado() {
        this.fechaCreacion = LocalDateTime.now();
        this.estado = EstadoEmpleado.ACTIVO;
    }
    
    // Constructor con parámetros básicos
    public Empleado(Usuario owner, LocalDate fechaContratacion, EstadoEmpleado estado,
                    Departamento departamento, BigDecimal salario) {
        this();
        this.owner = owner;
        this.fechaContratacion = fechaContratacion;
        this.estado = estado;
        this.departamento = departamento;
        this.salario = salario;
    }
    
    // Método para actualizar fecha de modificación
    @PreUpdate
    public void preUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }
    
    // Getters y Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Usuario getOwner() {
        return owner;
    }
    
    public void setOwner(Usuario owner) {
        this.owner = owner;
    }
    
    public Usuario getEmpleado() {
        return empleado;
    }
    
    public void setEmpleado(Usuario empleado) {
        this.empleado = empleado;
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
    
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
    
    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }
    
    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
    
    // Método de utilidad para obtener nombre completo del empleado
    public String getNombreCompleto() {
        if (empleado != null && empleado.getPersona() != null) {
            String nombre = empleado.getNombre();
            String apellido = empleado.getPersona().getApellido();
            return nombre + (apellido != null ? " " + apellido : "");
        }
        return "Sin asignar";
    }
    
    // Método para verificar si el empleado está activo
    public boolean isActivo() {
        return estado == EstadoEmpleado.ACTIVO;
    }
}