package org.example.Multiproceso_Vocales.src.com.ies;

import java.io.File;
import java.io.IOException;

public class Lanzador {

	public static void main(String[] args) throws IOException, InterruptedException {

		// OBTENEMOS EL NOMBRE DEL FICHERO DE ENTRADA
		String ficheroEntrada;
		ficheroEntrada = args[0];

		// OBTENEMOS EL CLASSPATH DE LAS UTILIDADES
		String classpathUtilidades;
		classpathUtilidades = args[1];

		// OBTENEMOS EL CLASSPATH DEL PROCESADOR DE FICHEROS
		String classpathProcesadorFichero;
		classpathProcesadorFichero = args[2];

		// DEFINIMOS EL ARRAY DE VOCALAS A BUSCAR
		String[] vocales = {"A", "E", "I", "O", "U"};

		// DEFINIMOS EL CLASSPATH COMPLETO PARA EJECUTAR LOS PROCESOS
		String classPath;
		classPath = classpathProcesadorFichero + ":" + classpathUtilidades;

		// IMPRIMIMOS EL CLASSPATH FINAL
		System.out.println("Usando classpath:" + classPath);

		/* SE LANZAN LOS PROCESOS PARA CADA VOCAL EN EL ARRAY "VOCAL" */
		for (int i = 0; i < vocales.length; i++) {

			// CREAMOS EL FICHERO DE ERRORES PARA CADA VOCAL
			String fichErrores = "Errores_" + vocales[i] + ".txt";

			// CREAMOS EL OBJETO ProcessBuilder PARA EJECUTAR CADA PROCESO
			ProcessBuilder pb;
			pb = new ProcessBuilder(
					"java", "-cp", classPath, "com.ies.ProcesadorFichero",
					ficheroEntrada, vocales[i], vocales[i] + ".txt"
			);

			// SI HAY UN ERROR AL EJECUTAR EL PROCESO, SE REDIRIGE A UN FICHERO DE ERRORES
			pb.redirectError(new File(fichErrores));

			// INICIAMOS EL PROCESO
			pb.start();
		}

		/* ESPERAMOS 3 SEGUNDOS PARA DAR TIEMPO A QUE LOS PROCESOS SE EJECUTEN */
		Thread.sleep(3000);

		/* LA RECOGIDA DE RESULTADOS SE DEJA COMO EJERCICIO AL LECTOR ;) */

	}
}
