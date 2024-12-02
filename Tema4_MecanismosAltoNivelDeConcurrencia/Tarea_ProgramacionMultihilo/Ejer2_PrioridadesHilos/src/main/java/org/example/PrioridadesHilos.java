package org.example;

public class PrioridadesHilos {
    public static void main(String[] args) {
        Thread hiloPrioritario = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Tarea prioritaria ejecutándose");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread hiloNoPrioritario = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Tarea no prioritaria ejecutándose");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        hiloPrioritario.setPriority(Thread.MAX_PRIORITY);
        hiloNoPrioritario.setPriority(Thread.MIN_PRIORITY);

        hiloPrioritario.start();
        hiloNoPrioritario.start();
    }
}