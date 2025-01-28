package org.example.Ejer2_ServidorBD;

import java.io.*;
import java.net.Socket;
import java.util.Map;

public class HiloConexion implements Runnable {

    private BufferedReader bfr;
    private PrintWriter pw;
    private Socket socket;
    private Map<String, String> empleados;

    public HiloConexion(Socket socket, Map<String, String> empleados) {
        this.socket = socket;
        this.empleados = empleados;
    }

    @Override
    public void run() {
        try {
            bfr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(socket.getOutputStream(), true);

            // Leer la operación que desea realizar el cliente
            String operacion = bfr.readLine();

            if ("CREAR".equals(operacion)) {
                // Leer código y nombre del empleado
                String codigo = bfr.readLine();
                String nombre = bfr.readLine();
                empleados.put(codigo, nombre);  // Almacenar empleado
            } else if ("SELECT".equals(operacion)) {
                // Responder con la cantidad de empleados
                pw.println(empleados.size());
                for (Map.Entry<String, String> entry : empleados.entrySet()) {
                    pw.println(entry.getKey());  // Código
                    pw.println(entry.getValue());  // Nombre
                }
            }
        } catch (IOException e) {
            System.out.println("Error al procesar la conexión");
        }
    }
}
