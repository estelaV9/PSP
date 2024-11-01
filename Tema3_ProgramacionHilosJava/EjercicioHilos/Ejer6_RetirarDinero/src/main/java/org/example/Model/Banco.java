package org.example.Model;

import java.util.Scanner;

public class Banco {
    private final Object lock = new Object();
    private boolean hayClienteEnCajero = false; // INDICA SI HAY UN CLIENTE EN EL CAJERO
    private final CuentaBancaria cuentaBancaria;
    private final Scanner reader = new Scanner(System.in);

    public Banco(double saldoInicial) {
        cuentaBancaria = new CuentaBancaria(saldoInicial); // SE CREA UNA CUENTA BANCARIA CON EL SALDO INICIAL
    }

    public void retirarDinero(String nombreCliente) throws InterruptedException {
        synchronized (lock) {
            while (hayClienteEnCajero) {
                System.out.println(nombreCliente + " espera para retirar dinero.");
                lock.wait(); // EL CLIENTE ESPERA SI EL CAJERO ESTA OCUPADO
            } // SI HAY UN CLIENTE RETIRANDO DINERO, ESPERA

            hayClienteEnCajero = true; // INDICAR SI HAY CLIENTE EN EL CAJERO

            double cantidadRetirar = -1;

            while (cantidadRetirar != 0) {
                // RETIRAR DINERO
                System.out.println("Cuanto quiere retirar " + nombreCliente + "? (0 para salir)");
                cantidadRetirar = reader.nextDouble();

                if (cantidadRetirar > 0) {
                    System.out.println(nombreCliente + " está retirando " + cantidadRetirar + " del cajero.");
                    Thread.sleep(2000); // TARDA 2 SEGUNDOS EN RETIRAR
                    cuentaBancaria.retirarDinero(cantidadRetirar); // SE RETIRA EL DINERO
                } else if (cantidadRetirar == 0) {
                    System.out.println(nombreCliente + " ha decidido no retirar dinero.");
                } // VERIFICAR LA CANTIDAD A RETIRAR (si es 0 se sale)
            }

            hayClienteEnCajero = false; // EL CLIENTE TERMINA
            lock.notifyAll(); // NOTIFICAR QUE YA PUEDEN INTENTAR RETIRAR DINERO
        }
    }
}
