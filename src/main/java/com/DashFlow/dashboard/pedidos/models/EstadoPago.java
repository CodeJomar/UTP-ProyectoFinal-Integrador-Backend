package com.DashFlow.dashboard.pedidos.models;

public enum EstadoPago {
    PENDIENTE("Pendiente", "status-badge-JE pending"),
    PAGADO("Pagado", "status-badge-JE completed"),
    CANCELADO("Cancelado", "status-badge-JE failed");
    
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
