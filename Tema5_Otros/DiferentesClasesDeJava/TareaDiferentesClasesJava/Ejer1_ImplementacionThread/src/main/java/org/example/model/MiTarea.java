package org.example.model;

import java.util.Random;

// CLASE QUE SIMULA UNA TAREA A SER EJECUTADA POR UN HILO
public class MiTarea implements Runnable {

    private String nombreTarea; // VARIABLE QUE ALMACENA EL NOMBRE DE LA TAREA

    public MiTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea; // INICIALIZA EL NOMBRE DE LA TAREA
    } // CONSTRUCTOR DE LA CLASE MI TAREA, QUE RECIBE EL NOMBRE DE LA TAREA

    // METODO QUE EJECUTA LA LOGICA DE LA TAREA
    @Override
    public void run() {
        try {
            // IMPRIME QUE LA TAREA HA SIDO INICIADA Y EL NOMBRE DEL HILO QUE LA EJECUTA
            System.out.println(nombreTarea + " INICIADA POR: " + Thread.currentThread().getName());

            // SE CREA UN OBJETO RANDOM PARA GENERAR EL TIEMPO DE ESPERA ALEATORIO
            Random random = new Random();

            // SE GENERA UN TIEMPO DE ESPERA ALEATORIO ENTRE 1000 Y 3000 MILISEGUNDOS (1 A 3 SEGUNDOS)
            int tiempoEspera = random.nextInt(3000) + 1000;

            // EL HILO DUERME EL TIEMPO GENERADO ALEATORIAMENTE
            Thread.sleep(tiempoEspera);

            // IMPRIME QUE LA TAREA HA FINALIZADO Y EL NOMBRE DEL HILO QUE LA EJECUTA
            System.out.println(nombreTarea + " FINALIZADA POR: " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            // SI EL HILO ES INTERRUPCION, SE MANEJA LA EXCEPCION
            Thread.currentThread().interrupt();
        }
    }
}
/* consola
Tarea 3 INICIADA POR: Hilo 3
Tarea 2 INICIADA POR: Hilo 2
Tarea 1 INICIADA POR: Hilo 1
Tarea 1 FINALIZADA POR: Hilo 1
Tarea 2 FINALIZADA POR: Hilo 2
Tarea 3 FINALIZADA POR: Hilo 3
*/