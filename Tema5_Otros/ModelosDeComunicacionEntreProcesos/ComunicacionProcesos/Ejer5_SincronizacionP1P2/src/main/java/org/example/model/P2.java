package org.example.model;

import static org.example.Main.s1;
import static org.example.Main.s2;

public class P2 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 2; i++) {  // EJECUTA EL BUCLE DOS VECES
            s2.down(); // BLOQUEA EL SEMAFORO S2 (DECRECE)
            f2(); // EJECUTA LA FUNCION f2()
            s1.up(); // INCREMENTA EL SEMAFORO S1 Y DESBLOQUEA A P1
        }
    }

    // FUNCION f2 SIMULADA
    private void f2() {
        System.out.println("P2: Ejecutando f2()");
    }
}

