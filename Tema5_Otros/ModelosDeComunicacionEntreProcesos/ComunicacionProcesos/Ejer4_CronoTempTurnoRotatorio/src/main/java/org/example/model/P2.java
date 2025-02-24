package org.example.model;

import static org.example.Main.msg;  // ACCEDER AL MENSAJE ENVIADO ENTRE PROCESOS

// CLASE PARA EL PROCESO P2 (CONSUMIDOR)
public class P2 implements Runnable {
    // IMPLEMENTACION DEL METODO RUN QUE SIMULA EL EJECUTADO DEL PROCESO P2
    @Override
    public void run() {
        // BUCLE QUE SIMULA EL PROCESO P2
        for (int i = 0; i < 2; i++) {  // ITERACION DEL BUCLE
            // LINES #1: EL PROCESO P2 RECIBE UN MENSAJE DE P1
            recv(msg, "P1");  // RECIBIR MENSAJE DE P1
            // LINES #2: EL PROCESO P2 ENVIAR UN MENSAJE A P1
            send(msg, "P1");  // ENVIAR MENSAJE A P1
        }
    }

    // FUNCION QUE SIMULA EL ENVIO DE UN MENSAJE
    private void send(String msg, String receiver) {
        System.out.println("P2: Enviando mensaje a " + receiver);  // MENSAJE ENVIADO
    }

    // FUNCION QUE SIMULA LA RECEPCION DE UN MENSAJE
    private void recv(String msg, String sender) {
        System.out.println("P2: Recibiendo mensaje de " + sender);  // MENSAJE RECIBIDO
    }
}
