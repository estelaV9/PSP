package org.example.ServidorDatos.src.com.ies.servidordatos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloConexion implements Runnable{

	// DECLARACION DE LOS FLUJOS DE ENTRADA Y SALIDA
	BufferedReader bfr;
	PrintWriter pw;
	Socket socket;

	/* CONSTRUCTOR DE LA CLASE HILO CONEXION
       ESTE CONSTRUCTOR RECIBE UN SOCKET Y LO ASOCIA AL HILO PARA SU MANEJO */
	public HiloConexion(Socket socket){
		this.socket = socket;  // INICIALIZA EL SOCKET PASADO COMO PARAMETRO
	}

	@Override
	public void run() {
		// EL METODO RUN DEBE IMPLEMENTAR LA LOGICA DE MANEJO DE CONEXIONES

		// TODO AUTO-GENERADO: ESTE METODO SE DEBE ENCARGAR DE LA LOGICA DE LA CONEXION
		// POR EJEMPLO: OBTENER LOS FLUJOS DE LECTURA Y ESCRITURA, LEER LOS DATOS DEL CLIENTE
		// Y ENVIAR RESPUESTAS APROPIADAS.

	}

}
