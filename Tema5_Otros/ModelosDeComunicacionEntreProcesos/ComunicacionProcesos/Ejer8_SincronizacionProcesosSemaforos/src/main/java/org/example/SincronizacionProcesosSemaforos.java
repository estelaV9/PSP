package org.example;

import java.util.concurrent.Semaphore;

// CLASE PRINCIPAL
public class SincronizacionProcesosSemaforos {

    // SEMAFOROS PARA CADA PROCESO
    private static Semaphore s1 = new Semaphore(1); // SEMAFORO PARA P1
    private static Semaphore s2 = new Semaphore(1); // SEMAFORO PARA P2

    // METODO PRINCIPAL
    public static void main(String[] args) {
        // CREACION DE LOS PROCESOS (HILOS)
        Thread p1 = new Thread(new Proceso1());
        Thread p2 = new Thread(new Proceso2());

        // INICIO DE LOS PROCESOS
        p1.start();
        p2.start();

        // ESPERA A QUE LOS PROCESOS TERMINEN SU EJECUCION
        try {
            p1.join();
            p2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // MENSAJE FINAL
        System.out.println("\nEJECUCION COMPLETADA");
    }

    // CLASE QUE REPRESENTA EL PROCESO P1
    static class Proceso1 implements Runnable {
        public void run() {
            for (int i = 0; i < 2; i++) { // SE EJECUTA DOS VECES
                try {
                    s1.acquire(); // DOWN(s1) - BLOQUEA SI ES NECESARIO

                    // EJECUCION DE INSTRUCCIONES
                    System.out.println("P1: f1() 1");
                    System.out.println("P1: f1() 2");
                    System.out.println("P1: f1() 3");

                    s1.release(); // UP(s1) - LIBERA EL SEMAFORO

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // CLASE QUE REPRESENTA EL PROCESO P2
    static class Proceso2 implements Runnable {
        public void run() {
            for (int i = 0; i < 2; i++) { // SE EJECUTA DOS VECES
                try {
                    s2.acquire(); // DOWN(s2) - BLOQUEA SI ES NECESARIO

                    // EJECUCION DE INSTRUCCIONES
                    System.out.println("P2: f2() 1");
                    System.out.println("P2: f2() 2");
                    System.out.println("P2: f2() 3");

                    s2.release(); // UP(s2) - LIBERA EL SEMAFORO

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

/* consola
P1: f1() 1
P1: f1() 2
P1: f1() 3
P1: f1() 1
P1: f1() 2
P1: f1() 3
P2: f2() 1
P2: f2() 2
P2: f2() 3
P2: f2() 1
P2: f2() 2
P2: f2() 3

EJECUCION COMPLETADA

USAMOS SEMÁFOROS s1 Y s2:

s1 controla a P1, y s2 a P2.
Ambos inician en 1, permitiendo que cada proceso pueda ejecutarse inmediatamente.
CUANDO UN PROCESO TOMA SU TURNO (3 OPERACIONES):

Ejecuta f1() o f2() tres veces.
Luego, libera su semáforo (up(s1) o up(s2)).
CADA PROCESO SE EJECUTA DOS VECES COMO EXIGE EL PROBLEMA
 */
