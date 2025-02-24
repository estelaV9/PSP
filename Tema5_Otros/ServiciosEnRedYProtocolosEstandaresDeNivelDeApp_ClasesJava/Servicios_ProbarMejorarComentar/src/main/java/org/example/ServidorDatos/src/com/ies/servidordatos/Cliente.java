package org.example.ServidorDatos.src.com.ies.servidordatos;

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

public class Cliente {

	/* METODO INTERACTUAR: ESTE METODO SE ENCARGARIA DE GESTIONAR LA INTERACCION CON EL SERVIDOR */
	public void interactuar(BufferedReader bfr, PrintWriter pw) throws IOException {
		// NO SE IMPLEMENTA NINGUNA ACCION EN ESTE METODO POR EL MOMENTO
	}

	/* METODO CREARCLIENTE: SOLICITA LOS DATOS AL USUARIO Y LOS ENVIA AL SERVIDOR PARA CREAR UN NUEVO CLIENTE */
	public void crearCliente(BufferedReader bfr, PrintWriter pw) throws IOException {
		System.out.println("ESCRIBE UN CODIGO ");
		String id;
		id = System.console().readLine();  // LEER EL CODIGO INGRESADO POR EL USUARIO

		System.out.println("ESCRIBE UN NOMBRE ");
		String nombre;
		nombre = System.console().readLine();  // LEER EL NOMBRE INGRESADO POR EL USUARIO

		pw.println("CREATE");  // ENVIAR AL SERVIDOR QUE SE DESEA CREAR UN NUEVO CLIENTE
		pw.println(id);  // ENVIAR EL CODIGO AL SERVIDOR
		pw.println(nombre);  // ENVIAR EL NOMBRE AL SERVIDOR
		pw.flush();  // FLUSH PARA ASEGURARSE DE QUE LOS DATOS SE ENVIE
	}

	/* METODO CONSULTAR: RECUPERA Y MUESTRA LA LISTA DE EMPLEADOS DEL SERVIDOR */
	public void consultar(BufferedReader bfr, PrintWriter pw) throws IOException {
		System.out.println("RECUPERANDO DATOS");

		String num = bfr.readLine();  // LEER EL NUMERO DE EMPLEADOS DEL SERVIDOR
		Integer nEmpleados = Integer.parseInt(num);  // CONVERTIR EL NUMERO A ENTERO

		for (int i = 0; i < nEmpleados; i++) {
			String id = bfr.readLine();  // LEER EL ID DE CADA EMPLEADO
			String nombre = bfr.readLine();  // LEER EL NOMBRE DE CADA EMPLEADO
			System.out.println(id + ": " + nombre);  // IMPRIMIR LOS DATOS DEL EMPLEADO
		}
	}

	public static void main(String[] args) {
		Cliente cliente;
		cliente = new Cliente();

		// ESTABLECER LA DIRECCION IP Y EL PUERTO DEL SERVIDOR
		InetSocketAddress direccion;
		direccion = new InetSocketAddress("10.13.0.100", 9876);

		Socket conexion;
		conexion = new Socket();

		try {
			conexion.connect(direccion);  // CONECTAR AL SERVIDOR

			// OBTENER LOS FLUJOS DE LECTURA Y ESCRITURA PARA LA COMUNICACION CON EL SERVIDOR
			BufferedReader bfr;
			bfr = Utilidades.getFlujoLectura(conexion);
			PrintWriter pw;
			pw = Utilidades.getFlujoEscritura(conexion);

			// INICIAR LA INTERACCION CON EL SERVIDOR
			cliente.interactuar(bfr, pw);

			// CERRAR LOS RECURSOS DESPUES DE LA INTERACCION
			pw.close();
			bfr.close();
			conexion.close();

		} catch (IOException e) {
			// MANEJO DE ERRORES: PUEDE SER QUE EL SERVIDOR NO ESTE ENCENDIDO
			// O QUE HAYA UN CORTAFUEGOS IMPIDIENDO LA CONEXION
		}
	}
}
