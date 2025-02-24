package com.sfaci.servidorecho;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String args[]) {

        final int PUERTO = 5555;  // SE DEFINE EL PUERTO EN EL QUE EL SERVIDOR ESCUCHARÁ
        boolean conectado = true;  // VARIABLE PARA CONTROLAR LA CONEXION

        try {
            // SE CREA UN SERVER SOCKET PARA ESCUCHAR EN EL PUERTO 5555
            ServerSocket socketServidor = new ServerSocket(PUERTO);

            // BUCLE PARA ACEPTAR CONEXIONES DE CLIENTES
            while (conectado) {
                // ACEPTA LA CONEXION DE UN CLIENTE
                Socket socketCliente = socketServidor.accept();

                // CREAR UNA NUEVA CONEXION CON EL CLIENTE Y MANEJARLA EN UN HILO SEPARADO
                ConexionCliente conexionCliente = new ConexionCliente(socketCliente);
                conexionCliente.start();  // INICIA EL HILO PARA MANEJAR EL CLIENTE
            }

            // CIERRE DEL SOCKET DEL SERVIDOR (NO SE ALCANZARÁ A MENOS QUE SE PARE EL SERVIDOR)
            socketServidor.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();  // MANEJA LAS EXCEPCIONES DE ENTRADA Y SALIDA
        }
    }
}
