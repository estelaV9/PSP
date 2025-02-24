package org.example.ServidorDiccionario.src.servidordiccionario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Utilidades {

    // METODO QUE OBTIENE UN FLUJO DE LECTURA (BufferedReader) A PARTIR DE UN SOCKET
    public static BufferedReader getBufferedReader(Socket conexion) throws IOException {

        // SE OBTIENE EL FLUJO DE ENTRADA DEL SOCKET
        InputStream is = conexion.getInputStream();

        // SE CREA UN INPUTSTREAMREADER PARA LEER LOS DATOS DEL FLUJO DE ENTRADA
        InputStreamReader isr = new InputStreamReader(is);

        // SE CREA UN BUFFEREDREADER PARA LEER LAS LINEAS DE DATOS ENVIADAS POR EL CLIENTE
        BufferedReader bfr = new BufferedReader(isr);

        // SE RETORNA EL BUFFEREDREADER PARA LEER LOS DATOS DEL SOCKET
        return bfr;
    }

    // METODO QUE OBTIENE UN FLUJO DE ESCRITURA (PrintWriter) A PARTIR DE UN SOCKET
    public static PrintWriter getPrintWriter(Socket conexion) throws IOException {

        // SE OBTIENE EL FLUJO DE SALIDA DEL SOCKET
        OutputStream os = conexion.getOutputStream();

        // SE CREA UN OUTPUTSTREAMWRITER PARA ESCRIBIR LOS DATOS EN EL FLUJO DE SALIDA
        OutputStreamWriter osw = new OutputStreamWriter(os);

        // SE CREA UN PRINTWRITER QUE PERMITE ESCRIBIR DATOS EN EL FLUJO DE SALIDA
        PrintWriter pw = new PrintWriter(osw);

        // SE RETORNA EL PRINTWRITER PARA ESCRIBIR LOS DATOS EN EL SOCKET
        return pw;
    }
}
