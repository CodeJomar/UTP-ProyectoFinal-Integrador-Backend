package com.DashFlow.dashboard.empleados.controllers;

import com.DashFlow.dashboard.auth.repository.RolRepository;
import com.DashFlow.dashboard.empleados.models.DepartamentoEmpleado;
import com.DashFlow.dashboard.empleados.models.Empleado;
import com.DashFlow.dashboard.empleados.models.EstadoEmpleado;
import com.DashFlow.dashboard.empleados.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("empleados")
public class EmpleadoController {
    
    @Autowired
    private EmpleadoService empleadoService;
    
    @Autowired
    private RolRepository rolRepository;
    
    @GetMapping("")
    public String mostrarPaginaEmpleados(Model model) {
        model.addAttribute("empleado", new Empleado());
        model.addAttribute("empleados", empleadoService.listarEmpleadosDelJefeActual());
        
        model.addAttribute("estados", EstadoEmpleado.values());
        model.addAttribute("departamentos", DepartamentoEmpleado.values());
        model.addAttribute("roles", rolRepository.findAll());
        
        return "empleados";
    }
    
    @PostMapping("/guardarEmpleado")
    public String guardarEmpleados(@ModelAttribute Empleado empleado) {
        empleadoService.guardarEmpleado(empleado);
        return "redirect:/empleados";
    }
    
    @GetMapping("/eliminarEmpleado")
    public String eliminarEmpleados(@RequestParam Long id) {
        empleadoService.eliminarEmpleado(id);
        return "redirect:/empleados";
    }
    
    @GetMapping("/editarEmpleado")
    public String mostrarFormularioEditar(@RequestParam Long id, Model model) {
        Empleado empleado = empleadoService.obtenerEmpleadoPorId(id);
        
        model.addAttribute("empleado", empleado);
        model.addAttribute("empleados", empleadoService.listarEmpleadosDelJefeActual());
        
        model.addAttribute("estados", EstadoEmpleado.values());
        model.addAttribute("departamentos", DepartamentoEmpleado.values());
        model.addAttribute("roles", rolRepository.findAll());
        
        return "empleados";
    }
    

}
