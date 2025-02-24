package org.example.Seguridad.JavaApplication3.src.com.ies.seguridad;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.rmi.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;

public class OtroCliente {

    SSLSocket conexion;

    // CONSTRUCTOR QUE INICIALIZA LA CONEXION SSL
    public OtroCliente(String almacen, String clave, String ip, int puerto)
            throws UnknownHostException, IOException,
            KeyManagementException, NoSuchAlgorithmException,
            KeyStoreException, FileNotFoundException, CertificateException
    {
        // OBTENEMOS EL SOCKET SSL UTILIZANDO EL METODO DE COMUNICACIONES SEGURAS
        conexion = ComunicacionesSeguras.getSSLSocket(almacen, clave);

        // DEFINIMOS LA DIRECCION IP Y EL PUERTO DEL SERVIDOR
        InetSocketAddress direccion = new InetSocketAddress(ip, puerto);

        // CONECTAMOS EL SOCKET A LA DIRECCION
        conexion.connect(direccion);
    }

    /* ENVIA UN MENSAJE DE PRUEBA PARA VERIFICAR QUE LA CONEXION
     * SSL ES CORRECTA */
    public void conectar() throws IOException {
        System.out.println("Iniciando..");

        // CREAMOS LOS FLUJOS DE ENTRADA Y SALIDA PARA LA CONEXION
        BufferedReader entrada;
        PrintWriter salida;

        // FLUJO DE ENTRADA PARA LEER DATOS DEL SOCKET
        entrada = new BufferedReader(new InputStreamReader(conexion.getInputStream()));

        // FLUJO DE SALIDA PARA ENVIAR DATOS AL SOCKET
        salida = new PrintWriter(new OutputStreamWriter(conexion.getOutputStream()));

        // ENVIAMOS UN MENSAJE DE PRUEBA PARA VERIFICAR LA CONEXION
        salida.println("1234567890");
        salida.flush();

        // LEEMOS LA RESPUESTA DEL SERVIDOR, QUE DEBERIA DEVOLVER LA LONGITUD DEL MENSAJE
        String num = entrada.readLine();

        // CONVERTIMOS EL NUMERO RECIBIDO A UN ENTERO Y MOSTRAMOS LA LONGITUD
        int longitud = Integer.parseInt(num);
        System.out.println("La longitud devuelta es: " + longitud);
    }

    // METODO PRINCIPAL QUE INICIA LA CONEXION Y MANEJA EXCEPCIONES
    public static void main(String[] a) {
        try {
            // RUTA AL ALMACEN DE CLAVES Y CLAVE PARA ACCEDER A EL
            String rutaAlmacen = "/home/usuario/repos/borrar/almacen.ks";
            String clave = "123456";

            // CREAMOS UNA INSTANCIA DE OTROCLIENTE Y ESTABLECEMOS LA CONEXION
            OtroCliente o = new OtroCliente(rutaAlmacen, clave, "127.0.0.1", 9876);
            o.conectar(); // LLAMAMOS AL METODO DE CONEXION

        } catch (KeyManagementException ex) {
            // CAPTURAMOS Y REGISTRAMOS CUALQUIER EXCEPCION DE GESTION DE CLAVES
            Logger.getLogger(OtroCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyStoreException ex) {
            // CAPTURAMOS Y REGISTRAMOS CUALQUIER EXCEPCION RELACIONADA CON EL ALMACEN DE CLAVES
            Logger.getLogger(OtroCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            // CAPTURAMOS Y REGISTRAMOS CUALQUIER EXCEPCION RELACIONADA CON ALGORITMOS NO SOPORTADOS
            Logger.getLogger(OtroCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CertificateException ex) {
            // CAPTURAMOS Y REGISTRAMOS CUALQUIER EXCEPCION DE CERTIFICADO
            Logger.getLogger(OtroCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            // CAPTURAMOS Y REGISTRAMOS CUALQUIER EXCEPCION DE ENTRADA/SELECCION DE FLUJOS
            Logger.getLogger(OtroCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
