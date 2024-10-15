package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // CREAMOS UN PROcESO
        MiProceso p1 = new MiProceso();
        // NO HACER p1.run PORQUE NO SE EJECUTARIA EL PROCESO EN REALIDAD NO SE EJECUTARIA CONCURRENTEMENTE

        // HAY QUE LLAMAR AL METODO START() PARA LLAMAR AL PROCESO
        p1.start(); // ESTO AUTOMATICAMENTE LLAMA AL METODO run()

    }
}