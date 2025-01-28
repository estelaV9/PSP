package org.example;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


// DEFINICIÓN DEL GESTOR DE DESCARGAS, UNA CLASE PARA GESTIONAR LA DESCARGA DE ARCHIVOS DESDE UNA URL

public class GestorDescargas {

    // MÉTODO PARA DESCARGAR UN ARCHIVO DESDE UNA URL Y GUARDARLO CON EL NOMBRE ESPECIFICADO
    public void descargarArchivo(
            String url_descargar,  // URL DESDE LA CUAL SE DESCARGARÁ EL ARCHIVO
            String nombreArchivo){  // NOMBRE DEL ARCHIVO DESTINO EN EL SISTEMA LOCAL

        // MOSTRAR UN MENSAJE EN CONSOLA INFORMANDO QUE SE ESTÁ DESCARGANDO
        System.out.println("Descargando "
                +url_descargar);

        try {
            // CREAR UN OBJETO URL A PARTIR DE LA URL PROPORCIONADA
            URL laUrl = new URL(url_descargar);

            // OBTENER UN FLUJO DE ENTRADA (INPUTSTREAM) DESDE LA URL
            InputStream is = laUrl.openStream();

            // CREAR UN FLUJO DE LECTURA DE CARACTERES A PARTIR DEL FLUJO DE BYTES
            InputStreamReader reader =
                    new InputStreamReader(is);

            // ENVOLVER EL FLUJO DE LECTURA EN UN BUFFEREDREADER PARA LEER LÍNEAS DE MANERA MÁS EFICIENTE
            BufferedReader bReader =
                    new BufferedReader(reader);

            // CREAR UN ESCRITOR DE ARCHIVO PARA GUARDAR EL ARCHIVO LOCALMENTE
            FileWriter escritorFichero =
                    new FileWriter(nombreArchivo);

            String linea;

            // LEER LAS LÍNEAS DEL FLUJO DE LA URL Y ESCRIBIRLAS EN EL ARCHIVO LOCAL
            while ((linea = bReader.readLine()) != null){
                escritorFichero.write(linea);  // ESCRIBIR CADA LÍNEA EN EL ARCHIVO
            }

            // CERRAR EL ESCRITOR Y LOS FLUJOS DE LECTURA PARA LIBERAR RECURSOS
            escritorFichero.close();
            bReader.close();
            reader.close();
            is.close();
        } catch (MalformedURLException e) {
            // CAPTURAR EXCEPCIÓN SI LA URL NO ES CORRECTA
            System.out.println("URL mal escrita!");
            return;
        } catch (IOException e) {
            // CAPTURAR EXCEPCIÓN SI HAY UN ERROR EN LA LECTURA DEL FICHERO
            System.out.println("Fallo en la lectura del fichero");
            return;
        }
    }

    // MÉTODO PRINCIPAL PARA INICIAR EL PROCESO DE DESCARGA
    public static void main (String[] argumentos){

        // CREAR UNA INSTANCIA DE GESTORDESCARGAS
        GestorDescargas gd = new GestorDescargas();

        // DEFINIR LA URL BASE DESDE LA CUAL SE DESCARGARÁN LOS ARCHIVOS
        String base =
                "http://10.13.0.20:8000" +
                        "/ServiciosProcesos/textos/";

        // INICIAR UN BUCLE PARA DESCARGAR CINCO ARCHIVOS CON NOMBRES SECUENCIALES
        for (int i = 1; i <= 5; i++){
            // GENERAR LA URL COMPLETA PARA CADA ARCHIVO
            String url = base + "tema" + i + ".rst";
            // LLAMAR AL MÉTODO PARA DESCARGAR EL ARCHIVO
            gd.descargarArchivo(url);
        }
    }
}
