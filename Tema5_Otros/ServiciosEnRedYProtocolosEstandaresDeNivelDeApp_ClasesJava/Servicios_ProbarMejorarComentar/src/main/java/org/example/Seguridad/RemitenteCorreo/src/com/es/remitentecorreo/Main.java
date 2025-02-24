/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.Seguridad.RemitenteCorreo.src.com.es.remitentecorreo;

import java.io.FileNotFoundException;
import org.apache.commons.mail.EmailException;

/**
 *
 * @author usuario
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws EmailException, FileNotFoundException {

        // DEFINIMOS LOS CORREOS DE CCO (COPIA OCULTA) Y BCC (COPIA CIEGA)
        String[] cc = {"oscar761001@gmail.com", "ogomezgarcia@gmail.com"};
        String[] bcc = {"oscar761001@hotmail.com", "o_gom_gar@hotmail.com"};

        // CREAMOS UNA INSTANCIA DE LA CLASE REMITENTE CORREO CON LOS DATOS DEL SERVIDOR SMTP
        RemitenteCorreo rc = new RemitenteCorreo("smtp.googlemail.com",
                "profesor.oscar.gomez",
                "atlantis_3031");

        // DEFINIMOS LOS ARCHIVOS ADJUNTOS
        String[] adjuntos = {"/home/usuario/repos/servicios/textos/tema5.rst",
                "/home/usuario/repos/servicios/textos/tema4.rst"};

        // ENVIAMOS UN MENSAJE SIMPLE
        rc.enviarMensaje("Probando", "Hola prueba",
                "profesor.oscar.gomez@gmail.com", cc, bcc);

        // ENVIAMOS UN MENSAJE CON ADJUNTOS
        rc.enviarMensajeConAdjuntos("Probando", "Hola prueba",
                "profesor.oscar.gomez@gmail.com",
                cc, bcc, adjuntos);
    }
}
