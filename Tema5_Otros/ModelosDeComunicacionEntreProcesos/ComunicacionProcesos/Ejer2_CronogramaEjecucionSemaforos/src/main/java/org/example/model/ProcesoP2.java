package org.example.model;

// CLASE PARA SIMULAR EL PROCESO P2
public class ProcesoP2 implements Runnable {

    private Semaphore s1; // SEMAFORO S1
    private Semaphore s2; // SEMAFORO S2

    // CONSTRUCTOR QUE INICIALIZA LOS SEMAFOROS
    public ProcesoP2(Semaphore s1, Semaphore s2) {
        this.s1 = s1; // ASIGNA SEMAFORO S1
        this.s2 = s2; // ASIGNA SEMAFORO S2
    }

    // IMPLEMENTACION DE LA EJECUCION DEL HILO
    @Override
    public void run() {
        for (int i = 0; i < 2; i++) { // BUCLE QUE SE EJECUTA DOS VECES
            // LÍNEA #2: P2 EJECUTA down(s2)
            System.out.println("P2: down(s2)"); // LINEA #2
            s2.down(); // DECRECE SEMAFORO S2

            // LÍNEA #3: P2 EJECUTA f2()
            System.out.println("P2: f2()"); // LINEA #3
            f2(); // SIMULA LA FUNCION f2()

            // LÍNEA #4: P2 EJECUTA up(s1)
            System.out.println("P2: up(s1)"); // LINEA #4
            s1.up(); // INCREMENTA SEMAFORO S1
        }
    }

    // FUNCION f2() SIMULADA PARA EL PROCESO P2
    private void f2() {
        // SIMULA UNA EJECUCION PARA EL PROCESO P2
        System.out.println("P2: ejecucion de f2");
    }
}
