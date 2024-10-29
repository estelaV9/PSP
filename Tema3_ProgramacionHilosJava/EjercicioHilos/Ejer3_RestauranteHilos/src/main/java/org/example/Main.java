package org.example;

import org.example.Model.Chef;
import org.example.Model.Cliente;

public class Main {
    public static void main(String[] args) {
        // OBJETO QUE REPRESENTA AL CHEF QUE PREPARA PLATOS
        Chef productor = new Chef("Eugenie");

        // OBJETO QUE REPRESENTA A LOS CLIENTES QUE CONSUMEN ESOS PLATOS
        Cliente consumidor = new Cliente("Daniele");


        productor.start(); // PRODUCE PLATOS
    }
}