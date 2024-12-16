import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);

        // FICHERO DEL CORREO
        String rutaCorreo = "resources/correo.txt";

        // BUFFEREDREADER PARA LEER EL ARCHIVO "correo.txt"
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/" + rutaCorreo))){
            String linea = bufferedReader.readLine(); // LEEMOS EL ARCHIVO LINEA POR LINEA

            if (linea != null) {
                // SI EL ARCHIVO CORREO NO ESTA VACIO SE MUESTRA UN ICONO DE CORREO
                System.out.printf("\uD83D\uDCE9");
            } else {
                // SI EL ARCHIVO CORREO ESTA VACIO, SE MUESTRA EL ICONO DE SIN CORREO
                System.out.println("✉\uFE0F");
            } // DEPENDIENDO SI TIENE CORREO O NO, MUESTRA UN ICONO U OTRO

        } catch (FileNotFoundException e) {
            System.out.println("Error archivo no encontrado: " + e);
        } catch (IOException e) {
            System.out.println("Error: " + e);
        } // try-catch br
    } // main
}