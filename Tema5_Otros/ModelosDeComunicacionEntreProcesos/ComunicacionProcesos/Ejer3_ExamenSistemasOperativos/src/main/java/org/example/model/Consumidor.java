package org.example.model;

import static org.example.Main.s1;
import static org.example.Main.s2;

// CLASE PARA EL PROCESO CONSUMIDOR (P2)
public class Consumidor implements Runnable {

    // IMPLEMENTACION DEL METODO RUN QUE SIMULA LA EJECUCION DEL PROCESO CONSUMIDOR
    @Override
    public void run() {
        // BUCLE INFINITO QUE SIMULA EL PROCESO DE CONSUMO
        while (true) {
            // LINES #1: EL CONSUMIDOR BLOQUEA EL SEMAFORO S2
            s2.down(); // DOWN DEL SEMAFORO S2 (ESPERA HASTA QUE EL SEMAFORO TENGA VALOR > 0)
            consume(); // LLAMADA A LA FUNCION QUE SIMULA EL CONSUMO DE UN ITEM
            // LINES #2: EL CONSUMIDOR LIBERA EL SEMAFORO S1
            s1.up(); // UP DEL SEMAFORO S1 (AUMENTA EL VALOR Y DESBLOQUEA AL PRODUCTOR)
        }
    }

    // FUNCION SIMULADA PARA CONSUMIR UN ITEM
    private void consume() {
        System.out.println("Consumidor: Consumiendo un item.");
    }
}
