package org.example.model;

public class Hilo implements Runnable {
    private final String nombreHilo; // NOMBRE DEL HILO
    private int contador; // CONTADOR DEL HILO
    private boolean enEjecucion; // ESTADO DEL HILO

    public Hilo(String nombreHilo) {
        this.nombreHilo = nombreHilo;
        this.contador = 0; // INICIALIZA EL CONTADOR
        this.enEjecucion = false; // INICIALIZA EL ESTADO COMO DETENIDO
    }

    public String getNombreHilo() {
        return nombreHilo;
    }

    public int getContador() {
        return contador;
    }

    public boolean isEnEjecucion() {
        return enEjecucion;
    }

    public void setEnEjecucion(boolean enEjecucion) {
        this.enEjecucion = enEjecucion;
    }


    @Override
    public void run() {
        try {
            while (enEjecucion) { // EJECUTA MIENTRAS EL HILO ESTE ACTIVO
                contador++; // INCREMENTA EL CONTADOR
                Thread.sleep(1000); // ESPERA 1 SEGUNDO
            }
        } catch (InterruptedException e) {
            System.out.println("hilo '" + nombreHilo + "' interrumpido.");
        }
    } // METODO RUN QUE AUMENTA EL CONTADOR
}