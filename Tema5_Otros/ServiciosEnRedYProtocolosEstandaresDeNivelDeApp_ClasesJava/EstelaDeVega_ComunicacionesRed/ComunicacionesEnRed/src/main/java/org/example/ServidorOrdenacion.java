package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorOrdenacion {

    // METODO QUE INICIA EL SERVIDOR Y ESCUCHA CONEXIONES
    public void escuchar() throws IOException {
        // DECLARACION DEL SERVERSOCKET
        ServerSocket socket;

        try {
            // INICIALIZA EL SERVIDOR EN EL PUERTO 9876
            socket = new ServerSocket(9876);
        } catch (Exception e) {
            // MENSAJE DE ERROR SI EL SERVIDOR NO PUEDE ARRANCAR
            System.out.println("NO SE PUDO ARRANCAR");
            return;
        }

        // BUCLE INFINITO PARA ACEPTAR CONEXIONES DE CLIENTES
        while (true) {
            System.out.println("SERVIDOR ESPERANDO");

            // ACEPTA UNA NUEVA CONEXION DE UN CLIENTE
            Socket conexionCliente = socket.accept();
            System.out.println("ALGUIEN CONECTO");

            // CREA UNA NUEVA PETICION PARA MANEJAR LA CONEXION DEL CLIENTE
            Peticion p = new Peticion(conexionCliente);

            // ASIGNA UN HILO PARA ATENDER LA PETICION DEL CLIENTE
            Thread hiloAsociado = new Thread(p);
            hiloAsociado.start(); // INICIA EL HILO
        }
    } // FIN DEL METODO ESCUCHAR

    public static void main(String[] argumentos) {
        // CREA UNA INSTANCIA DEL SERVIDOR
        ServidorOrdenacion s = new ServidorOrdenacion();

        try {
            // INICIA EL SERVIDOR Y EMPIEZA A ESCUCHAR CONEXIONES
            s.escuchar();
        } catch (Exception e) {
            // MENSAJE DE ERROR SI FALLA LA EJECUCION DEL SERVIDOR
            System.out.println("NO SE PUDO ARRANCAR");
            System.out.println(" EL CLIENTE O EL SERVIDOR");
        }
    }
}
