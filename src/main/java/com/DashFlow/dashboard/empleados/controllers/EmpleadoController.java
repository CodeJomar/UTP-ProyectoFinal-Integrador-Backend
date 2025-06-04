package com.DashFlow.dashboard.empleados.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {
    
    @GetMapping("")
    private String paginaEmpleados() {
        return "empleados";
    }
}
