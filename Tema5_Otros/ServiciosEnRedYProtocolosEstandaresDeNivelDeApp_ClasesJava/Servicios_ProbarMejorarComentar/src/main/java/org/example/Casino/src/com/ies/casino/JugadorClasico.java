package org.example.Casino.src.com.ies.casino;

public class JugadorClasico extends Jugador {
	// NUMERO ELEGIDO POR EL JUGADOR EN LA APOSTA
	int numeroElegido;

	// CONSTRUCTOR DE LA CLASE JUGADORCLASICO QUE INICIALIZA EL SALDO Y LA BANCA
	public JugadorClasico(long saldoInicial, Banca b) {
		super(saldoInicial, b);  // SE INICIALIZA LA CLASE PADRE JUGADOR
	}

	@Override
	public void comunicarNumero(int numero) {
		/* NO HACE FALTA COMPROBAR SI EL NUMERO ES 0, YA QUE ESTE JUGADOR NO LO ELIJE NUNCA */
		if (numero == numeroElegido) {
			// SI EL NUMERO ELEGIDO POR EL JUGADOR COINCIDE CON EL NUMERO GANADOR, GANA 36 VECES LO APOSTADO
			System.out.println(nombreHilo + ": ¡Gana 36 veces lo jugado, 360 euros");
			banca.restarSaldo(360);  // SE RESTA EL SALDO DE LA BANCA
			sumarSaldo(360);  // SE SUMA EL GANADO AL SALDO DEL JUGADOR
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
		numeroElegido = 1 + generador.nextInt(36);

		// SE APOSTARON 10 EUROS, SE SUMA A LA BANCA Y SE RESTA DEL SALDO DEL JUGADOR
		banca.sumarSaldo(10);
		restarSaldo(10);
		apuestaRealizada = true;  // SE MARCA QUE EL JUGADOR HA REALIZADO UNA APOSTA

		// SE REGISTRA LA APOSTA EN LA BANCA
		banca.aceptarApuesta(this);
	}
}
