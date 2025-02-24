package org.example.SolucionExamenHilos.src.com.ies.examen.mensajes;

public class GeneradorMensajes implements Runnable {

	private ProcesadorMensajes procMensajes;
	private int numMensajes;
	private String nombre;

	// CONSTRUCTOR DE LA CLASE
	public GeneradorMensajes(ProcesadorMensajes procMensajes) {
		this.procMensajes = procMensajes;
		this.numMensajes = 0;
	}

	// METODO PARA REALIZAR UNA TAREA COMPLEJA Y REGISTRAR LAS TRAZAS
	public void tareaCompleja() {
		// ESCRIBE TRAZA DE INICIO DE LA TAREA
		procMensajes.escribirTraza(
				nombre +
						" Ini:" +
						Utilidades.anadirCeros(numMensajes)
		);
		numMensajes++;

		// ESCRIBE TRAZA DE MEDIO DE LA TAREA
		procMensajes.escribirTraza(
				nombre +
						" Med:" +
						Utilidades.anadirCeros(numMensajes)
		);
		int mensajesTotales = procMensajes.getNumMensajes();
		// SI EL NUMERO DE MENSAJES ES IMPAR, SE REGISTRA UNA TRAZA
		if (mensajesTotales % 2 != 0) {
			procMensajes.escribirTraza("Voy por un numero impar");
		}

		numMensajes++;

		// ESCRIBE TRAZA DE FIN DE LA TAREA
		procMensajes.escribirTraza(
				nombre +
						" Fin:" +
						Utilidades.anadirCeros(numMensajes)
		);

		numMensajes++;
	}

	// METODO PARA REALIZAR UNA TAREA COMPLEJA ALTERNATIVA
	public void tareaCompleja2() {
		int mensajesTotales;
		String mensaje;
		// REALIZA UNA TAREA DE LECTURA DE MENSAJES Y ESCRIBE LAS TRAZAS
		for (int i = 0; i < 1000; i++) {
			mensajesTotales = procMensajes.getNumMensajes();
			mensaje = "Soy el hilo " + nombre;
			mensaje += " y he leido el ";
			mensaje += "numMensaje " + mensajesTotales;
			procMensajes.escribirTraza(mensaje);
		}
	}

	// IMPLEMENTACION DEL METODO RUN DE LA INTERFAZ Runnable
	@Override
	public void run() {
		nombre = Thread.currentThread().getName();
		// EJECUTA LA TAREA COMPLEJA VARIAS VECES
		for (int i = 0; i < 10; i++) {
			tareaCompleja();
		}
	}

}
