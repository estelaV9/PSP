package org.example.Model;

public class Cocina {
    // LA COCINA TIENE UN LIMITE EN LA CANTIDAD DE PLATOS QUE PUEDE CONTENER (5)
    private int numPlatos;
    private int capacidad;

    public Cocina(int capacidad) {
        this.numPlatos = 0; // SE INICIALIZA CON 0 PLATOS
        this.capacidad = capacidad; // CAPACIDAD MAXIMA DE PLATOS
    }

    public synchronized void agregarPlato() throws InterruptedException {
        // SE VERIFICA SI LA COCINA ESTA LLENA
        while (numPlatos > capacidad) {
            // SI ESTA LLENA LA COCINA, EL HILO DEL CHEF ESPERE HASTA QUE HAYA ESPACIO DISPONIBLE
            wait();
        }
        // CUANDO HAYA ESPACIO, SE AGREGA EL PLATO A LA COLA
        numPlatos++; // AGREGA PLATO
        System.out.println("El chef ha agregado un plato. Total: " + numPlatos);
        notify(); // NOTIFICA QUE AHORA HAY UN PLATO DISPONIBLE
    } // METODO PARA AGREGAR PLATOS A LA COLA

    public synchronized void consumirPlato() throws InterruptedException {
        // SE VERIFICA SI LA COCINA ESTA LLENA
        while(numPlatos < 0){
            // SI ESTA, EL HILO DEL CLIENTE DEBE ESPERAR HASTA QUE EL CHEF PRODUZCA UN NUEVO PLATO
            wait();
        }
        // CUANDO HAYA ESPACIO, SE RETIRA EL PLATO A LA COLA
        numPlatos--; // RETIRA PLATO
        System.out.println("El cliente ha comido un plato. Total: " + numPlatos);
        notify(); // NOTIFICA QUE AHORA HAY UN PLATO DISPONIBLE PARA MAS PLATOS
    } // METODO PARA CONSUMIR PLATOS

    public int getNumPlatos() {
        return numPlatos;
    }

    public void setNumPlatos(int numPlatos) {
        this.numPlatos = numPlatos;
    }
}