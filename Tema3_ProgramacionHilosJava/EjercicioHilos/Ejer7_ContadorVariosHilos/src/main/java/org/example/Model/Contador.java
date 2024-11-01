package org.example.Model;

public class Contador {
    private int valorContador;
    private final int valorFinal;
    private boolean hayHiloIncrementando = false; // INDICA SI HAY UN HILO INCREMENTANDO
    private final Object lock = new Object();

    public Contador() {
        this.valorContador = 0;
        this.valorFinal = 10; // LIMITE CONTADOR
    }

    public void incrementarContador(String nombre) throws InterruptedException {
        while (valorContador < valorFinal) {
            synchronized (lock) {
                if (valorContador >= valorFinal) {
                    break; // SALIR DEL BUCLE SI SE ALCANZA EL VALOR FINAL
                } // VERIFICAR SI EL CONTADOR YA HA ALCANZADO O SUPERADO EL VALOR FINAL

                while (hayHiloIncrementando) {
                    System.out.println(nombre + " espera a incrementar " + valorContador);
                    lock.wait(); // EL HILO ESPERA HASTA QUE SEA NOTIFICADO
                } // ESPERAR SI HAY UN HILO INCREMENTANDO

                hayHiloIncrementando = true; // HILO SE ESTÁ INCREMENTANDO
                valorContador++; // INCREMENTAR VALOR
                System.out.println(nombre + " ha incrementado el contador: " + valorContador);
                Thread.sleep(1000); // ESPERA 1 SEGUNDO

                hayHiloIncrementando = false; // HILO YA NO ESTA INCREMENTANDO
                lock.notifyAll(); // NOTIFICAR A OTROS HILOS QUE PUEDEN INCREMENTAR

            } // BLOQUE SINCRONIZADO
        } // INICIA UN BUCLE QUE CONTINÚA MIENTRAS EL VALOR DEL CONTADOR SEA MENOR QUE EL VALOR FINAL
    } // METODO SINCRONIZADO PARA INCREMENTAR EL CONTADOR
}