package org.example.ServicioEco.src.com.ies.servidoreco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Utilidades {

	/* OBTIENE UN FLUJO DE ESCRITURA A PARTIR DE UN SOCKET */
	public static
	PrintWriter getFlujoEscritura(Socket s) throws IOException{
		OutputStream os;
		// OBTENEMOS EL FLUJO DE SALIDA DEL SOCKET
		os = s.getOutputStream();
		PrintWriter pw;
		// CREAMOS UN PRINTWRITER PARA ESCRIBIR SOBRE EL FLUJO DE SALIDA
		pw = new PrintWriter(os);
		return pw;
	}

	/* OBTIENE UN FLUJO DE LECTURA A PARTIR DE UN SOCKET */
	public static
	BufferedReader getFlujoLectura(Socket s) throws IOException{
		InputStream is;
		// OBTENEMOS EL FLUJO DE ENTRADA DEL SOCKET
		is = s.getInputStream();
		InputStreamReader isr;
		// CREAMOS UN INPUTSTREAMREADER PARA LEER EL FLUJO DE ENTRADA
		isr = new InputStreamReader(is);
		BufferedReader bfr;
		// CREAMOS UN BUFFEREDREADER PARA LEER LINEAS DE TEXTO
		bfr = new BufferedReader(isr);
		return bfr;
	}
}
