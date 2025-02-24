package org.example.SolucionExamen2016_11_15.src.com.examenes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ContadorAparicionesPalabra {
	private static String EXTENSION_RESULTADOS = ".res";

	/* DADA UNA SUBCADENA, NOS DICE CUANTAS VECES APARECE LA SUBCADENA
	 * EN DICHA CADENA. USAREMOS EL METODO STRING.INDEXOF PARA
	 * IR BUSCANDO TODAS LAS APARICIONES DE LA SUBCADENA
	 */
	public static long contarApariciones(String cadena, String subcadena){
		int posBusqueda = 0;
		int posAparicion;
		long apariciones = 0;
		posBusqueda = 0;
		while (posBusqueda < cadena.length()){
			posAparicion = cadena.indexOf(subcadena, posBusqueda);
			if (posAparicion != -1){
				apariciones++;
				posBusqueda = posAparicion + 1;
			}
			else {
				posBusqueda++;
			}
		}
		return apariciones;
	}

	/* DADOS UNA PALABRA (ARGUMENTO 0)
	 * Y UN FICHERO (ARGUMENTO 1) SE
	 * CONTABILIZA EL TOTAL DE APARICIONES
	 * DE ESA PALABRA EN EL FICHERO Y SE ALMACENA
	 * EN UN FICHERO CON EXTENSION ".RES". OBSERVAR
	 * QUE LA EXTENSION SE ANADE AL FICHERO, EL CUAL
	 * PUEDE ACABAR TENIENDO 2 EXTENSIONES (COMO
	 * POR EJEMPLO "FICHERO1.TXT.RES")
	 */
	public static void main(String[] args) throws IOException {
		long aparicionesDeLaPalabra = 0;
		String palabraParaBuscar = args[0];
		String nombreFichero = args[1];

		// RECUPERAMOS LAS LINEAS DEL FICHERO
		ArrayList<String> lineas;
		lineas = UtilidadesFicheros.getLineasFichero(nombreFichero);

		// VAMOS RECORRIENDO LAS LINEAS...
		for (String linea : lineas) {

			// Y SI ENCONTRAMOS LA PALABRA...
			if (linea.contains(palabraParaBuscar)) {
				// ..INCREMENTAMOS EL CONTADOR
				aparicionesDeLaPalabra++;
			}
			// FIN DEL FOR QUE RECORRE LAS LINEAS
		}

		// YA TENEMOS LA CANTIDAD DE APARICIONES
		// ASI QUE ALMACENAMOS LOS RESULTADOS
		PrintWriter pw;
		pw = UtilidadesFicheros.getPrintWriter(
				nombreFichero + EXTENSION_RESULTADOS);

		pw.println(nombreFichero + ":" +
				palabraParaBuscar + ":" +
				aparicionesDeLaPalabra);
		pw.close();
	}

}
