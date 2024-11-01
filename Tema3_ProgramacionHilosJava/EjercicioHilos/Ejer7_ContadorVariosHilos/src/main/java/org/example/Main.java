package org.example;

import org.example.Model.Contador;
import org.example.Model.ManejoContador;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Contador contadorCompartido = new Contador();

        System.out.println("Contador Compartido");
        ManejoContador hilo1 = new ManejoContador(contadorCompartido, "Hilo 1");
        ManejoContador hilo2 = new ManejoContador(contadorCompartido, "Hilo 2");
        ManejoContador hilo3 = new ManejoContador(contadorCompartido, "Hilo 3");
        ManejoContador hilo4 = new ManejoContador(contadorCompartido, "Hilo 4");
        ManejoContador hilo5 = new ManejoContador(contadorCompartido, "Hilo 5");

        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();

        hilo1.join();
        hilo2.join();
        hilo3.join();
        hilo4.join();
        hilo5.join();

        System.out.println("Programa terminado");
    }
}