package org.example;

import java.util.concurrent.Semaphore;

// CLASE PRINCIPAL
public class SincronizacionSemaforoP1P2 {

    // SEMAFORO GLOBAL
    private static Semaphore s = new Semaphore(1); // SEMAFORO INICIALIZADO A 1

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
                    s.acquire(); // DOWN(s) - BLOQUEA SI ES NECESARIO

                    // EJECUCION DE INSTRUCCIONES
                    System.out.println("P1: f1() 1");
                    System.out.println("P1: f1() 2");
                    System.out.println("P1: f1() 3");

                    s.release(); // UP(s) - LIBERA EL SEMAFORO

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
                    s.acquire(); // DOWN(s) - BLOQUEA SI ES NECESARIO

                    // EJECUCION DE INSTRUCCIONES
                    System.out.println("P2: f2() 1");
                    System.out.println("P2: f2() 2");
                    System.out.println("P2: f2() 3");

                    s.release(); // UP(s) - LIBERA EL SEMAFORO

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

SEMAFORO s:

El semáforo s controla el acceso a la ejecución de los procesos.
Se inicia en 1, lo que permite que el primer proceso (P1) ejecute sin bloqueo.
EJECUCIÓN DE CADA PROCESO:

El primer proceso en obtener el semáforo (en este caso P1) puede ejecutar sus instrucciones.
Luego, al liberar el semáforo (up(s)), el siguiente proceso (P2) puede acceder a él y ejecutar sus instrucciones.
SINCRONIZACIÓN CON EL SEMÁFORO:

down(s) bloquea el semáforo para que ningún otro proceso pueda ejecutarse mientras se está usando.
up(s) libera el semáforo para que el siguiente proceso pueda ejecutarse
 */