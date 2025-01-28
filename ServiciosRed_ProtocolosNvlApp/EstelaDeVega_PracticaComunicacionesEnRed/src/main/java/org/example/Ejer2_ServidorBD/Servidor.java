package org.example.Ejer2_ServidorBD;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Servidor {

    private Map<String, String> empleados = new HashMap<>();

    public void servir() {
        ServerSocket serverSocket;
        final int PUERTO = 9876;
        try {
            serverSocket = new ServerSocket(PUERTO);
            while (true) {
                Socket conexion = serverSocket.accept();  // Aceptar conexiones
                HiloConexion hiloConexion = new HiloConexion(conexion, empleados);
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

