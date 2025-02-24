package org.example.MultiProceso1.src.es.ies.multiproceso;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {

	// CONSTANTE PARA EL NUMERO DE PROCESOS QUE SE VAN A LANZAR
	static final int NUM_PROCESOS=4;

	// CONSTANTE PARA EL PREFIJO DE LOS FICHEROS DE RESULTADOS
	static final String PREFIJO_FICHEROS="fich";

	// METODO PARA LANZAR UN PROCESO QUE EJECUTA UN SUMADOR
	public static void lanzarSumador(
			int n1, int n2, String fichResultados) throws IOException {

		// COMANDO QUE SE VA A EJECUTAR
		String comando;
		comando = "es.ies.multiproceso.Sumador";

		// DEFINIMOS EL DIRECTORIO DEL SUMADOR
		File directorioSumador;
		directorioSumador = new File("C:\\Users\\"+
				"ogomez\\workspace\\"+
				"MultiProceso1\\bin\\");

		// FICHERO DE RESULTADOS
		File fichResultado = new File(fichResultados);

		// CONFIGURAMOS EL PROCESO
		ProcessBuilder pb;
		pb = new ProcessBuilder("java",
				comando,
				String.valueOf(n1),
				String.valueOf(n2) );

		// ESTABLECEMOS EL DIRECTORIO DE EJECUCIÓN
		pb.directory(directorioSumador);

		// REDIRECCIONAMOS LA SALIDA DEL PROCESO A UN FICHERO
		pb.redirectOutput(fichResultado);

		// INICIAMOS EL PROCESO
		pb.start();
	}

	// METODO PARA LEER UN FICHERO Y OBTENER EL RESULTADO DE LA SUMA
	public static int getResultadoFichero(
			String nombreFichero) {

		int suma = 0;

		// INTENTAMOS LEER EL FICHERO
		try {
			FileInputStream fichero =
					new FileInputStream(
							nombreFichero);
			InputStreamReader fir =
					new InputStreamReader(
							fichero);
			BufferedReader br = new BufferedReader(fir);

			// LEEMOS LA PRIMERA LINEA DEL FICHERO
			String linea = br.readLine();

			// CONVERTIMOS EL RESULTADO A UN ENTERO
			suma = new Integer(linea);
			return suma;

		} catch (FileNotFoundException e) {
			// EN CASO DE QUE EL FICHERO NO SE ENCUENTRE
			System.out.println(
					"No se pudo abrir "+nombreFichero);

		} catch (IOException e) {
			// EN CASO DE QUE HAYA UN ERROR AL LEER EL FICHERO
			System.out.println(
					"No hay nada en "+nombreFichero);
		}
		return suma;
	}

	// METODO PARA OBTENER LA SUMA TOTAL DE LOS RESULTADOS DE LOS FICHEROS
	public static int getSumaTotal(int numFicheros) {
		int sumaTotal = 0;

		// SUMAMOS LOS RESULTADOS DE CADA FICHERO
		for (int i = 1; i <= NUM_PROCESOS; i++) {
			sumaTotal += getResultadoFichero(
					PREFIJO_FICHEROS + String.valueOf(i));
		}
		return sumaTotal;
	}

	/* METODO PRINCIPAL QUE LLEVA A CABO LA SUMA DE LOS VALORES
	 * COMPRENDIDOS ENTRE LOS PARAMETROS N1 Y N2 DIVIDIENDO EL TRABAJO
	 * EN VARIOS PROCESOS
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		// LEEMOS LOS PARAMETROS DE ENTRADA
		int n1 = Integer.parseInt(args[0]);
		int n2 = Integer.parseInt(args[1]);

		// CALCULAMOS EL "SALTO" PARA DIVIDIR LA SUMA ENTRE LOS PROCESOS
		int salto = (n2 / NUM_PROCESOS) - 1;

		// LANZAMOS LOS PROCESOS DE SUMA
		for (int i = 1; i <= NUM_PROCESOS; i++) {
			System.out.println("n1:" + n1);
			int resultadoSumaConSalto = n1 + salto;
			System.out.println("n2:" + resultadoSumaConSalto);

			// LLAMAMOS A LA FUNCION PARA LANZAR EL PROCESO DE SUMA
			lanzarSumador(n1, n1 + salto,
					PREFIJO_FICHEROS + String.valueOf(i));

			// ACTUALIZAMOS EL VALOR DE N1 PARA EL SIGUIENTE PROCESO
			n1 = n1 + salto + 1;
			System.out.println("Suma lanzada...");
		}

		// ESPERAMOS 5 SEGUNDOS PARA QUE LOS PROCESOS FINALICEN
		Thread.sleep(5000);

		// OBTENEMOS LA SUMA TOTAL DE LOS RESULTADOS
		int sumaTotal = getSumaTotal(NUM_PROCESOS);
		System.out.println("La suma total es:" +
				sumaTotal);
	}
}
