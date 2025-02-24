package org.example.ServidorDiccionario.src.servidordiccionario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClienteDiccionario {

    /* METODO QUE DICE CUAL DE LAS DOS PALABRAS VA ANTES ALFABETICAMENTE */
    public static String cualVaAntes(
            String pal1, String pal2) throws IOException
    {
        String resultado = "";  // INICIALIZAMOS UNA VARIABLE PARA EL RESULTADO
        InetSocketAddress direccionServidor;  // CREAMOS UNA DIRECCION PARA EL SERVIDOR
        direccionServidor = new InetSocketAddress(
                "127.0.0.1", 9876);  // CONFIGURAMOS LA DIRECCION Y PUERTO DEL SERVIDOR
        Socket conexion = new Socket();  // CREAMOS UN SOCKET DE CONEXION
        conexion.connect(direccionServidor);  // ESTABLECEMOS LA CONEXION AL SERVIDOR

        /* OBTENEMOS LOS FLUJOS DE ENTRADA Y SALIDA */
        PrintWriter salida;  // DECLARAMOS EL FLUJO DE ESCRITURA (SALIDA)
        salida = Utilidades.getPrintWriter(conexion);  // OBTENEMOS EL FLUJO DE ESCRITURA DEL SOCKET
        BufferedReader entrada;  // DECLARAMOS EL FLUJO DE LECTURA (ENTRADA)
        entrada = Utilidades.getBufferedReader(conexion);  // OBTENEMOS EL FLUJO DE LECTURA DEL SOCKET

        /* ENVIAMOS LAS PALABRAS AL SERVIDOR Y RECIBIMOS EL RESULTADO */
        salida.println(pal1);  // ENVIAMOS LA PRIMERA PALABRA AL SERVIDOR
        salida.println(pal2);  // ENVIAMOS LA SEGUNDA PALABRA AL SERVIDOR
        salida.flush();  // FLUSH PARA ASEGURAR QUE LOS DATOS SE ENVIAN INMEDIATAMENTE
        resultado = entrada.readLine();  // LEEMOS EL RESULTADO QUE NOS DEVUELVE EL SERVIDOR

        return resultado;  // RETORNAMOS EL RESULTADO
    }

    public static void main(String[] args) {
        // EL METODO MAIN NO TIENE IMPLEMENTACION EN ESTE CASO
    }
}
