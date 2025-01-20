package org.example;

import org.example.model.Hilo;

import java.util.ArrayList;

public class HiloContador {
    public static ArrayList<Hilo> listaHilos; // LISTA DE HILOS
    public static ArrayList<Boolean> listaEstados; // LISTA DE ESTADOS PARA CONTROLAR LOS HILOS

    public HiloContador() {
        listaHilos = new ArrayList<>(); // INICIALIZA LA LISTA DE HILOS
        listaEstados = new ArrayList<>(); // INICIALIZA LA LISTA DE ESTADOS
    }

    private Hilo buscarHilo(String nombre) {
        for (Hilo hilo : listaHilos) {
            if (hilo.getNombreHilo().equalsIgnoreCase(nombre)) {
                return hilo; // DEVUELVE EL HILO
            }
        }
        return null;
    } // METODO PARA BUSCAR EL HILO POR NOMBRE

    public void meterHilo(String nombre) {
        Hilo nuevoHilo = new Hilo(nombre); // CREA UN NUEVO HILO
        listaHilos.add(nuevoHilo); // AGREGALO A LA LISTA
        listaEstados.add(false); // AGREGA EL ESTADO INICIAL DEL HILO (DETENIDO)
        System.out.println("Hilo '" + nombre + "' se ha añadido correctamente a la lista.");
    } // METODO PARA AGREGAR UN HILO A LA LISTA

    public void iniciarHilo(String nombre) {
        Hilo nuevoHilo = buscarHilo(nombre); // CREA UN NUEVO HILO CON EL NOMBRE
        if (nuevoHilo != null) {
            if (!nuevoHilo.isEnEjecucion()) { // VERIFICA SI NO ESTÁ YA EN EJECUCIÓN
                nuevoHilo.setEnEjecucion(true); // MARCA EL ESTADO COMO EN EJECUCIÓN
                new Thread(nuevoHilo).start(); // INICIA EL HILO EN UN NUEVO THREAD
                System.out.println("hilo '" + nombre + "' iniciado.");
            } else {
                System.out.println("el hilo '" + nombre + "' ya está en ejecución.");
            }
        } else {
            System.out.println("no se encontró un hilo con el nombre '" + nombre + "'.");
        } // VERIFICAR QUE NO SEA NULO EL HILO PASADO
    } // METODO PARA INICIAR UN HILO POR INDICE

    public void comprobarCuenta(String nombre) {
        Hilo hilo = buscarHilo(nombre); // BUSCA EL HILO
        if (hilo != null) {
            System.out.println("cuenta del hilo '" + nombre + "': " + hilo.getContador());
        } else {
            System.out.println("no se encontró un hilo con el nombre '" + nombre + "'.");
        }
    } // METODO PARA COMPROBAR LA CUENTA DE UN HILO


    public void comprobarEstado(String nombre) {
        Hilo hilo = buscarHilo(nombre); // BUSCA EL HILO
        if (hilo != null) {
            System.out.println("estado del hilo '" + nombre + "': " + (hilo.isEnEjecucion() ? "en ejecución" : "detenido"));
        } else {
            System.out.println("no se encontró un hilo con el nombre '" + nombre + "'.");
        }
    } // METODO PARA COMPROBAR EL ESTADO DE UN HILO

    public void pararHilo(String nombre) {
        Hilo hilo = buscarHilo(nombre); // BUSCA EL HILO
        if (hilo != null) {
            if (hilo.isEnEjecucion()) {
                hilo.setEnEjecucion(false); // MARCA EL ESTADO COMO DETENIDO
                System.out.println("hilo '" + nombre + "' detenido.");
            } else {
                System.out.println("el hilo '" + nombre + "' ya estaba detenido.");
            }
        } else {
            System.out.println("no se encontró un hilo con el nombre '" + nombre + "'.");
        }
    } // METODO PARA DETENER UN HILO POR INDICE

    public void detenerTodos() {
        for (Hilo hilo : listaHilos) {
            hilo.setEnEjecucion(false); // MARCA TODOS LOS ESTADOS COMO DETENIDOS
        }
        System.out.println("todos los hilos han sido detenidos.");
    } // METODO PARA DETENER TODOS LOS HILOS
}