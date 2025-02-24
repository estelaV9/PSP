// PAQUETE DONDE SE ENCUENTRA LA CLASE
package org.example;

// IMPORTACION DE CLASES NECESARIAS PARA LA SINCRONIZACION
import java.util.concurrent.Semaphore;

// DEFINICION DE LA CLASE PRINCIPAL
public class SincronizacionProcesos {

    // SEMAFOROS PARA CONTROLAR LA EJECUCION DE LOS PROCESOS
    private static Semaphore semaforoP1 = new Semaphore(1); // P1 INICIA EJECUTANDO
    private static Semaphore semaforoP2 = new Semaphore(0); // P2 INICIA BLOQUEADO

    // METODO PRINCIPAL
    public static void main(String[] args) {
        // CREACION DE LOS HILOS PARA EJECUTAR LOS PROCESOS
        Thread proceso1 = new Thread(new Proceso1());
        Thread proceso2 = new Thread(new Proceso2());

        // INICIO DE LOS HILOS
        proceso1.start();
        proceso2.start();

        // ESPERA A QUE LOS HILOS TERMINEN SU EJECUCION
        try {
            proceso1.join();
            proceso2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // MENSAJE FINAL
        System.out.println("\nEJECUCION COMPLETADA");
    }

    // CLASE QUE REPRESENTA EL PROCESO 1
    static class Proceso1 implements Runnable {
        public void run() {
            for (int i = 0; i < 2; i++) { // SE EJECUTA DOS VECES
                try {
                    semaforoP1.acquire(); // ESPERA SU TURNO PARA EJECUTARSE

                    // EJECUCION DE INSTRUCCIONES
                    System.out.println("P1: send(msg)");  // ENVIA MENSAJE
                    System.out.println("P1: f(msg)");     // PROCESA MENSAJE
                    System.out.println("P1: recv(msg) X"); // SE BLOQUEA ESPERANDO MENSAJE

                    semaforoP2.release(); // DESBLOQUEA A P2

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // CLASE QUE REPRESENTA EL PROCESO 2
    static class Proceso2 implements Runnable {
        public void run() {
            for (int i = 0; i < 2; i++) { // SE EJECUTA DOS VECES
                try {
                    semaforoP2.acquire(); // ESPERA SU TURNO PARA EJECUTARSE

                    // EJECUCION DE INSTRUCCIONES
                    System.out.println("P2: recv(msg)");  // RECIBE MENSAJE
                    System.out.println("P2: f(msg)");     // PROCESA MENSAJE
                    System.out.println("P2: send(msg) /"); // ENVIA MENSAJE Y DESBLOQUEA P1

                    semaforoP1.release(); // DESBLOQUEA A P1

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


/* consola
P1: send(msg)
P1: f(msg)
P1: recv(msg) X
P2: recv(msg)
P2: f(msg)
P2: send(msg) /
P1: send(msg)
P1: f(msg)
P1: recv(msg) X
P2: recv(msg)
P2: f(msg)
P2: send(msg) /

EJECUCION COMPLETADA


send(msg) → Envía un mensaje de P1 a P2, o de P2 a P1.
recv(msg) → Recibe un mensaje.
Es bloqueante, lo que significa que un proceso se detiene si no hay un mensaje disponible.
En este caso, cuando P1 hace recv(msg), se bloquea (X) hasta que P2 lo envía.
Lo mismo ocurre después con P2, que debe esperar a P1
 */