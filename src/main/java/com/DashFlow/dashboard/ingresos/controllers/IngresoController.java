package com.DashFlow.dashboard.ingresos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ingresos")
public class IngresoController {
    
    @GetMapping("")
    private String paginaIngresos() {
        return "ingresos";
    }
}
