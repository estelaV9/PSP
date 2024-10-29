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
            cocina.agregarPlato();
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