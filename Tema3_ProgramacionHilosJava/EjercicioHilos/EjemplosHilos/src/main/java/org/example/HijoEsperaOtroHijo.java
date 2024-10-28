package org.example;

public class HijoEsperaOtroHijo extends Thread {

    static HijoEsperaOtroHijo hilo[];

    public void run() {
        long id = this.getId();
        String name = this.getName();
        try {
            if (name == "0")
                //Thread.sleep(3000);;
                hilo[1].join();
            else
                hilo[0].join();
            //Thread.sleep(3000);
        } catch (Exception e) {
            System.out.println("El hilo " + name + " se va por " + e);
        }
        System.out.println("El hilo " + name + " finaliza correctamente");
    }


    public static void main(String args[]) throws Exception {
        System.out.println("Hola desde el hilo principal!");

        hilo = new HijoEsperaOtroHijo[2];

        hilo[0] = new HijoEsperaOtroHijo();
        hilo[0].setName("0");
        hilo[0].start();

        hilo[1] = new HijoEsperaOtroHijo();
        hilo[1].setName("1");
        hilo[1].start();

        System.out.println("Fin Principal");
    }
}