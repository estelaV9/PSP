package org.example;
public class Cajera {

    private String nombre;

    public Cajera(String nombre) {
        this.nombre = nombre;
    }

    public void procesarCompra(Cliente cliente, long timeStamp) {
        // IMPRIME EL MENSAJE INICIAL QUE INDIQUE QUE LA CAJERA ESTÁ INICIANDO EL PROCESO DE COMPRA
        System.out.println("La cajera " + this.nombre +
                " COMIENZA A PROCESAR LA COMPRA DEL CLIENTE " + cliente.getNombre() +
                " EN EL TIEMPO: " + (System.currentTimeMillis() - timeStamp) / 1000 +
                " seg");

        // ITERA SOBRE CADA PRODUCTO EN EL CARRITO DE COMPRA DEL CLIENTE
        for (int i = 0; i < cliente.getCarroCompra().length; i++) {
            // ESPERA UN TIEMPO DADO (EN SEGUNDOS) PARA SIMULAR EL PROCESAMIENTO DEL PRODUCTO
            this.esperarXsegundos(cliente.getCarroCompra()[i]);

            // IMPRIME UN MENSAJE INDICANDO QUE SE HA PROCESADO UN PRODUCTO
            System.out.println("Procesado el producto " + (i + 1) +
                    " ->Tiempo: " + (System.currentTimeMillis() - timeStamp) / 1000 +
                    " seg");
        }

        // IMPRIME UN MENSAJE FINAL QUE INDIQUE QUE LA CAJERA HA TERMINADO DE PROCESAR LA COMPRA
        System.out.println("La cajera " + this.nombre + " HA TERMINADO DE PROCESAR " +
                cliente.getNombre() + " EN EL TIEMPO: " +
                (System.currentTimeMillis() - timeStamp) / 1000 + " seg");
    }

    private void esperarXsegundos(int segundos) {
        // INTENTA SUSPENDER EL HILO ACTUAL DURANTE EL NÚMERO DE SEGUNDOS ESPECIFICADO
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException ex) {
            // SI SE INTERRUMPE EL HILO, RESTABLECE EL ESTADO DE INTERRUPCIÓN
            Thread.currentThread().interrupt();
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}