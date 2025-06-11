package com.DashFlow.dashboard.pedidos.models;

import com.DashFlow.dashboard.auth.models.Usuario;
import com.DashFlow.dashboard.productos.models.Producto;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
public class Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nombre_cliente", nullable = false)
    private String nombreCliente;
    
    @Column(name = "email_cliente", nullable = false)
    private String emailCliente;
    
    @Column(name = "celular_cliente", nullable = false)
    private String celularCliente;
    
    @Column(name = "fecha_pedido", nullable = false)
    private LocalDateTime fechaPedido;
    
    @Column(name = "direccion_envio", nullable = false)
    private String direccionEnvio;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;
    
    @Column(nullable = false)
    private int cantidad;
    
    @Column(name = "monto_total", nullable = false)
    private BigDecimal montoTotal;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_pedido", nullable = false)
    private EstadoPedido estadoPedido;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_pago", nullable = false)
    private EstadoPago estadoPago;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creado_por", nullable = false)
    private Usuario creadoPor;
    
    @PrePersist
    public void prePersist() {
        this.fechaPedido = LocalDateTime.now();
        this.montoTotal = this.producto.getPrecioVenta().multiply(BigDecimal.valueOf(this.cantidad));
    }
    
    public Pedido() {
    }
    
    public Pedido(Long id, String nombreCliente, String emailCliente, String celularCliente, LocalDateTime fechaPedido, String direccionEnvio, Producto producto, int cantidad, BigDecimal montoTotal, EstadoPedido estadoPedido, EstadoPago estadoPago, Usuario creadoPor) {
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.emailCliente = emailCliente;
        this.celularCliente = celularCliente;
        this.fechaPedido = fechaPedido;
        this.direccionEnvio = direccionEnvio;
        this.producto = producto;
        this.cantidad = cantidad;
        this.montoTotal = montoTotal;
        this.estadoPedido = estadoPedido;
        this.estadoPago = estadoPago;
        this.creadoPor = creadoPor;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNombreCliente() {
        return nombreCliente;
    }
    
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
    
    public String getEmailCliente() {
        return emailCliente;
    }
    
    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }
    
    public String getCelularCliente() {
        return celularCliente;
    }
    
    public void setCelularCliente(String celularCliente) {
        this.celularCliente = celularCliente;
    }
    
    public LocalDateTime getFechaPedido() {
        return fechaPedido;
    }
    
    public void setFechaPedido(LocalDateTime fechaPedido) {
        this.fechaPedido = fechaPedido;
    }
    
    public String getDireccionEnvio() {
        return direccionEnvio;
    }
    
    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }
    
    public Producto getProducto() {
        return producto;
    }
    
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    public int getCantidad() {
        return cantidad;
    }
    
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public BigDecimal getMontoTotal() {
        return montoTotal;
    }
    
    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }
    
    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }
    
    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }
    
    public EstadoPago getEstadoPago() {
        return estadoPago;
    }
    
    public void setEstadoPago(EstadoPago estadoPago) {
        this.estadoPago = estadoPago;
    }
    
    public Usuario getCreadoPor() {
        return creadoPor;
    }
    
    public void setCreadoPor(Usuario creadoPor) {
        this.creadoPor = creadoPor;
    }

}