package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("************** EJERCICIO 4: GESTION DE HILOS ************");
        Scanner reader = new Scanner(System.in);

        try {
            int respuesta = -1; // ATRIBUTO PARA LA RESPUESTA DEL USUARIO
            // SE CREA INSTANCIA DEL HILO CONTADOR
            HiloContador hiloContador = new HiloContador();
            do {
                // MENU PRINCIPAL
                System.out.println("\n¿Que desea hacer?" +
                        "\n1-Meter un hilo en la lista" +
                        "\n2-Iniciar un hilo" +
                        "\n3-Comprobar cuenta de un hilo" +
                        "\n4-Comprobar estado de un hilo" +
                        "\n5-Parar un hilo" +
                        "\n6-Detener todos los hilos (cerrar la app)");

                System.out.print("Respuesta: ");

                // LEE LA OPCION DEL USUARIO
                respuesta = reader.nextInt();

                while (respuesta < 1 || respuesta > 6) {
                    System.out.println("Elija una opcion valida del 1 al 6");
                    respuesta = reader.nextInt(); // SI NO PONE UNA OPCION VALIDA, LE VUELVE A PEDIR LA RESPUESTA
                } // SE VALIDA LA RESPUESTA DEL USUARIO

                System.out.println(); // SALTO DE LINEA

                switch (respuesta) {
                    case 1 -> {
                        System.out.println("\n******** METER UN HILO EN LA LISTA ********");
                        System.out.print("Introduzca el nombre del hilo: ");
                        String nombre = reader.next(); // LEE EL NOMBRE DEL HILO
                        hiloContador.meterHilo(nombre); // LLAMA AL METODO PARA AGREGAR UN HILO
                    }
                    case 2 -> {
                        System.out.println("\n******** INICIAR UN HILO ********");
                        System.out.print("Introduzca el nombre del hilo: ");
                        String nombre = reader.next(); // LEE EL NOMBRE DEL HILO
                        hiloContador.iniciarHilo(nombre); // LLAMA AL METODO PARA INICIAR EL HILO
                    }
                    case 3 -> {
                        System.out.println("\n******** COMPROBAR CUENTA DE UN HILO ********");
                        System.out.print("Introduzca el nombre del hilo: ");
                        String nombre = reader.next(); // LEE EL NOMBRE DEL HILO
                        hiloContador.comprobarCuenta(nombre); // LLAMA AL METODO PARA COMPROBAR LA CUENTA
                    }
                    case 4 -> {
                        System.out.println("\n******** COMPROBAR ESTADO DE UN HILO ********");
                        System.out.print("Introduzca el nombre del hilo: ");
                        String nombre = reader.next(); // LEE EL NOMBRE DEL HILO
                        hiloContador.comprobarEstado(nombre); // LLAMA AL METODO PARA COMPROBAR EL ESTADO
                    }
                    case 5 -> {
                        System.out.println("\n******** PARA UN HILO ********");
                        System.out.print("Introduzca el nombre del hilo: ");
                        String nombre = reader.next(); // LEE EL NOMBRE DEL HILO
                        hiloContador.pararHilo(nombre); // LLAMA AL METODO PARA DETENER EL HILO

                    }
                    case 6 -> {
                        System.out.println("\n******** DETENER TODOS LOS HILOS ********");
                        hiloContador.detenerTodos(); // LLAMA AL METODO PARA DETENER TODOS LOS HILOS
                        System.out.println("Hasta pronto!");
                    }
                    default -> System.out.println("Numero invalido");
                } // switch CON LA RESPUESTA DEL USUARIO
            } while (respuesta != 6); // EL MENU SE REPITE HASTA QUE EL USUARIO ELIJA SALIR
        } catch (Exception e) {
            // SI METE UNA OPCION INVALIDA, SE CAPTURA CON UNA EXCEPCION
            System.out.println("Ocurrio un error: " + e);
        }

        reader.close(); // SE CIERRA EL SCANNER
    }
}