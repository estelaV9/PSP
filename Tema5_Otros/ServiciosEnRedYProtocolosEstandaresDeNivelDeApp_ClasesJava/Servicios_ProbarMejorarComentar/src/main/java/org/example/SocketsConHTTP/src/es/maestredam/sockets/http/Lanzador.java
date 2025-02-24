package org.example.SocketsConHTTP.src.es.maestredam.sockets.http;

import java.io.BufferedReader;
import java.io.IOException;

public class Lanzador {

	public static void main(String[] args) throws IOException {
		// SE CREA UNA INSTANCIA DE EXPLORADORHTTP CON LA DIRECCION Y PUERTO DEL SERVIDOR
		ExploradorHTTP explorador = new ExploradorHTTP("www.marca.com", 80);

		// SE IMPRIME UN MENSAJE QUE INFORMA QUE SE VA A ENVIAR UNA PETICION GET
		System.out.println("Enviando GET /");

		// SE ENVIA LA PETICION GET AL SERVIDOR
		explorador.enviarGET();

		// SE OBTIENE EL FLUJO DE LECTURA PARA LEER LA RESPUESTA DEL SERVIDOR
		BufferedReader flujoLectura = explorador.obtenerFlujoLectura();

		// SE LEEN LAS LINEAS DE LA RESPUESTA Y SE IMPRIMEN UNA POR UNA
		String linea = flujoLectura.readLine();
		while (linea != null) {
			System.out.println(linea); // SE IMPRIME CADA LINEA DE LA RESPUESTA
			linea = flujoLectura.readLine(); // SE LEE LA SIGUIENTE LINEA
		}
	}

}
