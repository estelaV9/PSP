package org.example.ServicioSumasVerificacion.src.com.ies.sumasverificacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Utilidades {
	/* METODO QUE OBTIENE UN FLUJO DE ESCRITURA A PARTIR DE UN SOCKET */
	public static PrintWriter getFlujoEscritura(Socket s) throws IOException{
		// OBTENEMOS EL FLUJO DE SALIDA DEL SOCKET
		OutputStream os;
		os = s.getOutputStream();
		// CREAMOS EL PRINTWRITER PARA ESCRIBIR EN EL SOCKET
		PrintWriter pw;
		pw = new PrintWriter(os);
		return pw;
	}

	/* METODO QUE OBTIENE UN FLUJO DE LECTURA A PARTIR DE UN SOCKET */
	public static BufferedReader getFlujoLectura(Socket s) throws IOException{
		// OBTENEMOS EL FLUJO DE ENTRADA DEL SOCKET
		InputStream is;
		is = s.getInputStream();
		// CREAMOS EL INPUTSTREAMREADER PARA LEER LOS DATOS DEL SOCKET
		InputStreamReader isr;
		isr = new InputStreamReader(is);
		// CREAMOS EL BUFFEREDREADER PARA LEER LINEAS DE DATOS
		BufferedReader bfr;
		bfr = new BufferedReader(isr);
		return bfr;
	}
}
