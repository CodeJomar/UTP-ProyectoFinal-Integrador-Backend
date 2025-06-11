package com.DashFlow.dashboard.pedidos.models;

public enum EstadoPago {
    PENDIENTE("Pendiente", "status-badge advertencia-JE"),
    PAGADO("Pagado", "status-badge exito-JE");
    
    private final String displayName;
    private final String cssClass;
    
    EstadoPago(String displayName, String cssClass) {
        this.displayName = displayName;
        this.cssClass = cssClass;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public String getCssClass() {
        return cssClass;
    }
}
