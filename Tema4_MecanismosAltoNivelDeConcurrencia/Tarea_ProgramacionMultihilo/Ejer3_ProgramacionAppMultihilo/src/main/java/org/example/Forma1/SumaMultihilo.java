package org.example.Forma1;
public class SumaMultihilo {
    public static int suma = 0;
    public static void main(String[] args) throws InterruptedException {
        int[] numeros = new int[10000]; // ARRAY DE 10000 NUMEROS ENTEROS
        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = i + 1; // A CADA ITEM EL SUMA UNO
        } // RECORRE CADA ITEM QUE CONTENGA EL ARRAY

        Thread hilo1 = new Thread(new GestionSuma(numeros)); // SE CREA EL PRIMER HILO
        Thread hilo2 = new Thread(new GestionSuma(numeros)); // SE CREA EL SEGUNDO HILO

        // SE EJECUTAN LOS HILOS
        hilo1.start();
        hilo2.start();

        // SE ESPERA A QUE TERMINEN LOS HILOS
        hilo1.join();
        hilo2.join();

        // SE FINALIZA IMPRIMIENDO LA SUMA TOTAL
        System.out.println("Suma total: " + suma);
    }
}