package com.DashFlow.dashboard.pedidos.controllers;

import com.DashFlow.dashboard.pedidos.models.EstadoPago;
import com.DashFlow.dashboard.pedidos.models.EstadoPedido;
import com.DashFlow.dashboard.pedidos.models.Pedido;
import com.DashFlow.dashboard.pedidos.services.PedidoService;
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

@Controller
@RequestMapping("/pedidos")
public class PedidoController {
    
    @Autowired
    private PedidoService pedidoService;
    
    @Autowired
    private ProductoService productoService;
    
    @GetMapping("")
    public String verPedidos(Model model) {
        model.addAttribute("pedido", new Pedido());
        model.addAttribute("pedidos", pedidoService.listarPedidosDelUsuarioActual());
        model.addAttribute("productos", productoService.listarProductosDelAdminActual());
        model.addAttribute("estadosPedido", EstadoPedido.values());
        model.addAttribute("estadosPago", EstadoPago.values());
        return "pedidos";
    }
    
    @PostMapping("/guardar")
    public String guardarPedido(@ModelAttribute Pedido pedido) {
        pedidoService.guardarPedido(pedido);
        return "redirect:/pedidos";
    }
    
    @GetMapping("/eliminar")
    public String eliminarPedido(@RequestParam Long id) {
        pedidoService.eliminarPedido(id);
        return "redirect:/pedidos";
    }
    
    @GetMapping("/editar")
    public String editarPedido(@RequestParam Long id, Model model) {
        Pedido pedido = pedidoService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        
        model.addAttribute("pedido", pedido);
        model.addAttribute("pedidos", pedidoService.listarPedidosDelUsuarioActual());
        model.addAttribute("productos", productoService.listarProductosDelAdminActual());
        model.addAttribute("estadosPedido", EstadoPedido.values());
        model.addAttribute("estadosPago", EstadoPago.values());
        return "pedidos";
    }
    
    @GetMapping("/exportar")
    public void exportarPedidos(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=pedidos.xlsx");
        
        List<Pedido> pedidos = pedidoService.listarPedidosDelUsuarioActual();
        
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Pedidos");
        
        CellStyle headerStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        headerStyle.setFont(font);
        headerStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        
        // Columnas
        String[] columnas = {
                "Nombre Cliente", "Email", "Celular", "Fecha", "Dirección",
                "Producto", "Precio Producto", "Cantidad", "Monto Total",
                "Estado Pedido", "Estado Pago", "Creado Por"
        };
        
        Row header = sheet.createRow(0);
        for (int i = 0; i < columnas.length; i++) {
            Cell cell = header.createCell(i);
            cell.setCellValue(columnas[i]);
            cell.setCellStyle(headerStyle);
        }
        
        // Datos
        int rowIndex = 1;
        for (Pedido p : pedidos) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(p.getNombreCliente());
            row.createCell(1).setCellValue(p.getEmailCliente());
            row.createCell(2).setCellValue(p.getCelularCliente());
            row.createCell(3).setCellValue(p.getFechaPedido().toString());
            row.createCell(4).setCellValue(p.getDireccionEnvio());
            row.createCell(5).setCellValue(p.getProducto().getNombre());
            row.createCell(6).setCellValue(p.getProducto().getPrecioVenta().doubleValue());
            row.createCell(7).setCellValue(p.getCantidad());
            row.createCell(8).setCellValue(p.getMontoTotal().doubleValue());
            row.createCell(9).setCellValue(p.getEstadoPedido().getDisplayName());
            row.createCell(10).setCellValue(p.getEstadoPago().getDisplayName());
            row.createCell(11).setCellValue(p.getCreadoPor().getNombre());
        }
        
        // Autoajustar columnas
        for (int i = 0; i < columnas.length; i++) {
            sheet.autoSizeColumn(i);
        }
        
        workbook.write(response.getOutputStream());
        workbook.close();
    }
    
    
}
