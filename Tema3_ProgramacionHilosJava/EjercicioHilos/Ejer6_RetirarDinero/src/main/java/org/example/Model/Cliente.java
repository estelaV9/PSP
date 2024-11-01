package org.example.Model;


public class Cliente extends Thread {
    private final Banco banco;
    private final String nombreCliente;

    public Cliente(Banco banco, String nombreCliente) {
        this.banco = banco;
        this.nombreCliente = nombreCliente;
    }

    @Override
    public void run() {
        try {
            banco.retirarDinero(nombreCliente);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
