package com.DashFlow.dashboard.productos.controllers;

import com.DashFlow.dashboard.productos.models.*;
import com.DashFlow.dashboard.productos.services.ProductoService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    
    @Autowired
    private ProductoService productoService;
    
    @GetMapping("")
    public String verProductos(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("productos", productoService.listarProductosDelAdminActual());
        
        model.addAttribute("tamanios", TamanioPizza.values());
        model.addAttribute("categorias", CategoriaPizza.values());
        model.addAttribute("masas", MasaPizza.values());
        model.addAttribute("estados", EstadoPizza.values());
        
        return "productos";
    }
    
    @PostMapping("/guardarProducto")
    public String guardarProducto(@ModelAttribute Producto producto) {
        productoService.guardarProducto(producto);
        return "redirect:/productos";
    }
    
    @GetMapping("/eliminarProducto")
    public String eliminarProducto(@RequestParam Long id) {
        productoService.eliminarProducto(id);
        return "redirect:/productos";
    }
    
    @GetMapping("/editarProducto")
    public String editarProducto(@RequestParam Long id, Model model) {
        Optional<Producto> optionalProducto = productoService.buscarPorId(id);
        
        if (optionalProducto.isEmpty()) {
            return "redirect:/productos?error=notfound";
        }
        
        Producto producto = optionalProducto.get();
        
        model.addAttribute("producto", producto);
        model.addAttribute("productos", productoService.listarProductosDelAdminActual());
        
        model.addAttribute("tamanios", TamanioPizza.values());
        model.addAttribute("categorias", CategoriaPizza.values());
        model.addAttribute("masas", MasaPizza.values());
        model.addAttribute("estados", EstadoPizza.values());
        
        return "productos";
    }
    
    @GetMapping("/exportar")
    public void exportarProductos(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=productos.xlsx");
        
        List<Producto> productos = productoService.listarProductosDelAdminActual();
        
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Productos");
        
        CellStyle headerStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        headerStyle.setFont(font);
        headerStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        
        String[] columnas = {
                "Nombre", "Precio Venta", "Tamaño", "Categoría", "Masa", "Estado", "Precio Costo", "Descripción"
        };
        
        Row header = sheet.createRow(0);
        for (int i = 0; i < columnas.length; i++) {
            Cell cell = header.createCell(i);
            cell.setCellValue(columnas[i]);
            cell.setCellStyle(headerStyle);
        }
        
        int rowIndex = 1;
        for (Producto p : productos) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(p.getNombre());
            row.createCell(1).setCellValue(p.getPrecioVenta().doubleValue());
            row.createCell(2).setCellValue(p.getTamanio().toString());
            row.createCell(3).setCellValue(p.getCategoria().toString());
            row.createCell(4).setCellValue(p.getMasa().toString());
            row.createCell(5).setCellValue(p.getEstado().toString());
            row.createCell(6).setCellValue(p.getPrecioCosto().doubleValue());
            row.createCell(7).setCellValue(p.getDescripcion());
        }
        
        for (int i = 0; i < columnas.length; i++) {
            sheet.autoSizeColumn(i);
        }
        
        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
