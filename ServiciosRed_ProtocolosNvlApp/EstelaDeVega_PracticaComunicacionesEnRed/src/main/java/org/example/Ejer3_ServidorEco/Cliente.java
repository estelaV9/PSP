package org.example.Ejer3_ServidorEco;

import java.io.*;
import java.net.Socket;

public class Cliente implements Runnable {

    private Socket conexion;
    private BufferedReader bfr;
    private PrintWriter pw;
    private String[] palabras = {"Hola", "Mundo", "Java", "Hilo"};

    @Override
    public void run() {
        try {
            conexion = new Socket("localhost", 9876);
            bfr = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            pw = new PrintWriter(conexion.getOutputStream(), true);

            // Enviar una palabra aleatoria
            String palabra = palabras[(int)(Math.random() * palabras.length)];
            pw.println(palabra);
            pw.flush();

            // Leer la respuesta del servidor (eco)
            String eco = bfr.readLine();
            System.out.println(Thread.currentThread().getName() + " recibió: " + eco);

            conexion.close();
        } catch (IOException e) {
            System.out.println("Error en el cliente");
        }
    }

    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        Thread hilo = new Thread(cliente);
        hilo.start();
    }
}

