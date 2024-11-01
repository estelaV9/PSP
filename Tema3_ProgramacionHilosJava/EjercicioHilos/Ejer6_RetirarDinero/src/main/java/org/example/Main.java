package org.example;

import org.example.Model.Banco;
import org.example.Model.Cliente;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bienvenido al banco BankPal!");

        Banco banco = new Banco(1000.0); // SE INICIALIZA EL BANCO CON UN SALDO DE 1000

        // CREAR CLIENTES
        Cliente cliente1 = new Cliente(banco, "Sal Viejo");
        Cliente cliente2 = new Cliente(banco, "Eugenie");
        Cliente cliente3 = new Cliente(banco, "Daniele");

        // SE INICIAN LOS HILOS DE LOS CLIENTES
        cliente1.start();
        cliente2.start();
        cliente3.start();
    }
}