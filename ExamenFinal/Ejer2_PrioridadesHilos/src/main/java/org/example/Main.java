package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("************* EJERCICIO 2: PRIORIDADES DE HILOS");
        // CREAMOS 2 HILOS DANDOLE VALOR A LA VARIABLE DE SI ES PRIORITARIO
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
            Thread.currentThread().interrupt();  // EXCEPCION POR SI SE INTERRUMPE EL HILO
            System.out.println("Error: el hilo fue interrumpido. " + e); // MENSAJE DE ERROR
        } // try-catch PARA MANEJAR LA EXCEPCION DEL join()

        System.out.println("Programa finalizado"); // MENSAJE DE FINALIZACION
    }
}