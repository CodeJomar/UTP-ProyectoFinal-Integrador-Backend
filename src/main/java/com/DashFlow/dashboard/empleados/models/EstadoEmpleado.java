package com.DashFlow.dashboard.empleados.models;

public enum EstadoEmpleado {
    ACTIVO("Activo", "exito-JE"),
    VACACIONES("Vacaciones", "advertencia-JE"),
    AUSENTE("Ausente", "peligro-JE"),
    INACTIVO("Inactivo", "peligro-JE");
    
    private final String nombre;
    private final String cssClass;
    
    EstadoEmpleado(String nombre, String cssClass) {
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
