package org.example.Model;

public class Mascotas {
    private String nombre;
    private int tiempoCarrera;

    public Mascotas(String nombre, int tiempoCarrera) {
        this.nombre = nombre;
        this.tiempoCarrera = tiempoCarrera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTiempoCarrera() {
        return tiempoCarrera;
    }

    public void setTiempoCarrera(int tiempoCarrera) {
        this.tiempoCarrera = tiempoCarrera;
    }
}