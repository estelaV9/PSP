package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloConexion implements Runnable {

    // BUFFER PARA LECTURA DE DATOS
    BufferedReader bfr;

    // PRINTWRITER PARA ESCRITURA DE DATOS
    PrintWriter pw;

    // SOCKET PARA LA CONEXION CON EL CLIENTE
    Socket socket;

    // CONSTRUCTOR QUE RECIBE UN SOCKET Y LO ASIGNA A LA VARIABLE
    public HiloConexion(Socket socket) {
        this.socket = socket;
    }

    // METODO RUN QUE EJECUTA LA LOGICA DEL HILO
    public void run() {
        try {
            /* NUESTRO HILO SE LIMITA A
             * RECIBIR UNA LINEA Y REENVIARLA
             * DE VUELTA AL CLIENTE
             */
            bfr = Utilidades.getFlujoLectura(this.socket); // OBTIENE FLUJO DE LECTURA
            pw = Utilidades.getFlujoEscritura(this.socket); // OBTIENE FLUJO DE ESCRITURA
            String lineaRecibida;
            lineaRecibida = bfr.readLine(); // LEE LA LINEA RECIBIDA DEL CLIENTE
            System.out.print(
                    Thread.currentThread().getName()); // IMPRIME EL NOMBRE DEL HILO
            System.out.println(" recibio:" + lineaRecibida); // IMPRIME EL MENSAJE RECIBIDO
            pw.println(lineaRecibida); // REENVIA EL MENSAJE AL CLIENTE
            pw.flush(); // FUERZA EL ENVIO DE DATOS
        } catch (IOException e) {
            System.out.println("Hubo un fallo al enviar/recibir datos"); // MENSAJE DE ERROR SI FALLA LA COMUNICACION
        }
    }
    // FIN DEL RUN
}