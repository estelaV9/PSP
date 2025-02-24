package org.example.Casino.src.com.ies.casino;

public class JugadorMartingala extends Jugador {
	// CANTIDAD A APOSTAR POR EL JUGADOR SEGUN LA ESTRATEGIA MARTINGALA
	private int cantidadAApostar;

	// NUMERO ELEGIDO POR EL JUGADOR PARA LA APOSTA
	private int numeroElegido;

	// CONSTRUCTOR QUE INICIALIZA EL SALDO Y LA BANCA, Y LA CANTIDAD A APOSTAR SE INICIA EN 1
	public JugadorMartingala(long saldoInicial, Banca b) {
		super(saldoInicial, b);  // SE INICIALIZA LA CLASE PADRE JUGADOR
		cantidadAApostar = 1;  // CANTIDAD INICIAL APOSTADA ES 1
	}

	@Override
	public void comunicarNumero(int numero) {
		// SI EL NUMERO GANADOR ES 0, EL JUGADOR PIERDE LO APOSTADO Y DUPLICA LA CANTIDAD PARA LA PROXIMA APOSTA
		if (numero == 0) {
			System.out.println(nombreHilo + " pierde " + cantidadAApostar);
			cantidadAApostar = cantidadAApostar * 2;  // DUPLICA LA APOSTA PARA EL PROXIMO JUEGO
			return;
		}

		// SI EL NUMERO ELEGIDO POR EL JUGADOR COINCIDE CON EL NUMERO GANADOR, GANA 36 VECES LO APOSTADO
		if (numeroElegido == numero) {
			int beneficios = cantidadAApostar * 36;  // CALCULA LOS BENEFICIOS
			banca.restarSaldo(beneficios);  // RESTA LOS BENEFICIOS A LA BANCA
			sumarSaldo(beneficios);  // SUMA LOS BENEFICIOS AL SALDO DEL JUGADOR
			cantidadAApostar = 1;  // RESTABLECE LA CANTIDAD APOSTADA A 1
		}

		// SI EL NUMERO ELEGIDO NO COINCIDE CON EL NUMERO GANADOR, EL JUGADOR DUPLICA LO APOSTADO
		if (numeroElegido != numero) {
			//System.out.println(nombreHilo + " pierde " + cantidadAApostar);
			cantidadAApostar = cantidadAApostar * 2;  // DUPLICA LA APOSTA PARA EL PROXIMO JUEGO
		}

		// SE IMPRIME EL SALDO ACTUAL DEL JUGADOR
		System.out.println(nombreHilo + " queda con un saldo de " + saldo);
		apuestaRealizada = false;  // SE MARCA QUE EL JUGADOR YA NO TIENE UNA APOSTA REALIZADA
	}

	@Override
	public void hacerApuesta() {
		if (!banca.aceptaApuestas()) return;  // SI LA BANCA NO ACEPTA APOSTAS, SE TERMINA EL METODO
		if (apuestaRealizada) return;  // SI EL JUGADOR YA HA REALIZADO UNA APOSTA, SE TERMINA EL METODO

		/* ELEGIMOS UN NUMERO ALEATORIO ENTRE 1 Y 36 (NO SE PUEDE ELEGIR EL 0) */
		numeroElegido = 1 + generador.nextInt(36);  // SE ELIGE UN NUMERO ALEATORIO PARA APOSTAR

		// SE APOSTO LA CANTIDAD SELECCIONADA, SE SUMA A LA BANCA Y SE RESTA DEL SALDO DEL JUGADOR
		banca.sumarSaldo(cantidadAApostar);
		restarSaldo(cantidadAApostar);
		apuestaRealizada = true;  // SE MARCA QUE EL JUGADOR HA REALIZADO UNA APOSTA

		// SE REGISTRA LA APOSTA EN LA BANCA
		banca.aceptarApuesta(this);
	}
}
