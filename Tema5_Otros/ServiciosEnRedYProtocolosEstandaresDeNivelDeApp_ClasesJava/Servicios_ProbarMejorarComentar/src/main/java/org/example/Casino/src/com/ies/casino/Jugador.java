package org.example.Casino.src.com.ies.casino;

import java.util.Random;

public abstract class Jugador implements Runnable {
	// ATRIBUTO SALDO QUE REPRESENTA EL DINERO DEL JUGADOR
	protected long saldo;

	// ATRIBUTO QUE INDICA SI EL JUGADOR ESTA EN BANCARROTA
	protected boolean enBancarrota;

	// ATRIBUTO QUE REPRESENTA LA CANTIDAD APOSTADA POR EL JUGADOR
	protected long cantidadApostada;

	// ATRIBUTO QUE INDICA SI EL JUGADOR YA HA REALIZADO UNA APOSTA
	protected boolean apuestaRealizada;

	// ATRIBUTO QUE HACE REFERENCIA A LA BANCA EN LA QUE SE REALIZA EL JUEGO
	protected Banca banca;

	// NOMBRE DEL HILO QUE REPRESENTA AL JUGADOR
	protected String nombreHilo;

	// GENERADOR DE NUMEROS ALEATORIOS
	protected Random generador;

	// CONSTRUCTOR DE LA CLASE JUGADOR, INICIALIZA EL SALDO Y LA BANCA
	public Jugador(long saldoInicial, Banca b) {
		saldo = saldoInicial;  // SE ASIGNA EL SALDO INICIAL AL JUGADOR
		banca = b;  // SE ASIGNA LA BANCA EN LA QUE EL JUGADOR PARTICIPARA
		apuestaRealizada = false;  // SE INICIALIZA EN FALSE, INDICANDO QUE NO SE HA REALIZADO UNA APOSTA
		generador = new Random();  // SE INICIALIZA EL GENERADOR DE NUMEROS ALEATORIOS
	}

	// METODO PARA SUMAR UNA CANTIDAD AL SALDO DEL JUGADOR
	public void sumarSaldo(long cantidad) {
		saldo = saldo + cantidad;
	}

	// METODO PARA RESTAR UNA CANTIDAD AL SALDO DEL JUGADOR
	public void restarSaldo(long cantidad) {
		if (saldo - cantidad <= 0) {
			saldo = 0;  // SI EL SALDO SE VUELVE CERO O NEGATIVO, EL JUGADOR SE PONE EN BANCARROTA
			enBancarrota = true;  // SE MARCA AL JUGADOR COMO EN BANCARROTA
			return;
		}
		saldo = saldo - cantidad;  // SE RESTA LA CANTIDAD DEL SALDO DEL JUGADOR
	}

	// METODO QUE DEVUELVE SI EL JUGADOR ESTA EN BANCARROTA
	public boolean enBancarrota() {
		return enBancarrota;
	}

	/* METODO ABSTRACTO QUE DEBE SER IMPLEMENTADO POR LAS SUBCLASES PARA
       QUE LA BANCA PUEDA COMUNICAR EL NUMERO GANADOR AL JUGADOR */
	public abstract void comunicarNumero(int numero);

	/* METODO ABSTRACTO QUE DEBE SER IMPLEMENTADO POR LAS SUBCLASES PARA
       REALIZAR LA APOSTA DEL JUGADOR */
	public abstract void hacerApuesta();

	/* METODO QUE EJECUTA LA LOGICA DE APOSTAR MIENTRAS EL JUGADOR Y LA BANCA
       NO ESTEN EN BANCARROTA. CADA VEZ QUE SE ACEPTAN APOSTAS, EL JUGADOR REALIZA UNA APOSTA */
	@Override
	public void run() {
		nombreHilo = Thread.currentThread().getName();  // SE ASIGNA EL NOMBRE DEL HILO AL NOMBRE DEL JUGADOR
		while ((!enBancarrota) && (!banca.enBancarrota())) {  // SE EJECUTA MIENTRAS EL JUGADOR Y LA BANCA NO ESTEN EN BANCARROTA
			int msAzar;
			/* MIENTRAS LA BANCA NO ESTE ACEPTANDO APOSTAS, EL JUGADOR DUERME UN TIEMPO AL AZAR */
			while (!banca.aceptaApuestas()) {
				msAzar = this.generador.nextInt(500);  // SE GENERA UN TIEMPO ALEATORIO
				try {
					// SE DUERME EL HILO DEL JUGADOR POR EL TIEMPO GENERADO
					// System.out.println(nombreHilo + ":banca ocupada, durmiendo...");
					Thread.sleep(msAzar);
				} catch (InterruptedException e) {
					return;  // SI SE INTERROMPE EL HILO, SE TERMINA EL JUEGO
				}
				if (banca.enBancarrota()) {
					return;  // SI LA BANCA ESTA EN BANCARROTA, EL JUGADOR TERMINA
				}
			}

			// UNA VEZ QUE LA BANCA ACEPTA APOSTAS, EL JUGADOR REALIZA UNA APOSTA
			hacerApuesta();
		}

		String nombre = Thread.currentThread().getName();
		if (enBancarrota) {
			// SI EL JUGADOR ESTA EN BANCARROTA, SE MUESTRA EL MENSAJE DE QUE SE ARRUINO
			System.out.println(nombre + ": ¡¡Me arruine!!");
			return;
		}

		if (banca.enBancarrota()) {
			// SI LA BANCA ESTA EN BANCARROTA, SE MUESTRA EL MENSAJE DE QUE EL JUGADOR HIZO SALTAR LA BANCA
			System.out.println(nombre + " hizo saltar la banca");
		}
	}
}
