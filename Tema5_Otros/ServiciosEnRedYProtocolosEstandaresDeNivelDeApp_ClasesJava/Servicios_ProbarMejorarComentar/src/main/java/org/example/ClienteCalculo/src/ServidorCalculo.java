package org.example.ClienteCalculo.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorCalculo {

	public static void main(String[] args) {
		ServerSocket socket;  // SE DECLARA EL SOCKET DEL SERVIDOR
		Socket cliente;  // SE DECLARA EL SOCKET PARA EL CLIENTE
		final int PUERTO = 9876;  // SE DEFINE EL PUERTO DE ESCUCHA

		try {
			socket = new ServerSocket(PUERTO);  // SE CREA EL SERVER SOCKET QUE ESCUCHARA EN EL PUERTO 9876

			while (true) {
				cliente = socket.accept();  // SE ACEPTA UNA CONEXION DE UN CLIENTE
				BufferedReader bfr;  // SE DECLARA UN BUFFEREDREADER PARA LEER LOS DATOS DE ENTRADA
				PrintWriter pw;  // SE DECLARA UN PRINTWRITER PARA ENVIAR DATOS AL CLIENTE

				// SE OBTIENEN LOS STREAMS DE ENTRADA Y SALIDA DEL SOCKET DEL CLIENTE
				InputStream is = cliente.getInputStream();
				OutputStream os = cliente.getOutputStream();

				// SE CREAN LOS FLUJOS DE ENTRADA Y SALIDA PARA LA LECTURA Y ESCRITURA DE DATOS
				bfr = ClienteCalculo.getBufferedReader(is);
				pw = ClienteCalculo.getPrintWriter(os);

				// SE LEE LA OPERACION Y LOS DOS NUMEROS ENVIADOS POR EL CLIENTE
				String op = bfr.readLine();  // SE LEE LA OPERACION (POR EJEMPLO, "+", "-", etc.)
				String n1 = bfr.readLine();  // SE LEE EL PRIMER NUMERO
				String n2 = bfr.readLine();  // SE LEE EL SEGUNDO NUMERO

				// SE MUESTRA EN CONSOLA LA OPERACION RECIBIDA
				System.out.println(n1 + op + n2);

				// AQUI SE PODRIA REALIZAR EL CALCULO DE LA OPERACION Y DEVOLVER EL RESULTADO AL CLIENTE
				// SIN EMBARGO, EN EL CODIGO ACTUAL NO SE REALIZA NINGUN CALCULO
			}

		} catch (IOException e) {
			e.printStackTrace();  // SE IMPRIME EL ERROR EN CASO DE EXCEPCION
		}
	}
}
