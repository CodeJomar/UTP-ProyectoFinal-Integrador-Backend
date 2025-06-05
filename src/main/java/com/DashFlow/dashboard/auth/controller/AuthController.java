package com.DashFlow.dashboard.auth.controller;


import com.DashFlow.dashboard.auth.dto.UsuarioRegistroDTO;
import com.DashFlow.dashboard.auth.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;
    
    @ModelAttribute("usuario")
    public UsuarioRegistroDTO retornarNuevoUsuario() {
        return new UsuarioRegistroDTO();
    }
    
    @GetMapping("/")
    public String paginaIndex(Model model) {
        model.addAttribute("usuario", new UsuarioRegistroDTO());
        return "index";
    }
    
    @PostMapping("/registro")
    public String registrarNuevoUsuario(@ModelAttribute("usuario") UsuarioRegistroDTO registroDTO) {
        System.out.println("REGISTRO RECIBIDO");
        usuarioService.guardar(registroDTO);
        return "redirect:/?registroExitoso=true";
    }
    
    
}
