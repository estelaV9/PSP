# Resumen de Ejercicio Hilos
## Primer ejercicio de introducción a hilos (<a href="https://www.youtube.com/watch?v=JTXWVZctk3I&list=PLYwQpNMBCzIm_o2e89tJXXQ0btAFKh0BE&index=2&t=1749s">vídeo de referencia</a>)
### Crear un proceso e instanciarlo
1. Creamos una clase donde implementaremos el método run()
```java
public class MiProceso extends Thread { // la clase extiende de Thread
    @Override
    public void run() {
        super.run(); // SE EJECUTA AUTOMATICAMENTE
    }
}
```
2. Creamos un objeto e iniciamos el hilo
```java
MiProceso proceso = new Proceso()
proceso.start() // SE IMPLEMNTA EL METODO 'start()' NO EL METODO 'run()'
```

### Constructor
```java
    // THREAD TIENE DISTINTOS CONSTRUCTORES
    // Thread(String name) METE UN NOMBRE AL PROCESO
    public MiProceso(String name, Contador contadorComun) {
        super(name); // LLAMAMOS AL OBJETO PADRE
        contadorProceso = contadorComun;
    }
```

> [!NOTE]
> Para saber el nombre del hilo poner `Thread.currentThread().getName()`. Por ejemplo:
> ```java
> System.out.println(Thread.currentThread().getName() + " Soy el proceso creado");
> ```

### Dormir un hilo
`Thread.sleep(1000)`: Duerme el hilo durante un segundo. <br>
Esto simula una operacion que toma tiempo pero implica que el hijo actual pierda el uso de la CPU.


### Bloque synchronized
Evitar que dos hilos accedan y modifiquen el contador al mismo tiempo
```java
// BUCLE QUE SE EJECUTA MIENTRAS EL NUMERO EN CONTADOR SEA MENOR QUE 1000
for (; contadorProceso.getNumero() < 1000; ) {
    // BLOQUE SINCRONIZADO PARA ASEGURAR QUE SOLO UN HIJO ACCEDA A CONTADOR A LA VEZ
    synchronized (contadorProceso) {
        // IMPRIME EL NOMBRE DEL HILO ACTUAL Y EL VALOR DEL CONTADOR
                System.out.println("Proceso: " + Thread.currentThread().getName() +
                        " - Contador: " + contadorProceso.getNumero());

                /*  PARA DORMIR EL HILO ACTUAL DURANTE 1 SEGUNDO. ESTO SIMULA UNA OPERACION QUE TOMA TIEMPO.
                    PERO IMPLICA QUE EL HILO ACTUAL PIERDA EL USO DE LA CPU *//*
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e); // Manejo de excepción si el hilo es interrumpido.
                } */

                // AUMENTO DE CONTADOR: EL PROCESO PUEDE PERDER LA CPU ENTRE LA LETURA Y LA ESCRITURA LO QUE PUEDE
                // CAUSAR PROBLEMAS DE CONCURRENCIA
                int tmp = contadorProceso.getNumero(); // OBTIENE EL VALOR ACTRUAL DEL CONTADOR
                tmp++; // INCREMENTA EL VALOR TEMPORALMENTE
                contadorProceso.setNumero(tmp); // ESTABLECE EL NUEVO VALOR DEL CONTADOR

            } // FIN BLOQUE SINCRONIZADO, LIBERANDO EL OBJETO COMPARTIDO
            /* ESTE MECANISMO EVITA QUE DOS HILOS ACCEDAN Y MODIFIQUEN EL CONTADOR AL MISMO TIEMPO LO QUE PREVIENE LA POSIBILIDAD
            DE OBTENER EL MISMO NUMERO EN DIFERENTES HILO */
        }

```




---
>_IES Ribera de Castilla 24/25._
