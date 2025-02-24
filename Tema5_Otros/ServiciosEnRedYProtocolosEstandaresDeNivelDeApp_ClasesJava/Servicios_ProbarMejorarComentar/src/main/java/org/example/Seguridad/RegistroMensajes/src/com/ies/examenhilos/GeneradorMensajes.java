package org.example.Seguridad.RegistroMensajes.src.com.ies.examenhilos;

import java.util.Random;

public class GeneradorMensajes implements Runnable {

	// INICIALIZACION DEL GENERADOR DE NUMEROS ALEATORIOS
	Random generadorNumeros=new Random();
	// NOMBRE DEL HILO Y CONTADOR DE MENSAJES
	String nombreHilo;
	int numMensaje=0;
	// INSTANCIA DEL PROCESADOR DE MENSAJES
	ProcesadorMensajes procesadorMensajes;

	// CONSTRUCTOR QUE INICIALIZA EL PROCESADOR DE MENSAJES
	public GeneradorMensajes(ProcesadorMensajes p) {
		this.procesadorMensajes=p;
	}

	// METODO PARA ESCRIBIR TRAZAS DE MENSAJES, ANADIR CERO AL NUMERO DEL MENSAJE
	public void escribirTraza(String mensaje){
		// ANADO CEROS AL NUMERO DE MENSAJE PARA UNIFORMIDAD EN EL FORMATO
		String cadNumMensaje;
		cadNumMensaje=Utilidades.anadirCeros(numMensaje);
		// ENVO EL MENSAJE AL PROCESADOR DE MENSAJES CON EL FORMATO CORRECTO
		procesadorMensajes.escribirTraza(nombreHilo+" Mensaje " + cadNumMensaje +" --> "+ mensaje);
		numMensaje++; // INCREMENTO EL CONTADOR DE MENSAJES
	}

	// METODO QUE SIMULA UNA TAREA COMPLEJA Y ESCRIBE MENSAJES DE TRAZA
	public void tareaCompleja(){
		// ESCRIBO MENSAJES DE TRAZA EN EL PROCESADOR
		this.escribirTraza(" Empieza mi mensaje de traza ");
		this.escribirTraza(" Mensaje de traza            ");
		this.escribirTraza(" Fin de mi traza             ");

		// MUESTRO UN MENSAJE EN CONSOLA AL FINALIZAR LA TAREA
		System.out.println(nombreHilo+" acaba de hacer algo");
	}

	// METODO RUN QUE EJECUTA LA LOGICA DEL HILO
	@Override
	public void run() {
		// OBTENGO EL NOMBRE DEL HILO ACTUAL
		nombreHilo=Thread.currentThread().getName();

		// EJECUTO LA TAREA COMPLEJA 100 VECES
		for (int i=0; i<100; i++){
			tareaCompleja(); // LLAMO A LA TAREA COMPLEJA
			// ESPERO UN TIEMPO ALEATORIO ANTES DE VOLVER A EJECUTAR
			Utilidades.esperarTiempoAlAzar();
		}

	}

}