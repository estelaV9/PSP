package org.example;

public class Protocolo {

    // DEFINICION DE UN TERMINADOR PARA LOS MENSAJES
    private final String terminador = "\n";

    // METODO QUE DEVUELVE UN MENSAJE DE VERSION CON EL TERMINADOR
    public String getMensajeVersion(int version) {
        Integer i = version; // CONVIERTE EL NUMERO A ENTERO
        return i.toString() + terminador; // CONCATENA EL NUMERO CON EL TERMINADOR Y LO RETORNA
    }

    // METODO ESTATICO QUE OBTIENE EL NUMERO DE VERSION A PARTIR DE UN MENSAJE
    public static int getNumVersion(String mensaje) {
        Integer num = Integer.parseInt(mensaje); // CONVIERTE EL MENSAJE A UN NUMERO ENTERO
        return num; // RETORNA EL NUMERO DE VERSION
    }

    // METODO QUE DEVUELVE UNA CADENA CON EL TERMINADOR AL FINAL
    public String getMensaje(String cadena) {
        return cadena + terminador; // CONCATENA LA CADENA CON EL TERMINADOR Y LA RETORNA
    }
}
