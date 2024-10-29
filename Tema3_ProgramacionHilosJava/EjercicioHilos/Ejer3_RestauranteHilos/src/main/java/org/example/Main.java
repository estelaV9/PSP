package org.example;

import org.example.Model.Chef;
import org.example.Model.Cliente;
import org.example.Model.Cocina;

public class Main {
    public static void main(String[] args) {
        // OBJETO PARA REPRESENTAR LA COCINA CON UNA CAPACIDAD DE 5 PLATOS
        Cocina cocina = new Cocina(5);
        // OBJETO QUE REPRESENTA AL CHEF QUE PREPARA PLATOS
        Chef productor = new Chef("Eugenie", cocina);

        // OBJETO QUE REPRESENTA A LOS CLIENTES QUE CONSUMEN ESOS PLATOS
        Cliente consumidor = new Cliente("Daniele", cocina);

        System.out.println("Bienvenido a la simulación de un restaurante!");
        productor.start(); // PRODUCE PLATOS
        consumidor.start(); // COME PLATOS
    }
}