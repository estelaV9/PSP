package org.example.Ejer1_SumasVerificacion;

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

            // Leer la cantidad de palabras que va a enviar el cliente
            int numLineas = Integer.parseInt(bfr.readLine());

            // Leer las palabras, calcular la suma de verificación y enviar la respuesta
            for (int i = 0; i < numLineas; i++) {
                String palabra = bfr.readLine();
                int suma = Sumador.sumaSimple(palabra);
                pw.println(suma);  // Enviar la suma de verificación
            }
        } catch (IOException e) {
            System.out.println("Error al procesar la conexión");
        }
    }
}

