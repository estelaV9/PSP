package org.example;

public class PadreInterrumpeTareaHijo extends Thread {

    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Esperando a recibir dato!");
            if (Thread.interrupted() == true) {
                System.out.println("Hilo interrumpido.");
                return;
            }
        }
        //qu� hago con el dato
        System.out.println("El hilo finaliza correctamente");
    }


    public static void main(String args[]) throws Exception {
        System.out.println("Hola desde el hilo principal!");

        PadreInterrumpeTareaHijo hilo1 = new PadreInterrumpeTareaHijo();
        hilo1.start();

        //TimeUnit.SECONDS.sleep(1);
        hilo1.interrupt();


        System.out.println("Fin Principal");
    }
}