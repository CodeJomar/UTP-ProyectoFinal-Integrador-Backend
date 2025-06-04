package com.DashFlow.dashboard.usuarios.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cuenta")
public class UsuarioController {
    
    @GetMapping("")
    private String paginaCuenta() {
        return "cuenta";
    }
}
