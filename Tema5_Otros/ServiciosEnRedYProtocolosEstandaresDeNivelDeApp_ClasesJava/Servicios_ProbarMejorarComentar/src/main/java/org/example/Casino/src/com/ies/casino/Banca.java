package org.example.Casino.src.com.ies.casino;

import java.util.ArrayList;
import java.util.Random;

public class Banca {
	// ATRIBUTO SALDO QUE REPRESENTA EL SALDO DE LA BANCA
	protected long saldo;

	// ATRIBUTO QUE INDICA SI LA BANCA ESTA EN BANCARROTA
	protected boolean enBancarrota;

	// GENERADOR DE NUMEROS ALEATORIOS PARA LA RUERTA
	protected Random generador;

	// ATRIBUTO QUE INDICA SI SE PUEDEN HACER APOSTAS
	protected boolean sePuedenHacerApuestas;

	// NUMERO GANADOR DE LA RUERTA
	protected int numeroGanador;

	// ENUM QUE REPRESENTA LOS DIFERENTES ESTADOS DE LA RUERTA
	public enum Estado {
		INICIO, ACEPTANDO_APUESTAS,
		RULETA_GIRANDO, PAGANDO_APUESTAS,
		EN_BANCARROTA
	};

	// ESTADO ACTUAL DE LA RUERTA
	private Estado estadoRuleta;

	// LISTA DE APOSTADORES (JUGADORES)
	private ArrayList<Jugador> apostadores;

	// CONSTRUCTOR DE LA CLASE Banca, INICIALIZA LA BANCA CON UN SALDO INICIAL
	public Banca(long saldoInicial) {
		saldo = saldoInicial;  // SE ASIGNA EL SALDO INICIAL
		enBancarrota = false;  // LA BANCA INICIA SIN ESTAR EN BANCARROTA
		estadoRuleta = Estado.INICIO;  // LA RUERTA INICIA EN EL ESTADO INICIO
		generador = new Random();  // SE INICIALIZA EL GENERADOR ALEATORIO
		apostadores = new ArrayList<Jugador>();  // SE INICIALIZA LA LISTA DE APOSTADORES
	}

	// METODO SINCRONIZADO QUE DEVUELVE SI LA BANCA ESTA EN BANCARROTA
	public synchronized boolean enBancarrota() {
		return enBancarrota;
	}

	// METODO SINCRONIZADO QUE SUMA UNA CANTIDAD AL SALDO DE LA BANCA
	public synchronized void sumarSaldo(long cantidad) {
		saldo = saldo + cantidad;
	}

	// METODO SINCRONIZADO QUE RESTA UNA CANTIDAD DEL SALDO DE LA BANCA
	public synchronized void restarSaldo(long cantidad) {
		if (saldo - cantidad <= 0) {
			saldo = 0;  // SI EL SALDO LLEGA A CERO, SE PONE EN BANCARROTA
			estadoRuleta = Estado.EN_BANCARROTA;  // SE CAMBIA EL ESTADO A BANCARROTA
			return;
		}
		saldo = saldo - cantidad;  // SE RESTA LA CANTIDAD DEL SALDO
	}

	// METODO SINCRONIZADO QUE ACEPTA LA APOSTA DE UN JUGADOR SI ESTA EN EL ESTADO ACEPTANDO APOSTAS
	public synchronized void aceptarApuesta(Jugador jugador) {
		if (estadoRuleta == Estado.ACEPTANDO_APUESTAS) {
			apostadores.add(jugador);  // SE AGREGA EL JUGADOR A LA LISTA DE APOSTADORES
		}
	}

	// METODO SINCRONIZADO QUE DEVUELVE SI SE PUEDEN HACER APOSTAS
	public synchronized boolean aceptaApuestas() {
		return estadoRuleta == Estado.ACEPTANDO_APUESTAS;  // SI EL ESTADO ES ACEPTANDO APOSTAS, DEVUELVE TRUE
	}

	// METODO QUE COMUNICA A LOS APOSTADORES EL NUMERO GANADOR Y LES RESTA EL SALDO CORRESPONDIENTE
	public void comunicarNumeroGanador(int numero) {
		int numApostadores = apostadores.size();  // OBTENEMOS EL NUMERO DE APOSTADORES

		// SE NOTIFICA A CADA APOSTADOR EL NUMERO GANADOR
		for (Jugador apostador : apostadores) {
			apostador.comunicarNumero(numeroGanador);
		}
		// SE LIMPIA LA LISTA DE APOSTADORES PARA EMPEZAR UNA NUEVA RONDA
		apostadores.clear();
	}

