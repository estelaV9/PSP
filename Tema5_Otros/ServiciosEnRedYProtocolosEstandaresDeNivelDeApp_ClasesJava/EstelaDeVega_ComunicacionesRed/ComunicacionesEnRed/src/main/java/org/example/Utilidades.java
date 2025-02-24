package org.example;

import java.io.*;
import java.net.Socket;

public class Utilidades {

    // METODO QUE OBTIENE UN FLUJO DE ESCRITURA A PARTIR DE UN SOCKET
    public static PrintWriter getFlujoEscritura(Socket s) throws IOException {
        OutputStream os = s.getOutputStream(); // OBTIENE EL FLUJO DE SALIDA DEL SOCKET
        PrintWriter pw = new PrintWriter(os); // CREA UN PRINTWRITER PARA ESCRIBIR DATOS
        return pw; // DEVUELVE EL FLUJO DE ESCRITURA
    }

    // METODO QUE OBTIENE UN FLUJO DE LECTURA A PARTIR DE UN SOCKET
    public static BufferedReader getFlujoLectura(Socket s) throws IOException {
        InputStream is = s.getInputStream(); // OBTIENE EL FLUJO DE ENTRADA DEL SOCKET
        InputStreamReader isr = new InputStreamReader(is); // CONVIERTE EL FLUJO ENTRANTE A UN READER
        BufferedReader bfr = new BufferedReader(isr); // CREA UN BUFFEREDREADER PARA LECTURA EFICIENTE
        return bfr; // DEVUELVE EL FLUJO DE LECTURA
    }
}