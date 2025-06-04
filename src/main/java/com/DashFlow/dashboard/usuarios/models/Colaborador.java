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
    
}
