package org.example.model;

public class Semaphore {
    private int count;

    // CONSTRUCTOR DEL SEMAFORO, INICIALIZA EL SEMAFORO CON EL VALOR PASADO
    public Semaphore(int initial) {
        count = initial;
    }

    // OPERACION DOWN (DECREMENTA EL SEMAFORO Y BLOQUEA SI EL VALOR ES 0)
    public synchronized void down() {
        while (count == 0) { // SI EL SEMAFORO ESTA A 0, SE BLOQUEA
            try {
                wait(); // ESPERA A QUE EL SEMAFORO SE INCREMEINTE
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        count--; // DECREMENTA EL SEMAFORO
    }

    // OPERACION UP (INCREMENTA EL SEMAFORO Y DESBLOQUEA LOS PROCESOS BLOQUEADOS)
    public synchronized void up() {
        count++; // INCREMENTA EL SEMAFORO
        notify(); // NOTIFICA A LOS HILOS BLOQUEADOS
    }
}

