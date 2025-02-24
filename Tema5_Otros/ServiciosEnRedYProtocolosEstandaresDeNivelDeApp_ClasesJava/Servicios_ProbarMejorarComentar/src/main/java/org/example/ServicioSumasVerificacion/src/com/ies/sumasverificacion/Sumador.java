package org.example.ServicioSumasVerificacion.src.com.ies.sumasverificacion;

public class Sumador {

	// METODO QUE CALCULA LA SUMA DE LOS PUNTOS DE CODIGO DE CADA CARACTER EN UNA CADENA
	public static int sumaSimple(String cad){
		int suma = 0;  // VARIABLE QUE ALMACENA LA SUMA TOTAL
		// BUCLE QUE RECORRE CADA CARACTER DE LA CADENA
		for (int i = 0; i < cad.length(); i++){
			// SE SUMA EL PUNTO DE CODIGO DE CADA CARACTER A LA VARIABLE suma
			suma += cad.codePointAt(i);
		}
		// SE DEVUELVE LA SUMA TOTAL DE LOS PUNTOS DE CODIGO
		return suma;
	}
}
