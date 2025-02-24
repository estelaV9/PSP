package org.example.ServicioEco.src.com.ies.servidoreco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Random;

public class Cliente implements Runnable{
	Socket         conexion;      // OBJETO SOCKET PARA LA CONEXION CON EL SERVIDOR
	Random         generador;     // GENERADOR DE NUMEROS ALEATORIOS PARA ELEGIR UNA PALABRA
	BufferedReader bfr;           // FLUJO DE LECTURA PARA RECIBIR DATOS DEL SERVIDOR
	PrintWriter    pw;            // FLUJO DE ESCRITURA PARA ENVIAR DATOS AL SERVIDOR
	String[]       palabras={     // ARREGLO DE PALABRAS PARA ENVIAR AL SERVIDOR
			"Hola", "mundo", "Java", "hilo"
	};
	int numHilo;  // NUMERO DE HILO PARA IDENTIFICAR EL CLIENTE
	boolean algoFallo=false; // INDICA SI HUBO UN FALLO EN EL CLIENTE

	// CONSTRUCTOR QUE INICIALIZA LOS FLUJOS Y LA CONEXION CON EL SERVIDOR
	public Cliente(){
		generador=new Random();  // INICIALIZA EL GENERADOR DE NUMEROS ALEATORIOS
		InetSocketAddress direccion;
		direccion=new InetSocketAddress(
				"localhost", 9876);  // DIRECCION DEL SERVIDOR Y PUERTO
		Socket conexion;
		conexion=new Socket();   // CREAR UNA NUEVA INSTANCIA DE SOCKET
		try {
			conexion.connect(direccion);  // CONECTA AL SERVIDOR
			bfr=Utilidades.getFlujoLectura(
					conexion);  // OBTIENE EL FLUJO DE LECTURA
			pw=Utilidades.getFlujoEscritura(conexion); // OBTIENE EL FLUJO DE ESCRITURA
		} catch (IOException e) {
			algoFallo=true;  // SI HAY UN ERROR, SE MARCA EL FALLO
		} //Fin del catch
	}

	// METODOS PARA OBTENER Y ESTABLECER EL NUMERO DE HILO
	public int getNumHilo() {
		return numHilo;
	}
	public void setNumHilo(int numHilo) {
		this.numHilo = numHilo;
	}

	// METODO QUE CHEQUEA SI EL SERVIDOR ESTA FUNCIONANDO CORRECTAMENTE
	public boolean servidorFunciona()
	{
		// ELEGIMOS UNA PALABRA AL AZAR PARA ENVIAR AL SERVIDOR
		String palabra=palabras[generador.nextInt(palabras.length)];
		String eco;  // VARIABLE PARA ALMACENAR LA RESPUESTA DEL SERVIDOR
		try {
			// SI NO PUDIMOS OBTENER FLUJO DE LECTURA O ESCRITURA, EL SERVIDOR ESTA COLAPSADO
			if ( (bfr==null) || (pw==null) ){
				algoFallo=true;  // INDICAMOS QUE HUBO UN FALLO
				return false;  // DEVOLVEMOS FALSE YA QUE EL SERVIDOR NO FUNCIONA
			}
			pw.println(palabra);  // ENVIAMOS LA PALABRA AL SERVIDOR
			pw.flush();  // FORZAMOS QUE SE ENVIE EL MENSAJE
			eco = bfr.readLine();  // LEEMOS LA RESPUESTA DEL SERVIDOR
			if (eco.equals(palabra)){
				// SI LA RESPUESTA ES LA MISMA PALABRA, EL SERVIDOR ESTA FUNCIONANDO
				System.out.println("Hilo "+numHilo +
						" recibio bien:"+eco);
				return true;  // DEVOLVEMOS TRUE YA QUE EL SERVIDOR FUNCIONA CORRECTAMENTE
			} //Fin del if
		} catch (IOException e) {
			// SI OCURRE UNA EXCEPCION, EL SERVIDOR COLAPSO
			return false;  // DEVOLVEMOS FALSE YA QUE EL SERVIDOR NO RESPONDE CORRECTAMENTE
		}
		// SI LLEGAMOS AQUI ES PORQUE EL SERVIDOR NO DEVOLVIO LA MISMA PALABRA
		return false;  // DEVOLVEMOS FALSE YA QUE EL SERVIDOR FALLO
	}

	// IMPLEMENTACION DEL METODO RUN DE LA INTERFAZ Runnable
	@Override
	public void run() {
		if (!servidorFunciona()){
			// SI EL SERVIDOR NO FUNCIONA, IMPRIMIMOS EL FALLO Y CAMBIAMOS LA BANDERA
			System.out.println("Fallo en el hilo "+
					numHilo);
			algoFallo=true;
		}
	} //Fin del run

	// METODO PARA CHEQUEAR SI HUBO UN FALLO EN EL CLIENTE
	public boolean huboFallo(){
		return algoFallo;  // DEVOLVEMOS TRUE SI HUBO UN FALLO, SINO FALSE
	}
} //Fin de la clase Cliente
