package com.DashFlow.dashboard.empleados.models;

import com.DashFlow.dashboard.auth.models.Rol;
import com.DashFlow.dashboard.auth.models.Usuario;
import com.DashFlow.dashboard.cuenta.model.Persona;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "empleados", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Empleado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String clave;
    
    @Column(name = "fecha_contratacion", nullable = false)
    private LocalDate fechaContratacion;
    
    @Column(name = "fecha_despido", nullable = false)
    private LocalDate fechaDespido;
    
    @Column(name = "salario", nullable = false)
    private BigDecimal salario;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoEmpleado estado;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "departamento", nullable = false)
    private DepartamentoEmpleado departamento;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "admin_id", nullable = false)
    private Usuario jefe; // El administrador que creó este empleado
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rol_id", referencedColumnName = "id", nullable = false)
    private Rol rol;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    private Persona persona;
    
    public Empleado() {
    }
    
    public Empleado(Long id, String nombre, String email, String clave, LocalDate fechaContratacion, LocalDate fechaDespido, BigDecimal salario, EstadoEmpleado estado, DepartamentoEmpleado departamento, Usuario jefe, Rol rol, Persona persona) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.clave = clave;
        this.fechaContratacion = fechaContratacion;
        this.fechaDespido = fechaDespido;
        this.salario = salario;
        this.estado = estado;
        this.departamento = departamento;
        this.jefe = jefe;
        this.rol = rol;
        this.persona = persona;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
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
    
    public DepartamentoEmpleado getDepartamento() {
        return departamento;
    }
    
    public void setDepartamento(DepartamentoEmpleado departamento) {
        this.departamento = departamento;
    }
    
    public Usuario getJefe() {
        return jefe;
    }
    
    public void setJefe(Usuario jefe) {
        this.jefe = jefe;
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
    
    public Persona getPersona() {
        return persona;
    }
    
    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
