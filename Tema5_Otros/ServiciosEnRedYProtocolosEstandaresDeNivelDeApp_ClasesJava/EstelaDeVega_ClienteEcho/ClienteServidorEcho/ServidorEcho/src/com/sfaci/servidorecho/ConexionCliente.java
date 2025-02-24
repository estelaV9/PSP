package com.sfaci.servidorecho;

import java.io.*;
import java.net.Socket;

public class ConexionCliente extends Thread {

    private Socket socketCliente;
    private PrintWriter salida;
    private BufferedReader entrada;

    // CONSTRUCTOR QUE INICIALIZA LOS FLUJOS DE COMUNICACION CON EL CLIENTE
    public ConexionCliente(Socket socketCliente) throws IOException {
        this.socketCliente = socketCliente;

        // INICIALIZA LOS FLUJOS DE ENTRADA Y SALIDA PARA LA CONEXION
        salida = new PrintWriter(socketCliente.getOutputStream(), true);
        entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
    }

    // METODO QUE SE EJECUTA AL INICIAR EL HILO
    @Override
    public void run() {

        // ENVÍA UN MENSAJE DE BIENVENIDA AL CLIENTE
        salida.println("Bienvenido a mi servidor de echo");
        salida.println("Para salir enviame un '.'");

        try {
            String mensaje = null;

            // BUCLE PRINCIPAL: EL SERVIDOR LEERÁ MENSAJES DEL CLIENTE Y LOS ENVIARÁ DE VUELTA (ECHO)
            while ((mensaje = entrada.readLine()) != null) {

                // SI EL CLIENTE ENVIÓ UN PUNTO (.), SE CERRARÁ LA CONEXION
                if (mensaje.equals(".")) {
                    salida.println("Bye");  // MUESTRA MENSAJE DE DESPEDIDA
                    socketCliente.close();  // CIERRE LA CONEXION
                    return;  // TERMINA EL HILO
                }

                // ENVÍA DE VUELTA EL MENSAJE DEL CLIENTE (ECHO)
                salida.println(mensaje);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();  // MANEJA EXCEPCIONES DE ENTRADA Y SALIDA
        }
    }
}
