package com.DashFlow.dashboard.pedidos.models;

public enum EstadoPedido {
    PENDIENTE("Pendiente", "status-badge-JE pending"),
    ENTREGADO("Entregado", "status-badge-JE completed"),
    CANCELADO("Cancelado", "status-badge-JE failed");
    
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
