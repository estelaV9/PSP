package org.example;

import org.example.model.ProcesoP1;
import org.example.model.ProcesoP2;
import org.example.model.Semaphore;

// CLASE PRINCIPAL QUE SIMULA EL CRONOGRAMA DE EJECUCION DE LOS PROCESOS
public class Main {

    // SEMAFOROS INICIALIZADOS CON LOS VALORES PERTINENTES
    public static Semaphore s1 = new Semaphore(1); // SEMAFORO S1 INICIALIZADO EN 1
    static Semaphore s2 = new Semaphore(0); // SEMAFORO S2 INICIALIZADO EN 0

    public static void main(String[] args) {
        // SE CREAN Y SE INICIAN LOS HILOS DE LOS PROCESOS P1 Y P2
        Thread p1 = new Thread(new ProcesoP1(s1, s2));
        Thread p2 = new Thread(new ProcesoP2(s1, s2));

        // INICIAMOS LOS HILOS
        p1.start();
        p2.start();
    }
}

/* consola
P1: down(s1)
P1: f1()
P1: ejecucion de f1
P1: up(s2)
P1: down(s1)
P2: down(s2)
P2: f2()
P2: ejecucion de f2
P2: up(s1)
P2: down(s2)
P1: f1()
P1: ejecucion de f1
P1: up(s2)
P2: f2()
P2: ejecucion de f2
P2: up(s1)


Para el proceso P1 (cuando se ejecuta):
P1: down(s1): P1 adquiere el semáforo s1.
P1: f1(): P1 ejecuta su función f1().
P1: up(s2): P1 libera el semáforo s2 para que P2 pueda acceder a él.
Para el proceso P2 (cuando se ejecuta):
P2: down(s2): P2 intenta acceder al semáforo s2, pero como s2 tiene valor 0 al principio, P2 se bloquea y espera.
P2: f2(): Cuando P1 libera el semáforo s2 con up(s2), P2 ejecuta su función f2().
P2: up(s1): P2 libera el semáforo s1 para que P1 pueda continuar.

resultado
X = El proceso pasa a estado bloqueado.
     / = El proceso pasa a estado preparado.
     > = Fin de su ejecucion


     | #1| #2| #3|   |   | #4| #1| #2|   |   |   | #3| #4| #1|   |   |   |   |   |   |
   P1|---|---|---|   |   |---|---|---X   |   /   |---|---|--->   |   |   |   |   |   |
     |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
     |   |   |   | #1| #2|   |   |   | #3| #4| #1|   |   |   | #2| #3| #4| #1|   |   |
   P2|   |   |   |---|---X   /   |   |---|---|---|   |   |   |---|---|------->   |   |
     |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
     |___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|
     0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  20
Observaciones:

El proceso P1 comienza su ejecución hasta agotar su ventana de tiempo. Tras esto, el proceso P2 tiene su oportunidad de ejecución, pero bloquea al hacer el down del semáforo s2 cuyo contador vale 0 (instante de tiempo 5). El proceso P2 volverá a pasar a estado preparado en cuanto el proceso P1 realice up sobre el semáforo s2.
La instrucción #1 se tiene que ejecutar en el intento de tercera iteración para comprobar la condición de fin del bucle.
--Pneira 09:24 10 may 2011 (UTC)



 */