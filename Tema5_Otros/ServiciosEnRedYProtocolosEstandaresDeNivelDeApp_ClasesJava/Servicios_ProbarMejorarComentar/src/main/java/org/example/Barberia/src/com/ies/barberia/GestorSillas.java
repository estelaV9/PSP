package org.example.Barberia.src.com.ies.barberia;

public class GestorSillas {

	// SE DECLARA LA VARIABLE QUE ALMACENARÁ EL NÚMERO DE SILLAS EN LA BARBERÍA
	private int numSillas;

	// ARREGLO QUE INDICA SI UNA SILLA ESTÁ OCUPADA (true = OCUPADA, false = LIBRE)
	private boolean sillaOcupada[];

	// ARREGLO QUE INDICA SI UNA SILLA HA SIDO ATENDIDA (true = ATENDIDA, false = NO ATENDIDA)
	private boolean sillaAtendida[];

	// CONSTRUCTOR DE LA CLASE GESTORSILLAS QUE INICIALIZA LOS ARREGLOS SEGÚN EL NÚMERO DE SILLAS
	GestorSillas(int num){
		numSillas = num;  // SE INICIALIZA EL NÚMERO DE SILLAS
		sillaOcupada = new boolean[num];  // SE INICIALIZA EL ARREGLO DE SILLAS OCUPADAS
		sillaAtendida = new boolean[num];  // SE INICIALIZA EL ARREGLO DE SILLAS ATENDIDAS

		// SE INICIALIZAN LOS ARREGLOS DE LAS SILLAS COMO LIBRES Y NO ATENDIDAS
		for (int i = 0; i < numSillas; i++){
			sillaOcupada[i] = false;  // TODAS LAS SILLAS INICIAN COMO LIBRES
			sillaAtendida[i] = false;  // TODAS LAS SILLAS INICIAN COMO NO ATENDIDAS
		}
	}

	// METODO QUE DEVUELVE EL ÍNDICE DE UNA SILLA LIBRE (SI NO HAY, DEVUELVE -1)
	public synchronized int getSillaLibre(){
		// SE RECORREN TODAS LAS SILLAS PARA ENCONTRAR UNA LIBRE
		for (int i = 0; i < numSillas; i++){
			if (sillaOcupada[i] == false){  // SI LA SILLA NO ESTÁ OCUPADA
				sillaOcupada[i] = true;  // SE MARCA LA SILLA COMO OCUPADA
				return i;  // SE DEVUELVE EL ÍNDICE DE LA SILLA LIBRE
			}
		}
		return -1;  // SI NO HAY SILLAS LIBRES, SE DEVUELVE -1
	}

	// METODO QUE DEVUELVE TRUE SI EL AFEITADO ESTÁ COMPLETO PARA UNA SILLA DADA
	public synchronized boolean afeitadoCompleto(int pos){
		if (sillaAtendida[pos] == false){  // SI LA SILLA NO HA SIDO ATENDIDA
			return true;  // DEVUELVE TRUE, INDICANDO QUE EL AFEITADO ESTÁ COMPLETO
		}
		return false;  // SI YA FUE ATENDIDA, DEVUELVE FALSE
	}

	// METODO PARA LIBERAR UNA SILLA, MARCANDOLA COMO LIBRE Y NO ATENDIDA
	public void liberarSilla(int pos){
		sillaOcupada[pos] = false;  // SE MARCA LA SILLA COMO LIBRE
		sillaAtendida[pos] = false;  // SE MARCA LA SILLA COMO NO ATENDIDA
	}

	// METODO QUE DEVUELVE EL ÍNDICE DE UNA SILLA OCUPADA PERO NO ATENDIDA (SI NO HAY, DEVUELVE -1)
	public synchronized int getSillaSinAtender(){
		// SE RECORREN LAS SILLAS PARA ENCONTRAR UNA QUE ESTÉ OCUPADA PERO NO ATENDIDA
		for (int i = 0; i < numSillas; i++){
			if (sillaOcupada[i] == true && sillaAtendida[i] == false){  // SI LA SILLA ESTÁ OCUPADA PERO NO ATENDIDA
				sillaAtendida[i] = true;  // SE MARCA LA SILLA COMO ATENDIDA
				return i;  // SE DEVUELVE EL ÍNDICE DE LA SILLA QUE ESTÁ SIENDO ATENDIDA
			}
		}
		return -1;  // SI NO HAY SILLAS OCUPADAS PERO NO ATENDIDAS, SE DEVUELVE -1
	}
}
