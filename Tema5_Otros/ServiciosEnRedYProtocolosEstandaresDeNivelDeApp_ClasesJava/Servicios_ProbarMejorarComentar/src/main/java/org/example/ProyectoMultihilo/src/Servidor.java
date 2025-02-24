package org.example.ProyectoMultihilo.src;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	// METODO QUE INICIALIZA EL SERVIDOR Y GESTIONA LAS CONEXIONES
	public void servir(){

		// DECLARO EL SERVER SOCKET Y EL PUERTO EN EL QUE ESTARA ESCUCHANDO EL SERVIDOR
		ServerSocket serverSocket;
		final int PUERTO=9876;

		try {
			// CREO UN SERVER SOCKET PARA ESCUCHAR CONEXIONES EN EL PUERTO DEFINIDO
			serverSocket=new ServerSocket(PUERTO);

			// EL SERVIDOR ESTARA ESCUCHANDO CONEXIONES EN UN BUCLE INFINITO
			while (true){

				// ACEPTA UNA CONEXION DE UN CLIENTE
				Socket conexion;
				conexion=serverSocket.accept();

				// CREO UN HILO PARA MANEJAR LA CONEXION CON EL CLIENTE
				HiloConexion hiloConexion;
				hiloConexion=new HiloConexion(
						conexion);

				// CREO UN HILO Y LO INICIO PARA MANEJAR LA CONEXION
				Thread hilo=new Thread(hiloConexion);
				hilo.start();
			}
		} catch (IOException e) {
			// NO SE PUDO CREAR EL SERVER SOCKET, PODRIA SER POR FALTA DE PERMISOS

			// SE PUDO CREAR EL SERVER SOCKET PERO HUBO PROBLEMAS AL ENVIAR O RECIBIR DATOS

			// EL SERVICIO FUNCIONABA, PERO EL USUARIO INTERROMPIO EL PROCESO
		}
	}

	// METODO PRINCIPAL QUE INICIA EL SERVIDOR
	public static void main(String[] args){

		// CREO UNA INSTANCIA DEL SERVIDOR
		Servidor servidor;
		servidor=new Servidor();

		// INICIO EL SERVICIO DEL SERVIDOR
		servidor.servir();
	}
}