package org.example;

public class MiProceso extends Thread {
    Contador contadorProceso;
    @Override
    public void run() {
        super.run(); // WSE EJECUTA AUTOMATICAMENTE
        // SE PUEDE PONER EL NOMBRE DEL HILO
        System.out.println(Thread.currentThread().getName() + " Soy el proceso creado");
    } // CUANDO CREEMOS UN PROCESO VA A IR A EL METODO RUN


    // THREAD TIENE DISTINTOS CONSTRUCTORES
    // Thread(String name) METE UN NOMBRE AL PROCESO
    public MiProceso (String name, Contador contadorComun){
        super(name); // LLAMAMOS AL OBJETO PADRE
        contadorProceso = contadorComun;
    }
}
