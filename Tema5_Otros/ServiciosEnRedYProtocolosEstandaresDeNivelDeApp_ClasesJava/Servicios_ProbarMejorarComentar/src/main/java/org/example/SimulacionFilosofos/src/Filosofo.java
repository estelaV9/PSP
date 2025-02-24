package org.example.SimulacionFilosofos.src;

import java.util.Random;

public class Filosofo implements Runnable {

	// INSTANCIA DE LA CLASE GESTORPALILLOS QUE SE ENCARGA DE ADMINISTRAR LOS PALILLOS
	GestorPalillos gestorPalillos;

	// CONSTRUCTOR DE LA CLASE FILOSOFO QUE RECIBE UN GESTOR DE PALILLOS COMO PARAMETRO
	public Filosofo(GestorPalillos g) {
		gestorPalillos = g;
	}

	// IMPLEMENTACION DEL METODO RUN() DE LA INTERFAZ Runnable
	@Override
	public void run() {

		// CREAR UN OBJETO GENERADOR DE NUMEROS ALEATORIOS
		Random generador = new Random();

		// OBTENER EL NOMBRE DEL FILOSOFO (NOMBRE DEL HILO)
		String nombreFilosofo;
		nombreFilosofo = Thread.currentThread().getName();

		// DECLARACION DE LA PAREJA DE PALILLOS QUE EL FILOSOFO UTILIZARA
		ParejaPalillos parejaAsignada;

		// BUCLE INFINITO QUE SIMULA LA ACCION DE UN FILOSOFO
		while (true) {
			try {
				// SECCION PARA COMER
				parejaAsignada = gestorPalillos.cogerPalillos();

				// SI NO SE PUEDEN COGER LOS PALILLOS, EL FILOSOFO ESPERA HASTA QUE ESTEN DISPONIBLES
				while (parejaAsignada == null) {
					parejaAsignada = gestorPalillos.cogerPalillos();
				}

				// EL FILOSOFO COMIENZA A COMER
				System.out.println("Soy " + nombreFilosofo + " y estoy comiendo");

				// SE SIMULA EL TIEMPO DE COMER CON UN DORMIR ALEATORIO ENTRE 0 Y 3000 MILISEGUNDOS
				int msAzar = generador.nextInt(3000);
				Thread.sleep(msAzar);

				// UNA VEZ QUE HA TERMINADO DE COMER, SE LIBERAN LOS PALILLOS
				gestorPalillos.liberarPalillos(parejaAsignada);

				// SECCION PARA FILOSOFAR
				System.out.println("Soy " + nombreFilosofo + " y estoy pensando");

				// SE SIMULA EL TIEMPO DE FILOSOFAR CON UN DORMIR ALEATORIO ENTRE 0 Y 3000 MILISEGUNDOS
				msAzar = generador.nextInt(3000);
				Thread.sleep(msAzar);
			}
			catch (InterruptedException e) {
				// SI EL FILOSOFO ES INTERrumpido, SE MUESTRA UN MENSAJE Y SE TERMINA EL HILO
				System.out.println("Se interrumpió al filósofo " + nombreFilosofo);
				System.out.println("Saliendo...");
				return;
			}
		}
	}
}
