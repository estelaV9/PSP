package org.example.Model;

import static org.example.Main.*;

public class CocheSalida implements Runnable {
    int id;

    public CocheSalida(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        // ESPERA A QUE SE PUEDA SALIR
        System.out.println("Coche " + id + " intentando salir.");
        for (int i = E; i < E + S; i++) {
            esperarLlegada(i); // ESPERA A LLEGAR A LA PUERTA DE SALIDA
            elevarBarrera(i); // SE ELEVA LA BARRERA
        }
        plazasLibres.release(); // SE LIBERA UNA PLAZA EN EL APARCAMIENTO
        System.out.println("Coche " + id + " ha salido.");
    } // METODO RUN()
} // CLASE COCHE SALIENDO
