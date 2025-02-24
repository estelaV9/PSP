package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Peticion implements Runnable {

    // SOCKET PARA ATENDER LA CONEXION DEL CLIENTE
    Socket conexionParaAtender;

    // CONSTRUCTOR QUE RECIBE UN SOCKET Y LO ASIGNA A LA VARIABLE
    public Peticion(Socket s) {
        this.conexionParaAtender = s;
    }

    @Override
    public void run() {
        try {
            // OBTIENE EL FLUJO DE ESCRITURA PARA ENVIAR RESPUESTAS AL CLIENTE
            PrintWriter flujoEscritura = Utilidades.getFlujoEscritura(this.conexionParaAtender);

            // OBTIENE EL FLUJO DE LECTURA PARA RECIBIR DATOS DEL CLIENTE
            BufferedReader flujoLectura = Utilidades.getFlujoLectura(conexionParaAtender);

            // LEE LA PRIMERA LINEA QUE CONTIENE EL PROTOCOLO
            String protocolo = flujoLectura.readLine();

            // OBTIENE LA VERSION DEL PROTOCOLO A PARTIR DE LA LINEA LEIDA
            int numVersion = Protocolo.getNumVersion(protocolo);

            // SI LA VERSION DEL PROTOCOLO ES 1, SE PROCESAN DOS LINEAS ADICIONALES
            if (numVersion == 1) {
                // LEE DOS LINEAS DEL CLIENTE
                String linea1 = flujoLectura.readLine();
                String linea2 = flujoLectura.readLine();

                // SE COMPARAN LAS DOS LINEAS PARA DETERMINAR SU ORDEN
                if (linea1.compareTo(linea2) > 0) {
                    // SI LINEA1 ES MAYOR QUE LINEA2, SE ENVIA PRIMERO LINEA2 Y LUEGO LINEA1
                    flujoEscritura.println(linea2);
                    flujoEscritura.println(linea1);
                    flujoEscritura.flush(); // ENVIA LOS DATOS AL CLIENTE
                } else {
                    // SI LINEA1 ES MENOR O IGUAL A LINEA2, SE ENVIA PRIMERO LINEA1 Y LUEGO LINEA2
                    flujoEscritura.println(linea1);
                    flujoEscritura.println(linea2);
                    flujoEscritura.flush(); // ENVIA LOS DATOS AL CLIENTE
                }
            }
        } catch (IOException e) {
            // MENSAJE DE ERROR SI FALLA LA CREACION DE ALGUN FLUJO
            System.out.println("NO SE PUDO CREAR ALGUN FLUJO");
            return;
        }
    }
}
