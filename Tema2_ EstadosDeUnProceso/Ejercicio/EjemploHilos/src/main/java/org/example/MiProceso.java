package org.example;

public class MiProceso extends Thread {
    Contador contadorProceso;

    @Override
    public void run() {
        super.run(); // WSE EJECUTA AUTOMATICAMENTE
        // SE PUEDE PONER EL NOMBRE DEL HILO
        System.out.println(Thread.currentThread().getName() + " Soy el proceso creado");

        for(;contadorProceso.getNumero() < 1000;){
            System.out.println("Proceso: " + Thread.currentThread().getName() + " - Contador: " + contadorProceso.getNumero());
            // EL PROCESO 2 PIERDE LA CPU
            /** se supone que se pierde --> PROBLEMA DE CONCURRENCIA**/
            int tmp = contadorProceso.getNumero();
            tmp++;
            contadorProceso.setNumero(tmp);
        } // BUCLE  PARA LANZAR EL NOMBRE Y EL CONTADOR DE PROCESO
    } // CUANDO CREEMOS UN PROCESO VA A IR A EL METODO RUN


    // THREAD TIENE DISTINTOS CONSTRUCTORES
    // Thread(String name) METE UN NOMBRE AL PROCESO
    public MiProceso (String name, Contador contadorComun){
        super(name); // LLAMAMOS AL OBJETO PADRE
        contadorProceso = contadorComun;
    }
}
