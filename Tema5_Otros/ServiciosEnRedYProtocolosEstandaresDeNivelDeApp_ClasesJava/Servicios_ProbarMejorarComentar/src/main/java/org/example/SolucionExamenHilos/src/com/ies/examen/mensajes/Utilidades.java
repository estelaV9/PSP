package org.example.SolucionExamenHilos.src.com.ies.examen.mensajes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Utilidades {
	/* USADO PARA GENERAR NUMEROS ALEATORIOS */
	private static Random generador = new Random();

	/* FORMATEADOR DE FECHAS PARA CONVERTIRLAS A UNA CADENA COMO "2020-12-09 16:04:28" */
	private static SimpleDateFormat formateadorFechas = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	/**
	 * ESPERA UN TIEMPO AL AZAR ENTRE 0 Y 3000 MILISEGUNDOS
	 */
	public static void esperarTiempoAlAzar() {
		try {
			int msAzar = Utilidades.generador.nextInt(3000);
			Thread.sleep(msAzar);
		} catch (InterruptedException e) {
			// ALGUIEN INTERROMPIO EL HILO
			System.out.println("Se interrumpio la ejecucion del hilo, saliendo...");
			return;
		}
	}

	/**
	 * DADO UN NUMERO COMO 3, LO CONVIERTE A UNA CADENA EN LA QUE
	 * HAYA CEROS POR LA IZQUIERDA HASTA QUE OCUPEN SEIS POSICIONES.
	 * ES DECIR 3 PASA A SER "000003"
	 * @param numero Numero a formatear
	 * @return
	 */
	public static String anadirCeros(int numero) {
		return String.format("%06d", numero);
	}

	/**
	 * DADO UN NOMBRE DE FICHERO DEVUELVE UN OBJETO BufferedReader
	 * PARA PODER LEER LINEAS DE EL
	 * @param nombreFichero Nombre del fichero. Se pueden incluir rutas completas
	 * @return
	 * @throws FileNotFoundException
	 */
	public static BufferedReader getBufferedReader(String nombreFichero) throws FileNotFoundException {
		FileReader fr = new FileReader(nombreFichero);
		BufferedReader bfr = new BufferedReader(fr);
		return bfr;
	}

	/**
	 * DADO UN NOMBRE DE FICHERO DEVUELVE UN OBJETO PrintWriter
	 * PARA PODER ESCRIBIR LINEAS EN EL
	 * @param nombreFichero Nombre del fichero. Se pueden incluir rutas completas
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static PrintWriter getPrintWriter(String nombreFichero) throws IOException {
		boolean abrirFicheroEnModoAnadido = false;

		FileWriter fw = new FileWriter(nombreFichero, abrirFicheroEnModoAnadido);
		PrintWriter pw = new PrintWriter(fw);
		return pw;
	}

	/**
	 * DADO UN NOMBRE DE FICHERO LO MUESTRA EN PANTALLA
	 * @param nombreFichero Nombre del fichero. Se pueden incluir rutas completas
	 * @throws IOException
	 */
	public static void mostrarFicheroEnPantalla(String nombreFichero) throws IOException {
		BufferedReader lector = getBufferedReader(nombreFichero);
		String linea;
		linea = lector.readLine();
		while (linea != null) {
			System.out.println(linea);
			linea = lector.readLine();
		}
	}

	/**
	 * ESCRIBE UN MENSAJE EN UN PrintWriter ASOCIADO A UN FICHERO
	 * @param pw Fichero asociado
	 * @param mensaje Texto a escribir. Irá precedido de la fecha (lo
	 * que será muy útil a la hora de ordenar los eventos que se han
	 * apuntado en el fichero)
	 */
	public static void escribirMensaje(PrintWriter pw, String mensaje) {
		Date instanteActual = new Date();
		String cadenaFecha = formateadorFechas.format(instanteActual);

		pw.println(mensaje + " ---- " + cadenaFecha);
	}

	/**
	 * DADO UN FICHERO EN EL QUE SE HAN APUNTADO MENSAJES, ORDENA
	 * LAS LINEAS POR ORDEN DE FECHA Y ESCRIBE UN NUEVO FICHERO
	 * CON LOS EVENTOS ORDENADOS POR FECHA
	 * @param nombreFichero Nombre del fichero que deseamos ordenar. Se
	 * presupone que en cada línea la fecha va al comienzo
	 * @param nombreFichero Nombre del fichero en el que deseamos que se
	 * almacenen las líneas ordenadas
	 * @throws IOException
	 */
	public static void ordenarLineasFichero(String nombreFichero, String nombreFicheroOrdenado) throws IOException {
		ComparadorCadenas c = new ComparadorCadenas();
		ArrayList<String> lineas = new ArrayList<String>();
		/* LEEMOS TODAS LAS LINEAS DEL FICHERO Y LAS ALMACENAMOS */
		BufferedReader bfr = getBufferedReader(nombreFichero);

		/* CARGAMOS TODAS LAS LINEAS DEL FICHERO EN EL VECTOR LINEAS */
		String linea = bfr.readLine();
		while (linea != null) {
			lineas.add(linea);
			linea = bfr.readLine();
		}
		/* ORDENAMOS LAS LINEAS Y BORRAMOS EL FICHERO */
		lineas.sort(c);
		bfr.close();

		/* Y AHORA BORRAMOS EL FICHERO ESCRIBIENDO ENCIMA LAS LINEAS ORDENADAS */
		PrintWriter pw = getPrintWriter(nombreFicheroOrdenado);
		for (String lineaNueva : lineas) {
			pw.println(lineaNueva);
		}
		pw.close();

	}
}
