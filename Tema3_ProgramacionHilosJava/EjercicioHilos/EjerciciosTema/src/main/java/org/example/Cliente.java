package org.example;

public class Cliente {

    private String nombre;
    private int[] carroCompra;

    public Cliente(int[] carroCompra, String nombre) {
        this.carroCompra = carroCompra;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int[] getCarroCompra() {
        return carroCompra;
    }

    public void setCarroCompra(int[] carroCompra) {
        this.carroCompra = carroCompra;
    }
}
