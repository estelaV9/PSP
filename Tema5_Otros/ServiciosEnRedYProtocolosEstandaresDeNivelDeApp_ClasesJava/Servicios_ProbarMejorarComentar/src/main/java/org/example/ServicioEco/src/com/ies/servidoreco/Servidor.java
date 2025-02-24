package org.example.ServicioEco.src.com.ies.servidoreco;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	public void servir(){
		// IMPRIME MENSAJE INDICANDO QUE EL SERVIDOR ESTA ACTIVO
		System.out.println("Servidor activo!");

		ServerSocket serverSocket;
		final int PUERTO = 9876;  // DEFINIMOS EL PUERTO EN EL QUE EL SERVIDOR ESTARA ESCUCHANDO
		try {
			// CREO UN SERVER SOCKET PARA ESCUCHAR EN EL PUERTO ESPECIFICADO
			serverSocket = new ServerSocket(PUERTO);

			// BUCLE INFINITO PARA ACEPTAR CONEXIONES DE CLIENTES
			while (true){
				Socket conexion;
				// ACEPTAMOS UNA NUEVA CONEXION DE CLIENTE
				conexion = serverSocket.accept();

				HiloConexion hiloConexion;
				// CREAMOS UN NUEVO HILO DE CONEXION PARA MANEJAR LA CONEXION CON EL CLIENTE
				hiloConexion = new HiloConexion(conexion);

				// CREAMOS UN HILO Y LO INICIAMOS
				Thread hilo = new Thread(hiloConexion);
				hilo.start();
			}
		} catch (IOException e) {
			// NO SE PUDO CREAR EL SERVER SOCKET PORQUE NO TENEMOS PERMISOS O HAY UN ERROR EN LA CONEXION
			// SE PUDO CREAR PERO NO FUIMOS CAPACES DE ENVIAR O RECIBIR NADA
			// O EL USUARIO INTERROMPIO EL PROCESO
			System.out.println("Error en conexion "+
					"o al crear los hilos o al procesar E/S");
		}
	}

	public static void main(String[] args){
		Servidor servidor;
		servidor = new Servidor();
		// INICIAMOS EL SERVIDOR
		servidor.servir();
	}
}
