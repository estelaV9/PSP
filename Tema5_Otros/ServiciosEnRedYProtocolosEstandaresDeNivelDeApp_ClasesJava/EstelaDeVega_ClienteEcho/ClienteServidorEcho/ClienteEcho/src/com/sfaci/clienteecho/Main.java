package com.sfaci.clienteecho;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {

    public static void main(String args[]) {

        final String host = "localhost";  // SE DEFINI EL HOST, QUE EN ESTE CASO ES LOCALHOST
        final int puerto = 5555;  // SE DEFINI EL PUERTO DE CONEXION AL SERVIDOR

        try {
            // SE CREA UN SOCKET PARA CONECTARSE AL SERVIDOR EN EL HOST Y PUERTO DADOS
            Socket socket = new Socket(host, puerto);

            // SE CREAN LOS FLUJOS DE ENTRADA Y SALIDA PARA LA COMUNICACION
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

            // SE CREA UN FLUJO PARA LEER LOS MENSAJES QUE EL USUARIO ESCRIBE EN EL TECLADO
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

            // SE MUESTRAN LOS MENSAJES DE BIENVENIDA O INSTRUCCIONES ENVIADOS POR EL SERVIDOR
            System.out.println(entrada.readLine());  // MUESTRA EL PRIMER MENSAJE DEL SERVIDOR
            System.out.println(entrada.readLine());  // MUESTRA EL SEGUNDO MENSAJE DEL SERVIDOR

            String mensaje = null;

            // BUCLE PRINCIPAL: EL CLIENTE LEERÁ MENSAJES DESDE EL TECLADO Y LOS ENVIARÁ AL SERVIDOR
            while ((mensaje = teclado.readLine()) != null) {

                // ENVÍA EL MENSAJE AL SERVIDOR
                salida.println(mensaje);

                // SI EL MENSAJE ES UN PUNTO (.) EL CLIENTE SE DESCONECTA Y CIERRE LA CONEXION
                if (mensaje.equals(".")) {
                    System.out.println(entrada.readLine());  // MUESTRA EL MENSAJE DE DESPEDIDA DEL SERVIDOR
                    socket.close();  // CIERRE DE LA CONEXION
                    return;  // TERMINA LA EJECUCION DEL CLIENTE
                }

                // MUESTRA LA RESPUESTA DEL SERVIDOR (EL MENSAJE ECHO)
                System.out.println(entrada.readLine());
            }

        } catch (UnknownHostException uhe) {
            uhe.printStackTrace();  // MANEJA EXCEPCIONES SI EL HOST ES DESCONOCIDO
        } catch (IOException ioe) {
            ioe.printStackTrace();  // MANEJA EXCEPCIONES DE ENTRADA Y SALIDA
        }
    }
}
