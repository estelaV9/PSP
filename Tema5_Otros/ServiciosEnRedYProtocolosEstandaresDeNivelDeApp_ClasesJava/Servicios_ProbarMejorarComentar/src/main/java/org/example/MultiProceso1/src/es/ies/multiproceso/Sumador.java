package org.example.MultiProceso1.src.es.ies.multiproceso;

public class Sumador {

	/**
	 * Suma todos los valores incluidos entre dos valores
	 * @param n1 Limite 1
	 * @param n2 Limite 2
	 * @return La suma de dichos valores
	 */

	// METODO QUE SUMA TODOS LOS VALORES DESDE N1 HASTA N2
	public static int sumar(int n1, int n2) {
		int suma = 0;

		// SI N1 ES MAYOR QUE N2, LOS INTERCAMBIAMOS PARA ASEGURARNOS DE QUE N1 SEA EL MENOR
		if (n1 > n2) {
			int aux = n1;
			n1 = n2;
			n2 = aux;
		}

		// SUMAMOS LOS VALORES ENTRE N1 Y N2
		for (int i = n1; i <= n2; i++) {
			suma = suma + i;
		}

		// DEVOLVEMOS EL RESULTADO DE LA SUMA
		return suma;
	}

	// METODO PRINCIPAL QUE RECIBE DOS PARAMETROS Y SUMA LOS VALORES ENTRE ELLOS
	public static void main(String[] args) {
		// OBTENEMOS LOS PARAMETROS DE ENTRADA
		int n1 = Integer.parseInt(args[0]);
		int n2 = Integer.parseInt(args[1]);

		// LLAMAMOS AL METODO SUMAR Y OBTENEMOS EL RESULTADO
		int suma = sumar(n1, n2);

		// IMPRIMIMOS EL RESULTADO DE LA SUMA
		System.out.println(suma);

		// VACÍAMOS EL BUFFER DE SALIDA
		System.out.flush();
	}
}
