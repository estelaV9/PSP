package org.example;

import java.util.concurrent.TimeUnit;

public class PadreInterrumpeSleepdelHijo extends Thread {
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("Esperando a recibir dato!");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("Hilo interrumpido.");
                return;
            }
        }

        //qu� hago con el dato
        System.out.println("El hilo finaliza correctamente");
    }


    public static void main(String args[]) throws Exception {
        System.out.println("Hola desde el hilo principal!");

        PadreInterrumpeSleepdelHijo hilo1 = new PadreInterrumpeSleepdelHijo();
        hilo1.start();

        TimeUnit.SECONDS.sleep(1);
        hilo1.interrupt();

        System.out.println("Fin Principal");
    }
}