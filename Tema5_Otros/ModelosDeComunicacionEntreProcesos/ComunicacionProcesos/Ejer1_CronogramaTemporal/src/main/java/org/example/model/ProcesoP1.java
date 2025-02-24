package org.example.model;

public class ProcesoP1 implements Runnable {
    private Semaphore s1;
    private Semaphore s2;

    // CONSTRUCTOR DE LA CLASE PROCESO P1
    public ProcesoP1(Semaphore s1, Semaphore s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    // IMPLEMENTACIÓN DEL HILO DE EJECUCIÓN DE P1
    public void run() {
        // EJECUTA UN BUCLE DE DOS ITERACIONES
        for (int i = 0; i < 2; i++) {
            // LÍNEA #2: P1 EJECUTA down(s1) - DECRECE EL SEMÁFORO S1
            System.out.println("P1: down(s1)"); // LINEA #2
            s1.down();

            // LÍNEA #3: P1 EJECUTA f1() - LÓGICA DE F1
            System.out.println("P1: f1()"); // LINEA #3
            f1();

            // LÍNEA #4: P1 EJECUTA get(s1) Y VERIFICA SI EL VALOR DE S1 ES 0
            if (s1.get() == 0) {
                // LÍNEA #5: P1 EJECUTA up(s2) - INCREMENTA EL SEMÁFORO S2
                System.out.println("P1: up(s2)"); // LINEA #5
                s2.up();
            }
        }
    }

    // FUNCION f1() PARA PROCESO P1
    private void f1() {
        // LÓGICA DE f1, AQUÍ PUEDE IR CUALQUIER CÓDIGO RELACIONADO CON EL PROCESO P1
        System.out.println("P1: ejecución de f1");
    }
}