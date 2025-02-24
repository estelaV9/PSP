package org.example.SumaContabilidades.src.com.ies;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.utilidades.UtilidadesFicheros;

public class LanzadorContabilidades {
	public static final String SUFIJO_RESULTADO = ".res";
	public static final String SUFIJO_ERRORES = ".err";
	public static final String RESULTADOS_GLOBALES = "Resultado_global.txt";

	public static void main(String[] args) throws IOException {
		String classpath = args[0];
		String[] ficheros = { "informatica.txt", "gerencia.txt", "contabilidad.txt", "comercio.txt", "rrhh.txt" };

		// LOS NOMBRES DE LOS FICHEROS DE RESULTADOS
		// SE GENERARAN Y LUEGO SE ALMACENARAN AQUI
		String[] ficherosResultado;
		ficherosResultado = new String[ficheros.length];

		/* LANZAMOS LOS PROCESOS */
		ProcessBuilder[] constructores;
		constructores = new ProcessBuilder[ficheros.length];
		for (int i = 0; i < ficheros.length; i++) {
			String fichResultado, fichErrores;
			fichResultado = ficheros[i] + SUFIJO_RESULTADO;
			fichErrores = ficheros[i] + SUFIJO_ERRORES;
			ficherosResultado[i] = fichResultado;
			constructores[i] = new ProcessBuilder();
			constructores[i].command("java", "-cp", classpath, "com.ies.ProcesadorContabilidad", ficheros[i],
					fichResultado);
			// EL FICHERO DE ERRORES SE GENERARA, AUNQUE
			// PUEDE QUE VACIO
			constructores[i].redirectError(new File(fichErrores));
			constructores[i].start();
		}

		// CALCULAMOS LAS SUMAS DE CANTIDADES
		long total = UtilidadesFicheros.getSuma(ficherosResultado);
		// Y LAS ALMACENAMOS
		PrintWriter pw = UtilidadesFicheros.getPrintWriter(RESULTADOS_GLOBALES);
		pw.println(total);
		pw.close();
	}
}
