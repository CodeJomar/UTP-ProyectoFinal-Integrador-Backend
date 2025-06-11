package com.DashFlow.dashboard.empleados.controllers;

import com.DashFlow.dashboard.auth.repository.RolRepository;
import com.DashFlow.dashboard.empleados.models.DepartamentoEmpleado;
import com.DashFlow.dashboard.empleados.models.Empleado;
import com.DashFlow.dashboard.empleados.models.EstadoEmpleado;
import com.DashFlow.dashboard.empleados.services.EmpleadoService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

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
    
    @GetMapping("/exportar")
    public void exportarEmpleados(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=empleados.xlsx");
        
        List<Empleado> empleados = empleadoService.listarEmpleadosDelJefeActual();
        
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Empleados");
        
        CellStyle headerStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        headerStyle.setFont(font);
        headerStyle.setFillForegroundColor(IndexedColors.DARK_GREEN.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        
        String[] columnas = {
                "Nombre", "Email", "Departamento", "Fecha Contratación", "Fecha Despido",
                "Salario", "Estado", "Rol", "Celular"
        };
        
        Row header = sheet.createRow(0);
        for (int i = 0; i < columnas.length; i++) {
            Cell cell = header.createCell(i);
            cell.setCellValue(columnas[i]);
            cell.setCellStyle(headerStyle);
        }
        
        int rowIndex = 1;
        for (Empleado e : empleados) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(e.getNombre());
            row.createCell(1).setCellValue(e.getEmail());
            row.createCell(2).setCellValue(e.getDepartamento().toString());
            row.createCell(3).setCellValue(
                    e.getFechaContratacion() != null ? e.getFechaContratacion().toString() : ""
            );
            row.createCell(4).setCellValue(
                    e.getFechaDespido() != null ? e.getFechaDespido().toString() : ""
            );
            row.createCell(5).setCellValue(e.getSalario().doubleValue());
            row.createCell(6).setCellValue(e.getEstado().toString());
            row.createCell(7).setCellValue(e.getRol().getNombreRol());
            row.createCell(8).setCellValue(
                    e.getPersona() != null ? e.getPersona().getCelular() : ""
            );
        }
        
        for (int i = 0; i < columnas.length; i++) {
            sheet.autoSizeColumn(i);
        }
        
        workbook.write(response.getOutputStream());
        workbook.close();
    }
    
    
    
}
