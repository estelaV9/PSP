package org.example.Utilidades.src.com.utilidades;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

// DEFINICION DE LA CLASE UTILIDADESFICHEROS
public class UtilidadesFicheros {

	// METODO PARA OBTENER UN BUFFEREDREADER PARA LEER UN FICHERO
	public static BufferedReader getBufferedReader(String nombreFichero)
			throws FileNotFoundException {

		// CREACION DEL OBJETO FILEREADER PARA LEER EL ARCHIVO
		FileReader lector = new FileReader(nombreFichero);

		// CREACION DEL BUFFEREDREADER PARA MEJORAR EL RENDIMIENTO DE LECTURA
		BufferedReader bufferedReader = new BufferedReader(lector);

		// DEVUELVE EL BUFFEREDREADER
		return bufferedReader;
	}

	// METODO PARA OBTENER UN PRINTWRITER PARA ESCRIBIR EN UN FICHERO
	public static PrintWriter getPrintWriter(String nombreFichero)
			throws IOException {

		// CREACION DE UN FILEWRITER PARA ESCRIBIR EN EL ARCHIVO
		FileWriter fileWriter = new FileWriter(nombreFichero);

		// CREACION DE UN PRINTWRITER PARA ESCRITURA MAS EFICIENTE
		PrintWriter printWriter = new PrintWriter(fileWriter);

		// DEVUELVE EL PRINTWRITER
		return printWriter;
	} // FIN DE getPrintWriter

	// METODO PARA LEER TODAS LAS LINEAS DE UN FICHERO Y DEVOLVERLAS EN UNA LISTA
	public static ArrayList<String> getLineasFichero(String nombreFichero)
			throws IOException {

		// CREACION DE UNA LISTA PARA ALMACENAR LAS LINEAS DEL ARCHIVO
		ArrayList<String> lineas = new ArrayList<>();

		// OBTENCION DEL BUFFEREDREADER PARA LEER EL ARCHIVO
		BufferedReader bfr = getBufferedReader(nombreFichero);

		// LECTURA DE LA PRIMERA LINEA DEL ARCHIVO
		String linea = bfr.readLine();

		// BUCLE PARA LEER TODAS LAS LINEAS DEL ARCHIVO
		while (linea != null) {

			// SE AGREGA LA LINEA A LA LISTA
			lineas.add(linea);

			// SE LEE LA SIGUIENTE LINEA
			linea = bfr.readLine();
		} // FIN DEL BUCLE QUE LEE LINEAS

		// DEVUELVE LA LISTA DE LINEAS
		return lineas;
	} // FIN DE getLineasFichero

	// METODO PARA SUMAR EL PRIMER VALOR DE CADA FICHERO DADO
	public static long getSuma(String[] listaNombresFichero) {

		// VARIABLE PARA ALMACENAR LA SUMA TOTAL
		long suma = 0;

		// LISTA PARA ALMACENAR LAS LINEAS LEIDAS
		ArrayList<String> lineas;

		// VARIABLE PARA ALMACENAR LA PRIMERA LINEA DEL ARCHIVO
		String lineaCantidad;

		// VARIABLE PARA ALMACENAR EL VALOR NUMERICO LEIDO DEL ARCHIVO
		long cantidad;

		// RECORRE CADA NOMBRE DE FICHERO EN LA LISTA
		for (String nombreFichero : listaNombresFichero) {
			try {

				// RECUPERA TODAS LAS LINEAS DEL ARCHIVO
				lineas = getLineasFichero(nombreFichero);

				// SOLO SE UTILIZA LA PRIMERA LINEA DEL ARCHIVO
				lineaCantidad = lineas.get(0);

				// CONVIERTE LA LINEA LEIDA A UN NUMERO
				cantidad = Long.parseLong(lineaCantidad);

				// SE AGREGA EL VALOR A LA SUMA TOTAL
				suma = suma + cantidad;

			} catch (IOException e) {

				// MENSAJE DE ERROR SI FALLA LA LECTURA DEL ARCHIVO
				System.err.println(
						"Fallo al procesar el fichero " + nombreFichero
				);
			} // FIN DEL BLOQUE CATCH
		} // FIN DEL BUCLE QUE RECORRE LOS NOMBRES DE FICHEROS

		// DEVUELVE LA SUMA TOTAL
		return suma;
	}
}
