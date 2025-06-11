package com.DashFlow.dashboard.pedidos.models;

public enum EstadoPedido {
    PENDIENTE("Pendiente", "status-badge advertencia-JE"),
    ENTREGADO("Entregado", "status-badge exito-JE"),
    CANCELADO("Cancelado", "status-badge peligro-JE");
    
    private final String displayName;
    private final String cssClass;
    
    EstadoPedido(String displayName, String cssClass) {
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
