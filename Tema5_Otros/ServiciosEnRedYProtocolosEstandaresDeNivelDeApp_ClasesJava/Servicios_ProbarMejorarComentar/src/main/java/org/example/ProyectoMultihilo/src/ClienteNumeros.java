package org.example.ProyectoMultihilo.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Random;

public class ClienteNumeros {

	// METODO PARA REALIZAR UNA PETICION AL SERVIDOR Y OBTENER UNA RESPUESTA
	public void preguntarPorAlgo(
			BufferedReader bfr,
			PrintWriter pw) throws IOException
	{
		// CREO UN GENERADOR DE NUMEROS ALEATORIOS
		Random generador=new Random();

		// GENERO UN NUMERO ALEATORIO
		int numero=generador.nextInt();

		// ENVIO EL NUMERO AL SERVIDOR
		pw.println(numero);
		pw.flush(); // ME ASEGURO DE QUE EL DATO SE ENVIE INMEDIATAMENTE

		// RECIBO LA RESPUESTA DEL SERVIDOR
		String respuestaServidor;
		respuestaServidor=bfr.readLine();

		// IMPRIMO EN PANTALLA LA PREGUNTA Y LA RESPUESTA DEL SERVIDOR
		System.out.println("Pregunte por "+
				numero + " y el servidor dijo "+
				respuestaServidor);
	}

	public static void main(String[] args) {

		// CREO UNA INSTANCIA DEL CLIENTE
		ClienteNumeros cliente;
		cliente=new ClienteNumeros();

		// DEFINO LA DIRECCION Y PUERTO DEL SERVIDOR
		InetSocketAddress direccion;
		direccion=new InetSocketAddress(
				"10.13.0.100", 9876);

		// CREO UNA INSTANCIA DE UN SOCKET PARA LA CONEXION
		Socket conexion;
		conexion=new Socket();

		try {
			// ESTABLEZCO LA CONEXION CON EL SERVIDOR
			conexion.connect(direccion);

			// CREO EL STREAM DE ENTRADA PARA LEER LA RESPUESTA DEL SERVIDOR
			InputStream is;
			is=conexion.getInputStream();

			// CONVIERTO EL STREAM DE ENTRADA EN UN LECTOR DE CARACTERES
			InputStreamReader isr;
			isr=new InputStreamReader(is);

			// CREO UN BUFFEREDREADER PARA LEER LAS LINEAS DE RESPUESTA
			BufferedReader bfr;
			bfr=new BufferedReader(isr);

			// CREO EL STREAM DE SALIDA PARA ENVIAR DATOS AL SERVIDOR
			OutputStream os;
			os=conexion.getOutputStream();

			// CONVIERTO EL STREAM DE SALIDA EN UN ESCRITOR DE CARACTERES
			OutputStreamWriter osw;
			osw=new OutputStreamWriter(os);

			// CREO UN PRINTWRITER PARA ENVIAR LINEAS AL SERVIDOR
			PrintWriter pw;
			pw=new PrintWriter(osw);

			// LLAMO AL METODO QUE REALIZA LA PETICION Y MUESTRA LA RESPUESTA
			cliente.preguntarPorAlgo(bfr, pw);

			// CIERRO LOS FLUJOS Y LA CONEXION
			pw.close();
			bfr.close();
			conexion.close();
		} catch (IOException e) {
			// QUIZA EL SERVIDOR NO ESTA ENCENDIDO
			// QUIZA LO ESTE PERO SU CORTAFUEGOS IMPIDE CONEXIONES
			// ...
		}
	}
}
