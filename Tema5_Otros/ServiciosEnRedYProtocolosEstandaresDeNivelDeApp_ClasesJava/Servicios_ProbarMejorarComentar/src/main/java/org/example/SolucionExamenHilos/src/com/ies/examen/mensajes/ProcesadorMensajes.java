package org.example.SolucionExamenHilos.src.com.ies.examen.mensajes;

import java.io.IOException;
import java.io.PrintWriter;

public class ProcesadorMensajes {
	private int numMensajes;
	private PrintWriter pw;

	// CONSTRUCTOR QUE INICIALIZA EL FICHERO DE TRAZAS Y LA VARIABLE DE MENSAJES
	public ProcesadorMensajes(String nombreFichero) throws IOException {
		pw = Utilidades.getPrintWriter(nombreFichero);
		numMensajes = 0;
	}

	// METODO SINCRONIZADO PARA ESCRIBIR TRAZAS EN EL FICHERO
	public synchronized void escribirTraza(String msg) {
		// SE ALMACENA EL MENSAJE EN EL FICHERO DE TRAZAS
		pw.println(msg);
		// HILO 1
		// LEER numMensajes
		// INCREMENTAR numMensajes
		// ALMACENAR numMensajes
		numMensajes = numMensajes + 1;
	}

	// METODO PARA OBTENER EL NUMERO ACTUAL DE MENSAJES
	public int getNumMensajes() {
		// HILO 2
		return numMensajes;
	}

	// METODO PARA CERRAR EL FICHERO DE TRAZAS
	public void cerrarFichero() {
		pw.close();
	}
}
