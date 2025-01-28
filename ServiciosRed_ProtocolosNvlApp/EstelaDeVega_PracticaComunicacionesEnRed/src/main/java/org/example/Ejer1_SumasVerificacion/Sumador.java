package org.example.Ejer1_SumasVerificacion;

public class Sumador {

    // Método que calcula la suma de verificación de una cadena
    public static int sumaSimple(String cad) {
        int suma = 0;
        for (int i = 0; i < cad.length(); i++) {
            suma += cad.codePointAt(i);  // Se suma el valor ASCII de cada carácter
        }
        return suma;
    }
}

