package org.example.Seguridad.JavaApplication3.src.com.ies.seguridad;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class PruebasSeguridad {

    // METODO PARA COMPROBAR SI EXISTE UN GESTOR DE SEGURIDAD
    public static boolean hayGestorSeguridad(){
        // OBTENEMOS EL GESTOR DE SEGURIDAD DEL SISTEMA
        SecurityManager gestorSeguridad;
        gestorSeguridad = System.getSecurityManager();

        // SI EL GESTOR ES NULL, NO HAY UN GESTOR DE SEGURIDAD
        if (gestorSeguridad == null) return false;

        // SI LLEGAMOS AQUÍ, ES QUE SI HAY UN GESTOR DE SEGURIDAD
        return true;
    }

    // METODO PARA LEER UN FICHERO QUE CONTENGA SECRETOS
    public static void leerFicheroConSecretos(String ruta)
            throws FileNotFoundException, IOException
    {
        // CREAMOS UN BUFFEREDREADER PARA LEER EL FICHERO
        BufferedReader bfr = new BufferedReader(new FileReader(ruta));

        // LEEMOS LINEA POR LINEA EL FICHERO
        String linea = bfr.readLine();
        while (linea != null){
            // MOSTRAMOS CADA LINEA LEIDA
            System.out.println(linea);

            // LEEMOS LA SIGUIENTE LINEA
            linea = bfr.readLine();
        }
    }

    // METODO PARA CONECTAR A GOOGLE EN EL PUERTO 80
    public static void conectarConGoogle() throws IOException{
        // CREAMOS UN SOCKET NUEVO
        Socket socket = new Socket();

        // DEFINIMOS LA DIRECCION DE GOOGLE EN EL PUERTO 80 (HTTP)
        InetSocketAddress direccion = new InetSocketAddress(
                "www.google.es", 80);

        // NOS CONECTAMOS A LA DIRECCION ESPECIFICADA
        socket.connect(direccion);
    }

    // METODO PRINCIPAL
    public static void main(String[] args) throws IOException{
        // LEEMOS EL FICHERO QUE CONTIENE SECRETOS
        leerFicheroConSecretos("../secretos.txt");

        // ESTABLECEMOS UNA CONEXION CON GOOGLE
        conectarConGoogle();
    }

}
