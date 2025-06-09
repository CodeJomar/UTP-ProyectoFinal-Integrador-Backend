package com.DashFlow.dashboard.empleados.models;

public enum Departamento {
    VENTAS("Ventas", "primario-JE"),
    MARKETING("Marketing", "exito-JE"),
    OPERACIONES("Operaciones", "advertencia-JE"),
    CONTABILIDAD("Contabilidad", "info-JE"),
    RECURSOS_HUMANOS("Recursos Humanos", "secundario-JE");
    
    private final String nombre;
    private final String cssClass;
    
    Departamento(String nombre, String cssClass) {
        this.nombre = nombre;
        this.cssClass = cssClass;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getCssClass() {
        return cssClass;
    }
}
