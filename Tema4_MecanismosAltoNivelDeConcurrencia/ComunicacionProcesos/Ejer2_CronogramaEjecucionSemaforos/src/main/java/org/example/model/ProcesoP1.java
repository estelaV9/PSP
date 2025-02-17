package org.example.model;

// CLASE PARA SIMULAR EL PROCESO P1
public class ProcesoP1 implements Runnable {

    private Semaphore s1; // SEMAFORO S1
    private Semaphore s2; // SEMAFORO S2

    // CONSTRUCTOR QUE INICIALIZA LOS SEMAFOROS
    public ProcesoP1(Semaphore s1, Semaphore s2) {
        this.s1 = s1; // ASIGNA SEMAFORO S1
        this.s2 = s2; // ASIGNA SEMAFORO S2
    }

    // IMPLEMENTACION DE LA EJECUCION DEL HILO
    @Override
    public void run() {
        for (int i = 0; i < 2; i++) { // BUCLE QUE SE EJECUTA DOS VECES
            // LÍNEA #2: P1 EJECUTA down(s1)
            System.out.println("P1: down(s1)"); // LINEA #2
            s1.down(); // DECRECE SEMAFORO S1

            // LÍNEA #3: P1 EJECUTA f1()
            System.out.println("P1: f1()"); // LINEA #3
            f1(); // SIMULA LA FUNCION f1()

            // LÍNEA #4: P1 VERIFICA EL VALOR DE S1 Y EJECUTA up(s2)
            if (s1.get() == 0) { // VERIFICA SI S1 ES 0
                System.out.println("P1: up(s2)"); // LINEA #4
                s2.up(); // INCREMENTA SEMAFORO S2
            }
        }
    }

    // FUNCION f1() SIMULADA PARA EL PROCESO P1
    private void f1() {
        // SIMULA UNA EJECUCION PARA EL PROCESO P1
        System.out.println("P1: ejecucion de f1");
    }
}

