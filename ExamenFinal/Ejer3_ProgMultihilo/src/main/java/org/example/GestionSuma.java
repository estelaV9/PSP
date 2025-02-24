package org.example;

public class GestionSuma implements Runnable {
    private final int[] listOfNumbers; // LISTA DE NUMEROS EN TOTAL
    private final int inicio; // ATRIBUTO QUE INDICA EL INICIO PARA LA SUMA
    private final int fin; // ATRIBUTO QUE INDICA EL FINAL PARA LA SUMA
    private final int[] resultado; // ARRAY PARA GUARDAR RESULTADO DE LA MITAD
    private final int indice; // ÍNDICE PARA GUARDAR RESULTADO EN EL ARRAY

    public GestionSuma(int[] listOfNumbers, int inicio, int fin, int[] resultado, int indice) {
        this.listOfNumbers = listOfNumbers;
        this.inicio = inicio;
        this.fin = fin;
        this.resultado = resultado;
        this.indice = indice;
    } // CONSTRUCTOR CON TODOS LOS PARAMETROS

    @Override
    public void run() {
        int sumaParcial = 0; // SE INICIALIZA LA SUMA PARCIAL

        for (int i = inicio; i < fin; i++) {
            sumaParcial += listOfNumbers[i];
        } // SE SUMA LOS NUMEROS EN EL RANGO ESPECIFICADO

        resultado[indice] = sumaParcial; // SE GUARDA EN EL ARRAY DE RESULTADOS
    } // METODO RUN
}
