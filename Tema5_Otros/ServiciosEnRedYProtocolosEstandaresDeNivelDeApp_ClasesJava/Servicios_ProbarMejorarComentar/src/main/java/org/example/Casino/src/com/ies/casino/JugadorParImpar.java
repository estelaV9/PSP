package org.example.Casino.src.com.ies.casino;

public class JugadorParImpar extends Jugador {
	// CONSTRUCTOR QUE INICIALIZA EL SALDO Y LA BANCA
	public JugadorParImpar(long saldoInicial, Banca b) {
		super(saldoInicial, b);  // SE INICIALIZA LA CLASE PADRE JUGADOR
	}

	// INDICA SI EL JUGADOR APOSTARA A PARES O IMPARES
	protected boolean jugamosAPares;

	@Override
	public void hacerApuesta() {
		if (!banca.aceptaApuestas()) return;  // SI LA BANCA NO ACEPTA APOSTAS, SE TERMINA EL METODO
		if (apuestaRealizada) return;  // SI YA HAY UNA APOSTA REALIZADA, SE TERMINA EL METODO

		/* ELEGIMOS UNA APOSTA ALEATORIA: PARES O IMPARES */
		if (generador.nextBoolean() == true) {
			//System.out.println(nombreHilo + " elige apostar a par");
			jugamosAPares = true;  // SI SE ELIGE "PAR"
		} else {
			//System.out.println(nombreHilo + " elige apostar a impar");
			jugamosAPares = false;  // SI SE ELIGE "IMPAR"
		}

		// EL JUGADOR APOSTA 10 EUROS
		banca.sumarSaldo(10);
		restarSaldo(10);
		apuestaRealizada = true;  // SE MARCA QUE YA SE REALIZO UNA APOSTA

		/* PEDIMOS A LA BANCA QUE ACEPTE LA APOSTA */
		banca.aceptarApuesta(this);
	}

	public boolean esGanador(int num) {
		/* ESTE JUGADOR NECESITA COMPROBAR SI HA SALIDO EL 0,
		 * YA QUE SI APOSTAMOS A PARES NO GANAMOS SI SALE EL 0 */
		if (num == 0) {
			return false;  // SI EL NUMERO ES 0, EL JUGADOR NO GANA
		} else {
			// SI EL NUMERO ES PAR Y EL JUGADOR APOSTO A PARES, GANA
			if ((num % 2 == 0) && (jugamosAPares)) {
				return true;
			}
			// SI EL NUMERO ES IMPAR Y EL JUGADOR APOSTO A IMPARES, GANA
			if ((num % 2 != 0) && (!jugamosAPares)) {
				return true;
			}
		}
		return false;  // SI NO CUMPLE LAS CONDICIONES, NO GANA
	}

	@Override
	public void comunicarNumero(int numero) {

		// SI EL JUGADOR GANO (NUMERO CORRECTO SEGUN LA APOSTA)
		if (esGanador(numero)) {
			/* EL JUGADOR GANA 20 EUROS POR ACERTAR EL NUMERO */
			System.out.println(nombreHilo + " gana 20 euros por acertar impar");
			banca.restarSaldo(20);  // LA BANCA RESTA LOS 20 EUROS
			this.sumarSaldo(20);  // EL JUGADOR SUMA LOS 20 EUROS GANADOS
		}

		// SE IMPRIME EL SALDO ACTUAL DEL JUGADOR
		System.out.println(nombreHilo + " se queda con un saldo de " + saldo);

		// SEA COMO SEA, EL JUGADOR FINALIZA LA APOSTA Y ESTA DISPONIBLE PARA UNA NUEVA APOSTA
		apuestaRealizada = false;
	}
}
