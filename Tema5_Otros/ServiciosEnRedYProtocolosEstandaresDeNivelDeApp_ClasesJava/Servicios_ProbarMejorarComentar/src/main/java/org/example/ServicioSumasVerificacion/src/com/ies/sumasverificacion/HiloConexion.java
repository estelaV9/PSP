package org.example.ServicioSumasVerificacion.src.com.ies.sumasverificacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloConexion implements Runnable{

	// SE DECLARAN LOS FLUJOS DE LECTURA Y ESCRITURA Y EL SOCKET
	BufferedReader bfr;
	PrintWriter pw;
	Socket socket;

	// CONSTRUCTOR QUE INICIALIZA EL SOCKET
	public HiloConexion(Socket socket){
		this.socket = socket;
	}

	/* METODO PARA PROCESAR LAS LINEAS RECIBIDAS POR EL CLIENTE,
	 * CALCULA LA SUMA SIMPLE DE CADA LINEA Y LA ENVIA DE VUELTA AL CLIENTE */
	public void procesarLineas()
			throws IOException {
		// LEEMOS EL NUMERO DE LINEAS QUE EL CLIENTE VA A ENVIAR
		String lineaNumero = bfr.readLine();
		Integer nLineas = Integer.parseInt(lineaNumero);

		// SE DECLARAN LOS ARREGLOS PARA ALMACENAR LAS LINEAS Y LOS RESULTADOS
		String[] lineas;
		int[] resultados;

		lineas = new String[nLineas];  // ARRAY PARA LAS LINEAS RECIBIDAS
		resultados = new int[nLineas];  // ARRAY PARA ALMACENAR LOS RESULTADOS DE LA SUMA SIMPLE

		// LEEMOS CADA LINEA Y CALCULAMOS LA SUMA SIMPLE
		for (int i = 0; i < nLineas; i++) {
			String linea = bfr.readLine();  // LEEMOS UNA LINEA
			lineas[i] = linea;  // ALMACENAMOS LA LINEA
			resultados[i] = Sumador.sumaSimple(linea);  // CALCULAMOS LA SUMA SIMPLE Y LA ALMACENAMOS
		}

		// ENVIO DE LOS RESULTADOS AL CLIENTE
		for (int i = 0; i < nLineas; i++) {
			pw.println(resultados[i]);  // ENVIAMOS EL RESULTADO DE CADA LINEA
			pw.flush();  // FLUSHEAMOS EL STREAM PARA ASEGURAR QUE SE ENVIE EL RESULTADO
		}
	}

	// METODO RUN QUE SE EJECUTA CUANDO SE INICIA EL HILO
	public void run() {
		try {
			// OBTENEMOS LOS FLUJOS DE LECTURA Y ESCRITURA A PARTIR DEL SOCKET
			bfr = Utilidades.getFlujoLectura(this.socket);
			pw = Utilidades.getFlujoEscritura(this.socket);

			// LLAMAMOS AL METODO PARA PROCESAR LAS LINEAS Y ENVIAR LOS RESULTADOS
			procesarLineas();
		} catch (IOException e) {
			// EN CASO DE UNA EXCEPCION, SE MANDA UN MENSAJE DE ERROR
			System.out.println("Hubo una interrupción");
		}
	}
}
