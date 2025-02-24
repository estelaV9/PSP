package org.example.SimulacionFilosofos.src;

public class Lanzador {

	public static void main(String[] args) throws InterruptedException {

		/* DEFINIMOS EL NUMERO DE FILOSOFOS Y PALILLOS */
		final int NUM_ELEMENTOS = 5;

		/* CREAMOS EL GESTOR DE PALILLOS */
		GestorPalillos gestorPalillos;
		gestorPalillos = new GestorPalillos(NUM_ELEMENTOS);

		/* CREACION DE LOS HILOS (FILOSOFOS) */
		Thread[] hilos = new Thread[NUM_ELEMENTOS];
		for (int i = 0; i < NUM_ELEMENTOS; i++) {
			/* CADA FILOSOFO SE ASOCIA A UN HILO */
			Filosofo filosofo;
			filosofo = new Filosofo(gestorPalillos);
			hilos[i] = new Thread(filosofo);
			hilos[i].start();
		}

		/* ESPERAMOS A QUE TODOS LOS FILOSOFOS TERMINEN SU EJECUCION */
		/* A PARTIR DE AQUÍ INNECESARIO, ¿POR QUÉ? */
        /* ESTA PARTE NO ES NECESARIA, YA QUE LOS FILOSOFOS ESTAN EJECUTANDOSE EN UN HILO SEPARADO Y
           EL PROGRAMA TERMINARA CUANDO TODOS LOS FILOSOFOS HAYAN FINALIZADO */
		for (int i = 0; i < NUM_ELEMENTOS; i++) {
			hilos[i].join();
		}

		/* MENSAJE FINAL AL TERMINAR EL PROGRAMA */
		System.out.println("Fin del programa. Gracias por usarlo");
	}
}
