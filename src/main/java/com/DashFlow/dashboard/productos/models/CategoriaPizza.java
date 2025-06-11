package com.DashFlow.dashboard.productos.models;

public enum CategoriaPizza {
    CLASICA("Clásica"),
    ESPECIAL("Especial"),
    VEGETARIANA("Vegetariana"),
    CARNE("Carne");
    
    private final String displayName;
    
    CategoriaPizza(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}
