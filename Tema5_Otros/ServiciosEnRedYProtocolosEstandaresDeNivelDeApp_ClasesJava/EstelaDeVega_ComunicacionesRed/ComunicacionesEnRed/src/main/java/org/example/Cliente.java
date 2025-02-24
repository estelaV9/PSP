package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Random;

public class Cliente implements Runnable {

    // SOCKET PARA ESTABLECER CONEXION CON EL SERVIDOR
    Socket conexion;

    // GENERADOR DE NUMEROS ALEATORIOS
    Random generador;

    // BUFFER PARA LECTURA DE DATOS
    BufferedReader bfr;

    // PRINTWRITER PARA ESCRITURA DE DATOS
    PrintWriter pw;

    // ARREGLO DE PALABRAS QUE SE USARAN PARA COMUNICACION
    String[] palabras = { "Hola", "mundo", "Java", "hilo" };

    // IDENTIFICADOR DEL HILO
    int numHilo;

    /* ESTA VARIABLE SE PUEDE COMPROBAR POR
     * PARTE DEL LANZADOR PARA VER SI HUBO UN FALLO
     */
    boolean algoFallo = false;

    // CONSTRUCTOR DE LA CLASE CLIENTE
    public Cliente() {
        generador = new Random(); // INICIALIZA GENERADOR DE NUMEROS ALEATORIOS
        InetSocketAddress direccion; // DIRECCION DEL SERVIDOR
        direccion = new InetSocketAddress("localhost", 9876);
        Socket conexion;
        conexion = new Socket(); // INICIALIZA SOCKET
        try {
            conexion.connect(direccion); // ESTABLECE CONEXION CON EL SERVIDOR
            bfr = Utilidades.getFlujoLectura(conexion); // OBTIENE FLUJO DE LECTURA
            pw = Utilidades.getFlujoEscritura(conexion); // OBTIENE FLUJO DE ESCRITURA
        } catch (IOException e) {
            algoFallo = true; // INDICA QUE HUBO UN ERROR AL CONECTAR
        }
        // FIN DEL CATCH
    }

    // METODO PARA OBTENER EL NUMERO DEL HILO
    public int getNumHilo() {
        return numHilo;
    }

    // METODO PARA ASIGNAR UN NUMERO AL HILO
    public void setNumHilo(int numHilo) {
        this.numHilo = numHilo;
    }

    // METODO QUE VERIFICA SI EL SERVIDOR FUNCIONA
    public boolean servidorFunciona() {
        /* ELIGE UNA PALABRA AL AZAR */
        String palabra = palabras[generador.nextInt(palabras.length)];
        String eco;
        try {
            /* SI NO SE PUEDE OBTENER UN FLUJO
             * DE LECTURA O ESCRITURA CON EL SERVIDOR,
             * SIGNIFICA QUE ESTA COLAPSADO
             */
            if ((bfr == null) || (pw == null)) {
                /* INDICA QUE HUBO UN FALLO*/
                algoFallo = true;
                /* Y DEVUELVE FALSO */
                return false;
            }
            pw.println(palabra); // ENVIA LA PALABRA AL SERVIDOR
            pw.flush(); // FUERZA EL ENVIO DE DATOS
            eco = bfr.readLine(); // LEE LA RESPUESTA DEL SERVIDOR
            if (eco.equals(palabra)) {
                System.out.println("Hilo " + numHilo + " recibio bien:" + eco); // IMPRIME CONFIRMACION DE RECEPCION
                return true;
            }
            // FIN DEL IF
        } catch (IOException e) {
            return false;
        }
        /* SI SE LLEGA A ESTE PUNTO SIGNIFICA QUE
         * LA PALABRA DEVUELTA NO FUE LA MISMA QUE SE ENVIO,
         * POR LO QUE EL SERVIDOR FALLA
         */
        return false;
    }

    @Override
    public void run() {
        if (!servidorFunciona()) {
            /* IMPRIME MENSAJE DE ERROR Y CAMBIA VARIABLE DE FALLO */
            System.out.println("Fallo en el hilo " + numHilo);
            algoFallo = true;
        }
    }

    // FIN DEL RUN

    // METODO QUE INDICA SI HUBO UN FALLO
    public boolean huboFallo() {
        return algoFallo;
    }
}