package org.example;

import java.util.concurrent.Semaphore;

// CLASE PRINCIPAL
public class Main {

    // SEMAFOROS PARA CONTROLAR EL ACCESO A CADA HILO
    private static Semaphore semaforoH1 = new Semaphore(1); // HILO 1 EMPIEZA PRIMERO
    private static Semaphore semaforoH2 = new Semaphore(0); // HILO 2 ESPERA SU TURNO

    // METODO PRINCIPAL
    public static void main(String[] args) {
        // CREACION DE LOS HILOS
        Thread h1 = new Thread(new Hilo1());
        Thread h2 = new Thread(new Hilo2());

        // INICIO DE LOS HILOS
        h1.start();
        h2.start();

        // ESPERA A QUE LOS HILOS TERMINEN SU EJECUCION
        try {
            h1.join();
            h2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // MENSAJE FINAL
        System.out.println("\nEJECUCION COMPLETADA");
    }

    // CLASE QUE REPRESENTA EL HILO 1
    static class Hilo1 implements Runnable {
        public void run() {
            for (int i = 0; i < 2; i++) { // SE EJECUTA DOS VECES
                try {
                    semaforoH1.acquire(); // ESPERA SU TURNO PARA EJECUTARSE

                    // EJECUCION DE INSTRUCCIONES
                    System.out.println("H1: f() 1");
                    System.out.println("H1: f() 2");
                    System.out.println("H1: f() 3");

                    semaforoH2.release(); // LIBERA A H2 PARA EJECUTARSE

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // CLASE QUE REPRESENTA EL HILO 2
    static class Hilo2 implements Runnable {
        public void run() {
            for (int i = 0; i < 2; i++) { // SE EJECUTA DOS VECES
                try {
                    semaforoH2.acquire(); // ESPERA SU TURNO PARA EJECUTARSE

                    // EJECUCION DE INSTRUCCIONES
                    System.out.println("H2: f() 1");
                    System.out.println("H2: f() 2");
                    System.out.println("H2: f() 3");

                    semaforoH1.release(); // LIBERA A H1 PARA EJECUTARSE

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


/* consola
H1: f() 1
H1: f() 2
H1: f() 3
H2: f() 1
H2: f() 2
H2: f() 3
H1: f() 1
H1: f() 2
H1: f() 3
H2: f() 1
H2: f() 2
H2: f() 3


USAMOS SEMÁFOROS EN LUGAR DE CERROJOS:

semaforoH1 comienza en 1, permitiendo que H1 inicie primero.
semaforoH2 empieza en 0, lo que bloquea a H2 hasta que H1 lo libere.
CUANDO UN HILO TERMINA SU CUANTUM (3 OPERACIONES):

H1 libera a H2 (semaforoH2.release();).
H2 libera a H1 (semaforoH1.release();).
CADA HILO SE EJECUTA DOS VECES PARA CUMPLIR CON EL REQUISITO DEL ENUNCIADO.
 */