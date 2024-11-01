package org.example.Model;

public class Chef extends Thread {
    private String nombreChef;
    private int platosRealizados;
    private Cocina cocina;

    public Chef(String nombreChef, Cocina cocina) {
        this.nombreChef = nombreChef;
        this.cocina = cocina;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) { // PREPARAR 5 PLATOS
                cocina.agregarPlato();
                System.out.println(nombreChef + " ha preparado un plato.");
                Thread.sleep(1000); // TARDA UN SEGUNDO EN AGREGAR PLATO
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getNombreChef() {
        return nombreChef;
    }

    public void setNombreChef(String nombreChef) {
        this.nombreChef = nombreChef;
    }

    public int getPlatosRealizados() {
        return platosRealizados;
    }

    public void setPlatosRealizados(int platosRealizados) {
        this.platosRealizados = platosRealizados;
    }
}