package org.example.model;

import static org.example.Main.s1;
import static org.example.Main.s2;

public class P1 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 2; i++) {  // EJECUTA EL BUCLE DOS VECES
            s1.down(); // BLOQUEA EL SEMAFORO S1 (DECRECE)
            f1(); // EJECUTA LA FUNCION f1()
            s2.up(); // INCREMENTA EL SEMAFORO S2 Y DESBLOQUEA A P2
        }
    }

    // FUNCION f1 SIMULADA
    private void f1() {
        System.out.println("P1: Ejecutando f1()");
    }
}
