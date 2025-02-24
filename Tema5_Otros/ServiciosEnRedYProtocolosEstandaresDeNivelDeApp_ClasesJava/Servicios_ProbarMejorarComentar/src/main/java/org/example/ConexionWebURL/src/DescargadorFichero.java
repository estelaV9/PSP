package org.example.ConexionWebURL.src;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class DescargadorFichero {

	// METODO PARA DESCARGAR UN FICHERO DESDE UNA URL Y GUARDARLO CON UN NOMBRE ESPECIFICO
	public void descargarFichero(String urlDescarga, String nombreFichero) throws IOException{
		// SE CREA UN OBJETO URL CON LA URL DE DESCARGA
		URL url = new URL(urlDescarga);

		// SE OBTIENE EL FLUJO DE ENTRADA DESDE LA URL
		InputStream is = url.openStream();

		// SE CREA UN FLUJO DE SALIDA PARA GUARDAR EL FICHERO DESCARGADO
		FileOutputStream fos = new FileOutputStream(nombreFichero);

		// SE CREA UN BUFFER DE 1024 BYTES PARA LEER LOS DATOS
		byte[] buffer = new byte[1024];

		// SE LEE EL FICHERO EN BLOQUES DE 1024 BYTES Y SE ESCRIBE EN EL FICHERO DE SALIDA
		int bytesLeidos = is.read(buffer, 0, 1024);

		// SE MIENTRAS SE LEAN DATOS, SE ESCRIBEN EN EL FICHERO
		while (bytesLeidos != 0){
			fos.write(buffer, 0, bytesLeidos);
			// SE LEE OTRA PARTE DEL FICHERO
			bytesLeidos = is.read(buffer, 0, 1024);
		}

		// SE CIERRA EL FLUJO DE SALIDA
		fos.close();
	}

	// METODO PRINCIPAL PARA PROBAR EL DESCARGADOR
	public static void main(String[] args) throws IOException {
		// SE CREA UNA INSTANCIA DE LA CLASE DescargadorFichero
		DescargadorFichero d = new DescargadorFichero();

		// SE LLAMA AL METODO descargarFichero CON UNA URL Y UN NOMBRE DE FICHERO DE DESTINO
		d.descargarFichero("http://www.google.es", "pagina.html");
	}
}
