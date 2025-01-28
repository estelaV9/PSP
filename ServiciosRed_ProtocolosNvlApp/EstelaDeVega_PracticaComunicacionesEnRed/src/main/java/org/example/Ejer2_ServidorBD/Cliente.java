package org.example.Ejer2_ServidorBD;

import java.io.*;
import java.net.Socket;

public class Cliente {

    public void crearEmpleado(PrintWriter pw, String codigo, String nombre) throws IOException {
        pw.println("CREAR");
        pw.println(codigo);
        pw.println(nombre);
        pw.flush();
    }

    public void consultarEmpleados(BufferedReader bfr) throws IOException {
        pw.println("SELECT");
        pw.flush();
        int numEmpleados = Integer.parseInt(bfr.readLine());  // Leer la cantidad de empleados
        for (int i = 0; i < numEmpleados; i++) {
            System.out.println("Código: " + bfr.readLine() + ", Nombre: " + bfr.readLine());
        }
    }

    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        try {
            Socket conexion = new Socket("localhost", 9876);
            BufferedReader bfr = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            PrintWriter pw = new PrintWriter(conexion.getOutputStream(), true);

            // Crear algunos empleados
            cliente.crearEmpleado(pw, "1", "Juan Pérez");
            cliente.crearEmpleado(pw, "2", "Ana López");

            // Consultar todos los empleados
            cliente.consultarEmpleados(bfr);

            // Cerrar los flujos y la conexión
            pw.close();
            bfr.close();
            conexion.close();
        } catch (IOException e) {
            System.out.println("Error en la conexión con el servidor");
        }
    }
}

