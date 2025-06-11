package com.DashFlow.dashboard.productos.models;

public enum TamanioPizza {
    PERSONAL("Personal"),
    MEDIANA("Mediana"),
    GRANDE("Grande"),
    FAMILIAR("Familiar"),
    EXTRA_GRANDE("Extra Grande");
    
    private final String displayName;
    
    TamanioPizza(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}
