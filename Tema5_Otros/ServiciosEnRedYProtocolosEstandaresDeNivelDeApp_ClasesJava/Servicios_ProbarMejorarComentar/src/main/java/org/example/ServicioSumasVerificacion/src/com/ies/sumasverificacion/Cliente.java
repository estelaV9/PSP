package org.example.ServicioSumasVerificacion.src.com.ies.sumasverificacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Cliente {

	/* METODO QUE ENVIA UNA PETICION DE VERIFICACION DE CADENAS AL SERVIDOR
	 * Y RECIBE LAS SUMAS DE VERIFICACION O RESPUESTAS DEL SERVIDOR */
	public void verificarCadenas(
			BufferedReader bfr,
			PrintWriter pw) throws IOException
	{
		// SE ENVIA UN NUMERO Y DOS CADENAS AL SERVIDOR
		pw.println(2);  // ENVIA EL NUMERO DE CADENAS A VERIFICAR
		pw.println("ABC");  // ENVIA LA PRIMERA CADENA
		pw.println("ZZ");   // ENVIA LA SEGUNDA CADENA
		pw.flush();  // SE FLUSHEA EL STREAM PARA ASEGURAR QUE SE ENVIEN LOS DATOS

		// SE RECIBEN LAS RESPUESTAS DEL SERVIDOR
		String suma1 = bfr.readLine();  // LEEMOS LA PRIMERA SUMA DE VERIFICACION
		String suma2 = bfr.readLine();  // LEEMOS LA SEGUNDA SUMA DE VERIFICACION

		// SE MUESTRAN LAS RESPUESTAS RECIBIDAS
		System.out.println(suma1);
		System.out.println(suma2);
	}

	public static void main(String[] args) {
		Cliente cliente;
		cliente = new Cliente();

		// SE CREA UNA DIRECCION CON LA IP Y EL PUERTO DEL SERVIDOR
		InetSocketAddress direccion;
		direccion = new InetSocketAddress(
				"10.13.0.100", 9876);  // DIRECCION IP Y PUERTO DEL SERVIDOR

		Socket conexion;
		conexion = new Socket();  // SE CREA UN SOCKET PARA LA CONEXION

		try {
			// SE CONECTA EL SOCKET AL SERVIDOR EN LA DIRECCION Y PUERTO DADOS
			conexion.connect(direccion);

			BufferedReader bfr;
			// SE OBTIENE UN FLUJO DE LECTURA PARA LEER LOS DATOS DEL SERVIDOR
			bfr = Utilidades.getFlujoLectura(
					conexion);

			PrintWriter pw;
			// SE OBTIENE UN FLUJO DE ESCRITURA PARA ENVIAR DATOS AL SERVIDOR
			pw = Utilidades.getFlujoEscritura(conexion);

			// SE VERIFICAN LAS CADENAS ENVIANDO Y RECIBIENDO LOS RESULTADOS
			cliente.verificarCadenas(bfr, pw);

			// SE CIERRAN LOS FLUJOS Y EL SOCKET
			pw.close();
			bfr.close();
			conexion.close();

		} catch (IOException e) {
			// SE MANEJA EL ERROR SI NO SE PUEDE CONECTAR AL SERVIDOR
			// PUEDE DEBERSE A QUE EL SERVIDOR NO ESTA ENCENDIDO
			// O A QUE UN CORTAFUEGOS IMPIDE LA CONEXION
			e.printStackTrace();
		}
	}
}
