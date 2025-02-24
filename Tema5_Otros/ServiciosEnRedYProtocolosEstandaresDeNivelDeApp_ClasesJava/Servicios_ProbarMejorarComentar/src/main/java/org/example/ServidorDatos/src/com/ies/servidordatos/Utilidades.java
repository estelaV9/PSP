package org.example.ServidorDatos.src.com.ies.servidordatos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Utilidades {

	/* METODO QUE OBTIENE UN FLUJO DE ESCRITURA A PARTIR DE UN SOCKET */
	public static
	PrintWriter getFlujoEscritura
	(Socket s) throws IOException {

		OutputStream os;  // SE DECLARA UN FLUJO DE SALIDA
		os = s.getOutputStream();  // OBTENEMOS EL FLUJO DE SALIDA DEL SOCKET
		PrintWriter pw;  // CREAMOS UN FLUJO DE ESCRITURA (PrintWriter)
		pw = new PrintWriter(os);  // ASOCIAMOS EL FLUJO DE ESCRITURA AL FLUJO DE SALIDA
		return pw;  // RETORNAMOS EL FLUJO DE ESCRITURA
	}

	/* METODO QUE OBTIENE UN FLUJO DE LECTURA A PARTIR DE UN SOCKET */
	public static
	BufferedReader
	getFlujoLectura(Socket s)
			throws IOException {

		InputStream is;  // SE DECLARA UN FLUJO DE ENTRADA
		is = s.getInputStream();  // OBTENEMOS EL FLUJO DE ENTRADA DEL SOCKET
		InputStreamReader isr;  // CREAMOS UN LECTOR DE FLUJO DE ENTRADA
		isr = new InputStreamReader(is);  // ASOCIAMOS EL LECTOR AL FLUJO DE ENTRADA
		BufferedReader bfr;  // CREAMOS UN BUFFER DE LECTURA
		bfr = new BufferedReader(isr);  // ASOCIAMOS EL BUFFER AL LECTOR
		return bfr;  // RETORNAMOS EL FLUJO DE LECTURA
	}
}
