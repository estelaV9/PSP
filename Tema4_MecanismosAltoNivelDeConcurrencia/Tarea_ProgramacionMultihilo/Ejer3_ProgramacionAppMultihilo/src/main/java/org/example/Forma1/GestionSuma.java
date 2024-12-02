package org.example.Forma1;

public class GestionSuma implements Runnable {
    private final int[] listOfNumbers;
    private static boolean isHalfSumFinish = false; // ATRIBUTO PARA CONTROLAR SI LA SUMA DE LA MITAD HA TERMINADO
    private static final Object lock = new Object(); // ATRIBUTO PARA EL BLOQUEO DE LA SINCRONIZACION


    public GestionSuma(int[] listOfNumbers) {
        this.listOfNumbers = listOfNumbers;
    } // CONSTRUCTOR CON LA LISTA DE NUMEROS ENTEROS

    @Override
    public void run() {
        synchronized (lock) {
            if (!isHalfSumFinish) {
                // SI LA SUMA DE LA PRIMERA MITAD NO HA TERMINADO, ENTONCES SE SUMA LA PRIMERA MITAD
                sumaParcial(listOfNumbers, 0, listOfNumbers.length / 2);
                isHalfSumFinish = true; // SE INDICA QUE SE HA TERMINADO LA SUMA DE LA PRIMERA MITA
                lock.notify(); // NOTIFICA AL SEGUNDO HILO
            } else {
                try {
                    // EL SEGUNDO HILO ESPERA SU TURNO
                    lock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Error, el hilo ha sido interrumpido : " + e);
                }
                // SE SUMA LA OTRA MITAD
                sumaParcial(listOfNumbers, listOfNumbers.length / 2, listOfNumbers.length);
            }
        }
    }

    private static void sumaParcial(int[] numeros, int inicio, int fin) {
        int sumaParcial = 0; // ATRIBUTO PARA GUARDAR LA SUMA DE LA MITAD

        for (int i = inicio; i < fin; i++) {
            sumaParcial += numeros[i];
        } // SE RECORRE EL BUCLE DESDE LA MITAD QUE INDIQUEMOS SUMANDO LOS NUMEROS

        SumaMultihilo.suma += sumaParcial; // AÑADIMOS EL VALOR DE LA SUMA DEL PARCIAL A LA SUMA DEL MULTIHILO
    } // METODO PARA SUMAR LAS MITADES DEL ARRAY DE ENTEROS
}