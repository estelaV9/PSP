package org.example.Model;

import java.io.InterruptedIOException;

public class Tarea extends Thread {
    @Override
    public void run() {
        try {
            System.out.println("Empezando descarga...");

            for (int i = 0; i <= 100; i += 10) {
                // SE BORRA LO QUE TENGA EL SOUT CON \r Y SE IMPRIME EL VALOR DE i EN FORMA DE PORCENTAJE
                System.out.print("\r" + i + "%");
                Thread.sleep(1000); // ESPERA 1 SEGUNDO
            } // SE RECORRE DEL 1 AL 100 EN PASO DE 10

            System.out.println("\nDescarga completada"); // MUESTRA MENSAJE DE FINALIZACION
        } catch (InterruptedException e) {
            // SI SE INTERRUMPE EL HILO SE TERMINA
            Tarea.currentThread().interrupt();
        }
    } // METODO RUN
}
