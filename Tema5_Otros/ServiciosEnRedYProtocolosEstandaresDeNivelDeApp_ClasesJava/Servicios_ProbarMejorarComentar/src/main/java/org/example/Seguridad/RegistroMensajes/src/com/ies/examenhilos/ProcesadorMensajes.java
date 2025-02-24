package org.example.Seguridad.RegistroMensajes.src.com.ies.examenhilos;

import java.io.IOException;
import java.io.PrintWriter;

public class ProcesadorMensajes  {
	// SE DECLARA EL PRINTWRITER PARA ESCRIBIR EN EL FICHERO DE TRAZAS
	private PrintWriter pwFicheroTrazas;
	// CONTADOR PARA EL NUMERO DE MENSAJES ESCRITOS
	int numeroDeMensaje=0;
	// INSTANCIA DEL OBJETO DE PRUEBAS
	Pruebas p;

	// CONSTRUCTOR QUE INICIALIZA EL PROCESADOR DE MENSAJES Y ABRE EL FICHERO DE TRAZAS
	public ProcesadorMensajes(Pruebas pruebas, String ficheroTrazas) throws IOException {
		p=pruebas; // ASIGNO EL OBJETO DE PRUEBAS
		pwFicheroTrazas=Utilidades.getPrintWriter(ficheroTrazas); // OBTENGO EL PRINTWRITER PARA EL FICHERO
	}

	// METODO SINCRONIZADO PARA ESCRIBIR UN MENSAJE EN EL FICHERO Y ACTUALIZAR EL NUMERO DE MENSAJE
	public synchronized void escribirTraza(String mensaje){
		// ESCRIBO EL MENSAJE EN EL FICHERO DE TRAZAS
		Utilidades.escribirMensaje(this.pwFicheroTrazas, mensaje);
		// IMPRIMO EL NUMERO DE MENSAJE EN CONSOLA
		System.out.println(numeroDeMensaje);
		numeroDeMensaje++; // INCREMENTO EL NUMERO DE MENSAJE
		// ACTUALIZO EL NUMERO DE ESCRITURAS EN EL OBJETO DE PRUEBAS
		p.setEscrituras(this.numeroDeMensaje);
	}

	// METODO PARA CERRAR EL FICHERO DE TRAZAS Y ASEGURARSE DE QUE SE GUARDE TODO EL CONTENIDO
	public void cerrarFicheros(){
		// HAGO FLUSH PARA ASEGURAR QUE SE ESCRIBAN TODOS LOS MENSAJES EN EL FICHERO
		this.pwFicheroTrazas.flush();
		// CIERRO EL PRINTWRITER PARA EL FICHERO DE TRAZAS
		this.pwFicheroTrazas.close();
	}
}
