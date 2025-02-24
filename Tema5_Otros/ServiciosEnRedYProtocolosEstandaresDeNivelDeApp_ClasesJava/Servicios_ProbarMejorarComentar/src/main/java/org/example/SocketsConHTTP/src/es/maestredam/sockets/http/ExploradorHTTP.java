package org.example.SocketsConHTTP.src.es.maestredam.sockets.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ExploradorHTTP {
	// DIRECCION Y PUERTO DEL SERVIDOR, CONEXION, FLUJO DE LECTURA Y FLUJO DE ESCRITURA
	String direccion;
	int puerto;
	Socket conexion;
	BufferedReader flujoLectura;
	PrintWriter flujoEscritura;

	// CONSTRUCTOR PARA INICIALIZAR LOS PARAMETROS DE DIRECCION Y PUERTO
	public ExploradorHTTP(String direccion, int puerto)  {
		super();
		this.direccion = direccion;
		this.puerto = puerto;
		this.conexion = null;
		this.flujoEscritura = null;
		this.flujoLectura = null;
	}

	// METODO PRIVADO PARA CONECTAR AL SERVIDOR USANDO EL SOCKET
	private void conectar() throws IOException {
		// SI NO HAY CONEXION, SE REALIZA LA CONEXION
		if (this.conexion == null) {
			InetSocketAddress direccionInet = new InetSocketAddress(direccion, puerto);
			this.conexion = new Socket();
			this.conexion.connect(direccionInet); // SE CONECTA AL SERVIDOR
		}
	}

	// METODO PARA OBTENER EL FLUJO DE LECTURA (BUFFEREDREADER) DE LA CONEXION
	public BufferedReader obtenerFlujoLectura() {
		// SI YA SE HA CREADO EL FLUJO DE LECTURA, SE RETORNA
		if (this.flujoLectura != null) {
			return this.flujoLectura;
		}
		try {
			this.conectar(); // SE CONECTA AL SERVIDOR SI NO ESTA CONECTADO
			InputStream is = this.conexion.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			this.flujoLectura = new BufferedReader(isr); // CREA EL FLUJO DE LECTURA
		} catch (IOException e) {
			this.imprimirMensajeErrorIO(); // IMPRIME EL MENSAJE DE ERROR SI SE PRODUCE UNA EXCEPCION
			e.printStackTrace();
		}
		return this.flujoLectura;
	}

	// METODO PARA OBTENER EL FLUJO DE ESCRITURA (PRINTWRITER) DE LA CONEXION
	public PrintWriter obtenerFlujoEscritura() {
		// SI YA SE HA CREADO EL FLUJO DE ESCRITURA, SE RETORNA
		if (this.flujoEscritura != null) {
			return this.flujoEscritura;
		}
		try {
			this.conectar(); // SE CONECTA AL SERVIDOR SI NO ESTA CONECTADO
			OutputStream os = this.conexion.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			this.flujoEscritura = new PrintWriter(osw); // CREA EL FLUJO DE ESCRITURA
		} catch (IOException e) {
			this.imprimirMensajeErrorIO(); // IMPRIME EL MENSAJE DE ERROR SI SE PRODUCE UNA EXCEPCION
			e.printStackTrace();
		}
		return this.flujoEscritura;
	}

	// METODO PARA IMPRIMIR UN MENSAJE DE ERROR EN CASO DE FALLA DE I/O
	private void imprimirMensajeErrorIO() {
		String mensaje =
				"Hubo un error de I/O. Algunas posibles causas son:\n" +
						"1. No hay conexion a la red.\n" +
						"2. Aunque haya conexion quizas sea demasiado lenta.\n" +
						"3. La red funciona bien pero hay un fallo en el programa\n";
		System.out.println(mensaje);
	}

	// METODO PARA ENVIAR UNA SOLICITUD GET AL SERVIDOR
	public void enviarGET() {
		try {
			this.conectar(); // SE CONECTA AL SERVIDOR
			this.flujoEscritura = this.obtenerFlujoEscritura(); // OBTIENE EL FLUJO DE ESCRITURA
			this.flujoEscritura.println("GET / HTTP/1.1"); // ENVIA LA PETICION GET
			this.flujoEscritura.println("Host: " + this.direccion); // AGREGA EL HOST
			this.flujoEscritura.println(); // LINEA VACIA PARA FINALIZAR LA PETICION
			this.flujoEscritura.flush(); // FLUSH DEL FLUJO DE ESCRITURA
		} catch (IOException e) {
			this.imprimirMensajeErrorIO(); // IMPRIME EL MENSAJE DE ERROR SI SE PRODUCE UNA EXCEPCION
			e.printStackTrace();
		}
	}
}
