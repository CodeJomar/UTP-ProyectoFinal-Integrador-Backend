package com.DashFlow.dashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class Controlador {
    /*
    @GetMapping("")
    private String paginaIndex() {
        return "index";
    }
    */
    @GetMapping("dashboard")
    private String paginaDashboard() {
        return "dashboard";
    }
    
    @GetMapping("tiers")
    private String paginaTiers() {
        return "tiers";
    }
    
    @GetMapping("reportes")
    private String paginaReportes() {
        return "reportes";
    }
}
