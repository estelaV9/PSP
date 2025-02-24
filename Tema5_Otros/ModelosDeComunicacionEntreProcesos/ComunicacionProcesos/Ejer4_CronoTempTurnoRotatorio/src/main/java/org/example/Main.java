package org.example;

import org.example.model.P1;
import org.example.model.P2;

// CLASE PRINCIPAL PARA SIMULAR LA EJECUCION DE LOS PROCESOS P1 Y P2
public class Main {
    // MENSAJE SIMULADO ENTRE LOS PROCESOS P1 Y P2
    public static String msg = "Mensaje Testigo";

    public static void main(String[] args) {
        // CREACION DE LOS HILOS PARA LOS PROCESOS P1 Y P2
        Thread p1 = new Thread(new P1(), "Proceso P1");
        Thread p2 = new Thread(new P2(), "Proceso P2");

        // INICIO DE LOS HILOS
        p1.start();
        p2.start();
    }
}
/*
consola
P2: Recibiendo mensaje de P1
P1: Enviando mensaje a P2
P2: Enviando mensaje a P1
P2: Recibiendo mensaje de P1
P2: Enviando mensaje a P1
P1: Recibiendo mensaje de P2
P1: Enviando mensaje a P2
P1: Recibiendo mensaje de P2
 */