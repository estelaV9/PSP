package org.example.SumaContabilidades.src.com.ies;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.utilidades.UtilidadesFicheros;

public class ProcesadorContabilidad {
	public static void main(String[] args) throws IOException {
		String nombreFichero = args[0];
		String nombreFicheroResultado = args[1];
		ArrayList<String> cantidades;
		long total = 0;
		try {
			// EXTRAEMOS LAS CANTIDADES
			cantidades = UtilidadesFicheros.getLineasFichero(nombreFichero);
			// Y LAS SUMAMOS UNA POR UNA
			for (String lineaCantidad : cantidades) {
				long cantidad = Long.parseLong(lineaCantidad);
				total = total + cantidad;
			}

			// ALMACENAMOS EL TOTAL EN UN FICHERO
			PrintWriter pw;
			pw = UtilidadesFicheros.getPrintWriter(nombreFicheroResultado);
			pw.println(total);
			pw.close();
		} catch (IOException e) {
			System.err.println("NO SE PUDO PROCESAR EL FICHERO " + nombreFichero);
			e.printStackTrace();
		}
	}
}
