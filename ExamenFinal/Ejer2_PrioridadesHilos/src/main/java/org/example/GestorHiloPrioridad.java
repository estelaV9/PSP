package org.example;

public class GestorHiloPrioridad implements Runnable {
    private static final Object lock = new Object(); // ATRIBUTO PARA EL BLOQUEO DE LA SINCRONIZACION
    private static boolean hiloPrioritarioTerminado = false; // ATRIBUTO PARA CONTROLAR SI EL HILO PRIORITARIO HA TERMINADO
    private final boolean esPrioritario; // ATRIBUTO PARA SABER SI EL HILO ES PRIORITARIO O NO

    public GestorHiloPrioridad(boolean esPrioritario) {
        this.esPrioritario = esPrioritario;
    } // CONSTRUCTOR QUE RECIBE SI EL HILO ES PRIORITARIO

    @Override
    public void run() {
        synchronized (lock) {
            if (!esPrioritario && !hiloPrioritarioTerminado) {
                try {
                    lock.wait(); // HILO NO PRIORITARIO ESPERA HASTA QUE LE NOTIFIQUE
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // EXCEPCION POR SI SE INTERRUMPE EL HILO
                } // try-catch PARA MANEJAR LA EXCEPCION DEL wait()
            } // SI EL HILO NO ES PRIORITARIO Y EL HILO PRIORITARIO AUN NO HA TERMINADO, EL BLOQUE ESPERA

            for (int i = 0; i < 5; i++) {
                try {
                    System.out.println("Hilo " + Thread.currentThread().getName() +
                            " con prioridad " + Thread.currentThread().getPriority() +
                            " está ejecutándose.");
                    Thread.sleep(500); // EL HILO DUERME MEDIO SEGUNDO
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // EXCEPCION POR SI SE INTERRUMPE EL HILO
                    break; // SI SE INTERRUMPE EL HILO, SE SALE DEL BUCLE
                } // try-catch PARA MANEJAR LA EXCEPCION DEL sleep()
            } // SE RECORRE 5 ITERACIONES MOSTRANDO EL MENSAJE DE PRIORIDAD

            if (esPrioritario) {
                System.out.println("****************");
                hiloPrioritarioTerminado = true; // INDICA QUE EL HILO PRIORITARIO HA TERMINADO
                lock.notify(); // SE NOTIFICAN A LOS HILOS QUE ESTAN ESPERANDO EN EL BLOQUEO
            } // SI ES PRIORITARIO
        } // BLOQUE DE SINCRONIZACION PARA CONTROLAR EL BLOQUEO LOCK
    } // METODO RUN
} // Class GestorHiloPrioridad