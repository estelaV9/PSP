package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hola");

        // CREAMOS UN PROCESO
        MiProceso p1 = new MiProceso();

        // NO HACER p1.run PORQUE NO SE EJECUTARIA EL PROCESO EN REALIDAD NO SE EJECUTARIA CONCURRENTEMENTE
        // HAY QUE LLAMAR AL METODO START() PARA LLAMAR AL PROCESO
        p1.start(); // ESTO AUTOMATICAMENTE LLAMA AL METODO run()

        // CUANDO CREAS UN PROCESO Y LO LANZAS NO SE EJECUTAS DIRECTAMENTE, LO MANDAS A LA COLA DE LA CPU
        // Y EL PROCESO QUE ESTE EN LA CPU, EL SIGUE EJECUTANDO
        // (por eso al ejecutarlo el mensaje del run() se ejecutara en medio del bucle / al principio...
        // dependiendo el ordenador

        for( int i = 0; i < 100; i++)
            System.out.println(Thread.currentThread().getName() + " : " + i);
    }
}