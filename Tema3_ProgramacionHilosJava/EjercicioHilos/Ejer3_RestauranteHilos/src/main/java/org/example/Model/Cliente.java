package org.example.Model;

public class Cliente extends Thread{
    private String nombre;

    public Cliente(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        super.run();
    }
}
