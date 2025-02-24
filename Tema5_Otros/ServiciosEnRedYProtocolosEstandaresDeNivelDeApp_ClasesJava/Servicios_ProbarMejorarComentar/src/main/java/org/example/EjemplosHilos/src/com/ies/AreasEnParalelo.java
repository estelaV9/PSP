package org.example.EjemplosHilos.src.com.ies;

import java.util.Random;

// CLASE "CalculadorAreas" QUE IMPLEMENTA "Runnable" PARA SER EJECUTADA EN UN HILO
class CalculadorAreas implements Runnable{
	// VARIABLES PARA ALMACENAR BASE, ALTURA Y AREA
	float base, altura, area;
	// CONTADOR DE CANTIDAD DE CALCULOS REALIZADOS
	public int contador=0;

	// CONSTRUCTOR QUE INICIALIZA LOS VALORES DE BASE Y ALTURA
	public CalculadorAreas(float b, float a){
		this.base=b;
		this.altura=a;
	}

	// METODO SINCRONIZADO PARA INCREMENTAR EL CONTADOR DE CALCULOS
	public synchronized void incrementarContador(){
		contador = contador +1; // INCREMENTA EL CONTADOR CADA VEZ QUE SE LLAME
	}

	// METODO RUN() QUE SE EJECUTA EN CADA HILO
	@Override
	public void run() {
		Random generador; // GENERADOR DE NUMEROS ALEATORIOS
		generador=new Random(); // INICIALIZA EL GENERADOR DE NUMEROS ALEATORIOS
		area=base * altura / 2; // CALCULA EL AREA DE UN TRIANGULO
		try {
			// DORMIR EL HILO UN TIEMPO ALEATORIO ENTRE 0 Y 650 MILISEGUNDOS
			Thread.sleep(generador.nextInt(650));
		} catch (InterruptedException e) {
			e.printStackTrace(); // MUESTRA EL ERROR EN CASO DE UNA INTERRUPCION
		}
		// INCREMENTA EL CONTADOR DESPUES DE CALCULAR EL AREA
		this.incrementarContador();
	}
}

// CLASE PRINCIPAL "AreasEnParalelo" QUE EJECUTA LOS CALCULOS EN PARALELO CON HILOS
public class AreasEnParalelo{
	// METODO MAIN
	public static void main(String[] args) throws InterruptedException{
		// CREAMOS UNA INSTANCIA DE "CalculadorAreas" CON BASE=1 Y ALTURA=2
		CalculadorAreas ca=new CalculadorAreas(1,2);

		// CONSTANTE QUE DEFINE EL NUMERO MAXIMO DE HILOS
		final int MAX_HILOS = 10000;

		// CREACION DEL ARREGLO DE HILOS
		Thread[] hilos=new Thread[MAX_HILOS];

		// CREACION Y EJECUCION DE LOS HILOS
		for (int i=0; i<MAX_HILOS; i++){
			hilos[i]=new Thread(ca); // CREAMOS UN NUEVO HILO CON EL OBJETO "ca"
			hilos[i].start(); // INICIAMOS EL HILO
		}

		// ESPERAMOS A QUE TODOS LOS HILOS TERMINEN
		for (int i=0; i<MAX_HILOS; i++){
			hilos[i].join(); // ESPERA QUE CADA HILO TERMINADO
		}

		// MUESTRA EL TOTAL DE CALCULOS REALIZADOS
		System.out.println(
				"Total de calculos:" +
						ca.contador
		);
	}
}
