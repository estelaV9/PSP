package org.example.ServidorDiccionario.src.servidordiccionario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HiloPeticion implements Runnable{
    Socket conexionEntrante;  // VARIABLE PARA LA CONEXION DE CADA CLIENTE

    /* CONSTRUCTOR QUE RECIBE LA CONEXION Y LA ASIGNA A LA VARIABLE */
    public HiloPeticion(Socket conexion){
        conexionEntrante = conexion;  // ASIGNAMOS LA CONEXION A LA VARIABLE
    }

    @Override
    public void run() {
        BufferedReader entrada;  // DECLARAMOS EL FLUJO DE LECTURA
        PrintWriter salida;  // DECLARAMOS EL FLUJO DE ESCRITURA

        try {
            /* OBTENEMOS LOS FLUJOS DE ENTRADA Y SALIDA A PARTIR DEL SOCKET */
            entrada = Utilidades.getBufferedReader(conexionEntrante);
            salida = Utilidades.getPrintWriter(conexionEntrante);

            /* LEEMOS LAS DOS PALABRAS ENVIADAS POR EL CLIENTE */
            String l1 = entrada.readLine();  // PRIMERA PALABRA
            String l2 = entrada.readLine();  // SEGUNDA PALABRA

            /* COMPARA LAS PALABRAS ALFABETICAMENTE */
            int resultado = l1.compareTo(l2);  // COMPARA LAS DOS PALABRAS

            if (resultado == -1) {  // SI LA PRIMERA PALABRA VA ANTES
                salida.println(l1);  // ENVIA LA PRIMERA PALABRA
                salida.flush();  // FLUSH PARA ASEGURAR QUE SE ENVIA INMEDIATAMENTE
            }

            if (resultado == 0) {  // SI LAS PALABRAS SON IGUALES
                salida.println(l1);  // ENVIA CUALQUIERA DE LAS DOS (SON IGUALES)
                salida.flush();
            }

            if (resultado == 1) {  // SI LA SEGUNDA PALABRA VA ANTES
                salida.println(l2);  // ENVIA LA SEGUNDA PALABRA
                salida.flush();
            }
        } catch (IOException ex) {
            /* CAPTURAMOS EXCEPCIONES EN CASO DE ERROR DE E/S Y LO LOGUEAMOS */
            Logger.getLogger(HiloPeticion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
