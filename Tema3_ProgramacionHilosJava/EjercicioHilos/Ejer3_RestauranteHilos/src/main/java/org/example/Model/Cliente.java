package org.example.Model;

public class Cliente extends Thread{
    private String nombre;
    private Cocina cocina;

    public Cliente(String nombre, Cocina cocina) {
        this.nombre = nombre;
        this.cocina = cocina;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) { // CONSUMIR 5 PLATOS
                cocina.consumirPlato();
                System.out.println(nombre + " ha consumido un plato.");
                Thread.sleep(1000); // TARDA UN SEGUNDO EN CONSUMIR PLATO
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}