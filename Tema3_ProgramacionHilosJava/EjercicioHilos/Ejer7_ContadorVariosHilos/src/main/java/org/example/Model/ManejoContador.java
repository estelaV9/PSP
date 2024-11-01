package org.example.Model;

public class ManejoContador extends Thread {
    private final Contador contador;
    private final String nombreHilo;

    public ManejoContador(Contador contador, String nombreHilo) {
        this.contador = contador;
        this.nombreHilo = nombreHilo;
    }

    @Override
    public void run() {
        try {
            contador.incrementarContador(nombreHilo);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}