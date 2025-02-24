package org.example.SolucionExamenHilos.src.com.ies.examen.mensajes;

import java.util.Comparator;

/* PUEDES IGNORAR ESTA CLASE. SOLO SE USA PARA ORDENAR
 * CADENAS AUTOMATICAMENTE EN UN ArrayList
 */
public class ComparadorCadenas implements Comparator<String> {

	// METODO PARA COMPARAR DOS CADENAS Y DEVOLVER EL RESULTADO
	public int compare(String cad1, String cad2) {
		// SE UTILIZA EL METODO compareTo PARA COMPARAR LAS CADENAS
		return cad1.compareTo(cad2);
	}

}
