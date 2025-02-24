package org.example.SolucionExamen2016_11_15.src.com.examenes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class UtilidadesFicheros {

	// METODO PARA OBTENER UN BufferedReader PARA UN FICHERO
	public static BufferedReader
	getBufferedReader(
			String nombreFichero)
			throws
			FileNotFoundException
	{
		// SE CREA UN LECTOR DE FICHERO
		FileReader lector;
		lector=new FileReader(
				nombreFichero);

		// SE CREA UN BufferedReader PARA LEER EL FICHERO
		BufferedReader bufferedReader;
		bufferedReader=new BufferedReader(
				lector);
		return bufferedReader;
	}

	// METODO PARA OBTENER UN PrintWriter PARA ESCRIBIR EN UN FICHERO
	public static PrintWriter
	getPrintWriter(
			String nombreFichero)
			throws IOException
	{
		// SE CREA UN FileWriter Y UN PrintWriter PARA ESCRIBIR EN EL FICHERO
		PrintWriter printWriter;
		FileWriter fileWriter;
		fileWriter=new FileWriter(nombreFichero);
		printWriter=new PrintWriter(fileWriter);
		return printWriter;
		//Fin de getPrintWriter
	}

	// METODO PARA OBTENER TODAS LAS LINEAS DE UN FICHERO Y DEVOLVERLAS EN UN ArrayList
	public static ArrayList<String> getLineasFichero(String nombreFichero) throws IOException{
		// SE CREA UN ArrayList PARA ALMACENAR LAS LINEAS DEL FICHERO
		ArrayList<String> lineas=new ArrayList<String>();
		BufferedReader bfr=getBufferedReader(nombreFichero);
		// LEEMOS LINEAS DEL FICHERO
		String linea=bfr.readLine();
		while ( linea != null ) {
			// Y LAS AÑADIMOS AL ARRAY
			lineas.add(linea);
			linea=bfr.readLine();
		} //Fin del bucle que lee lineas
		return lineas;
	} //Fin de getLineasFichero


	// METODO PARA OBTENER LA SUMA DE CANTIDADES EN FICHEROS
	public static long getSuma(String[] listaNombresFichero){
		// SE INICIALIZA LA VARIABLE PARA LA SUMA TOTAL
		long suma=0;
		ArrayList<String> lineas;
		String lineaCantidad;
		long cantidad;
		// RECORREMOS LOS FICHEROS
		for (String nombreFichero: listaNombresFichero){
			try {
				// RECUPERAMOS TODAS LAS LINEAS DEL FICHERO
				lineas=getLineasFichero(nombreFichero);
				// PERO SOLO NOS INTERESA LA PRIMERA
				lineaCantidad=lineas.get(0);
				// CONVERTIMOS LA LINEA A NUMERO
				cantidad=Long.parseLong(lineaCantidad);
				// Y SE INCREMENTA LA SUMA TOTAL
				suma=suma+cantidad;
			} catch (IOException e) {
				// SI HAY UN ERROR AL LEER EL FICHERO, MOSTRAMOS EL MENSAJE DE ERROR
				System.err.println(
						"Fallo al procesar el fichero " +
								nombreFichero
				);
				//Fin del catch
			}
			//Fin del for que recorre los nombres de fichero
		}
		// DEVOLVEMOS LA SUMA TOTAL
		return suma;
	}

}
