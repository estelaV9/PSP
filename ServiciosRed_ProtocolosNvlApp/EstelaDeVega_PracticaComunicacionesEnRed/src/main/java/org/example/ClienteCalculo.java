package org.example;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.io.PrintWriter;

public class ClienteCalculo {

    // MÉTODO QUE CREA UN FLUJO DE LECTURA A PARTIR DE UN InputStream
    public static BufferedReader getFlujo(InputStream is){
        // CREAMOS UN InputStreamReader A PARTIR DEL InputStream
        InputStreamReader isr=
                new InputStreamReader(is);

        // CREAMOS UN BufferedReader PARA LEER DE MANERA EFICIENTE EL FLUJO DE DATOS
        BufferedReader bfr=
                new BufferedReader(isr);

        // DEVOLVEMOS EL BufferedReader PARA SER UTILIZADO POR EL LLAMADOR
        return bfr;
    }

    /**
     * MÉTODO PRINCIPAL QUE SE EJECUTA AL INICIAR EL PROGRAMA
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        // DEFINIMOS LA DIRECCIÓN DEL SERVIDOR AL QUE NOS VAMOS A CONECTAR
        InetSocketAddress direccion=new
                InetSocketAddress("10.13.0.20", 9876);

        // CREAMOS UN OBJETO Socket PARA ESTABLECER UNA CONEXIÓN
        Socket socket=new Socket();

        // NOS CONECTAMOS AL SERVIDOR UTILIZANDO LA DIRECCIÓN Y PUERTO DEFINIDOS
        socket.connect(direccion);

        // OBTENEMOS EL FLUJO DE ENTRADA DEL SOCKET Y LO PASAMOS AL MÉTODO getFlujo
        BufferedReader bfr=
                ClienteCalculo.getFlujo(
                        socket.getInputStream());

        // CREAMOS UN PrintWriter PARA ENVIAR DATOS AL SERVIDOR A TRAVÉS DEL SOCKET
        PrintWriter pw=new
                PrintWriter(socket.getOutputStream());

        // ENVIAMOS LOS DATOS AL SERVIDOR (OPERACIÓN, NÚMEROS)
        pw.print("+\n");  // OPERACIÓN: SUMA
        pw.print("42\n"); // PRIMER NÚMERO
        pw.print("84\n"); // SEGUNDO NÚMERO
        pw.flush(); // NOS ASEGURAMOS DE QUE LOS DATOS SE ENVÍEN AL SERVIDOR

        // LEEMOS LA RESPUESTA DEL SERVIDOR
        String resultado=bfr.readLine();

        // MOSTRAMOS EL RESULTADO RECIBIDO DEL SERVIDOR
        System.out.println
                ("El resultado fue:"+resultado);
    }
}

