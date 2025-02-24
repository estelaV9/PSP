package org.example.model;

public class Hilo implements Runnable {
    private final String nombreHilo; // NOMBRE DEL HILO
    private int contador; // CONTADOR DEL HILO
    private boolean enEjecucion; // ESTADO DEL HILO

    public Hilo(String nombreHilo) {
        this.nombreHilo = nombreHilo;
        this.contador = 0; // INICIALIZA EL CONTADOR
        this.enEjecucion = false; // INICIALIZA EL ESTADO COMO DETENIDO
    } // CONSTRUCTOR PARA CREAR EL HILO

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
            while (enEjecucion) {
                contador++; // INCREMENTA EL CONTADOR
                Thread.sleep(1000); // ESPERA 1 SEGUNDO
            } // SE EJECUTA MIENTRAS EL HILO ESTE EN EJECUCION
        } catch (InterruptedException e) {
            // MUESTRA UN MENSAJE SI EL HILO SE HA INTERRUMPIDO
            System.out.println("hilo '" + nombreHilo + "' interrumpido.");
        } // try-catch PARA MANEJAR LA EXCEPCION DEL sleep()
    } // METODO RUN QUE AUMENTA EL CONTADOR
}