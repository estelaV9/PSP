package org.example.model;

// CLASE SEMAFORO PARA SIMULAR EL FUNCIONAMIENTO DE UN SEMAFORO
public class Semaphore {

    private int count; // VARIABLE PARA ALMACENAR EL VALOR DEL SEMAFORO

    // CONSTRUCTOR QUE INICIALIZA EL SEMAFORO CON UN VALOR ESPECIFICO
    public Semaphore(int initial) {
        count = initial; // INICIALIZA EL SEMAFORO CON EL VALOR PASADO
    }

    // METODO DOWN: DECRECE EL SEMAFORO
    public synchronized void down() {
        while (count == 0) {
            try {
                wait(); // ESPERA HASTA QUE EL SEMAFORO SEA MAYOR QUE 0
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // MANEJA LA EXCEPCION
            }
        }
        count--; // DECRECE EL SEMAFORO
    }

    // METODO UP: INCREMENTA EL SEMAFORO
    public synchronized void up() {
        count++; // INCREMENTA EL SEMAFORO
        notify(); // NOTIFICA A LOS HILOS BLOQUEADOS
    }

    // METODO PARA OBTENER EL VALOR DEL SEMAFORO
    public synchronized int get() {
        return count; // RETORNA EL VALOR DEL SEMAFORO
    }
}
