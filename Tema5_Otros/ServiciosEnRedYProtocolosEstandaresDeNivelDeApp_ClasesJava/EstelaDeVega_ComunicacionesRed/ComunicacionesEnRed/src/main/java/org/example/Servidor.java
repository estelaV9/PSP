package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    // METODO QUE INICIA EL SERVIDOR Y ESCUCHA CONEXIONES
    public void servir() {
        System.out.println("SERVIDOR ACTIVO!");

        // DECLARACION DEL SERVERSOCKET
        ServerSocket serverSocket;

        // PUERTO EN EL QUE ESCUCHA EL SERVIDOR
        final int PUERTO = 9876;

        try {
            // CREA EL SERVERSOCKET EN EL PUERTO ESPECIFICADO
            serverSocket = new ServerSocket(PUERTO);

            // BUCLE INFINITO PARA ACEPTAR CONEXIONES DE CLIENTES
            while (true) {
                // ACEPTA UNA NUEVA CONEXION DE UN CLIENTE
                Socket conexion;
                conexion = serverSocket.accept();

                // CREA UN NUEVO HILO PARA MANEJAR LA CONEXION DEL CLIENTE
                HiloConexion hiloConexion;
                hiloConexion = new HiloConexion(conexion);
                Thread hilo = new Thread(hiloConexion);

                // INICIA EL HILO PARA ATENDER AL CLIENTE
                hilo.start();
            }
        } catch (IOException e) {
            // MANEJO DE ERRORES EN LA CREACION DEL SERVIDOR O LAS CONEXIONES
            System.out.println("ERROR EN CONEXION O AL CREAR LOS HILOS O AL PROCESAR E/S");
        }
    }

    public static void main(String[] args) {
        // CREA UNA INSTANCIA DEL SERVIDOR
        Servidor servidor;
        servidor = new Servidor();

        // INICIA EL SERVIDOR
        servidor.servir();
    }
}