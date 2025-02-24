package org.example;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("*********** EJERCICIO 3: PROGRAMACION DE APLICACIONES MULTIHILO ************");
        int[] numeros = new int[10000]; // ARRAY DE 10000 NUMEROS ENTEROS
        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = i + 1; // A CADA ITEM EL SUMA UNO
        } // RECORRE CADA ITEM QUE CONTENGA EL ARRAY

        int mitad = numeros.length / 2; // ATRIBUTO QUE GUARDA EL RESULTADO DE DIVIDIR EL ARRAY EN DOS PARTES
        int[] resultado = new int[2]; // ARRAY QUE ALMACENA LOS RESULTADOS DE LAS MITADES

        // SE CREA EL PRIMER HILO CON LA PRIMERA MITAD
        Thread hilo1 = new Thread(new GestionSuma(numeros, 0, mitad, resultado, 0));
        // SE CREA EL SEGUNDO HILO CON LA OTRA MITAD
        Thread hilo2 = new Thread(new GestionSuma(numeros, mitad, numeros.length, resultado, 1));

        // SE EJECUTAN LOS HILOS
        hilo1.start();
        hilo2.start();

        // SE ESPERA A QUE TERMINEN LOS HILOS
        hilo1.join();
        hilo2.join();

        // SE CALCULA LA SUMA TOTAL A PARTIR DE LOS RESULTADOS DE LAS MITADES
        int sumaTotal = resultado[0] + resultado[1];

        // SE FINALIZA IMPRIMIENDO LA SUMA TOTAL
        System.out.println("Suma total: " + sumaTotal);
    } // main
} // Class Main