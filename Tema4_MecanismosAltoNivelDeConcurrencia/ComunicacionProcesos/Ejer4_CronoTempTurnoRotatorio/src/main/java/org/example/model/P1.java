package org.example.model;

import static org.example.Main.msg;  // ACCEDER AL MENSAJE ENVIADO ENTRE PROCESOS

// CLASE PARA EL PROCESO P1 (PRODUCTOR)
public class P1 implements Runnable {
    // IMPLEMENTACION DEL METODO RUN QUE SIMULA EL EJECUTADO DEL PROCESO P1
    @Override
    public void run() {
        // BUCLE QUE SIMULA EL PROCESO P1
        for (int i = 0; i < 2; i++) {  // ITERACION DEL BUCLE
            // LINES #1: EL PROCESO P1 ENVIAR UN MENSAJE A P2
            send(msg, "P2");  // ENVIAR MENSAJE A P2
            // LINES #2: EL PROCESO P1 RECIBE UN MENSAJE DE P2
            recv(msg, "P2");  // RECIBIR MENSAJE DE P2
        }
    }

    // FUNCION QUE SIMULA EL ENVIO DE UN MENSAJE
    private void send(String msg, String receiver) {
        System.out.println("P1: Enviando mensaje a " + receiver);  // MENSAJE ENVIADO
    }

    // FUNCION QUE SIMULA LA RECEPCION DE UN MENSAJE
    private void recv(String msg, String sender) {
        System.out.println("P1: Recibiendo mensaje de " + sender);  // MENSAJE RECIBIDO
    }
}
