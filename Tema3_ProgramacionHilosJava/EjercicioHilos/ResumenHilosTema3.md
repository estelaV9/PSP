# Resumen de los ejercicios de hilos del tema 3
## Índice
- <a href="https://github.com/estelaV9/PSP/tree/master/Tema3_ProgramacionHilosJava/EjercicioHilos/EjemplosHilos/src/main/java/org/example">Ejemplo hilos</a>.
- <a href="https://github.com/estelaV9/PSP/tree/master/Tema3_ProgramacionHilosJava/EjercicioHilos/EjerciciosTema/src/main/java/org/example">Ejercicio Tema</a>.
- <a href="https://github.com/estelaV9/PSP/tree/master/Tema3_ProgramacionHilosJava/EjercicioHilos/Ejer1_CronometroSincronizadoHasta100/src/main/java/org/example">Ejercicio 1</a>: Cronómetro sincronizado hasta 100.
- <a href="https://github.com/estelaV9/PSP/tree/master/Tema3_ProgramacionHilosJava/EjercicioHilos/Ejer2_ProgresoTarea/src/main/java/org/example">Ejercicio 2</a>: Progreso de una Tarea.
- <a href="https://github.com/estelaV9/PSP/tree/master/Tema3_ProgramacionHilosJava/EjercicioHilos/Ejer3_RestauranteHilos/src/main/java/org/example">Ejercicio 3</a>: Restaurante sincronizado.
- <a href="https://github.com/estelaV9/PSP/tree/master/Tema3_ProgramacionHilosJava/EjercicioHilos/Ejer4_MostrarNumerosConRetardo/src/main/java/org/example">Ejercicio 4</a>: Mostrar numeros con retardo.
- <a href="https://github.com/estelaV9/PSP/tree/master/Tema3_ProgramacionHilosJava/EjercicioHilos/Ejer5_SimulacionCarrera/src/main/java/org/example">Ejercicio 5</a>: Simulación de carrera.
- <a href="https://github.com/estelaV9/PSP/tree/master/Tema3_ProgramacionHilosJava/EjercicioHilos/Ejer6_RetirarDinero/src/main/java/org/example">Ejercicio 6</a>: Retirar dinero de un banco.
- <a href="https://github.com/estelaV9/PSP/tree/master/Tema3_ProgramacionHilosJava/EjercicioHilos/Ejer7_ContadorVariosHilos/src/main/java/org/example">Ejercicio 7</a>: Contador que se incrementa con varios hilos.


