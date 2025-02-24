package org.example.Barberia.src.com.ies.barberia;

public class Lanzador {
	public static void main(String[] args) throws InterruptedException {

		// SE CREA UNA INSTANCIA DE GESTORSILLAS CON 3 SILLAS EN LA BARBERIA
		GestorSillas gestorSillas;
		gestorSillas = new GestorSillas(3);

		/* CREAMOS LOS BARBEROS */
		int numBarberos = 2;  // SE DEFINEN DOS BARBEROS PARA LA BARBERIA
		Thread barberos[];  // SE DECLARA UN ARREGLO DE HILOS PARA LOS BARBEROS
		barberos = new Thread[numBarberos];  // SE INICIALIZA EL ARREGLO CON EL NUMERO DE BARBEROS

		// SE INICIALIZA Y SE PONE EN EJECUCION A CADA BARBERO
		for (int i = 0; i < numBarberos; i++) {
			Barbero b = new Barbero(gestorSillas);  // SE CREA UN NUEVO OBJETO BARBERO
			barberos[i] = new Thread(b);  // SE ASIGNA EL HILO PARA EL BARBERO
			barberos[i].start();  // SE INICIA EL HILO DEL BARBERO
			barberos[i].setName("Barbero " + i);  // SE ASIGNA UN NOMBRE AL HILO PARA IDENTIFICAR EL BARBERO
		}

		// BUCLE INFINITO DONDE SE CREAMOS CLIENTES DE FORMA INDEFINIDA
		while (true) {
			Cliente c = new Cliente(gestorSillas);  // SE CREA UN NUEVO CLIENTE
			Thread hilo = new Thread(c);  // SE CREA UN HILO PARA EL CLIENTE
			hilo.start();  // SE INICIA EL HILO DEL CLIENTE
		}
	}
}
