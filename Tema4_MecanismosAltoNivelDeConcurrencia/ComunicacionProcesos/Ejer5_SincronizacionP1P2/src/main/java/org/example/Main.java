package org.example;

import org.example.model.P1;
import org.example.model.P2;
import org.example.model.Semaphore;

public class Main {
    // SEMAFOROS INICIALIZADOS A 1 Y 0 RESPECTIVAMENTE
    public static Semaphore s1 = new Semaphore(1); // SEMAFORO S1 INICIALIZADO A 1
    public static Semaphore s2 = new Semaphore(0); // SEMAFORO S2 INICIALIZADO A 0

    public static void main(String[] args) {
        // CREACION DE LOS HILOS PARA P1 Y P2
        Thread p1 = new Thread(new P1(), "P1");
        Thread p2 = new Thread(new P2(), "P2");

        // INICIO DE LOS HILOS
        p2.start();  // P2 INICIA PRIMERO SEGUN EL PLANIFICADOR
        p1.start();  // P1 INICIA SEGUNDO
    }
}