	// METODO QUE SIMULA EL JUEGO DE LA RUERTA
	public void girarRuleta() throws InterruptedException {
		int segundosAzar;
		System.out.println("Empieza el juego!");

		// EL JUEGO CONTINUA MIENTRAS NO ESTE EN BANCARROTA
		while (estadoRuleta != Estado.EN_BANCARROTA) {
			estadoRuleta = Estado.ACEPTANDO_APUESTAS;  // CAMBIA EL ESTADO A ACEPTANDO APOSTAS
			// SE GENERA UN TIEMPO ALEATORIO PARA QUE LOS JUGADORES PUEDAN APOSTAR
			segundosAzar = 1 + generador.nextInt(3);  // SE GENERA UN NUMERO ALEATORIO ENTRE 1 Y 3
			System.out.println("Hagan juego, tienen Vds " + segundosAzar + " segundos");
			Thread.sleep(1000 * segundosAzar);  // SE DUERME EL HILO POR EL TIEMPO ALEATORIO

			System.out.println("Ya no va mas, senores. ¡Girando!");
			estadoRuleta = Estado.RULETA_GIRANDO;  // SE CAMBIA EL ESTADO A RULETA GIRANDO
			Thread.sleep(3000);  // SE DUERME EL HILO POR 3 SEGUNDOS SIMULANDO EL GIRON DE LA RUERTA

			numeroGanador = generador.nextInt(37);  // SE GENERA UN NUMERO ALEATORIO ENTRE 0 Y 36 (CASILLAS DE LA RUERTA)
			System.out.println("El numero ganador es el :" + numeroGanador);
			estadoRuleta = Estado.PAGANDO_APUESTAS;  // SE CAMBIA EL ESTADO A PAGANDO APOSTAS
			this.comunicarNumeroGanador(numeroGanador);  // SE NOTIFICA A LOS APOSTADORES EL NUMERO GANADOR
			System.out.println("El saldo de la banca es ahora:" + saldo);
		}
	}

	// METODO QUE SIMULA UNA PARTIDA CON JUGADORES DE DIFERENTES TIPOS
	public void simular(int jugadoresPar, int jugadoresMartingala,
						int jugadoresClasicos) throws InterruptedException {
		// SE CREAN LOS JUGADORES DE TIPO "PAR/IMPAR"
		Thread[] hilosJugadoresPares = new Thread[jugadoresPar];
		for (int i = 0; i < jugadoresPar; i++) {
			JugadorParImpar jugador = new JugadorParImpar(1000, this);
			hilosJugadoresPares[i] = new Thread(jugador);
			hilosJugadoresPares[i].setName("Apostador par/impar " + i);
			hilosJugadoresPares[i].start();
		}

		// SE CREAN LOS JUGADORES DE TIPO "MARTINGALA"
		Thread[] hilosJugadoresMartingala = new Thread[jugadoresMartingala];
		for (int i = 0; i < jugadoresMartingala; i++) {
			JugadorMartingala jugador = new JugadorMartingala(1000, this);
			hilosJugadoresMartingala[i] = new Thread(jugador);
			hilosJugadoresMartingala[i].setName("Apostador martingala " + i);
			hilosJugadoresMartingala[i].start();
		}

		// SE CREAN LOS JUGADORES DE TIPO "CLASICO"
		Thread[] hilosJugadoresClasico = new Thread[jugadoresClasicos];
		for (int i = 0; i < jugadoresClasicos; i++) {
			JugadorClasico jugador = new JugadorClasico(1000, this);
			hilosJugadoresClasico[i] = new Thread(jugador);
			hilosJugadoresClasico[i].setName("Apostador clasico " + i);
			hilosJugadoresClasico[i].start();
		}

		// SE INICIA LA RUERTA
		this.girarRuleta();
	}

	// METODO PRINCIPAL QUE INICIA EL JUEGO CON 5 JUGADORES DE CADA TIPO
	public static void main(String[] args) throws InterruptedException {
		Banca b = new Banca(50000);  // SE CREA UNA INSTANCIA DE LA BANCA CON UN SALDO INICIAL DE 50,000
		b.simular(5, 5, 5);  // SE SIMULA EL JUEGO CON 5 JUGADORES DE CADA TIPO
	}
}
