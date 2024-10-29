package org.example;
/* Progreso de Tarea: Crea un hilo que simule una tarea que dure 10 segundos,
mostrando el porcentaje de la descarga en la consola. */

import org.example.Model.Tarea;

public class Main {
    public static void main(String[] args) {
        Tarea tarea = new Tarea(); // SE CREA UN OBJETO DE TAREA
        tarea.start(); // SE EMPIEZA LA "DESCARGA"
    }
}