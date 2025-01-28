package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorCalculo {

    // FUNCION PARA EXTRAER UN NÚMERO DE UNA CADENA, VERIFICANDO SI ES VÁLIDO
    public int extraerNumero(String linea){
        int numero;
        try{
            numero = Integer.parseInt(linea);  // INTENTA CONVERTIR LA CADENA A UN NÚMERO ENTERO
        } catch (NumberFormatException e){
            numero = 0;  // SI HAY UN ERROR, ASUMIMOS QUE EL NÚMERO ES 0
        }

        // SI EL NÚMERO ES MAYOR O IGUAL A 100 MILLONES, LO CONSIDERAMOS NO VÁLIDO
        if (numero >= 100000000){
            numero = 0;
        }
        return numero;  // DEVOLVEMOS EL NÚMERO VALIDADO
    }

    // FUNCION PARA REALIZAR LA OPERACIÓN
    public int calcular(String op, String n1, String n2){
        int resultado = 0;
        char simbolo = op.charAt(0);  // OBTENEMOS EL OPERADOR
        int num1 = this.extraerNumero(n1);  // EXTRAEMOS EL PRIMER NÚMERO
        int num2 = this.extraerNumero(n2);  // EXTRAEMOS EL SEGUNDO NÚMERO

        // REALIZAMOS LA OPERACIÓN SEGÚN EL OPERADOR
        if (simbolo == '+'){
            resultado = num1 + num2;  // SI ES SUMA
        }
        return resultado;
    }

    // FUNCION PRINCIPAL QUE MANTIENE EL SERVIDOR A LA ESCUCHA DE CONEXIONES
    public void escuchar() throws IOException {
        System.out.println("Arrancado el servidor");

        ServerSocket socketEscucha = null;
        try {
            socketEscucha = new ServerSocket(9876);  // CREAMOS EL SOCKET PARA ESCUCHAR CONEXIONES EN EL PUERTO 9876
        } catch (IOException e) {
            System.out.println("No se pudo poner un socket a escuchar en TCP 9876");
            return;
        }

        // MIENTRAS EL SERVIDOR ESTE EN EJECUCIÓN, ACEPTAMOS CONEXIONES
        while (true){
            Socket conexion = socketEscucha.accept();  // ACEPTAMOS UNA NUEVA CONEXIÓN
            System.out.println("Conexión recibida!");

            InputStream is = conexion.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader bf = new BufferedReader(isr);

            String linea = bf.readLine();  // LEEMOS LA OPERACIÓN
            String num1 = bf.readLine();  // LEEMOS EL PRIMER NÚMERO
            String num2 = bf.readLine();  // LEEMOS EL SEGUNDO NÚMERO

            // CALCULAMOS EL RESULTADO DE LA OPERACIÓN
            Integer result = this.calcular(linea, num1, num2);

            OutputStream os = conexion.getOutputStream();
            PrintWriter pw = new PrintWriter(os);

            // ENVIAMOS EL RESULTADO DE LA OPERACIÓN AL CLIENTE
            pw.write(result.toString() + "\n");
            pw.flush();
        }
    }
}
