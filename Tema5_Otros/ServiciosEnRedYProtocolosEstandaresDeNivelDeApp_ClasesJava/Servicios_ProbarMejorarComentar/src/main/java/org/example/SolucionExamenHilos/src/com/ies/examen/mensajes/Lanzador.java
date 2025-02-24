package org.example.SolucionExamenHilos.src.com.ies.examen.mensajes;

import java.io.IOException;

public class Lanzador {

	public static void main(String[] args) throws IOException, InterruptedException {
		// DEFINIMOS EL NOMBRE DEL FICHERO DE TRAZAS
		String FICHERO = "trazas.txt";
		// DEFINIMOS EL NUMERO DE HILOS A CREAR
		int NUM_HILOS = 100;
		ProcesadorMensajes procesadorMensajes;
		// INICIALIZAMOS EL PROCESADOR DE MENSAJES CON EL FICHERO DE TRAZAS
		procesadorMensajes = new ProcesadorMensajes(FICHERO);

		// CREAR Y ALMACENAR LOS HILOS
		Thread[] hilos = new Thread[NUM_HILOS];
		for (int i = 0; i < NUM_HILOS; i++) {
			GeneradorMensajes gm;
			// INICIALIZAMOS UN NUEVO GENERADOR DE MENSAJES
			gm = new GeneradorMensajes(procesadorMensajes);
			// CREAMOS UN HILO Y ASIGNAMOS EL GENERADOR COMO EL RANURABLE
			hilos[i] = new Thread(gm);
			// ESTABLECEMOS UN NOMBRE PARA CADA HILO
			hilos[i].setName("Generador " + i);
			// INICIAMOS EL HILO
			hilos[i].start();
		}

		// ESPERAMOS A QUE TODOS LOS HILOS TERMINEN
		try {
			for (int i = 0; i < NUM_HILOS; i++) {
				hilos[i].join();
			}
		} catch (Exception e) {
			// SI SE PRODUCE UNA EXCEPCION, LA MOSTRAMOS
			e.printStackTrace();
		}
		// CERRAMOS EL FICHERO DE TRAZAS DESPUES DE QUE TODOS LOS HILOS TERMINEN
		procesadorMensajes.cerrarFichero();

	}

}
