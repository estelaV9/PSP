package org.example.model;

public class ProcesoP2 implements Runnable {
    private Semaphore s1;
    private Semaphore s2;

    // CONSTRUCTOR DE LA CLASE PROCESO P2
    public ProcesoP2(Semaphore s1, Semaphore s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    // IMPLEMENTACIÓN DEL HILO DE EJECUCIÓN DE P2
    public void run() {
        // EJECUTA UN BUCLE DE DOS ITERACIONES
        for (int i = 0; i < 2; i++) {
            // LINEA #2: P2 EJECUTA down(s2) - DECRECE EL SEMAFORO S2
            System.out.println("P2: down(s2)"); // LINEA #2
            s2.down();

            // LINEA #3: P2 EJECUTA f2() - LÓGICA DE F2
            System.out.println("P2: f2()"); // LINEA #3
            f2();

            // LINEA #4: P2 EJECUTA up(s1) - INCREMENTA EL SEMAFORO S1
            System.out.println("P2: up(s1)"); // LINEA #4
            s1.up();
        }
    }

    // FUNCION f2() PARA PROCESO P2
    private void f2() {
        // LÓGICA DE f2, AQUI PUEDE IR CUALQUIER CÓDIGO RELACIONADO CON EL PROCESO P2
        System.out.println("P2: ejecución de f2");
    }
}