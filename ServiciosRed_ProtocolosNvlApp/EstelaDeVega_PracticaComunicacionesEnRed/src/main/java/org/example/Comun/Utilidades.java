package org.example.Comun;

import java.io.*;
import java.net.Socket;

public class Utilidades {

    /* OBTIENE UN FLUJO DE ESCRITURA A PARTIR DE UN SOCKET */
    public PrintWriter getFlujoEscritura(Socket s) throws IOException {
        OutputStream os = s.getOutputStream();  // OBTIENE EL FLUJO DE SALIDA
        PrintWriter pw = new PrintWriter(os);  // CREAMOS UN PrintWriter PARA ESCRIBIR EN EL FLUJO
        return pw;
    }

    /* OBTIENE UN FLUJO DE LECTURA A PARTIR DE UN SOCKET */
    public BufferedReader getFlujoLectura(Socket s) throws IOException {
        InputStream is = s.getInputStream();  // OBTIENE EL FLUJO DE ENTRADA
        InputStreamReader isr = new InputStreamReader(is);  // CONVIERTE EL FLUJO EN UN InputStreamReader
        BufferedReader bfr = new BufferedReader(isr);  // CREAMOS UN BufferedReader PARA LEER LOS DATOS
        return bfr;
    }
}

