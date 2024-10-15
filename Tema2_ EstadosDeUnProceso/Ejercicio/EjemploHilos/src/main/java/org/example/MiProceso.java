package org.example;

public class MiProceso extends Thread {
    @Override
    public void run() {
        super.run(); // WSE EJECUTA AUTOMATICAMENTE
        System.out.println("Soy el proceso creado");
    } // CUANDO CREEMOS UN PROCESO VA A IR A EL METODO RAN
}
