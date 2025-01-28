package org.example.Ejer1_SumasVerificacion;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    // Método que escucha las conexiones de los clientes
    public void servir() {
        ServerSocket serverSocket;
        final int PUERTO = 9876;
        try {
            serverSocket = new ServerSocket(PUERTO);
            while (true) {
                Socket conexion = serverSocket.accept();  // Aceptar conexiones
                HiloConexion hiloConexion = new HiloConexion(conexion);
                Thread hilo = new Thread(hiloConexion);
                hilo.start();  // Iniciar un hilo para atender al cliente
            }
        } catch (IOException e) {
            System.out.println("Error al crear el servidor o al procesar la conexión");
        }
    }

    public static void main(String[] args) {
        Servidor servidor = new Servidor();
        servidor.servir();
    }
}

