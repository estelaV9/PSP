package org.example;

import org.example.model.MiTarea;

// CLASE PRINCIPAL QUE INICIA LOS HILOS Y LAS TAREAS
public class Main {

    public static void main(String[] args) {
        // SE CREAN Y SE INICIAN LOS HILOS CON LAS TAREAS CORRESPONDIENTES

        // HILO 1 EJECUTA "TAREA 1"
        Thread hilo1 = new Thread(new MiTarea("Tarea 1"), "Hilo 1");

        // HILO 2 EJECUTA "TAREA 2"
        Thread hilo2 = new Thread(new MiTarea("Tarea 2"), "Hilo 2");

        // HILO 3 EJECUTA "TAREA 3"
        Thread hilo3 = new Thread(new MiTarea("Tarea 3"), "Hilo 3");

        // INICIA LOS HILOS
        hilo1.start();
        hilo2.start();
        hilo3.start();
    }
}