package org.example.Seguridad.JavaApplication3.src.com.ies.seguridad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocket;

public class Servidor {

    // METODO QUE ESCUCHA LAS CONEXIONES EN EL SERVIDOR
    public void escuchar(String rutaAlmacen, String clave)
            throws KeyManagementException, UnrecoverableKeyException,
            KeyStoreException, NoSuchAlgorithmException,
            CertificateException, IOException
    {
        // OBTENEMOS EL SOCKET SSL DEL SERVIDOR UTILIZANDO EL ALMACEN Y LA CLAVE
        SSLServerSocket socketServidor = ComunicacionesSeguras.getSSLServerSocket(rutaAlmacen, clave);

        // DECLARAMOS LOS FLUJOS DE ENTRADA Y SALIDA
        BufferedReader entrada;
        PrintWriter salida;

        // BUCLE INFINITO PARA ACEPTAR CONEXIONES
        while (true){
            // ESPERAMOS UNA CONEXION EN EL SOCKET DEL SERVIDOR
            Socket connRecibida = socketServidor.accept();
            System.out.println("Conexion segura recibida");

            // CREAMOS LOS FLUJOS DE ENTRADA Y SALIDA PARA LA CONEXION
            entrada = new BufferedReader(new InputStreamReader(connRecibida.getInputStream()));
            salida = new PrintWriter(new OutputStreamWriter(connRecibida.getOutputStream()));

            // LEEMOS LA LINEA ENVIADA POR EL CLIENTE
            String linea = entrada.readLine();

            // ENVIAMOS LA LONGITUD DE LA LINEA AL CLIENTE
            salida.println(linea.length());
            salida.flush();
        }
    }

    // METODO PRINCIPAL PARA INICIAR EL SERVIDOR
    public static void main(String[] a){
        try {
            // DEFINIMOS LA RUTA DEL ALMACEN DE CLAVES Y LA CLAVE
            String rutaAlmacen = "/home/usuario/repos/borrar/almacen.ks";
            String clave = "123456";

            // CREAMOS UNA INSTANCIA DEL SERVIDOR Y LO INICIAMOS
            Servidor s = new Servidor();
            s.escuchar(rutaAlmacen, clave);
        } catch (KeyManagementException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnrecoverableKeyException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyStoreException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CertificateException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
