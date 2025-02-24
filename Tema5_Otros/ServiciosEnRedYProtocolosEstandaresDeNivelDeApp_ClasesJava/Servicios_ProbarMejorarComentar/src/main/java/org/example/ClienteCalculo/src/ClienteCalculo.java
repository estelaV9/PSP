package org.example.ClienteCalculo.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClienteCalculo {

	// METODO QUE DEVUELVE UN BUFFEREDREADER A PARTIR DE UN INPUTSTREAM
	public static BufferedReader getBufferedReader(InputStream is) {
		InputStreamReader isr;
		isr = new InputStreamReader(is);
		BufferedReader bfr;
		bfr = new BufferedReader(isr);
		return bfr;  // DEVUELVE EL BUFFEREDREADER
	}

	// METODO QUE DEVUELVE UN PRINTWRITER A PARTIR DE UN OUTPUTSTREAM
	public static PrintWriter getPrintWriter(OutputStream os) {
		OutputStreamWriter osw;
		osw = new OutputStreamWriter(os);
		PrintWriter pw;
		pw = new PrintWriter(osw);
		return pw;  // DEVUELVE EL PRINTWRITER
	}

	// METODO PRINCIPAL QUE INICIA EL CLIENTE Y REALIZA LA CONEXION CON EL SERVIDOR
	public static void main(String[] args) {
		String ipServidor = "10.13.0.3";  // IP DEL SERVIDOR
		int puerto = 9876;  // PUERTO DEL SERVIDOR
		InetSocketAddress direccion;
		direccion = new InetSocketAddress(ipServidor, puerto);  // DIRECCION DEL SERVIDOR

		Socket socket = new Socket();  // SE CREA UN NUEVO SOCKET
		try {
			// SE CONECTA AL SERVIDOR
			socket.connect(direccion);
			InputStream is = socket.getInputStream();  // SE OBTIENE EL INPUTSTREAM DEL SOCKET
			OutputStream os = socket.getOutputStream();  // SE OBTIENE EL OUTPUTSTREAM DEL SOCKET

			// SE PREPARAN LOS DATOS A ENVIAR AL SERVIDOR
			byte[] op = "+\n".getBytes();  // OPERACION A REALIZAR
			byte[] num1 = "12\n".getBytes();  // PRIMER NUMERO
			byte[] num2 = "21\n".getBytes();  // SEGUNDO NUMERO

			// SE ENVIAN LOS DATOS AL SERVIDOR
			os.write(op);
			os.write(num1);
			os.write(num2);
			os.flush();  // SE HACE FLUSH PARA ASEGURARSE DE QUE LOS DATOS SE ENVÍAN

			BufferedReader bfr;
			bfr = getBufferedReader(is);  // SE CREA EL BUFFEREDREADER PARA LEER LA RESPUESTA DEL SERVIDOR
			String linea = bfr.readLine();  // SE LEE LA RESPUESTA DEL SERVIDOR
			System.out.println(linea);  // SE MUESTRA LA RESPUESTA EN LA CONSOLA

		} catch (IOException e) {
			// SI HAY UN ERROR EN LA CONEXION, SE MUESTRA UN MENSAJE DE ERROR
			System.out.println(
					"No se estableció conexión con el servidor en " + ipServidor
			);
		}
	}
}
