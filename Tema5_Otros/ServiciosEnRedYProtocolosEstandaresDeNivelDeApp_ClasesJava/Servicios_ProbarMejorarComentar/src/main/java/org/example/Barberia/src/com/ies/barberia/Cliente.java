package org.example.Barberia.src.com.ies.barberia;

import java.util.Random;

public class Cliente implements Runnable {

	// SE DECLARA UNA INSTANCIA DE GESTORSILLAS QUE GESTIONARÁ LAS SILLAS DE LA BARBERIA
	GestorSillas gestorSillas;

	// CONSTRUCTOR DE LA CLASE CLIENTE QUE RECIBE UN OBJETO GESTORSILLAS
	public Cliente(GestorSillas gestor){
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

	// METODO QUE SE EJECUTA AL INICIAR EL HILO DEL CLIENTE
	@Override
	public void run() {
		int sillaLibre;

		// SE CONSULTA SI HAY UNA SILLA LIBRE DISPONIBLE PARA EL CLIENTE
		sillaLibre = gestorSillas.getSillaLibre();

		// SI NO HAY SILLAS LIBRES, EL CLIENTE SE VA (FINALIZA LA EJECUCION)
		if (sillaLibre == -1) {
			//System.out.println("Me voy");  // SE PODRIA IMPRIMIR UN MENSAJE, PERO ESTÁ COMENTADO
			return;  // EL CLIENTE TERMINA SU EJECUCION
		}

		boolean terminar = false;  // VARIABLE PARA CONTROLAR SI EL CLIENTE HA TERMINADO DE SER ATENDIDO

		// SE VERIFICA SI EL CLIENTE HA SIDO COMPLETAMENTE AFEITADO
		terminar = gestorSillas.afeitadoCompleto(sillaLibre);

		// MIENTRAS EL CLIENTE NO HAYA SIDO AFEITADO COMPLETAMENTE, SE SIGUE VERIFICANDO
		while (!terminar) {
			// EN EL COMENTARIO ORIGINAL SE SUGERIA ESPERAR, PERO EL CÓDIGO ESTÁ COMENTADO
			// esperarTiempoAlAzar();  // SE PODRIA ESPERAR UN TIEMPO ALEATORIO ANTES DE VOLVER A VERIFICAR
			terminar = gestorSillas.afeitadoCompleto(sillaLibre);  // SE VUELVE A VERIFICAR SI EL AFEITADO ESTÁ COMPLETO
		}
	}
}
