package org.example.model;

// CLASE SEMAFORO QUE SIMULA EL FUNCIONAMIENTO DE UN SEMAFORO
public class Semaphore {
    private int count;

    // CONSTRUCTOR DEL SEMAFORO QUE INICIALIZA EL CONTADOR
    public Semaphore(int initial) {
        count = initial; // ESTABLECE EL VALOR INICIAL DEL SEMAFORO
    }

    // OPERACION DOWN QUE DECRECE EL SEMAFORO Y BLOQUEA SI EL VALOR ES 0
    public synchronized void down() {
        while (count == 0) { // SI EL SEMAFORO ESTA A 0, SE BLOQUEA
            try {
                System.out.println(Thread.currentThread().getName() + " esperando en down()...");
                wait(); // ESPERA HASTA QUE EL SEMAFORO SE INCREMEINTE
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        count--; // DECREMENTA EL CONTADOR DEL SEMAFORO
        System.out.println(Thread.currentThread().getName() + " pasa por down() con count = " + count);
    }

    // OPERACION UP QUE INCREMENTA EL SEMAFORO Y NOTIFICA A LOS HILOS BLOQUEADOS
    public synchronized void up() {
        count++; // INCREMENTA EL CONTADOR DEL SEMAFORO
        System.out.println(Thread.currentThread().getName() + " pasa por up() con count = " + count);
        notifyAll(); // NOTIFICA A LOS HILOS BLOQUEADOS QUE EL SEMAFORO ESTA DISPONIBLE
    }

    // FUNCION PARA OBTENER EL VALOR DEL SEMAFORO (NO USADA EN ESTE EJEMPLO)
    public synchronized int get() {
        return count;
    }
}
