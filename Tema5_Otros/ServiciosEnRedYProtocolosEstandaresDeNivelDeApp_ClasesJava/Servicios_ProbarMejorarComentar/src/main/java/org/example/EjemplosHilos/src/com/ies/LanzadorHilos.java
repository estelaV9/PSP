package org.example.EjemplosHilos.src.com.ies;

import java.util.Random;

// CLASE QUE IMPLEMENTA "Runnable" Y SIMULA UN CALCULO CON HILOS
class HiloCalculo implements Runnable{

	// METODO QUE SE EJECUTA CUANDO EL HILO ES INICIADO
	public void run() {
		String miNombre; // VARIABLE PARA OBTENER EL NOMBRE DEL HILO
		miNombre=Thread.currentThread().getName(); // OBTIENE EL NOMBRE DEL HILO ACTUAL
		for (double d=0.0d; d<10; d=d+0.05){
			double r=Math.sqrt(d); // REALIZA UNA OPERACION DE RAIZ CUADRADA
			System.out.print("Soy "+miNombre); // IMPRIME EL NOMBRE DEL HILO
			System.out.println(" y voy por el :"+d); // IMPRIME EL VALOR DEL ITERADOR
		}
	}
}

// CLASE PRINCIPAL QUE INICIA LOS HILOS Y MANEJA SU EJECUCION
public class LanzadorHilos {
	public static void main(String[] args) throws InterruptedException {
		Thread hilo1, hilo2; // DECLARACION DE DOS HILOS
		Random generador; // GENERADOR DE NUMEROS ALEATORIOS

		// CREACION DE LOS HILOS
		hilo1=new Thread(new HiloCalculo()); // CREAMOS EL PRIMER HILO
		hilo1.setName("Hilo 1"); // ASIGNAMOS UN NOMBRE AL PRIMER HILO
		hilo1.start(); // INICIAMOS EL PRIMER HILO

		hilo2=new Thread(new HiloCalculo()); // CREAMOS EL SEGUNDO HILO
		hilo2.setName("Hilo 2"); // ASIGNAMOS UN NOMBRE AL SEGUNDO HILO

		hilo1.setPriority(Thread.MAX_PRIORITY); // ASIGNAMOS LA PRIORIDAD MAXIMA AL HILO 1
		hilo2.start(); // INICIAMOS EL SEGUNDO HILO

		hilo1.join(); // ESPERA A QUE EL HILO 1 TERMINO SU EJECUCION
		System.out.println("Paro el hilo 2"); // IMPRIME MENSAJE INDIACANDO QUE EL HILO 2 SE DETIENE

		hilo2.sleep(3000); // PONE EL HILO 2 A DORMIR POR 3 SEGUNDOS
		hilo2.join(); // ESPERA A QUE EL HILO 2 TERMINO SU EJECUCION

		// MENSAJES DE FINALIZACION
		System.out.println("Soy el padre ");
		System.out.println("Y sé que mis hilos acabaron"); // INFORMA QUE LOS HILOS HAN TERMINADO
	}
}
