import java.io.*;
import java.util.Scanner;

public class Main {
    /** nota: se ha variado del enunciado y basicamente el programa verifica cada
     * 10 segundo el correo, se manda aleatoriamente numero de mensajes al correo
     * y te da la opcion de leerlo y salir **/
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        String rutaCorreo = "src/resources/correo.txt";
        File correoFile = new File(rutaCorreo);

        try {
            if (!correoFile.exists()) {
                correoFile.createNewFile();
            } // SI EL ARCHIVO NO EXITE, SE CREA
        } catch (IOException e) {
            System.out.println("Error al crear el archivo de correo: " + e.getMessage());
            return;
        }

        while (true) {
            // LLAMAMOS AL METODO PARA SIMULAR QUE LLEGAN CORREOS
            try {
                generarCorreoAleatorio(correoFile);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Error en la espera: " + e.getMessage());
            }


            // VERIFICAMOS EL TAMAÑO DEL ARCHIVO CADA 10 SEGUNDOS
            /** nota: en el enunciado pone 30 pero me parecio mejor poner 10 **/
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println("Error en la espera: " + e.getMessage());
            }

            if (correoFile.length() != 0) {
                // SI EL TAMAÑO HA AUMENTADO QUIERE DECIR QUE HAY NUEVO CORREO
                System.out.println("¡Tienes nuevo correo! 📧");

                // SE PREGUNTA SI QUIERE MARCARLO COMO LEIDO (para darle un poco mas de juego)
                System.out.println("¿Quieres marcarlo como leído y borrar el correo? (sí/no):");
                String respuesta = reader.nextLine();

                if (respuesta.equalsIgnoreCase("si")) {
                    borrarCorreo(correoFile);
                    System.out.println("Correo marcado como leído.");
                    System.out.println("No hay correo nuevo. 📭");
                } // SI DICE QUE SI, SE BORRA EL CONTENIDO DEL ARCHIOV
            } else {
                // SI NO HA AUMENTADO EL TAMAÑO, SE MUESTRA UN MENSAJE DE QUE NO HAY CORREO
                System.out.println("No hay correo nuevo. 📭");
            } // SI HAY CORREO EN EL ARCHIVO MAND

            // SE PREGUNTA SI QUIERE SALIR
            System.out.println("¿Quieres seguir revisando el correo? (sí/no):");
            String continuar = reader.nextLine();

            if (continuar.equalsIgnoreCase("no")) {
                System.out.println("Saliendo del programa...");
                break; // SALE DEL BUCLE
            }
        }
        reader.close();
    }

    private static void generarCorreoAleatorio(File correoFile) {
        int numMensajes = (int) (Math.random()* 10) + 1; // SE GENERA UN NUMERO ALEATORIO
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(correoFile, true))) {
            for (int i = 0; i < numMensajes; i++) {
                // ESCRIBE
                writer.write(numMensajes + " nuevos mensaje de correo");
                writer.newLine();
            }
            writer.write("-----------------------------------");
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo de correo: " + e.getMessage());
        }
    } // METODO PARA SIMULAR QUE ENVIAN CORREOS


    private static void borrarCorreo(File correoFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(correoFile))) {
            // SE BORRA EL CONTENIDO DEL ARCHIVO
            writer.write("");
        } catch (IOException e) {
            System.out.println("Error al borrar el archivo de correo: " + e.getMessage());
        }
    } // MÉTODO PARA BORRAR EL CORREO (MARCARLO COMO LEÍDO)
}