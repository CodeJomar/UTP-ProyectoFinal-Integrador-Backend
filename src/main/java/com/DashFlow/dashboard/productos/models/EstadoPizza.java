package com.DashFlow.dashboard.productos.models;

public enum EstadoPizza {
    ACTIVO("Activo", "status-badge-JE active"),
    INACTIVO("Inactivo", "status-badge-JE inactive");
    
    private final String displayName;
    private final String cssClass;
    
    EstadoPizza(String displayName, String cssClass) {
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
