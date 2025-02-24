package org.example.Seguridad.JavaApplication3.src.com.ies.seguridad;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

public class ComunicacionesSeguras {

    // METODO QUE CREA UN SOCKET SSL PARA EL CLIENTE
    public static SSLSocket getSSLSocket
    (String rutaAlmacen, String clave) throws KeyStoreException,
            FileNotFoundException,
            IOException,
            NoSuchAlgorithmException,
            CertificateException,
            CertificateException,
            KeyManagementException
    {
        SSLSocket socketSeguro=null;

        // PASO 1: CARGAMOS EL ALMACEN DE CLAVES (KEYSTORE)
        KeyStore almacen=KeyStore.getInstance(KeyStore.getDefaultType());
        FileInputStream fis=new FileInputStream(rutaAlmacen);
        almacen.load(fis, clave.toCharArray());


        // PASO 2: CREAMOS UN TRUSTMANAGER PARA GESTIONAR LOS CERTIFICADOS DE CONFIANZA
        TrustManagerFactory fabricaGestoresConfianza;
        fabricaGestoresConfianza=TrustManagerFactory.getInstance(
                TrustManagerFactory.getDefaultAlgorithm()
        );
        fabricaGestoresConfianza.init(almacen);

        // PASO 3: CREAMOS UN CONTEXTO SSL UTILIZANDO EL TRUSTMANAGER
        SSLContext contextoSSL=SSLContext.getInstance("TLS");
        contextoSSL.init(null,
                fabricaGestoresConfianza.getTrustManagers()
                , null);

        // PASO 4: OBTENEMOS UN SOCKET SSL PARA EL CLIENTE
        SSLSocketFactory fabricaSockets=(SSLSocketFactory)
                SSLSocketFactory.getDefault();
        socketSeguro=(SSLSocket) fabricaSockets.createSocket();
        return socketSeguro;
    }

    // METODO QUE CREA UN SOCKET SSL PARA EL SERVIDOR
    public static SSLServerSocket getSSLServerSocket
    (String rutaAlmacen, String clave) throws KeyStoreException, FileNotFoundException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException, KeyManagementException
    {
        SSLServerSocket socketServidorSeguro=null;

        // PASO 1: CARGAMOS EL ALMACEN DE CLAVES (KEYSTORE) PARA EL SERVIDOR
        KeyStore almacen;
        almacen=KeyStore.getInstance( KeyStore.getDefaultType() );
        FileInputStream ficheroAlmacen;
        ficheroAlmacen=new FileInputStream(rutaAlmacen);
        almacen.load ( ficheroAlmacen, clave.toCharArray());

        // PASO 2: CREAMOS UN KEYMANAGER PARA GESTIONAR LAS CLAVES DEL SERVIDOR
        KeyManager administradorClaves;
        KeyManagerFactory fabricaAdministradores;
        fabricaAdministradores=KeyManagerFactory.getInstance(
                KeyManagerFactory.getDefaultAlgorithm()
        );
        fabricaAdministradores.init(almacen, clave.toCharArray());


        // PASO 3: CREAMOS UN CONTEXTO SSL UTILIZANDO EL KEYMANAGER
        SSLContext contextoSSL;
        contextoSSL=SSLContext.getInstance("TLS");
        contextoSSL.init(fabricaAdministradores.getKeyManagers(),
                null, null);


        // PASO 4: OBTENEMOS UN SOCKET SSL PARA EL SERVIDOR
        SSLServerSocketFactory fabricaSockets;
        fabricaSockets=(SSLServerSocketFactory)
                SSLServerSocketFactory.getDefault();
        socketServidorSeguro=(SSLServerSocket)
                fabricaSockets.createServerSocket(9876);
        return socketServidorSeguro;

    }

    // METODO PRINCIPAL PARA INICIAR EL SERVIDOR SSL
    public static void main(String[] args) throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, FileNotFoundException, CertificateException, UnrecoverableKeyException, KeyManagementException {
        String ruta="C:\\users\\ogomez\\documents\\almacen.ks"; // RUTA AL ALMACEN DE CLAVES
        String clave="123456"; // CLAVE DEL ALMACEN

        // CREAMOS EL SOCKET SSL DEL SERVIDOR
        SSLServerSocket socketSeguro;
        socketSeguro=getSSLServerSocket(ruta, clave);
    }

}
