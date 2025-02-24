package org.example.Barberia.src.com.ies.barberia;

import java.util.Random;

public class Barbero implements Runnable {

	// SE DECLARA UNA INSTANCIA DE GESTORSILLAS QUE GESTIONARÁ LAS SILLAS DE LA BARBERIA
	GestorSillas gestorSillas;

	// VARIABLE QUE REPRESENTA LA SILLA QUE EL BARBERO ESTÁ ATENDIENDO EN CADA MOMENTO
	int sillaQueAtendemos;

	// CONSTRUCTOR DE LA CLASE BARBERO QUE RECIBE UN OBJETO GESTORSILLAS
	public Barbero(GestorSillas gestor){
		gestorSillas = gestor;  // SE INICIALIZA EL GESTOR DE SILLAS
	}

	// METODO QUE SIMULA UN TIEMPO DE ESPERA ALEATORIO ENTRE 0 Y 3000 MILISEGUNDOS
	public void esperarTiempoAlAzar(){
		Random generador = new Random();  // SE CREA UN OBJETO RANDOM PARA GENERAR NUMEROS ALEATORIOS
		int msAzar = generador.nextInt(3000);  // GENERA UN NUMERO ALEATORIO ENTRE 0 Y 3000 MILISEGUNDOS
		try {
			Thread.sleep(msAzar);  // EL HILO SE DUERME DURANTE EL TIEMPO ALEATORIO
		} catch (Exception e) {
			// SE MANEJA EXCEPCIONES, PERO NO SE HACE NADA EN ESTE CASO
		}
	}

	// METODO QUE BUSCA UNA SILLA LIBRE PARA ATENDER A UN CLIENTE
	public int esperarSillaConCliente(){
		int sillaSinAtender;

		// SE OBTIENE UNA SILLA QUE NO HA SIDO ATENDIDA
		sillaSinAtender = gestorSillas.getSillaSinAtender();

		// SE MANTIENE EN UN BUCLE HASTA QUE SE ENCUENTRE UNA SILLA DISPONIBLE
		while (sillaSinAtender == -1) {
			// SI NO HAY SILLAS DISPONIBLES, EL BARBERO DUERME UN RATO Y LO VUELVE A INTENTAR
			esperarTiempoAlAzar();

			// SE VUELVE A CONSULTAR SI HAY UNA SILLA DISPONIBLE
			sillaSinAtender = gestorSillas.getSillaSinAtender();
		}

		// SE DEVUELVE EL NÚMERO DE LA SILLA DISPONIBLE
		return sillaSinAtender;
	}

	// METODO QUE SE EJECUTA AL INICIAR EL HILO DEL BARBERO
	@Override
	public void run() {
		String nombre = Thread.currentThread().getName();  // OBTIENE EL NOMBRE DEL HILO (BARBERO)

		// BUCLE INFINITO PARA SIMULAR QUE EL BARBERO SIEMPRE ESTÁ ATENDIENDO CLIENTES
		while (true) {
			// SE OBTIENE UNA SILLA PARA ATENDER
			int silla = this.esperarSillaConCliente();

			// SE MUESTRA EN PANTALLA QUE EL BARBERO ESTÁ ATENDIENDO UNA SILLA
			System.out.println(nombre + " atendiendo silla:" + silla);

			// EL BARBERO ESPERA UN TIEMPO ALEATORIO SIMULANDO EL PROCESO DE ATENDER AL CLIENTE
			esperarTiempoAlAzar();

			// SE LIBERA LA SILLA DESPUES DE ATENDER AL CLIENTE
			gestorSillas.liberarSilla(silla);

			// SE MUESTRA EN PANTALLA QUE EL BARBERO HA LIBERADO LA SILLA
			System.out.println(nombre + " libera silla:" + silla);
		}
	}
}
