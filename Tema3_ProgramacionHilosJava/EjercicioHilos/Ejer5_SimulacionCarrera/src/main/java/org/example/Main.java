package org.example;

import org.example.Model.ManejoHilo;
import org.example.Model.Mascota;

public class Main {
    public static void main(String[] args) {
        // CREAR OBJETO MASCOTAS
        Mascota perro = new Mascota("Perro");
        Mascota gato = new Mascota("Gato");

        // CREAR OBJETO DE LA CLASE PARA MANEJAR HILOS
        ManejoHilo perroHilo = new ManejoHilo(perro.getNombre());
        ManejoHilo gatoHilo = new ManejoHilo(gato.getNombre());

        System.out.println("Bienvenidos a la carrera!");
        System.out.println("Los concursantes son: " + perro.getNombre() + " y " + gato.getNombre());
        System.out.println("EMPIEZA LA CARRERA");

        perroHilo.start();
        gatoHilo.start();

        // ESPERAMOS A QUE LOS HILOS TERMINEN
        /** nota: El método join() se usa para esperar a que un hilo termine su ejecución
         * antes de continuar con el codigo. Esto se utiliza para que un hilo no prosiga hasta
         * que otro hilo haya completado su trabajo. **/
        try {
            perroHilo.join();
            gatoHilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // SE MUESTRA AL GANADOR
        System.out.println("El ganador es: " + ManejoHilo.getNombreGanador());
    }
}