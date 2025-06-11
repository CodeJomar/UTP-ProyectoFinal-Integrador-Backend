package com.DashFlow.dashboard.productos.models;

public enum MasaPizza {
    ORIGINAL("Original"),
    DELGADA("Delgada"),
    RELLENA_QUESO("Rellena de Queso"),
    SIN_GLUTEN("Sin Gluten");
    
    private final String displayName;
    
    MasaPizza(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}
