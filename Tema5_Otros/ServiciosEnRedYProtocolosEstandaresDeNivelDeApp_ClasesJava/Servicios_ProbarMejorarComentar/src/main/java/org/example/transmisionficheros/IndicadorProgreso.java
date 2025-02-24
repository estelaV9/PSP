package org.example.transmisionficheros;

// DEFINICION DE LA CLASE INDICADORPROGRESO
public class IndicadorProgreso {

    // VARIABLE QUE ALMACENA EL INDICE DEL CARACTER ACTUAL
    int num_caracter = 0;

    // CADENA QUE CONTIENE LOS CARACTERES PARA EL INDICADOR DE PROGRESO
    String caracteres = "\\|/-";

    // METODO QUE DEVUELVE EL SIGUIENTE CARACTER DEL INDICADOR DE PROGRESO
    char siguienteCaracter() {

        // OBTIENE EL CARACTER ACTUAL SEGUN EL INDICE num_caracter
        char caracterADevolver = this.caracteres.charAt(num_caracter);

        // ACTUALIZA EL INDICE PARA APUNTAR AL SIGUIENTE CARACTER EN LA SECUENCIA
        this.num_caracter = (this.num_caracter + 1) % this.caracteres.length();

        // RETORNA EL CARACTER OBTENIDO
        return caracterADevolver;
    }
}
