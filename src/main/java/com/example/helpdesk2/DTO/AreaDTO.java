package com.example.helpdesk2.DTO;

public class AreaDTO {
    private String nombre;

    public AreaDTO() {
    }

    public AreaDTO(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
