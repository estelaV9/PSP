package org.example.Comun;

import java.io.*;
import java.net.Socket;

public class Peticion implements Runnable{
    BufferedReader bfr;
    PrintWriter pw;
    Socket socket;

    // CONSTRUCTOR QUE RECIBE UN SOCKET Y LO ASIGNA AL OBJETO
    public Peticion(Socket socket){
        this.socket=socket;
    }

    // MÉTODO PARA EXTRAER Y VALIDAR UN NÚMERO DESDE UNA CADENA DE TEXTO
    public int extraerNumero(String linea){
        /* 1. COMPROBAR SI ES UN NÚMERO
         * 2. VER SI EL NÚMERO ES CORRECTO (32a75)
         * 3. VER SI TIENE DE 1 A 8 CIFRAS
         */
        int numero;
        try{
            numero=Integer.parseInt(linea);  // INTENTA PARSEAR LA CADENA A UN NÚMERO ENTERO
        }
        catch (NumberFormatException e){
            numero=0;  // SI NO SE PUEDE PARSEAR, DEVUELVE 0
        }

        /* SI EL NÚMERO ES MAYOR DE 100 MILLONES NO ES VÁLIDO TAMPOCO */
        if (numero>=100000000){
            numero=0;  // SI EL NÚMERO ES MAYOR O IGUAL A 100 MILLONES, DEVUELVE 0
        }
        return numero;  // DEVUELVE EL NÚMERO O 0 EN CASO DE ERROR
    }

    // MÉTODO PARA CALCULAR EL RESULTADO DE UNA OPERACIÓN ARITMÉTICA
    public int calcular(String op, String n1, String n2){
        int resultado=0;
        char simbolo=op.charAt(0);  // OBTENEMOS EL PRIMER CARÁCTER DE LA OPERACIÓN
        int num1=this.extraerNumero(n1);  // OBTENEMOS EL PRIMER NÚMERO
        int num2=this.extraerNumero(n2);  // OBTENEMOS EL SEGUNDO NÚMERO
        if (simbolo=='+'){  // SI LA OPERACIÓN ES UNA SUMA
            resultado=num1+num2;  // CALCULAMOS LA SUMA
        }
        return resultado;  // DEVUELVE EL RESULTADO DE LA OPERACIÓN
    }

    // MÉTODO QUE SE EJECUTA AL INICIAR EL HILO
    public void run(){
        try {
            // OBTENEMOS LOS FLUJOS DE LECTURA Y ESCRITURA
            PrintWriter flujoEscritura = Utilidades.getFlujoEscritura(this.conexionParaAtender);
            BufferedReader flujoLectura = Utilidades.getFlujoLectura(conexionParaAtender);

            // LEEMOS EL MENSAJE DE PROTOCOLO
            String protocolo = flujoLectura.readLine();
            int numVersion = Protocolo.getNumVersion(protocolo);  // OBTENEMOS LA VERSIÓN DEL PROTOCOLO

            // SI ES LA VERSIÓN 1, SE MANEJA EL PROCESO DE ORDENACIÓN DE DOS CADENAS
            if (numVersion == 1) {
                String linea1 = flujoLectura.readLine();  // LEEMOS LA PRIMERA CADENA
                String linea2 = flujoLectura.readLine();  // LEEMOS LA SEGUNDA CADENA

                // SE COMPRUEBA CUAL CADENA VA PRIMERO EN EL DICCIONARIO
                if (linea1.compareTo(linea2) > 0) {
                    flujoEscritura.println(linea2);  // SI LA PRIMERA VA DESPUES, LA ENVIAMOS EN SEGUNDO LUGAR
                    flujoEscritura.println(linea1);  // ENVIAMOS LA PRIMERA CADENA DESPUES
                } else {
                    flujoEscritura.println(linea1);  // SI YA ESTÁ EN EL ORDEN CORRECTO, LA ENVIAMOS PRIMERO
                    flujoEscritura.println(linea2);  // ENVIAMOS LA SEGUNDA CADENA DESPUES
                }
                flujoEscritura.flush();  // SE ENVÍAN LOS DATOS AL CLIENTE
            }
            // SI ES LA VERSIÓN 2, SE MANEJA LA ORDENACIÓN DE VARIAS PALABRAS
            else if (numVersion == 2) {
                System.out.println("Llegó un v2");

                // LEEMOS LA CANTIDAD DE PALABRAS A ORDENAR
                String lineaCantidadPalabras = flujoLectura.readLine();
                int numPalabras = Integer.parseInt(lineaCantidadPalabras);
                String[] palabras = new String[numPalabras];

                // LEEMOS LAS PALABRAS
                for (int i = 0; i < numPalabras; i++) {
                    palabras[i] = flujoLectura.readLine();
                }

                // ORDENAMOS LAS PALABRAS
                palabras = this.ordenar(palabras);

                // ENVIAMOS LAS PALABRAS ORDENADAS AL CLIENTE
                for (int i = 0; i < palabras.length; i++) {
                    flujoEscritura.println(palabras[i]);
                }
                flujoEscritura.flush();  // ASEGURAMOS QUE LOS DATOS SE ENVÍEN AL CLIENTE
            }
        } catch (IOException e) {
            System.out.println("No se pudo crear algún flujo");
        }
    }

    // MÉTODO QUE ORDENA UN ARREGLO DE PALABRAS ALFABÉTICAMENTE
    private String[] ordenar(String[] palabras) {
        java.util.Arrays.sort(palabras);  // UTILIZA EL MÉTODO sort DE LA CLASE Arrays
        return palabras;
    }
}
