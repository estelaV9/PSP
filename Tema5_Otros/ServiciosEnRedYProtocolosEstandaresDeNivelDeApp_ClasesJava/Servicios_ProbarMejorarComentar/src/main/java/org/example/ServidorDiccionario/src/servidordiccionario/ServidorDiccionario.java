package org.example.ServidorDiccionario.src.servidordiccionario;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorDiccionario {

    // ESTE METODO SE ENCARGA DE CREAR EL SERVIDOR Y ACEPTAR CONEXIONES EN EL PUERTO DADO.
    // RECIBE UN ENTERO 'PUERTO' QUE ESPECIFICA EN QUE PUERTO ESCUCHAR LAS CONEXIONES ENTRANTES.
    public static void servir(int puerto) throws IOException {

        // SE CREA UN OBJETO ServerSocket QUE ESCUCHA CONEXIONES EN EL PUERTO ESPECIFICADO
        ServerSocket socketServidor;
        socketServidor = new ServerSocket(puerto);

        // BUCLE INFINITO PARA QUE EL SERVIDOR SIGA ESCUCHANDO CONEXIONES
        while (true) {

            // ACEPTAMOS UNA CONEXION ENTRANTE (ESTO BLOQUEA EL HILO HASTA QUE UN CLIENTE SE CONECTA)
            Socket conexionEntrante;
            conexionEntrante = socketServidor.accept();

            // CREAMOS UN OBJETO HiloPeticion QUE MANEJARA LA CONEXION CON EL CLIENTE
            HiloPeticion p;
            p = new HiloPeticion(conexionEntrante);

            // CREAMOS UN HILO Y ASIGNAMOS EL OBJETO HiloPeticion COMO SU TAREA
            Thread hilo;
            hilo = new Thread(p);

            // INICIAMOS EL HILO, PERMITIENDO QUE EL SERVIDOR MANEJE MULTIPLES CONEXIONES SIMULTANEAMENTE
            hilo.start();
        }
    }

    // EL METODO MAIN ESTA VACIO, PERO SE PODRIA USAR PARA INICIAR EL SERVIDOR CON UN PUERTO ESPECIFICADO
    public static void main(String[] args) {
        // AQUÍ SE PODRIA INICIAR EL SERVIDOR, POR EJEMPLO:
        // TRY {
        //     SERVIR(9876);
        // } CATCH (IOEXCEPTION E) {
        //     E.PRINTSTACKTRACE();
        // }
    }
}
