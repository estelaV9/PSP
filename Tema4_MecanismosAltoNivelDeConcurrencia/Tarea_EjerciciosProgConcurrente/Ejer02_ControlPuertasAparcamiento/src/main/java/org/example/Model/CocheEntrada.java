package org.example.Model;

import org.example.Main;

import static org.example.Main.*;

public class CocheEntrada implements Runnable {
    int id;

    public CocheEntrada(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        // ESPERA A QUE HAYA PLAZAS LIBRES
        try {
            plazasLibres.acquire();  // EL COCHE SOLO PUEDE ENTRAR SI HAY PLAZAS DISPONIBLES
            System.out.println("Coche " + id + " intentando entrar.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // SELECCIONAR UNA BARRERA DE ENTRADA DISPONIBLE
        for (int i = 0; i < E; i++) {
            esperarLlegada(i);  // ESPERA A LLEGAR A LA PUERTA
            elevarBarrera(i);    // ELEVAMOS LA BARRERA
        }
    }
} // CLASE COCHE ENTRANDO
