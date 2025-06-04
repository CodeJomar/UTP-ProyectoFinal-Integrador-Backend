package com.DashFlow.dashboard.productos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    
    @GetMapping("")
    private String paginaProductos() {
        return "productos";
    }
}
