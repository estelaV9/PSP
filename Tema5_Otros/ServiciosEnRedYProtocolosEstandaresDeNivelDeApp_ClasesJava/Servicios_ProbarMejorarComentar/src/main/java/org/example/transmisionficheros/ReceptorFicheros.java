package org.example.transmisionficheros;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.io.FileOutputStream;
import java.net.ServerSocket;

// DEFINICION DE LA CLASE RECEPTORFICHEROS
public class ReceptorFicheros {

    // METODO PARA RECIBIR UN FICHERO DESDE UN SOCKET Y GUARDARLO EN DISCO
    public static void recibir(String fichero, Socket conexion) throws IOException {

        // OBTENCION DEL FLUJO DE ENTRADA DEL SOCKET
        InputStream is = conexion.getInputStream();

        // DEFINICION DEL TAMANO DEL BUFFER PARA LECTURA
        final int TAM_BUFFER = 8192;

        // CREACION DE UN FLUJO DE SALIDA PARA GUARDAR EL ARCHIVO RECIBIDO
        FileOutputStream fos = new FileOutputStream(fichero);

        // BUFFER PARA ALMACENAR LOS DATOS RECIBIDOS
        byte[] bytesRecibidos = new byte[TAM_BUFFER];

        // MENSAJE INDICANDO QUE HA COMENZADO LA RECEPCION
        System.out.println("Recibiendo...");

        // LECTURA DE LOS PRIMEROS BYTES DESDE EL SOCKET
        int bytes_recibidos = is.read(bytesRecibidos);

        // CREACION DEL INDICADOR DE PROGRESO
        IndicadorProgreso indicador = new IndicadorProgreso();

        // BUCLE PARA LEER Y GUARDAR TODOS LOS DATOS HASTA QUE NO HAYA MAS
        while (bytes_recibidos != -1) {

            // IMPRIME UN CARACTER DEL INDICADOR DE PROGRESO
            System.out.print("\b" + indicador.siguienteCaracter());

            // ESCRIBE LOS BYTES RECIBIDOS EN EL ARCHIVO
            fos.write(bytesRecibidos, 0, bytes_recibidos);

            // LEE LOS SIGUIENTES BYTES DESDE EL SOCKET
            bytes_recibidos = is.read(bytesRecibidos);
        };

        // CIERRA EL FLUJO DE SALIDA DEL ARCHIVO
        fos.close();
    }

    // METODO PRINCIPAL PARA INICIAR EL SERVIDOR QUE RECIBE EL FICHERO
    public static void main(String[] args) throws IOException {

        // CREACION DE UN SERVIDOR DE SOCKETS QUE ESCUCHA EN EL PUERTO 9876
        ServerSocket conexionesEntrantes = new ServerSocket(9876);

        // ESPERA Y ACEPTA UNA CONEXION ENTRANTE
        Socket conexion = conexionesEntrantes.accept();

        // INVOCA AL METODO PARA RECIBIR EL ARCHIVO Y GUARDARLO COMO "fichero.deb"
        recibir("fichero.deb", conexion);

        // CIERRA LA CONEXION DEL SOCKET
        conexion.close();

        // CIERRA EL SERVIDOR DE SOCKETS
        conexionesEntrantes.close();
    }
}
