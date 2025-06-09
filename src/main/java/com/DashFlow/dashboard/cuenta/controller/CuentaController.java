package com.DashFlow.dashboard.cuenta.controller;

import com.DashFlow.dashboard.cuenta.dto.PersonaDTO;
import com.DashFlow.dashboard.cuenta.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cuenta")
public class CuentaController {
    
    @Autowired
    private PersonaService personaService;
    
    @GetMapping
    public String mostrarPaginaCuenta(Model model, Authentication authentication) {
        PersonaDTO personaDTO = personaService.obtenerPersonaUsuarioActual();
        model.addAttribute("persona", personaDTO);
        
        if (authentication != null) {
            model.addAttribute("nombreUsuario", authentication.getName());
        }
        return "cuenta";
    }
    
    @PostMapping("/actualizar")
    public String actualizarInformacionPersonal(@ModelAttribute("persona") PersonaDTO personaDTO, RedirectAttributes redirectAttributes) {
        try {
            personaService.actualizarPersona(personaDTO);
            redirectAttributes.addFlashAttribute("mensaje", "Información personal actualizada con éxito");
            redirectAttributes.addFlashAttribute("tipo", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al actualizar la información: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipo", "error");
        }
        return "redirect:/cuenta";
    }
    
}
