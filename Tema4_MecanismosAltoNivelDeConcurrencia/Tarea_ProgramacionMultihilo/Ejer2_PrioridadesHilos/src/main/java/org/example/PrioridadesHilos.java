package org.example;

public class PrioridadesHilos {
    public static void main(String[] args) throws InterruptedException {
        // CREAMOS 2 HILOS
        Thread hilo1 = new Thread(new GestorHiloPrioridad(true));
        Thread hilo2 = new Thread(new GestorHiloPrioridad(false));

        hilo1.setPriority(Thread.MAX_PRIORITY);  // LE ASIGNAMOS AL PRIMER HILO AL PRIORIDAD MAXIMA
        hilo2.setPriority(Thread.MIN_PRIORITY);  // LE ASIGNAMOS AL SEGUNDO HILO AL PRIORIDAD MINIMA

        hilo1.start(); // EJECUTAMOS EL PRIMER HILO
        hilo2.start(); // EJECUTAMOS EL SEGUNDO HILO

        // ESPERAMOS A QUE TERMINEN AMBOS HILOS
        try {
            hilo1.join();
            hilo2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Error: el hilo fue interrumpido. " + e);
        }

        System.out.println("Programa finalizado"); // MENSAJE DE FINALIZACION
    }
}