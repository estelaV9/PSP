package org.example.Seguridad.RegistroMensajes.src.com.ies.examenhilos;

import java.io.IOException;
import java.lang.management.ManagementFactory;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

public class Lanzador {

	// METODO PARA REGISTRAR UN MBEAN EN EL MBEAN SERVER
	public static void registrarBean(Pruebas p) throws InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException, MalformedObjectNameException{
		/*
		 * A LA HORA DE EJECUTAR ESTO ES IMPORTANTE DEFINIR LAS SIGUIENTES OPCIONES
		 * EN LA MAQUINA VIRTUAL (EN RUN AS -> RUN CONFIGURATIONS):
		 * -Dcom.sun.management.jmxremote,
		 * -Dcom.sun.management.jmxremote.ssl=false,
		 * -Dcom.sun.management.jmxremote.authenticate=false
		 */
		MBeanServer servidor=ManagementFactory.getPlatformMBeanServer(); // OBTENGO EL SERVIDOR DE MBEAN
		ObjectName nombre=new ObjectName("com.ies.examenhilos:type=Pruebas"); // DEFINO UN NOMBRE UNICO PARA EL MBEAN
		servidor.registerMBean(p, nombre); // REGISTRO EL MBEAN EN EL SERVIDOR
	}

	// METODO PRINCIPAL
	public static void main(String[] args)
			throws IOException, InterruptedException, InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException, MalformedObjectNameException{

		/* SI NO TE DEJA ESCRIBIR EN ESTE FICHERO, MODIFICALO. */
		String rutaFicheroTrazas="trazas.txt"; // RUTA DEL FICHERO DE TRAZAS
		String rutaFicheroTrazasOrdenadas="trazas_ordenadas.txt"; // RUTA DEL FICHERO DE TRAZAS ORDENADAS
		final int MAX_HILOS = 500; // CANTIDAD MAXIMA DE HILOS A CREAR
		Pruebas pruebas=new Pruebas(); // INICIALIZO EL OBJETO DE PRUEBAS
		registrarBean(pruebas); // REGISTRO EL MBEAN PARA MONITOREO
		ProcesadorMensajes p=new ProcesadorMensajes(pruebas, rutaFicheroTrazas); // CREAMOS EL PROCESADOR DE MENSAJES
		Thread[] hilos=new Thread[MAX_HILOS]; // CREO UN ARREGLO DE HILOS

		// CREO Y LANZO LOS HILOS QUE GENERAN MENSAJES
		for (int i=0; i<MAX_HILOS; i++){
			hilos[i]=new Thread(new GeneradorMensajes(p)); // CADA HILO ES UN GENERADOR DE MENSAJES
			hilos[i].setName("Generador "+Utilidades.anadirCeros(i)); // ASIGNO UN NOMBRE UNICO A CADA HILO
			hilos[i].start(); // INICIO EL HILO
		}

		// ESPERO A QUE TODOS LOS HILOS FINALICEN SU EJECUCION
		System.out.println("Hilos lanzados, esperando la finalización");
		for (int i=0; i<MAX_HILOS; i++){
			hilos[i].join(); // ESPERO A QUE CADA HILO TERMINO SU EJECUCION
		}
		p.cerrarFicheros(); // CIERRO LOS FICHEROS DESPUES DE TERMINAR EL PROCESAMIENTO
		System.out.println("Hilos finalizados. Compruebe el fichero");

		// ORDENAR LAS LINEAS DEL FICHERO DE TRAZAS Y GUARDARLO EN UN NUEVO FICHERO
		Utilidades.ordenarLineasFichero(rutaFicheroTrazas, rutaFicheroTrazasOrdenadas);
	}
}
