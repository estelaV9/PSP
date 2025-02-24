package org.example;

import org.example.model.Consumidor;
import org.example.model.Productor;
import org.example.model.Semaphore;

// CLASE PRINCIPAL PARA SIMULAR EL PROBLEMA PRODUCTOR-CONSUMIDOR
public class Main {
    // SEMAFOROS INICIALIZADOS A 1
    public static Semaphore s1 = new Semaphore(1); // El productor espera a que el consumidor consuma.
    public static Semaphore s2 = new Semaphore(0); // El consumidor espera a que el productor produzca.

    public static void main(String[] args) {
        // CREACION DE LOS HILOS PARA LOS PROCESOS PRODUCTOR Y CONSUMIDOR
        Thread productor = new Thread(new Productor(), "Productor");
        Thread consumidor = new Thread(new Consumidor(), "Consumidor");

        // INICIO DE LOS HILOS
        productor.start();
        consumidor.start();
    }
}

/* consola
Consumidor pasa por up() con count = 1
Consumidor esperando en down()...
Productor pasa por down() con count = 0
Productor: Produciendo un item.
Productor pasa por up() con count = 1
Productor esperando en down()...
Consumidor pasa por down() con count = 0
Consumidor: Consumiendo un item.
Consumidor pasa por up() con count = 1
Consumidor esperando en down()...
Productor pasa por down() con count = 0
Productor: Produciendo un item.
Productor pasa por up() con count = 1
Productor esperando en down()...
Consumidor pasa por down() con count = 0
Consumidor: Consumiendo un item.
Consumidor pasa por up() con count = 1
Consumidor esperando en down()...
Productor pasa por down() con count = 0
Productor: Produciendo un item.
Productor pasa por up() con count = 1
Productor esperando en down()...


Consumidor pasa por up() con count = 1: Esto ocurre cuando el Consumidor ha terminado de consumir un
item y libera el semáforo s1, lo que permite que el Productor pase a producir.

Consumidor esperando en down(): Aquí el Consumidor está esperando a que el Productor produzca un item,
ya que el semáforo s2 está a 0 en este momento, y no puede proceder hasta que el Productor lo libere con un up().

Productor pasa por down() con count = 0: El Productor está esperando en el semáforo s1, bloqueado porque
el Consumidor todavía no ha consumido ningún item. Después de que el Consumidor consuma, el Productor puede proceder a producir.

Productor produce y pasa por up() con count = 1: Una vez que el Productor ha producido el item, libera el
semáforo s2, permitiendo que el Consumidor pase a consumir.

Ciclo repetitivo: se repite en un bucle infinito. Los semáforos funcionan como mecanismo de
 sincronización entre ambos procesos. El Productor y el Consumidor se van alternando para producir y consumir, respetando el orden adecuado.
 */