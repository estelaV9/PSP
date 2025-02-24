package org.example.SolucionExamen2016_11_15.src.com.examenes;

import java.io.File;
import java.io.IOException;

public class LanzadorContadoresApariciones {
	private static String EXTENSION_ERRORES=".err";

	public static void main(String[] args) throws IOException{
		// OBTENEMOS LA PALABRA PARA BUSCAR DEL PRIMER ARGUMENTO
		String palabraParaBuscar = args[0];

		// SE INICIAN LOS CONSTRUCTORES DE PROCESOS
		ProcessBuilder[] constructores;
		constructores = new ProcessBuilder[args.length-1];

		// RECORREMOS LOS ARGUMENTOS, EXCLUYENDO EL PRIMERO (LA PALABRA)
		for (int i=1; i<args.length; i++){

			// OBTENEMOS EL NOMBRE DEL FICHERO DONDE BUSCAR
			String ficheroDondeBuscar;
			ficheroDondeBuscar = args[i];

			// OBTENEMOS EL NOMBRE DEL FICHERO DE ERRORES (CON EXTENSION ".ERR")
			String ficheroDeErrores;
			ficheroDeErrores = ficheroDondeBuscar + EXTENSION_ERRORES;

			// PREPARAMOS EL PROCESO PARA CONTAR LAS APARICIONES
			constructores[i-1] = new ProcessBuilder();
			constructores[i-1].command(
					"java", "com.examenes.ContadorAparicionesPalabra",
					palabraParaBuscar, ficheroDondeBuscar);

			// REDIRIGIMOS LOS ERRORES A UN FICHERO CON EXTENSION ".ERR"
			constructores[i-1].redirectError(
					new File(ficheroDeErrores));

			// EJECUTAMOS EL PROCESO
			constructores[i-1].start();
		}
		// AQUÍ SE PODRÍAN PROCESAR LOS FICHEROS DE RESULTADOS
	}
}
