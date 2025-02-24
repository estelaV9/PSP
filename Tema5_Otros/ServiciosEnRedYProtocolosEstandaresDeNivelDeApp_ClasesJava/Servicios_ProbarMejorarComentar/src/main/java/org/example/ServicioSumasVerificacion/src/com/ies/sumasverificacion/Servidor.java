package org.example.ServicioSumasVerificacion.src.com.ies.sumasverificacion;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	// METODO QUE INICIA EL SERVIDOR Y SE MANTIENE A LA ESPERA DE CONEXIONES
	public void servir(){
		ServerSocket serverSocket;
		final int PUERTO = 9876;  // PUERTO EN EL QUE EL SERVIDOR ESTARA ESCUCHANDO
		try {
			// SE CREA UN SERVER SOCKET QUE ESCUCHA EN EL PUERTO 9876
			serverSocket = new ServerSocket(PUERTO);

			// BUCLE INFINITO QUE SE ENCARGA DE ACEPTAR CONEXIONES Y CREAR HILOS PARA MANEJARLAS
			while (true){
				Socket conexion;
				// SE ACEPTA UNA CONEXION DE UN CLIENTE
				conexion = serverSocket.accept();

				// SE CREA UN HILO DE CONEXION PARA MANEJAR LA CONEXION ESTABLECIDA
				HiloConexion hiloConexion;
				hiloConexion = new HiloConexion(conexion);

				// SE CREA UN HILO Y SE INICIA
				Thread hilo = new Thread(hiloConexion);
				hilo.start();
			}
		} catch (IOException e) {
			// MANEJO DE ERRORES: SI NO SE PUEDE CREAR EL SERVER SOCKET O SI SURGEN PROBLEMAS
			// AL ENVIAR O RECIBIR DATOS
			// NO SE PUDO CREAR EL SERVER SOCKET POR FALTA DE PERMISOS O PROBLEMAS DE CONEXION
			// SE PUDO CREAR EL SOCKET, PERO NO SE PUDO ESTABLECER UNA COMUNICACION EXITOSA
			// EL SERVIDOR FUE INTERUMPIDO O CERRADO POR EL USUARIO
		}
	}

	// METODO PRINCIPAL PARA INICIAR EL SERVIDOR
	public static void main(String[] args){
		// SE CREA UNA INSTANCIA DEL SERVIDOR Y SE INICIA EL SERVICIO
		Servidor servidor;
		servidor = new Servidor();
		servidor.servir();
	}
}
