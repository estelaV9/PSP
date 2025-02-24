package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static int contador = 0; // ATRIBUTO PARA LA VARIABLE DEL CONTADOR
    public static final Object lock = new Object(); // ATRIBUTO OBJETO PARA MANEJAR LA SINCRONIZACION

    public static void main(String[] args) throws InterruptedException {
        System.out.println("********** EJERCICIO 1: SINCRONIZACION HILOS **********");

        // CREAMOS UN ExecutorService CON UN POOL DE 10 HILOS
        // YA QUE MANEJA MEJOR UN GRUPO DE HILOS
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            executorService.execute(new IncrementarContador());
        } // CREAMOS VARIOS OBJETOS AL EXECUTOR

        // CERRAMOS EL EXECUTOR PARA QUE NO ACEPTE MAS TAREAS NUEVAS
        executorService.shutdown();

        /* AUNQUE USAMOS shutdown(), NO DEBEMOS ASUMIR QUE TODAS LAS TAREAS TERMINAN
         * INMEDIATAMENTE. POR ELLO, USAMOS awaitTermination PARA ESPERAR A QUE FINALICEN
         * TODAS LAS TAREAS O SE ALCANCE EL TIMEOUT. */

        // ATRIBUTO PARA GUARDAR SI TODAS LAS TAREAS HAN FINALIZADO O SE HA CUMPLIDO EL TIMEOUT
        boolean terminado = executorService.awaitTermination(60, TimeUnit.SECONDS);

        if (terminado) {
            System.out.println("Valor final del contador: " + contador);
        } // SI TODAS LAS TAREAS HAN TERMINADO DENTRO DEL TIEMPO, SE MUESTRA EL VALOR DEL CONTADOR

        if (!terminado) {
            // EN ESTE CASO, UTILIZAMOS shutdownNow() PARA TERMINAR LAS TAREAS INMEDIATAMENTE
            // A PESAR DE QUE PUEDEN NO HABER SIDO COMPLETADAS
            executorService.shutdownNow();
            System.out.println("Valor final del contador: " + contador);
        } // SI EL EXECUTOR NO HA TERMINADO DENTRO DEL TIMEOUT, SE FORZA A QUE TERMINE
    } //main
} // Class Main