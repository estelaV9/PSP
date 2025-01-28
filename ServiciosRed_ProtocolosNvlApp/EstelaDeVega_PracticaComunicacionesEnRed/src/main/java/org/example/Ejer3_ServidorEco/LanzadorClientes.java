package org.example.Ejer3_ServidorEco;

public class LanzadorClientes {

    public boolean servidorAtendioClientes(
            int numClientes) {
        //En este metodo lanzamos x clientes y luego comprobamos
        //si todos funcionaron bien. Si todo fue bien
        //devolvemos true y si no devolveremos false
        Thread[] hilos = new Thread[numClientes];
        Cliente[] clientes = new Cliente[numClientes];
        for (int i = 0; i < numClientes; i++) {
            Cliente cliente = new Cliente();
            cliente.setNumHilo(i);
            Thread hiloAsociado = new Thread(cliente);
            hilos[i] = hiloAsociado;
            hiloAsociado.start();
            clientes[i] = cliente;
        }
        System.out.println("Lanzados!");
        /* Esperamos que todos los hilos acaben
         * dandoles un plazo maximo mas bien pequeño
         * Si en ese tiempo no se completó
         * una operación tan simple, probablemente
         * el servidor falló*/
        for (int i = 0; i < numClientes; i++) {
            try {
                hilos[i].join();
            } catch (InterruptedException e) {
                System.out.println("Se interrumpió un hilo por parte "
                        + "de alguna clase del cliente ");
            }
        }
        /* Comprobamos si todos los hilos están bien
         * y en cuanto uno sufra un fallo podemos
         * asumir que el servidor no pudo atender tantos
         * clientes*/
        for (int i = 0; i < numClientes; i++) {
            if (clientes[i].huboFallo()) {
                return false;
            }
        }
        //pudo atender a todos los clientes a la vez
        return true;
    }

    public static void main(String[] args) {
        LanzadorClientes lanzador = new
                LanzadorClientes();
        /* Vamos probando a lanzar muchos clientes
         * hasta que forcemos un fallo
         */
        for (int i = 1; i < 1000; i++) {
            boolean todoOK;
            int numClientes = i * 1000;
            System.out.println("Lanzando " + numClientes +
                    " clientes...");
            todoOK = lanzador.servidorAtendioClientes(
                    numClientes);
            /* Si algo no fue bien, indicamos la cantidad
             * de clientes con que se produjo el fallo
             */
            if (!todoOK) {
                System.out.println("El servidor pareció fallar con:"
                        + numClientes);
                return;
            }
            //Fin del if
        }
        //Fin del main
    }
}
