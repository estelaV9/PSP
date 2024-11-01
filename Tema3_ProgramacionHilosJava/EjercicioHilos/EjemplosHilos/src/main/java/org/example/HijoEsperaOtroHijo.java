package org.example;

public class HijoEsperaOtroHijo extends Thread {
    static HijoEsperaOtroHijo hilo[]; // ARRAY ESTÁTICO PARA GUARDAR REFERENCIAS A LOS DOS HILOS

    public void run() {
        String name = this.getName(); // OBTENEMOS EL NOMBRE DEL HILO

        try {
            // SI EL NOMBRE DEL HILO ES "0", ESTE HILO ESPERA A HILO[1] QUE ES EL OTRO HILO
            // SOLO HILO "0" ESPERA A HILO "1", PARA EVITAR EL DEADLOCK
            if (name.equals("0")) {
                hilo[1].join();  // EL HILO "0" ESPERA A QUE TERMINE HILO[1]
            }
            // EN CASO CONTRARIO, HILO "1" NO ESPERA A NADIE Y FINALIZA DIRECTAMENTE
        } catch (Exception e) {
            // CAPTURA Y MUESTRA CUALQUIER EXCEPCIÓN QUE PUEDA OCURRIR
            System.out.println("El hilo " + name + " se va por " + e);
        }

        // IMPRIME CUANDO EL HILO TERMINA SU EJECUCIÓN
        System.out.println("El hilo " + name + " finaliza correctamente");
    } // MÉTODO RUN() QUE EJECUTA LA LÓGICA DE CADA HILO

    public static void main(String args[]) throws Exception {
        System.out.println("Hola desde el hilo principal!");

        // INICIALIZA EL ARRAY DE DOS HILOS
        hilo = new HijoEsperaOtroHijo[2];

        // CREA EL PRIMER HILO Y LE ASIGNA EL NOMBRE "0"
        hilo[0] = new HijoEsperaOtroHijo();
        hilo[0].setName("0");
        hilo[0].start();  // INICIA EL PRIMER HILO

        // CREA EL SEGUNDO HILO Y LE ASIGNA EL NOMBRE "1"
        hilo[1] = new HijoEsperaOtroHijo();
        hilo[1].setName("1");
        hilo[1].start();  // INICIA EL SEGUNDO HILO

        // IMPRIME QUE EL HILO PRINCIPAL HA TERMINADO
        System.out.println("Fin Principal");
    }
}