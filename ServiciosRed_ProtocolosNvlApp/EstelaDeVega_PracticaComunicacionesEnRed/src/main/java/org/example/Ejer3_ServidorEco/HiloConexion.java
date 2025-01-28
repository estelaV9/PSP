package org.example.Ejer3_ServidorEco;

import java.io.*;
import java.net.Socket;

public class HiloConexion implements Runnable {

    private BufferedReader bfr;
    private PrintWriter pw;
    private Socket socket;

    public HiloConexion(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            bfr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(socket.getOutputStream(), true);

            // Leer la línea enviada por el cliente
            String lineaRecibida = bfr.readLine();
            System.out.println(Thread.currentThread().getName() + " recibió: " + lineaRecibida);

            // Devolver la misma línea al cliente (eco)
            pw.println(lineaRecibida);
            pw.flush();
        } catch (IOException e) {
            System.out.println("Error al procesar la conexión");
        }
    }
}

