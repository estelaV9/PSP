package org.example.Seguridad.RemitenteCorreo.src.com.es.remitentecorreo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

/* PARA PODER USAR ESTA CLASE DEBES DISPONER
DEL JAR DE LA BIBLIOTECA APACHE COMMONS EMAIL Y
LAS EXTENSIONES JAVAMAIL ASI COMO CONFIGURAR TU ENTORNO
PARA QUE ENCUENTRE CORRECTAMENTE DICHAS BIBLIOTECAS.
PUEDES ENCONTRAR EL JAR EN HTTPS://COMMONS.APACHE.ORG/EMAIL/
*/
public class RemitenteCorreo {
    private String          servidorSMTP;   // ALMACENA EL NOMBRE DEL SERVIDOR SMTP
    private String          usuarioRemitente; // ALMACENA EL NOMBRE DE USUARIO DEL REMITENTE
    private String          claveRemitente;   // ALMACENA LA CLAVE DEL REMITENTE
    private MultiPartEmail  email;   // OBJETO EMAIL PARA MANEJAR EL MENSAJE MULTIPARTE

    // CONSTRUCTOR DE LA CLASE, INICIALIZA LOS VALORES DEL SERVIDOR, USUARIO Y CLAVE
    public RemitenteCorreo(String servidorSMTP,
                           String usuarioRemitente, String claveRemitente) {
        this.servidorSMTP       =   servidorSMTP;   // ASIGNA EL SERVIDOR SMTP
        this.usuarioRemitente   =   usuarioRemitente; // ASIGNA EL USUARIO DEL REMITENTE
        this.claveRemitente     =   claveRemitente;   // ASIGNA LA CLAVE DEL REMITENTE
        this.email              =   null;   // INICIALIZA EL OBJETO EMAIL COMO NULO
    } //FIN DEL CONSTRUCTOR

    // METODO PRIVADO QUE INICIALIZA LA CONEXION CON EL SERVIDOR DE CORREO
    private void iniciarConexionEmail(){
        email = new MultiPartEmail(); // CREAR UNA NUEVA INSTANCIA DE EMAIL MULTIPARTE
        /* SE INDICA EL SERVIDOR DEL REMITENTE */
        email.setHostName(servidorSMTP);
        /* HABITUALMENTE EL PUERTO 465 SE USA PARA SMTPS,
        EN EL QUE LA ENCRIPCION SE INICIA ANTES DE ENVIAR NADA */
        email.setSmtpPort(465);

        /* SE CONFIGURA LA AUTENTICACION CON EL USUARIO Y LA CLAVE */
        DefaultAuthenticator sistemaAutenticacion;
        sistemaAutenticacion = new DefaultAuthenticator(
                this.usuarioRemitente,
                this.claveRemitente );
        email.setAuthenticator(sistemaAutenticacion);

        /* SE INDICA QUE VAMOS A USAR EL CIFRADO AL INICIO DE LA CONEXION */
        email.setSSLOnConnect(true);
    }

    // METODO PRIVADO PARA CONFIGURAR LOS PARAMETROS BASICOS DEL MENSAJE
    private void configurarParametrosBasicos(
            String asunto, String textoEmail, String destinatario,
            String[] destinatariosCC, String[] destinatariosBCC) throws EmailException
    {
        /* SE INDICA EL ASUNTO DEL MENSAJE */
        email.setSubject(asunto);
        /* SE INDICA EL REMITENTE DEL CORREO */
        email.setFrom(this.usuarioRemitente + "@" + this.servidorSMTP);
        /* SE PASA EL TEXTO DEL MENSAJE */
        email.setMsg(textoEmail);
        /* SE CONFIGURA EL DESTINATARIO PRINCIPAL */
        email.addTo(destinatario);
        /* SE CONFIGURAN OTROS POSIBLES DESTINATARIOS CC Y BCC */
        if (destinatariosCC != null) {
            email.addCc(destinatariosCC);
        }
        if (destinatariosBCC != null) {
            email.addBcc(destinatariosBCC);
        }
    }

    // METODO PUBLICO PARA ENVIAR UN MENSAJE SIMPLE (SIN ADJUNTOS)
    public void enviarMensaje(String asunto, String textoEmail, String destinatario,
                              String[] destinatariosCC, String[] destinatariosBCC)
            throws EmailException
    {
        // INICIALIZAMOS LA CONEXION Y CONFIGURAMOS LOS PARAMETROS BASICOS
        iniciarConexionEmail();
        this.configurarParametrosBasicos(asunto, textoEmail,
                destinatario, destinatariosCC, destinatariosBCC);

        /* SE ENVIA EL MENSAJE */
        email.send();
    }

    // METODO PUBLICO PARA ENVIAR UN MENSAJE CON ADJUNTOS
    public void enviarMensajeConAdjuntos(String asunto, String textoEmail, String destinatario,
                                         String[] destinatariosCC, String[] destinatariosBCC,
                                         String[] listaRutasArchivo)
            throws EmailException, FileNotFoundException
    {
        /* SE CONFIGURAN LOS PARAMETROS BASICOS */
        iniciarConexionEmail();
        this.configurarParametrosBasicos(asunto, textoEmail,
                destinatario, destinatariosCC, destinatariosBCC);

        /* SE AÑADEN LOS ADJUNTOS */
        for (String ruta : listaRutasArchivo) {
            File fichero = new File(ruta); // CREAMOS UN OBJETO DE TIPO FILE CON LA RUTA DEL ADJUNTO
            this.email.attach(fichero); // SE ADJUNTA EL ARCHIVO AL MENSAJE
        }

        /* SE ENVIA EL MENSAJE */
        email.send();
    }
}
