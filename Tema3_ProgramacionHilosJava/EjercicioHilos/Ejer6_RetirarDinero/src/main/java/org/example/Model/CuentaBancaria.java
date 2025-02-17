package org.example.Model;

public class CuentaBancaria {
    private double cantidadCuenta; // CANTIDAD TOTAL QUE TIENE EN LA CUENTA

    public CuentaBancaria(double cantidadCuenta) {
        this.cantidadCuenta = cantidadCuenta;
    }

    public synchronized void retirarDinero(double cantidad) {
        if(cantidadCuenta < cantidad) {
            System.out.println("No hay suficiente saldo");
        } else {
            cantidadCuenta -= cantidad;
            System.out.println("Se ha retirado el dinero correctamente. Dinero actual: " + cantidadCuenta);
        } // VERIFICAR SI HAY SUFICIENTE SALDO EN LA CUENTA PARA RETIRAR
    } // METODO PARA RETIRAR DINERO
} // CLASE CUENTA BANCARIA QUE PERMITE RETIRAR DINERO DE UNA CUENTA