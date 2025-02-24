package org.example;

public class LanzadorClientes {

    // METODO QUE LANZA CLIENTES Y VERIFICA SI EL SERVIDOR LOS ATENDIO CORRECTAMENTE
    public boolean servidorAtendioClientes(int numClientes) {
        // ARREGLO PARA ALMACENAR LOS HILOS DE LOS CLIENTES
        Thread[] hilos = new Thread[numClientes];

        // ARREGLO PARA ALMACENAR LAS INSTANCIAS DE CLIENTE
        Cliente[] clientes = new Cliente[numClientes];

        // CREACION Y LANZAMIENTO DE LOS CLIENTES
        for (int i = 0; i < numClientes; i++) {
            Cliente cliente = new Cliente(); // CREA UNA NUEVA INSTANCIA DE CLIENTE
            cliente.setNumHilo(i); // ASIGNA UN NUMERO AL HILO
            Thread hiloAsociado = new Thread(cliente); // CREA UN NUEVO HILO PARA EL CLIENTE
            hilos[i] = hiloAsociado; // ALMACENA EL HILO EN EL ARREGLO
            hiloAsociado.start(); // INICIA EL HILO
            clientes[i] = cliente; // ALMACENA EL CLIENTE EN EL ARREGLO
        }

        System.out.println("LANZADOS!");

        /* ESPERAMOS QUE TODOS LOS HILOS TERMINEN
         * SI NO SE COMPLETAN EN UN TIEMPO RAZONABLE,
         * ES POSIBLE QUE EL SERVIDOR FALLE
         */
        for (int i = 0; i < numClientes; i++) {
            try {
                hilos[i].join(); // ESPERA A QUE EL HILO TERMINE SU EJECUCION
            } catch (InterruptedException e) {
                System.out.println("SE INTERRUMPIO UN HILO POR PARTE DE ALGUNA CLASE DEL CLIENTE");
            }
        }

        /* COMPROBAMOS SI TODOS LOS CLIENTES FUNCIONARON CORRECTAMENTE
         * SI ALGUNO FALLO, SE ASUME QUE EL SERVIDOR NO PUDO ATENDER A TODOS
         */
        for (int i = 0; i < numClientes; i++) {
            if (clientes[i].huboFallo()) {
                return false; // SI HUBO UN FALLO, DEVOLVEMOS FALSO
            }
        }

        // SI EL SERVIDOR ATENDIO A TODOS LOS CLIENTES, DEVOLVEMOS VERDADERO
        return true;
    }

    public static void main(String[] args) {
        LanzadorClientes lanzador = new LanzadorClientes();

        /* SE LANZAN CLIENTES EN BLOQUES HASTA QUE EL SERVIDOR FALLE */
        for (int i = 1; i < 1000; i++) {
            boolean todoOK;
            int numClientes = i * 1000; // SE INCREMENTA EL NUMERO DE CLIENTES
            System.out.println("LANZANDO " + numClientes + " CLIENTES...");
            todoOK = lanzador.servidorAtendioClientes(numClientes);

            /* SI EL SERVIDOR FALLA, SE IMPRIME LA CANTIDAD DE CLIENTES QUE LO COLAPSO */
            if (!todoOK) {
                System.out.println("EL SERVIDOR PARECIO FALLAR CON: " + numClientes);
                return;
            }
        }
    }
}
