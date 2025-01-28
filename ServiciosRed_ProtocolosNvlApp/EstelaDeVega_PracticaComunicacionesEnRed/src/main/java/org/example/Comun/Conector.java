package org.example.Comun;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Conector {

    public static void main(String[] args) {

        System.out.println("Iniciando...");

        // DEFINIMOS LA DIRECCIÓN IP Y PUERTO DE DESTINO
        String destino = "10.8.0.253";  // IP REMOTA A LA QUE NOS VAMOS A CONECTAR
        int puertoDestino = 80;  // PUERTO REMOTO (COMÚN PARA HTTP)

        // CREAMOS UN OBJETO SOCKET QUE SE UTILIZARÁ PARA LA CONEXIÓN
        Socket socket = new Socket();

        // CREAMOS UN OBJETO InetSocketAddress QUE REPRESENTA LA DIRECCIÓN DESTINO (IP Y PUERTO)
        InetSocketAddress direccion = new InetSocketAddress(destino, puertoDestino);

        try {
            // INTENTAMOS ESTABLECER LA CONEXIÓN A LA DIRECCIÓN REMOTA
            socket.connect(direccion);  // INTENTA CONECTAR AL SERVIDOR REMOTO

            // SI LLEGAMOS AQUÍ, LA CONEXIÓN SE ESTABLECIÓ CON ÉXITO

            // OBTENEMOS LOS FLUJOS DE ENTRADA Y SALIDA DEL SOCKET PARA COMUNICARNOS
            InputStream is = socket.getInputStream();  // FLUJO DE ENTRADA (LEER DATOS)
            OutputStream os = socket.getOutputStream();  // FLUJO DE SALIDA (ENVIAR DATOS)

            // CREACIÓN DE FLUJOS DE CARACTERES PARA MAYOR COMODIDAD EN LA LECTURA Y ESCRITURA
            InputStreamReader isr = new InputStreamReader(is);  // CONVERTIMOS EL FLUJO DE BYTES A CARACTERES
            OutputStreamWriter osw = new OutputStreamWriter(os);  // CONVERTIMOS EL FLUJO DE BYTES A CARACTERES

            // FLUJOS DE LÍNEAS (LEER Y ESCRIBIR LÍNEAS DE TEXTO)
            BufferedReader bReader = new BufferedReader(isr);  // LEE LAS LÍNEAS DE LA RESPUESTA
            PrintWriter pWriter = new PrintWriter(osw);  // ENVÍA LAS PETICIONES DE TEXTO AL SERVIDOR

            // ENVÍO DE UNA PETICIÓN HTTP GET PARA SOLICITAR LA PÁGINA "index.html"
            pWriter.println("GET /index.html");  // ESCRIBE LA PETICIÓN EN EL FLUJO DE SALIDA
            pWriter.flush();  // ASEGURAMOS QUE SE ENVÍE LA PETICIÓN AL SERVIDOR

            String linea;

            // CREAMOS UN ESCRITOR PARA GUARDAR LA RESPUESTA EN UN ARCHIVO LOCAL
            FileWriter escritorArchivo = new FileWriter("resultado.txt");

            // LEEMOS LAS LÍNEAS DE LA RESPUESTA DEL SERVIDOR Y LAS ESCRIBIMOS EN UN ARCHIVO
            while ((linea = bReader.readLine()) != null) {
                escritorArchivo.write(linea);  // ESCRIBIMOS CADA LÍNEA EN EL ARCHIVO "resultado.txt"
            }

            // CERRAMOS TODOS LOS RECURSOS PARA LIBERARLOS
            escritorArchivo.close();
            pWriter.close();
            bReader.close();
            isr.close();
            osw.close();
            is.close();
            os.close();

        } catch (IOException e) {
            // SI OCURRE UNA EXCEPCIÓN, LA CONEXIÓN NO SE ESTABLECIÓ O HUBO UN ERROR EN LA LECTURA/ESCRITURA
            System.out.println(
                    "No se pudo establecer la conexion " +
                            " o hubo un fallo al leer datos."
            );
        } // Fin del catch
    } // Fin del main
} // Fin de la clase Conector