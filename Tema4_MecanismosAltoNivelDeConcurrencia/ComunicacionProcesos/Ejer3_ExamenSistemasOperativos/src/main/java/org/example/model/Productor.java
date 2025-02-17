package org.example.model;

import static org.example.Main.s1;
import static org.example.Main.s2;

// CLASE PARA EL PROCESO PRODUCTOR (P1)
public class Productor implements Runnable {
    // IMPLEMENTACION DEL METODO RUN QUE SIMULA LA EJECUCION DEL PROCESO PRODUCTOR
    @Override
    public void run() {
        // BUCLE INFINITO QUE SIMULA EL PROCESO DE PRODUCCION
        while (true) {
            // LINES #1: EL PRODUCTOR BLOQUEA EL SEMAFORO S1
            s1.down(); // DOWN DEL SEMAFORO S1
            produce(); // LLAMADA A LA FUNCION QUE SIMULA LA PRODUCCION DE UN ITEM
            // LINES #2: EL PRODUCTOR LIBERA EL SEMAFORO S2
            s2.up(); // UP DEL SEMAFORO S2
        }
    }

    // FUNCION SIMULADA PARA PRODUCIR UN ITEM
    private void produce() {
        System.out.println("Productor: Produciendo un item.");
    }
}
