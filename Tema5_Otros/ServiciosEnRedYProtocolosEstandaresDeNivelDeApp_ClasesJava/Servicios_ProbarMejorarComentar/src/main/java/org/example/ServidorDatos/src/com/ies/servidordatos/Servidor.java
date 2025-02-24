package org.example.ServidorDatos.src.com.ies.servidordatos;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	// METODO QUE SE ENCARGA DE INICIAR EL SERVICIO DEL SERVIDOR
	public void servirDatos(){
		ServerSocket serverSocket;  // DECLARACION DEL SOCKET DEL SERVIDOR
		final int PUERTO = 9876;    // PUERTO DONDE EL SERVIDOR ESTARA ESCUCHANDO LAS CONEXIONES
		try {
			// SE CREA EL SERVER SOCKET EN EL PUERTO DEFINIDO
			serverSocket = new ServerSocket(PUERTO);

			// EL SERVIDOR ESTARÁ EN UN BUCLE ESCUCHANDO CONSTANTEMENTE LAS CONEXIONES
			while (true) {
				// ACEPTAMOS UNA CONEXION DE UN CLIENTE
				Socket conexion;
				conexion = serverSocket.accept();

				// CREAMOS UN NUEVO HILO DE CONEXION PARA MANEJAR LA COMUNICACION CON EL CLIENTE
				HiloConexion hiloConexion;
				hiloConexion = new HiloConexion(conexion);

				// INICIAMOS UN NUEVO HILO PARA GESTIONAR LA CONEXION
				Thread hilo = new Thread(hiloConexion);
				hilo.start();  // INICIAMOS EL HILO
			}
		} catch (IOException e) {
			// SI HUBO UN ERROR AL CREAR EL SOCKET O DURANTE LA COMUNICACION, SE MANEJA AQUÍ
			// PUEDE SER QUE NO TENGAMOS PERMISOS O QUE HAYA UN PROBLEMA EN LA RED O EN EL CLIENTE
			// SE PUEDE IMPRESIONAR EL ERROR AQUI O TOMAR OTRA ACCION SEGUN EL CASO
		}
	}

	// METODO PRINCIPAL QUE INICIA EL SERVIDOR
	public static void main(String[] args){
		Servidor servidor;  // CREAMOS UNA INSTANCIA DEL SERVIDOR
		servidor = new Servidor();  // INICIALIZAMOS EL SERVIDOR
		servidor.servirDatos();  // INICIAMOS EL SERVICIO
	}
}
