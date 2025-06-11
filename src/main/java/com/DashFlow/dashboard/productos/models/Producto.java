package com.DashFlow.dashboard.productos.models;

import com.DashFlow.dashboard.auth.models.Usuario;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "productos")
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    
    private String descripcion;
    
    @Column(name = "precio_costo")
    private BigDecimal precioCosto;
    
    @Column(name = "precio_venta")
    private BigDecimal precioVenta;
    
    @Enumerated(EnumType.STRING)
    private TamanioPizza tamanio;
    
    @Enumerated(EnumType.STRING)
    private  CategoriaPizza categoria;
    
    @Enumerated(EnumType.STRING)
    private MasaPizza masa;
    
    @Enumerated(EnumType.STRING)
    private EstadoPizza estado;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "creado_por", referencedColumnName = "id", nullable = false)
    private Usuario creadoPor;
    
    public Producto() {
    }
    
    public Producto(Long id, String nombre, String descripcion, BigDecimal precioCosto, BigDecimal precioVenta, TamanioPizza tamanio, CategoriaPizza categoria, MasaPizza masa, EstadoPizza estado, Usuario creadoPor) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioCosto = precioCosto;
        this.precioVenta = precioVenta;
        this.tamanio = tamanio;
        this.categoria = categoria;
        this.masa = masa;
        this.estado = estado;
        this.creadoPor = creadoPor;
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
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public BigDecimal getPrecioCosto() {
        return precioCosto;
    }
    
    public void setPrecioCosto(BigDecimal precioCosto) {
        this.precioCosto = precioCosto;
    }
    
    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }
    
    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }
    
    public TamanioPizza getTamanio() {
        return tamanio;
    }
    
    public void setTamanio(TamanioPizza tamanio) {
        this.tamanio = tamanio;
    }
    
    public CategoriaPizza getCategoria() {
        return categoria;
    }
    
    public void setCategoria(CategoriaPizza categoria) {
        this.categoria = categoria;
    }
    
    public MasaPizza getMasa() {
        return masa;
    }
    
    public void setMasa(MasaPizza masa) {
        this.masa = masa;
    }
    
    public EstadoPizza getEstado() {
        return estado;
    }
    
    public void setEstado(EstadoPizza estado) {
        this.estado = estado;
    }
    
    public Usuario getCreadoPor() {
        return creadoPor;
    }
    
    public void setCreadoPor(Usuario creadoPor) {
        this.creadoPor = creadoPor;
    }
    
}
