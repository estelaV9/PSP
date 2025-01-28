package org.example.Comun;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Cliente {

    // MÉTODO QUE ENVÍA DOS CADENAS AL SERVIDOR Y OBTIENE LA RESPUESTA ORDENADA
    public void ordenar(String s1, String s2) throws IOException {
        InetSocketAddress direccion = new InetSocketAddress("10.13.0.20", 9876);
        Socket conexion = new Socket();
        conexion.connect(direccion);
        System.out.println("Conexion establecida");

        try {
            // CREAMOS LOS FLUJOS DE LECTURA Y ESCRITURA
            BufferedReader flujoLectura = Utilidades.getFlujoLectura(conexion);
            PrintWriter flujoEscritura = Utilidades.getFlujoEscritura(conexion);

            // ENVIAMOS EL PROTOCOLO Y LAS CADENAS AL SERVIDOR
            flujoEscritura.println("1");  // VERSIÓN 1 DEL PROTOCOLO
            flujoEscritura.println(s1);
            flujoEscritura.println(s2);
            flujoEscritura.flush();

            // LEEMOS LA RESPUESTA ORDENADA
            String linea1 = flujoLectura.readLine();
            String linea2 = flujoLectura.readLine();
            System.out.println("El servidor devolvió " + linea1 + " y " + linea2);
        } catch (IOException e) {
            System.out.println("Fallo la conexión o los flujos");
        }
    }

    // MÉTODO PRINCIPAL QUE INICIA EL CLIENTE
    public static void main(String[] args) {
        Cliente c = new Cliente();
        try {
            c.ordenar("aaa", "bbb");  // ENVÍA LAS CADENAS PARA ORDENAR
        } catch (IOException e) {
            System.out.println("Fallo la conexión o los flujos");
        }
    }
}

