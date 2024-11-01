package org.example;

public class PadreEsperaHijo extends Thread {
    public void run() {
        long id = this.getId();
        try {
            sleep(3000);
            System.out.println("dentro del run");
        } catch (Exception e) {
            System.out.println("El hilo " + id + " se va por " + e);
        }
        System.out.println("El hilo " + id + " finaliza correctamente");
    }


    public static void main(String args[]) throws Exception {
        System.out.println("Hola desde el hilo principal!");

        PadreEsperaHijo hilo1 = new PadreEsperaHijo();
        hilo1.start();
        hilo1.join();

        System.out.println("Fin Principal");
    }
}