package org.example.model;

// CLASE SEMÁFORO QUE SIMULA EL FUNCIONAMIENTO DE UN SEMÁFORO BINARIO
public class Semaphore {
    private int count;

    // CONSTRUCTOR DEL SEMÁFORO
    public Semaphore(int initial) {
        count = initial;
    }

    // OPERACIÓN DOWN: DECRECE EL SEMÁFORO
    public synchronized void down() {
        while (count == 0) {
            try {
                wait(); // ESPERA HASTA QUE EL SEMÁFORO SEA MAYOR QUE 0
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        count--; // DECRECE EL SEMÁFORO
    }

    // OPERACIÓN UP: INCREMENTA EL SEMÁFORO
    public synchronized void up() {
        count++; // INCREMENTA EL SEMÁFORO
        notify(); // NOTIFICA A LOS HILOS BLOQUEADOS
    }

    // OBTIENE EL VALOR ACTUAL DEL SEMÁFORO
    public synchronized int get() {
        return count;
    }
}
