package org.example.Ejer1_SumasVerificacion;

import java.io.*;
import java.net.Socket;

public class Cliente {

    public void verificarCadenas(BufferedReader bfr, PrintWriter pw) throws IOException {
        pw.println(2);  // Indicar que enviará 2 palabras
        pw.println("ABC");
        pw.println("ZZ");
        pw.flush();  // Enviar los datos al servidor

        // Leer las sumas de verificación recibidas
        String suma1 = bfr.readLine();
        String suma2 = bfr.readLine();
        System.out.println(suma1);  // Imprimir la suma de "ABC"
        System.out.println(suma2);  // Imprimir la suma de "ZZ"
    }

    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        try {
            Socket conexion = new Socket("localhost", 9876);
            BufferedReader bfr = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            PrintWriter pw = new PrintWriter(conexion.getOutputStream(), true);
            cliente.verificarCadenas(bfr, pw);

            // Cerrar los flujos y la conexión
            pw.close();
            bfr.close();
            conexion.close();
        } catch (IOException e) {
            System.out.println("Error en la conexión con el servidor");
        }
    }
}

