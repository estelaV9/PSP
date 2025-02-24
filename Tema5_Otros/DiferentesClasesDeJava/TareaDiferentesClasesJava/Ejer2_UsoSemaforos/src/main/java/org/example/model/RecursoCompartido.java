package org.example.model;

import java.util.concurrent.Semaphore;
import java.util.Random;

// CLASE QUE SIMULA UN RECURSO COMPARTIDO CONTROLADO POR UN SEMAFORO
public class RecursoCompartido {

    private Semaphore semaforo; // SEMAFORO PARA CONTROLAR EL ACCESO AL RECURSO

    // CONSTRUCTOR QUE INICIALIZA EL SEMAFORO CON LA CANTIDAD DE PERMISOS
    public RecursoCompartido(int permisos) {
        this.semaforo = new Semaphore(permisos); // INICIALIZA EL SEMAFORO CON EL NUMERO DE PERMISOS
    }

    // METODO PARA ACCEDER AL RECURSO, CON CONTROL DE SEMAFORO
    public void accederRecurso(String nombreThread) {
        try {
            // EL HILO ADQUIERE UN PERMISO DEL SEMAFORO
            semaforo.acquire();

            // IMPRIME QUE EL HILO HA ACCEDIDO AL RECURSO
            System.out.println(nombreThread + " ACCEDIO AL RECURSO.");

            // SE CREA UN OBJETO RANDOM PARA GENERAR EL TIEMPO DE ESPERA ALEATORIO
            Random random = new Random();

            // SE GENERA UN TIEMPO DE ESPERA ALEATORIO ENTRE 1 Y 2 SEGUNDOS
            int tiempoEspera = random.nextInt(2000) + 1000;

            // EL HILO DUERME EL TIEMPO GENERADO ALEATORIAMENTE
            Thread.sleep(tiempoEspera);

            // IMPRIME QUE EL HILO LIBERO EL RECURSO
            System.out.println(nombreThread + " LIBERO EL RECURSO.");
        } catch (InterruptedException e) {
            // SI EL HILO ES INTERUMPIDO, SE MANEJA LA EXCEPCION
            Thread.currentThread().interrupt();
        } finally {
            // LIBERA UN PERMISO DEL SEMAFORO AL FINAL
            semaforo.release();
        }
    }
}
