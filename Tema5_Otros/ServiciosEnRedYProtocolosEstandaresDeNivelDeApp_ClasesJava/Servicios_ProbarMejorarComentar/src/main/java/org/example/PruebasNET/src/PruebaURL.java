package org.example.PruebasNET.src;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;

public class PruebaURL {

	public static void main(String[] args) {

		// DEFINO LA URL DE LA QUE QUIERO OBTENER EL CONTENIDO
		URL url;
		String direccion="https://www.youtube.com/results?search_query=cristiano+ronaldo";

		try {
			// CREO UN OBJETO URL CON LA DIRECCION DEFINIDA
			url=new URL(direccion);

			// ABRO UN STREAM DE ENTRADA PARA LEER EL CONTENIDO DE LA URL
			InputStream is=url.openStream();

			// CONVIERTO EL STREAM DE ENTRADA A UN LECTOR DE CARACTERES
			InputStreamReader isr;
			isr=new InputStreamReader(is);

			// CREO UN BUFFEREDREADER PARA LEER LAS LINEAS DE LA PAGINA
			BufferedReader bfr;
			bfr=new BufferedReader(isr);

			// DECLARO UN STRING PARA ALMACENAR CADA LINEA LEIDA
			String linea;

			// CREO UN ARCHIVO LOCAL PARA GUARDAR EL CONTENIDO DESCARGADO
			FileWriter fw=new FileWriter("cr.html");
			PrintWriter pw=new PrintWriter(fw);

			// LEYO EL CONTENIDO DE LA PAGINA Y LO GUARDO EN EL ARCHIVO LOCAL
			while ((linea=bfr.readLine())!=null){
				pw.print(linea); // ESCRIBO CADA LINEA EN EL ARCHIVO
			}
		} catch (MalformedURLException e) {
			// SE PRODUJO UN ERROR EN LA FORMACION DE LA URL
			e.printStackTrace();
		} catch (IOException e) {
			// SE PRODUJO UN ERROR AL LEER O ESCRIBIR EN LOS STREAMS
			e.printStackTrace();
		}

		// EL FICHERO ESTA DESCARGADO Y GUARDADO COMO "cr.html"
	}
}
