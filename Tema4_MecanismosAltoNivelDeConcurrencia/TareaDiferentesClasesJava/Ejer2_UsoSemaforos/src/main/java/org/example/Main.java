package org.example;

import org.example.model.MiTarea;
import org.example.model.RecursoCompartido;

// CLASE PRINCIPAL QUE INICIA LOS HILOS Y EL RECURSO COMPARTIDO
public class Main {

    public static void main(String[] args) {

        // SE CREA EL RECURSO COMPARTIDO CON 2 PERMISOS, SOLO 2 HILOS PUEDEN ACCEDER AL RECURSO SIMULTANEAMENTE
        RecursoCompartido recurso = new RecursoCompartido(2);

        // SE CREAN Y SE INICIAN LOS HILOS QUE EJECUTAN LAS TAREAS
        Thread hilo1 = new Thread(new MiTarea(recurso, "Hilo 1"));
        Thread hilo2 = new Thread(new MiTarea(recurso, "Hilo 2"));
        Thread hilo3 = new Thread(new MiTarea(recurso, "Hilo 3"));
        Thread hilo4 = new Thread(new MiTarea(recurso, "Hilo 4"));
        Thread hilo5 = new Thread(new MiTarea(recurso, "Hilo 5"));

        // INICIA LOS HILOS
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();
    }
}

/* consola
Hilo 1 ACCEDIO AL RECURSO.
Hilo 2 ACCEDIO AL RECURSO.
Hilo 2 LIBERO EL RECURSO.
Hilo 5 ACCEDIO AL RECURSO.
Hilo 1 LIBERO EL RECURSO.
Hilo 4 ACCEDIO AL RECURSO.
Hilo 5 LIBERO EL RECURSO.
Hilo 3 ACCEDIO AL RECURSO.
Hilo 4 LIBERO EL RECURSO.
Hilo 3 LIBERO EL RECURSO.*/