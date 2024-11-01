package org.example.Model;

public class Contador extends Thread{
    @Override
    public void run() {
        for(int i = 1; i <= 20; i++){
            if(i != 20){
                System.out.print(i + " - ");
            } else {
                System.out.print(i);
            } // SI i NO ES 20 SE PONE UN GUION

            try {
                Thread.sleep(1500); // ESPERAR 1 SEGUNDO Y MEDIO
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } // BUCLE QUE ITERA HASTA EL NUMERO 20
    } // METODO RUN
}
