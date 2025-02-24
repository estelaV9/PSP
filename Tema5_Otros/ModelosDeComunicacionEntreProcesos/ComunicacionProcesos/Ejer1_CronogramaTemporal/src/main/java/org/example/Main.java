package org.example;

import org.example.model.ProcesoP1;
import org.example.model.ProcesoP2;
import org.example.model.Semaphore;

public class Main {
    // SEMAFOROS S1 Y S2
    public static Semaphore s1 = new Semaphore(1); // SEMAFORO S1 INICIALIZADO A 1
    static Semaphore s2 = new Semaphore(0); // SEMAFORO S2 INICIALIZADO A 0

    public static void main(String[] args) {
        // CREAR Y EJECUTAR LOS HILOS PARA LOS PROCESOS P1 Y P2
        Thread p1 = new Thread(new ProcesoP1(s1, s2)); // Se pasa s1 y s2 a P1
        Thread p2 = new Thread(new ProcesoP2(s1, s2)); // Se pasa s1 y s2 a P2

        // INICIAR LOS HILOS DE LOS PROCESOS
        p1.start();
        p2.start();
    }
}

/* CONSOLA
P1: down(s1)
P1: f1()
P1: ejecución de f1
P1: up(s2)
P1: down(s1)
P2: down(s2)
P2: f2()
P2: ejecución de f2
P2: up(s1)
P2: down(s2)
P1: f1()
P1: ejecución de f1
P1: up(s2)
P2: f2()
P2: ejecución de f2
P2: up(s1)
 */