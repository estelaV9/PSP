package org.example.PruebaSockets.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class DescargadorHTML {

	public static void main(String[] args) {

		// PARAMETROS DE CONEXION: PUERTO Y DIRECCION DEL SERVIDOR
		int puerto=80; // PUERTO HTTP
		String direccion="www.google.es"; // DIRECCION DEL SERVIDOR

		// INICIALIZACION DE LOS OBJETOS NECESARIOS PARA LA CONEXION
		Socket socket;
		InetSocketAddress direccionRed;
		direccionRed=new InetSocketAddress(
				direccion, puerto); // DEFINO LA DIRECCION Y EL PUERTO DE LA CONEXION
		socket=new Socket(); // CREO UNA NUEVA INSTANCIA DE SOCKET

		try {
			// ESTABLEZCO LA CONEXION AL SERVIDOR
			socket.connect(direccionRed);

			// OBTENGO LOS FLUJOS DE ENTRADA Y SALIDA DEL SOCKET
			OutputStream os;
			InputStream is;
			os=socket.getOutputStream(); // FLUJO DE SALIDA
			is=socket.getInputStream(); // FLUJO DE ENTRADA

			// CONVIERTO EL FLUJO DE ENTRADA EN UN LECTOR DE CARACTERES
			InputStreamReader isr;
			isr=new InputStreamReader(is);

			// CONVIERTO EL FLUJO DE SALIDA EN UN ESCRITOR DE CARACTERES
			OutputStreamWriter osw;
			osw=new OutputStreamWriter(os);

			// CREO UN BUFFEREDREADER PARA LEER LAS LINEAS DE LA RESPUESTA
			BufferedReader bfr;
			bfr=new BufferedReader(isr);

			// CREO UN PRINTWRITER PARA ENVIAR LAS PETICIONES AL SERVIDOR
			PrintWriter pw;
			pw=new PrintWriter (osw);

			// ENVIO UNA PETICION HTTP "GET" AL SERVIDOR PARA OBTENER EL ARCHIVO HTML
			pw.println("GET /index.html"); // SE SOLICITA EL ARCHIVO "index.html"
			pw.flush(); // ME ASEGURO DE QUE SE ENVIe LA PETICION INMEDIATAMENTE

			// LEYO LA RESPUESTA DEL SERVIDOR Y LA IMPRIMO EN CONSOLE
			String linea;
			while ( (linea=bfr.readLine())!=null){
				System.out.println(linea); // IMPRIMO CADA LINEA DEL ARCHIVO HTML RECIBIDO
			}

		} catch (IOException e) {
			// SE PRODUJO UN ERROR EN LA CONEXION O LA COMUNICACION
			e.printStackTrace();
		}
	}
}