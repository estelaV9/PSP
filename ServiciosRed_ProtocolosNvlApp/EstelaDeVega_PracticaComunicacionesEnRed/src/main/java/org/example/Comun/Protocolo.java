package org.example.Comun;

public class Protocolo {
    private final String terminador="\n";  // SE DEFINE EL TERMINADOR DE LAS LÍNEAS

    // DEVUELVE UN MENSAJE DE LA VERSIÓN DEL PROTOCOLO EN FORMATO DE CADENA
    public String getMensajeVersion(int version){
        Integer i=version;
        return i.toString()+terminador;
    }

    // OBTIENE EL NÚMERO DE VERSIÓN A PARTIR DE UN MENSAJE
    public int getNumVersion(String mensaje){
        Integer num=Integer.parseInt(mensaje);  // CONVIERTE EL MENSAJE A NÚMERO
        return num;
    }

    // DEVUELVE UN MENSAJE DE UNA CADENA CON UN TERMINADOR
    public String getMensaje(String cadena){
        return cadena+terminador;
    }
}

