public class SincronizacionHilos {
    private static int contador = 0;
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread[] hilos = new Thread[10];
        for (int i = 0; i < 10; i++) {
            hilos[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    synchronized (lock) {
                        contador++;
                    }
                }
            });
            hilos[i].start();
        }

        for (Thread hilo : hilos) {
            hilo.join();
        }

        System.out.println("Valor final del contador: " + contador);
    }
}