## <a href="https://github.com/estelaV9/PSP/tree/master/Tema3_ProgramacionHilosJava/EjercicioHilos/EjemplosHilos/src/main/java/org/example">Ejemplo hilos</a>.
### <a href="https://github.com/estelaV9/PSP/blob/master/Tema3_ProgramacionHilosJava/EjercicioHilos/EjemplosHilos/src/main/java/org/example/HijoEsperaOtroHijo.java">Ejemplo 1: </a>Hijo espera otro hijo.
En este caso, dos hilos se crean en un array `hilo[]`, y uno de ellos (`hilo[0]`) espera la finalización del otro (`hilo[1])` usando `join()`. 
```java
if (name.equals("0")) {
    hilo[1].join(); // hilo[0] espera a que termine hilo[1]
}
```

### <a href="https://github.com/estelaV9/PSP/blob/master/Tema3_ProgramacionHilosJava/EjercicioHilos/EjemplosHilos/src/main/java/org/example/PadreEsperaHijo.java">Ejemplo 2: </a>Padre espera a hijo.
El hilo principal crea un hilo (`PadreEsperaHijo`) y usa `join()` para esperar a que dicho hilo termine antes de continuar. 
Este método asegura que el hilo principal no imprima `"Fin Principal"` hasta que `hilo1` haya completado su ejecución.
```java
hilo1.start();
hilo1.join(); // ESPERA A QUE HILO1 TERMINE ANTES DE CONTINUAR
```

### <a href="https://github.com/estelaV9/PSP/blob/master/Tema3_ProgramacionHilosJava/EjercicioHilos/EjemplosHilos/src/main/java/org/example/PadreInterrumpeSleepdelHijo.java">Ejemplo 3:</a> Interrupcion durante `sleep()`.
Se maneja las interrupciones durante el estado de espera de un hilo usando `sleep()`. 
Cuando el hilo recibe una interrupción mientras está dormido, se lanza una excepción `InterruptedException`, lo cual el hilo utiliza para terminar su ejecución.
```java
public void run() {
    for (int i = 0; i < 10; i++) {
        try {
            System.out.println("Esperando a recibir dato!");
            Thread.sleep(5000); // Simula una espera prolongada
        } catch (InterruptedException e) { // Maneja la interrupción durante sleep
            System.out.println("Hilo interrumpido.");
            return; // Termina la ejecución del hilo
        }
    }
}
```

### <a href="https://github.com/estelaV9/PSP/blob/master/Tema3_ProgramacionHilosJava/EjercicioHilos/EjemplosHilos/src/main/java/org/example/PadreInterrumpeSleepdelHijo.java">Ejemplo 4:</a> Interrupcion de la tarrea con `interrupted()`.
Un hilo (`PadreInterrumpeTareaHijo`) puede ser interrumpido durante la ejecución de una tarea. 
El hilo imprime un mensaje varias veces y verifica periódicamente si ha sido interrumpido. 
Cuando el hilo detecta la interrupción (usando `Thread.interrupted()`), sale del bucle y termina.
```java
public void run() {
    for (int i = 0; i < 10; i++) {
        System.out.println("Esperando a recibir dato!");
        if (Thread.interrupted()) { // Verifica si el hilo ha sido interrumpido
            System.out.println("Hilo interrumpido.");
            return; // Termina la ejecución del hilo
        }
    }
}
```

## <a href="https://github.com/estelaV9/PSP/tree/master/Tema3_ProgramacionHilosJava/EjercicioHilos/Ejer1_CronometroSincronizadoHasta100/src/main/java/org/example">Ejercicio 1</a>: Cronómetro sincronizado hasta 100.
Implementar en el método `run()` un bucle `for` que vaya imprimiento el valor de `i` esperando 1 segundo.
```java
@Override
public void run() {
    try {
        for(int i = 1; i <= 100; i++){
            Cronometro.sleep(1000);
            System.out.println(i);
        }
    } catch (InterruptedException e) {
        // SI EL HILO ES INTERRUMPIDO, FINALIZA EL HILO
        Cronometro.currentThread().interrupt();
    }
} // CREAR METODO
```

 
## <a href="https://github.com/estelaV9/PSP/tree/master/Tema3_ProgramacionHilosJava/EjercicioHilos/Ejer2_ProgresoTarea/src/main/java/org/example">Ejercicio 2</a>: Progreso de una Tarea.
Implementar en el método `run()` un bucle `for` que vaya imprimiento el valor de `i`, simulando el porcentaje de descarga, esperando 1 segundo.
```java
System.out.println("Empezando descarga...");

for (int i = 0; i <= 100; i += 10) {
    // SE BORRA LO QUE TENGA EL SOUT CON \r Y SE IMPRIME EL VALOR DE i EN FORMA DE PORCENTAJE
    System.out.print("\r" + i + "%");
    Thread.sleep(1000); // ESPERA 1 SEGUNDO
} // SE RECORRE DEL 1 AL 100 EN PASO DE 10

System.out.println("\nDescarga completada"); // MUESTRA MENSAJE DE FINALIZACION
```

## <a href="https://github.com/estelaV9/PSP/tree/master/Tema3_ProgramacionHilosJava/EjercicioHilos/Ejer3_RestauranteHilos/src/main/java/org/example">Ejercicio 3</a>: Restaurante sincronizado.
```java

```

## <a href="https://github.com/estelaV9/PSP/tree/master/Tema3_ProgramacionHilosJava/EjercicioHilos/Ejer4_MostrarNumerosConRetardo/src/main/java/org/example">Ejercicio 4</a>: Mostrar numeros con retardo.


## <a href="https://github.com/estelaV9/PSP/tree/master/Tema3_ProgramacionHilosJava/EjercicioHilos/Ejer5_SimulacionCarrera/src/main/java/org/example">Ejercicio 5</a>: Simulación de carrera.
Se utiliza el método `join()` para que todos los hilos terminen su ejecución antes de mostrar el ganador.
```java
// ESPERAMOS A QUE LOS HILOS TERMINEN
/** nota: El método join() se usa para esperar a que un hilo termine su ejecución
 * antes de continuar con el codigo. Esto se utiliza para que un hilo no prosiga hasta
 * que otro hilo haya completado su trabajo. **/
try {
    perroHilo.join();
    gatoHilo.join();
} catch (InterruptedException e) {
    e.printStackTrace();
}
```

Se utiliza el metodo `synchronized` con un objeto para establecer un solo ganador, evitando que múltiples hilos sobreescriban esta información.
```java
if (i == finalCarrera) {
  System.out.println(nombre + " ha acabado la carrera.");
  // SOLO EL PRIMER HILO QUE LLEGA ACTUALIZA EL GANADOR
  synchronized (lock) {
      if (nombreGanador == null) {
          nombreGanador = nombre; // ESTABLECE EL GANADOR
      } // VERIFICA SI YA HYA UN GANADOR
  } // SINCRONIZAR HILOS
} // SI HA LLEGADO AL FINAL DE LA CARRERA SE ESTABLECE UN GANADOR
```

## <a href="https://github.com/estelaV9/PSP/tree/master/Tema3_ProgramacionHilosJava/EjercicioHilos/Ejer6_RetirarDinero/src/main/java/org/example">Ejercicio 6</a>: Retirar dinero de un banco.
Solo un cliente puede estar sacando dinero de un cajero, asi que se utiliza la sincronización para gestionar el acceso concurrente. 

Al entrar al método, se verifica si otro cliente ya está utilizando el cajero; si es así, el cliente actual espera hasta que el cajero esté libre. Una vez dentro, el cliente puede ingresar la cantidad que desea retirar, con la opción de salir introduciendo `0`. Mientras se retira el dinero, se simula un retraso de dos segundos. 

Una vez que el cliente termina su transacción, se libera el cajero, permitiendo que otros clientes puedan acceder a él, lo que se notifica a través del método `notifyAll`. Este enfoque asegura que las operaciones de retiro se realicen de manera ordenada y evita conflictos entre múltiples clientes que intentan usar el cajero simultáneamente.

```java
public void retirarDinero(String nombreCliente) throws InterruptedException {
  synchronized (lock) {
      while (hayClienteEnCajero) {
          System.out.println(nombreCliente + " espera para retirar dinero.");
          lock.wait(); // EL CLIENTE ESPERA SI EL CAJERO ESTA OCUPADO
      } // SI HAY UN CLIENTE RETIRANDO DINERO, ESPERA

      hayClienteEnCajero = true; // INDICAR SI HAY CLIENTE EN EL CAJERO

      double cantidadRetirar = -1;

      while (cantidadRetirar != 0) {
          // RETIRAR DINERO
          System.out.println("Cuanto quiere retirar " + nombreCliente + "? (0 para salir)");
          cantidadRetirar = reader.nextDouble();

          if (cantidadRetirar > 0) {
              System.out.println(nombreCliente + " está retirando " + cantidadRetirar + " del cajero.");
              Thread.sleep(2000); // TARDA 2 SEGUNDOS EN RETIRAR
              cuentaBancaria.retirarDinero(cantidadRetirar); // SE RETIRA EL DINERO
          } else if (cantidadRetirar == 0) {
              System.out.println(nombreCliente + " ha decidido no retirar dinero.");
          } // VERIFICAR LA CANTIDAD A RETIRAR (si es 0 se sale)
      }

      hayClienteEnCajero = false; // EL CLIENTE TERMINA
      lock.notifyAll(); // NOTIFICAR QUE YA PUEDEN INTENTAR RETIRAR DINERO
  }
}
```

## <a href="https://github.com/estelaV9/PSP/tree/master/Tema3_ProgramacionHilosJava/EjercicioHilos/Ejer7_ContadorVariosHilos/src/main/java/org/example">Ejercicio 7</a>: Contador que se incrementa con varios hilos.
Este ejercicio permite que varios hilos incrementen un contador utilizando sincronización. 

Mientras el contador no alcance un valor final, el hilo verifica si otro hilo ya está incrementando el contador. Si es así, espera hasta ser notificado. Una vez que obtiene acceso, incrementa el contador, imprime el nuevo valor y espera un segundo para simular el tiempo de procesamiento. 

Finalmente, notifica a otros hilos que pueden intentar incrementar el contador. Así, garantiza que el acceso al contador sea solo para un hilo a la vez.

```java
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
```


---
>_IES Ribera de Castilla 24/25._
