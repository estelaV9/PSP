package org.example.transmisionficheros;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class EmisorFicheros {
    // METODO PARA EMITIR EL FICHERO A TRAVES DE UN SOCKET
    public static void emitir(String nombreFichero,
                              Socket conexionSalida) throws FileNotFoundException, IOException{
        final int TAM_BUFFER=8192;  // TAMANO DEL BUFFER PARA LEER EL FICHERO EN BLOQUES
        byte[] buffer=new byte[TAM_BUFFER]; // CREAR EL BUFFER PARA ALMACENAR LOS DATOS LEIDOS
        IndicadorProgreso indicador=new IndicadorProgreso();  // OBJETO PARA MOSTRAR EL PROGRESO DEL ENVIO
        FileInputStream fis=new FileInputStream(nombreFichero);  // LEER EL FICHERO COMO UN FLUJO DE ENTRADA
        OutputStream flujoSalida=conexionSalida.getOutputStream();  // OBTENER EL FLUJO DE SALIDA DEL SOCKET
        int bytes_leidos=fis.read(buffer);  // LEER EL FICHERO EN EL BUFFER
        System.out.println("Enviando...");  // MENSAJE INICIAL INDICANDO QUE SE ESTA ENVIANDO
        while (bytes_leidos!=-1){  // MIENTRAS NO SE HAYA LEIDO TODO EL FICHERO
            System.out.print("\b"+indicador.siguienteCaracter());  // MOSTRAR EL PROGRESO
            flujoSalida.write(buffer, 0, bytes_leidos);  // ENVIAR LOS DATOS LEIDOS AL SOCKET
            flujoSalida.flush();  // VACiar EL FLUJO DE SALIDA
            bytes_leidos=fis.read(buffer);  // LEER EL SIGUIENTE BLOQUE DEL FICHERO
        }
    }

    // METODO PRINCIPAL PARA INICIAR EL ENVIO DEL FICHERO
    public static void main(String[] args) throws IOException{
        String destinatario="127.0.0.1";  // DIRECCION IP DEL DESTINATARIO (LOCALHOST)
        int puerto=9876;  // PUERTO DE LA CONEXION
        String fichero="/home/usuario/repos/linux-tools-common_4.8.0-59.64-oscar_all.deb";  // RUTA DEL FICHERO A ENVIAR
        InetSocketAddress inetAddress=new InetSocketAddress(
                destinatario, puerto);  // CREAR LA DIRECCION DEL SOCKET (IP Y PUERTO)
        Socket conexion=new Socket();  // CREAR UN NUEVO SOCKET
        conexion.connect(inetAddress);  // CONECTAR AL SOCKET
        emitir(fichero, conexion);  // LLAMAR AL METODO PARA ENVIAR EL FICHERO
        conexion.close();  // CERRAR LA CONEXION DEL SOCKET
    }
}
