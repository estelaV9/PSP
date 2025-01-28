package org.example.Ejer3_ServidorEco;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public void servir() {
        System.out.println("Servidor activo!");
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
            System.out.println("Error al crear el servidor");
        }
    }

    public static void main(String[] args) {
        Servidor servidor = new Servidor();
        servidor.servir();
    }
}

