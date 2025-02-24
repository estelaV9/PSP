package org.example.ServicioEco.src.com.ies.servidoreco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloConexion implements Runnable{
	BufferedReader bfr;    // FLUJO DE LECTURA PARA LEER DATOS DEL CLIENTE
	PrintWriter pw;        // FLUJO DE ESCRITURA PARA ENVIAR DATOS AL CLIENTE
	Socket socket;         // SOCKET DE CONEXION PARA LA COMUNICACION CON EL CLIENTE

	// CONSTRUCTOR QUE RECIBE EL SOCKET DE CONEXION
	public HiloConexion(Socket socket){
		this.socket = socket;  // INICIALIZA EL SOCKET
	}

	// METODO run() IMPLEMENTADO DE LA INTERFAZ Runnable
	@Override
	public void run() {
		try {
			// SE OBTIENEN LOS FLUJOS DE LECTURA Y ESCRITURA DEL SOCKET
			bfr = Utilidades.getFlujoLectura(this.socket);  // FLUJO DE LECTURA PARA RECIBIR DATOS
			pw = Utilidades.getFlujoEscritura(this.socket); // FLUJO DE ESCRITURA PARA ENVIAR DATOS

			String lineaRecibida;  // VARIABLE PARA ALMACENAR LA LINEA RECIBIDA DEL CLIENTE
			lineaRecibida = bfr.readLine();  // LEER UNA LINEA DE DATOS DEL CLIENTE

			// SE MUESTRA EL NOMBRE DEL HILO Y LA LINEA RECIBIDA
			System.out.print(Thread.currentThread().getName());
			System.out.println(" recibio:" + lineaRecibida);

			// SE ENVIA LA MISMA LINEA DE VUELTA AL CLIENTE (ECO)
			pw.println(lineaRecibida);
			pw.flush();  // SE FORZA EL ENVIO DE LOS DATOS
		} catch (IOException e) {
			// EN CASO DE ERROR SE IMPRIME EL MENSAJE DE FALLO
			System.out.println("Hubo un fallo al enviar/recibir datos");
		}
	} //Fin del run
} //Fin de la clase
