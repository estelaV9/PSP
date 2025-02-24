package org.example.PlantillaAplicacionMultihilo.src.com.ies.plantilla;


/* Clase que ejecuta una tarea permitiendo 
 * el paralelismo con otros procesos */
public class Hilo implements Runnable {

	public Hilo() {
		/* Inicializar aquí todos los atributos de interés*/
	}

	/* Es obligatorio implementar este método.
	 * Nunca lo llamaremos nosotros, sino Java */
	@Override
	public void run() {
		/* Poner aquí el código de la tarea
		 * que deseemos que permita el paralelismo
		 * con otras
		 * ¡Atención! La tarea puede ser de 3 tipos
		 * 1.- Una tarea simple que ejecute algunas sentencias
		 * 2.- Una tarea repetitiva que se ejecute mientras se dé una condicion
		 * 3.- Una tarea repetitiva que se ejecute siempre (bucle infinito)*/
	}

}
