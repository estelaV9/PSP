package org.example;

public class Sumador {

    // METODO QUE SUMA LOS VALORES ASCII DE CADA CARACTER DE UNA CADENA
    public static int sumaSimple(String cad) {
        int suma = 0; // INICIALIZA LA SUMA EN CERO

        // RECORRE CADA CARACTER DE LA CADENA
        for (int i = 0; i < cad.length(); i++) {
            suma += cad.codePointAt(i); // SUMA EL VALOR ASCII DEL CARACTER ACTUAL
        }

        return suma; // DEVUELVE EL RESULTADO DE LA SUMA
    }
}
