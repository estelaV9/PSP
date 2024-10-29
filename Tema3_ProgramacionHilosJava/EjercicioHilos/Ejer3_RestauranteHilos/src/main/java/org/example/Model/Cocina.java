package org.example.Model;

public class Cocina {
    // LA COCINA TIENE UN LIMITE EN LA CANTIDAD DE PLATOS QUE PUEDE CONTENER (5)
    private int numPlatos;

    public Cocina() {
        this.numPlatos = 5;
    }

    public int getNumPlatos() {
        return numPlatos;
    }

    public void setNumPlatos(int numPlatos) {
        this.numPlatos = numPlatos;
    }
}
