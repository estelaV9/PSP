package org.example;

public class Cronometro extends java.lang.Thread {

    @Override
    public void run() {
        try {
            for(int i = 1; i <= 100; i++){
                Cronometro.sleep(1000);
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            // SI EL HILO ES INTERRUMPIDO, FINALIZA EL HILO
            Cronometro.currentThread().interrupt();
        }
    } // CREAR METODO
}