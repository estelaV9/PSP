package org.example;

import org.example.Comun.Peticion;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorOrdenacion {

    // MÉTODO PARA INICIAR EL SERVIDOR Y ESCUCHAR LAS CONEXIONES DE LOS CLIENTES
    public void escuchar() throws IOException {
        ServerSocket socket;
        try {
            socket = new ServerSocket(9876);  // CREAMOS EL SOCKET EN EL PUERTO 9876
        } catch (Exception e) {
            System.out.println("No se pudo arrancar");
            return;
        }

        while (true) {
            System.out.println("Servidor esperando");
            Socket conexionCliente = socket.accept();  // ACEPTAMOS LAS CONEXIONES DE LOS CLIENTES
            System.out.println("Alguien conectó");

            // CREAMOS UNA NUEVA PETICIÓN PARA EL CLIENTE Y CREAMOS UN HILO PARA ATENDERLO
            Peticion p = new Peticion(conexionCliente);
            Thread hiloAsociado = new Thread(p);
            hiloAsociado.start();  // INICIAMOS EL HILO
        }
    }

    // MÉTODO PRINCIPAL QUE INICIA EL SERVIDOR
    public static void main(String[] argumentos) {
        ServidorOrdenacion s = new ServidorOrdenacion();
        try {
            s.escuchar();  // INICIAMOS EL SERVIDOR
        } catch (Exception e) {
            System.out.println("No se pudo arrancar");
            System.out.println(" el cliente o el servidor");
        }
    }
}
