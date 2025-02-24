package org.example.ProyectoMultihilo.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloConexion implements Runnable{

	// VARIABLES PARA LA CONEXION Y LA COMUNICACION
	BufferedReader bfr;
	PrintWriter pw;
	Socket socket;

	// CONSTRUCTOR QUE INICIALIZA EL SOCKET
	public HiloConexion(Socket socket){
		this.socket=socket;
	}

	// METODO PARA PROCESAR EL MENSAJE RECIBIDO
	public void procesarMensaje()
			throws IOException{
		// LEO EL NUMERO ENVIADO POR EL CLIENTE
		String lineaNumero=bfr.readLine();

		// CONVIERTO LA LINEA LEIDA A UN ENTERO
		Integer num=Integer.parseInt(lineaNumero);

		// OBTENGO EL VALOR ABSOLUTO DEL NUMERO (EVITAMOS NUMEROS NEGATIVOS)
		num=Math.abs(num);

		// ITERO DESDE 2 HASTA EL NUMERO-1 PARA VER SI TIENE DIVISORES
		for (int i=2;i<num;i++){
			if (num%i==0){
				// SI ENCUENTRO UN DIVISOR, NO ES PRIMO Y LO COMUNICO AL CLIENTE
				pw.println("NO");
				pw.flush();
				return ;
			}
		}

		// SI EL BUCLE TERMINA ES PORQUE NO HAY DIVISORES
		// CONCLUSION: EL NUMERO ES PRIMO
		pw.println("SI");
		pw.flush();

		return ;
	}

	// METODO PRINCIPAL DEL HILO, ENCARGADO DE LA CONEXION Y LA COMUNICACION
	public void run() {
		try {
			// OBTENGO EL STREAM DE ENTRADA DEL SOCKET
			InputStream is=socket.getInputStream();

			// CONVIERTO EL STREAM DE ENTRADA EN UN LECTOR DE CARACTERES
			InputStreamReader isr;
			isr=new InputStreamReader(is);
			bfr=new BufferedReader(isr);

			// OBTENGO EL STREAM DE SALIDA DEL SOCKET
			OutputStream os=socket.getOutputStream();

			// CONVIERTO EL STREAM DE SALIDA EN UN ESCRITOR DE CARACTERES
			OutputStreamWriter osw;
			osw=new OutputStreamWriter(os);
			pw=new PrintWriter(osw);

			// INICIO EL PROCESO DE COMUNICACION EN UN BUCLE INFINITO
			while (true){
				// PROCESO EL MENSAJE RECIBIDO DEL CLIENTE
				procesarMensaje();
			}

		} catch (IOException e) {
			// SI HAY UNA INTERRUPCION EN LA CONEXION, IMPRIMO UN MENSAJE DE ERROR
			System.out.println(
					"Hubo una interrupcion");
		}

	}
}
