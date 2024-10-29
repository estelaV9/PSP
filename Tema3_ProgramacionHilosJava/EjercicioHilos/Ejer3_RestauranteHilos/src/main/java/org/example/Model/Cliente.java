package org.example.Model;

public class Cliente extends Thread{
    private String nombre;
    private Cocina cocina;

    public Cliente(String nombre, Cocina cocina) {
        this.nombre = nombre;
        this.cocina = cocina;
    }

    @Override
    public void run() {
        try {
            cocina.consumirPlato();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}