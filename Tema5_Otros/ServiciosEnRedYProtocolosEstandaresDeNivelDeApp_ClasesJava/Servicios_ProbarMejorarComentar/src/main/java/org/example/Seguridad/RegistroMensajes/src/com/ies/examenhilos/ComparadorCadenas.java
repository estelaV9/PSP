package org.example.Seguridad.RegistroMensajes.src.com.ies.examenhilos;

import java.util.Comparator;

/* PUEDES IGNORAR ESTA CLASE. SOLO SE USA PARA ORDENAR
 * CADENAS AUTOMATICAMENTE EN UN ARRAYLIST
 */
public class ComparadorCadenas implements Comparator<String> {

	// IMPLEMENTACION DEL METODO COMPARE PARA COMPARAR DOS CADENAS
	public int compare(String cad1, String cad2) {
		// DEVUELVO EL RESULTADO DE LA COMPARACION ALFABETICA DE LAS DOS CADENAS
		return cad1.compareTo(cad2);
	}

}
