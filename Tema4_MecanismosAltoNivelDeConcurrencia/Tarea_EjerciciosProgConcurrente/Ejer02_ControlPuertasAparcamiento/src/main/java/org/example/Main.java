package org.example;

import org.example.Model.CocheEntrada;
import org.example.Model.CocheSalida;
import java.util.concurrent.Semaphore;

public class Main {
    // DEFINIMOS EL NÚMERO DE BARRERAS DE ENTRADA Y SALIDA
    public static final int E = 2;  // BARRERAS DE ENTRADA
    public static final int S = 2;  // BARRERAS DE SALIDA
    public static final int N = 5;  // PLAZAS EN EL APARCAMIENTO

    static Semaphore[] barreras = new Semaphore[E + S]; // SEMÁFOROS PARA LAS BARRERAS

    // SEMÁFORO PARA EL CONTROL DE PLAZAS EN EL APARCAMIENTO
    public static Semaphore plazasLibres = new Semaphore(N, true);  // AL INICIO HAY N PLAZAS LIBRES

    public static void main(String[] args) {
        for (int i = 0; i < E + S; i++) {
            barreras[i] = new Semaphore(1);  // INICIALIZAMOS CADA BARRERA CON UN SOLO RECURSO
        }

        for (int i = 0; i < 10; i++) {
            new Thread(new CocheEntrada(i)).start();  // COCHE ENTRANDO
            new Thread(new CocheSalida(i)).start();   // COCHE SALIENDO
        } // SE SIMULA UN COCHE ENTRANDO Y SALIENDO DEL APARCAMEINTO
    }

    public static void esperarLlegada(int numPuerta) {
        try {
            barreras[numPuerta].acquire();  // ESPERA HASTA QUE LA BARRERA ESTÉ DISPONIBLE
            System.out.println("Coche llegó a la puerta " + numPuerta);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    } // METODO PARA ESPERAR LLEGADA (SIMULADO CON SEMÁFORO)

    public static void elevarBarrera(int numPuerta) {
        System.out.println("Elevando barrera " + numPuerta);
        // SIMULA QUE EL COCHE ESTÁ PASANDO
        try {
            Thread.sleep(2000);  // SIMULAMOS EL TIEMPO EN QUE EL COCHE PASA
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Barrera " + numPuerta + " descendió");
        barreras[numPuerta].release();  // DESCENDIENDO LA BARRERA
    } // METODO PARA ELEVAR LA BARRERA Y DEJAR PASAR EL COCHE
}