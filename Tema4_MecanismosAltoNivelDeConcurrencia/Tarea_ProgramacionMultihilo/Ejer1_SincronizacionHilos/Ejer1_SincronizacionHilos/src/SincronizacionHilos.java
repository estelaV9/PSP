import java.lang.management.ManagementFactory;

public class SincronizacionHilos {
    public static int contador = 0;
    public static void main(String[] args) throws InterruptedException {
        Thread[] hilos = new Thread[10];

        // LANZAR LOS 10 HILOS
        for (int i = 0; i < 10; i++) {
            hilos[i] = new Thread(new IncrementarContador());
            hilos[i].start();
        }

        // ESPERAR A QUE TERMINEN LOS HILOS
        for (Thread hilo : hilos) {
            hilo.join();
        }

        // Imprimir el valor final del contador
        System.out.println("Valor final del contador: " + contador);
    }
}