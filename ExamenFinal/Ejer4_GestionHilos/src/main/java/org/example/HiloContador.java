package org.example;

import org.example.model.Hilo;

import java.util.ArrayList;

public class HiloContador {
    public static ArrayList<Hilo> listaHilos; // LISTA DE HILOS
    public static ArrayList<Boolean> listaEstados; // LISTA DE ESTADOS PARA CONTROLAR LOS HILOS

    public HiloContador() {
        listaHilos = new ArrayList<>(); // INICIALIZA LA LISTA DE HILOS
        listaEstados = new ArrayList<>(); // INICIALIZA LA LISTA DE ESTADOS
    } // CONSTRUCTOR PARA CREAR EL HILO INICIALIZANDO LAS LISTAS

    private Hilo buscarHilo(String nombre) {
        for (Hilo hilo : listaHilos) {
            if (hilo.getNombreHilo().equalsIgnoreCase(nombre)) {
                return hilo; // DEVUELVE EL HILO
            } // SI EL NOMBRE DEL HILO ES IGUAL AL INTRODUCIDO, DEVUELVE LOS VALORES DE ESE HILO
        } // RECORRE TODOS LOS HILOS DE LA LISTA
        return null; // SI NO HAY UN HILO CON ESE NOMBRE, RETORNA NULL
    } // METODO PARA BUSCAR EL HILO POR NOMBRE

    public void meterHilo(String nombre) {
        if(buscarHilo(nombre) == null) {
            Hilo nuevoHilo = new Hilo(nombre); // CREA UN NUEVO HILO
            listaHilos.add(nuevoHilo); // AGREGALO A LA LISTA
            listaEstados.add(false); // AGREGA EL ESTADO INICIAL DEL HILO (DETENIDO)
            System.out.println("Hilo '" + nombre + "' se ha añadido correctamente a la lista.");
        } else {
            System.out.println("Ya existe un hilo con el nombre '" + nombre + "'.");
        } // VERIFICA SI EXISTE EL HILO CON EL MISMO NOMBRE
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
            } // SI EL HILO DEVUELTO NO ES NULO, MUESTRA UN MENSAJE DE QUE ESTA EN EJECUCION
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
        } // SI EL HILO DEVUELTO NO ES NULO, MUESTRA LA CUENTA
    } // METODO PARA COMPROBAR LA CUENTA DE UN HILO

    public void comprobarEstado(String nombre) {
        Hilo hilo = buscarHilo(nombre); // BUSCA EL HILO
        if (hilo != null) {
            System.out.println("estado del hilo '" + nombre + "': " + (hilo.isEnEjecucion() ? "en ejecución" : "detenido"));
        } else {
            System.out.println("no se encontró un hilo con el nombre '" + nombre + "'.");
        } // SI EL HILO DEVUELTO NO ES NULO, MUESTRA EL ESTADO
    } // METODO PARA COMPROBAR EL ESTADO DE UN HILO

    public void pararHilo(String nombre) {
        Hilo hilo = buscarHilo(nombre); // BUSCA EL HILO
        if (hilo != null) {
            if (hilo.isEnEjecucion()) {
                hilo.setEnEjecucion(false); // MARCA EL ESTADO COMO DETENIDO
                System.out.println("hilo '" + nombre + "' detenido.");
            } else {
                System.out.println("el hilo '" + nombre + "' ya estaba detenido.");
            } // SI ESTABA EN EJECUCION, MUESTRA QUE ESTA DETENIDO, SI NO MUESTRA QUE YA ESTABA DETENIDO
        } else {
            System.out.println("no se encontró un hilo con el nombre '" + nombre + "'.");
        } // SI EL HILO DEVUELTO NO ES NULO, MUESTRA SI ESTA DETENIDO
    } // METODO PARA DETENER UN HILO POR INDICE

    public void detenerTodos() {
        for (Hilo hilo : listaHilos) {
            hilo.setEnEjecucion(false); // MARCA TODOS LOS ESTADOS COMO DETENIDOS
        } // RECORRE LA LISTA DE HILOS, Y SETTEA A TODOS LOS HILOS COMO DETENIDOS
        System.out.println("todos los hilos han sido detenidos."); // MENSAJE DESPUES DE REALIZAR LA ACCION
    } // METODO PARA DETENER TODOS LOS HILOS
} //Class HiloContador