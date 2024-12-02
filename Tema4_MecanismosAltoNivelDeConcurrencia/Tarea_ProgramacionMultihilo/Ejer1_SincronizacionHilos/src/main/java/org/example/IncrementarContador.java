package org.example;

import java.lang.management.ManagementFactory;

public class IncrementarContador implements Runnable {

    public IncrementarContador() {
    }

    @Override
    public void run() {
        // ITERAR 1000 VECES PARA SIMULAR EL INCREMENTO DEL CONTADOR
        for (int j = 0; j < 1000; j++) {
            synchronized (SincronizacionHilos.lock) {
                // OBTENER EL TIEMPO DE CPU DEL HILO ACTUAL
                long cpuTime = ManagementFactory.getThreadMXBean().getThreadCpuTime(Thread.currentThread().threadId());
                double cpuTimeInSeconds = cpuTime / 1_000_000_000.0; // CONVERTIR A SEGUNDOS

                // IMPRIMIR EL TIEMPO DE CPU Y EL ID DEL HILO
                System.out.println("Hilo ID: " + Thread.currentThread().threadId() + " - CPU Time: "
                        + cpuTimeInSeconds + " segundos");

                // INCREMENTAR EL CONTADOR DE MANERA SEGURA Y EFICIENTE CON AtomicInteger
                SincronizacionHilos.contador++;
            }
        } // SINCRONIZACION EXPLICITA PARA EVITAR CONDICIONES DE CARRERA
    }
}