package org.example;

public class SumaMultihilo {
    public static void main(String[] args) throws InterruptedException {
        int[] numeros = new int[10000];
        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = i + 1;
        }

        int[] resultado = new int[2];
        int mitad = numeros.length / 2;

        Thread hilo1 = new Thread(() -> {
            resultado[0] = sumaParcial(numeros, 0, mitad);
        });

        Thread hilo2 = new Thread(() -> {
            resultado[1] = sumaParcial(numeros, mitad, numeros.length);
        });

        hilo1.start();
        hilo2.start();

        hilo1.join();
        hilo2.join();

        int sumaTotal = resultado[0] + resultado[1];
        System.out.println("Suma total: " + sumaTotal);
    }

    private static int sumaParcial(int[] numeros, int inicio, int fin) {
        int suma = 0;
        for (int i = inicio; i < fin; i++) {
            suma += numeros[i];
        }
        return suma;
    }
}