package org.example.Model;

public class ManejoHilo extends Thread {
    private static String nombreGanador; // VARIABLE ESTATICA PARA ALMACENAR EL GANADOR
    private static final Object lock = new Object(); // OBJETO PARA LA SINCRONIZACION
    private final int finalCarrera; // ATRIBUTO PARA SABER EN QUE METRO TERMINA LA CARRERA
    private final String nombreCorredor; // NOMBRE DE LA MASCOTA

    public ManejoHilo(String nombreCorredor) {
        this.finalCarrera = 10; // LA META ES 10 METROS
        this.nombreCorredor = nombreCorredor;
    }

    public static String getNombreGanador() {
        return nombreGanador;
    }

    @Override
    public void run() {
        try {
            startCarrera(nombreCorredor, tiempoCarrera()); // LLAMAR AL TIEMPO DE EMPIECE DE LA CARRERA
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    } // METODO RUN

    public int tiempoCarrera() {
        return (int) (Math.random() * 500) + 100; // NUMERO ENTRE 100 Y 500
    } // METODO PARA GENERAR ALEATORIAMENTE EL TIEMPO QUE TARDA EN HACER 1 METRO

    public void startCarrera(String nombre, int tiempoPorMetro) throws InterruptedException {
        for (int i = 0; i <= finalCarrera; i++) { // Cambiar el bucle para ir de 1 a finalCarrera
            System.out.println(nombre + " va por el metro " + i);
            Thread.sleep(tiempoPorMetro); // TIEMPO EN CADA METRO

            if (i == finalCarrera) {
                System.out.println(nombre + " ha acabado la carrera.");
                // SOLO EL PRIMER HILO QUE LLEGA ACTUALIZA EL GANADOR
                synchronized (lock) {
                    if (nombreGanador == null) {
                        nombreGanador = nombre; // ESTABLECE EL GANADOR
                    } // VERIFICA SI YA HYA UN GANADOR
                } // SINCRONIZAR HILOS
            } // SI HA LLEGADO AL FINAL DE LA CARRERA SE ESTABLECE UN GANADOR
        } // SE ITERA TODOS LOS METROS DE LA CARRERA
    }
}