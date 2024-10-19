# TEMA 2: ESTADOS DE UN PROCESO
## Estados de un Proceso
Un proceso en un sistema operativo puede encontrarse en diversos estados según su etapa de ejecución. Los principales estados son:

1. **Nuevo (New)**: El proceso se está creando a partir de un archivo ejecutable.
2. **Preparado (Ready)**: El proceso está listo y espera que el planificador del sistema operativo le asigne un procesador.
3. **En Ejecución (Running)**: El proceso se encuentra en la CPU ejecutando instrucciones.
4. **Bloqueado (Waiting)**: El proceso no puede continuar su ejecución hasta que ocurra un evento, como la finalización de una operación de E/S.
5. **Interbloqueo (Deadlock)**: Ocurre cuando dos o más procesos se bloquean mutuamente al esperar por recursos asignados entre ellos.
6. **Terminado (Terminated)**: El proceso ha finalizado su ejecución y el sistema operativo libera los recursos que consumía.

## Modelo de Proceso de Cinco Estados
Las transiciones entre estados en este modelo incluyen:
- **Ninguno → Nuevo**: Se crea un nuevo proceso para ejecutar un programa.
- **Nuevo → Preparado**: El proceso está listo para entrar en la cola de preparados.
- **Preparado → Ejecución**: El sistema selecciona un proceso de la cola de preparados para ejecutarse.
- **Ejecución → Terminado**: El proceso finaliza su ejecución y libera los recursos.
- **Ejecución → Preparado**: El proceso cede voluntariamente la CPU o es interrumpido.
- **Ejecución → Bloqueado**: El proceso espera por un evento específico para continuar.
- **Bloqueado → Preparado**: El evento por el cual el proceso estaba esperando ocurre.
- **Preparado → Terminado**: Un padre puede terminar con un proceso hijo en cualquier momento o si el padre termina, todos sus hijos se pueden terminar.
- **Bloqueado → Terminado**: el mismo criterio que el anterior.

## Modelo de Proceso de Siete Estados
Además de los cinco estados anteriores, este modelo agrega:
- **Nuevo a Bloqueado**: Un proceso puede estar bloqueado desde el principio si requiere la espera de recursos.
- **Bloqueado a Ejecución**: Un proceso puede pasar directamente a la ejecución cuando un recurso se libera.

## Colas de Espera
Existen dos colas principales:
1. **Cola de Listos**: Contiene los procesos nuevos que están listos para ejecutarse.
2. **Cola de Bloqueados**: Contiene los procesos que están esperando que ocurra un evento.

El uso de una única cola de bloqueados es ineficiente en sistemas grandes, ya que al ocurrir un evento, el sistema debe recorrer toda la cola para encontrar los procesos correspondientes. Una solución más eficiente es tener varias colas, cada una asociada a un evento específico, lo que permite mover directamente los procesos a la cola de listos cuando dicho evento ocurre.

## Niveles de Planificación
La planificación hace referencia a un conjunto de políticas y mecanismos incorporados al SO que determinan en qué orden se va a ejecutar las tareas pendientes. Existen tres tipos:

### 1. **Planificador de Largo Plazo (PLP)**:
El planificador de largo plazo asigna procesos a la cola de preparados, equilibrando las cargas de trabajo entre procesos que demandan mucho uso de CPU o de E/S. Si la utilización de la CPU es baja, admite más trabajos para aumentar la cantidad de procesos listos, mejorando la eficiencia. En cambio, cuando la CPU está muy ocupada, el planificador reduce la admisión de nuevos trabajos para evitar sobrecargar el sistema y aumentar los tiempos de respuesta.


### 2. **Planificador de Corto Plazo (PCP)**:
Administra la asignación y desasignación de la CPU entre los procesos listos para ejecutarse (**gestiona la cola de listos**). Se invoca frecuentemente por lo que debe ser rápido en la decisión.

Tipos de planificación:
   - **Planificación sin Desalojo (Cooperativa)**: El proceso en ejecución se cambia si dicho proceso se bloquea o termina.
     
   - **Planificación Apropiativa**: Además de los casos de la planificación cooperativa, se cambia el proceso en ejecución si en cualquier momento en que un proceso se está ejecutando, otro proceso con mayor prioridad se puede ejecutar. <br>
La aparición de un proceso más prioritario se puede deber tanto al desbloqueo del mismo como a la creación de un nuevo proceso.
     
   - **Tiempo Compartido**: Cada cierto tiempo (**cuanto**) se desaloja el proceso que estaba en ejecución y se selecciona otro proceso para ejecutarse. En este caso, todas las prioridades de los hilos se consideran iguales.

### 3. **Planificador de Medio Plazo (PMP)**:
En los sistemas de multiprogramación y tiempo compartido, varios procesos residen en la memoria principal, pero su tamaño limitado restringe el número de procesos. Si todos los procesos en memoria están bloqueados, la CPU se desperdicia. <br>
Para evitarlo, se utiliza **swapping**, intercambiando procesos entre la memoria principal y secundaria. El planificador a medio plazo gestiona estas transiciones, maximizando el uso de recursos al mover procesos bloqueados a memoria secundaria o trayendo de vuelta aquellos que solo estaban bloqueados por falta de memoria.

## Cambio de Contexto
Consiste en desalojar a un proceso de la CPU y reanudar otro. Se conoce como **contexto** a:
- **Estado del proceso**: Se guarda el estado del proceso saliente en su PCB (Process Control Block) y recuperan los registros del proceso que entra.
- **Estado del procesador**: Se almacenan los valores de los diferentes registros del procesador.
- **Información de gestión de memoria**: Espacio de memoria reservada para el proceso.

El cambio de contexto (**tiempo de conmutación** es tiempo perdido, ya que el procesador no hace trabajo útil durante ese tiempo, por lo que debe de ser lo más rápido posible. <br> 
Únicamente es tiempo necesario para permitir la multiprogramación y su duración depende de la arquitectura en concreto del procesador.

## Bloque de Control de Proceso (PCB)
El **bloque de control de proceso** (PCB) es una estructura de datos fundamental en un sistema operativo, que contiene toda la información necesaria para gestionar un proceso. Es consultado y modificado por varios módulos del sistema, como los encargados de la planificación, asignación de recursos, interrupciones y monitoreo del rendimiento. El conjunto de los PCB definin el estado del SO y guarda la **tabla de procesos**, la cual s epuede implemntar como una lista enlazada (es donde reside en la memoria princiapl debido a su alta frecuencia de consulta)

Son posiciones de memoria reservadas para contener:
- **Identificador del proceso (PID)**: Se le asigna a cada proceso un ID numérico único. Además, un proceso puede también tener asignado un ID de usuario que indica a quién pertenece el proceso.
- **Estado actual**: en que se encuentra el proceso e información de planificación.
- **Contador de programa**: indica la dirección de la siguiente intrucción que se ejecutará ese proceso.
- **Registros de CPU**: mientras el proceso está ejecutándose, la información está en los
registros. Cuando se interrumpe el proceso, toda la información de los registros debe salvarse de forma que pueda restaurarse cuando el proceso reanude su ejecución.

- **Información de gestión de memoria**: valor de los registros de base y límite, tabla de páginas o tabla de segmentos.
- **Información de contabilidad**: tiempo de CPU, tiempo consumido, número de procesos, etc.
- **Información de estado de E/S**: dispositivos de E/S asignados a este proceso, lista de archivos abiertos, etc.

Un PCB siempre ocupa el mismo espacio en memoria.



## Hilos 
Los **hilos** son un mecanismo que permite la ejecución de dos o más procesos a la misma vez (multitarea).
Se pueden crear **extendiendo** la clase Thread o implementando la **Interface Runnable**.

### Crear un hilo
1. Crear una clase que extienda de la clase Thread.
2. Añadirle el método run()
```java
  public class HijoEjemplo extends Thread{
    public void run(){
      System.out.println("Inicia Hilo"); // ESTO ES LO QUE SE VA A EJECUTAR CUANDO LLAMEMOS AL METODO START
    }
  }
```

### Ejecutar el hilo
1. Hacer una instancia de esa clase.
2. Llamar al método `start()`: Cuando se llama a este método, el sistema crea recursos necesarios para el funcionamiento del hilo y se encarga de llamar al método `run()`, en ese momento el hilo se encuentra en estado "**runable**"
```java
  HiloEjemplo miHilo = new HiloEjemplo();
  miHilo.start();
```

### Ejecutar varias veces el hilo
Para ejecutarlo varias veces, creamos un bucle dentro de la clase `run()`.
```java
  public class HijoEjemplo extends Thread{
    public void run(){
      for(int i = 0; i < 10; i++) {
         System.out.println(i + " " + getName()); // IMPRIMIR EL NOMRBE DEL PROCESO DEL MOMENTO
      }      
      System.out.println("Inicia Hilo: " + getName()); // ESTO ES LO QUE SE VA A EJECUTAR CUANDO LLAMEMOS AL METODO START
    }
  }
```
> CONSOLA <br>
> 0 Thread-8 <br>
> 1 Thread-0 <br>
> 2 Thread-20 <br>
> 0 Thread-1 <br>
> ... <br>
> 7 Thread-1 <br>
> Finaliza el Hilo Thread-0 <br>
> 8 Thread-1 <br>
> 9. Thread-1 <br>
> Finaliza el Hilo Thread-1 <br>

>[!IMPORTANT]
> Siempre se ejecutan de forma distinta.


### Crear un constructor
```java
  public HiloEjemplo (String nombre){
    susper(nombre); // PARA QUE AL PONER getName NO SAQUE "Thread 0..." SINO EL NOMBRE QUE LE DEMOS
  }
  HiloEjmplo miHilo = new HiloEjemplo("Hilo Uno");
```

### Mostrar mensaje dependiendo el nombre del hilo
```java
  public class HijoEjemplo extends Thread{
    public void run(){
      for(int i = 0; i < 10; i++) {
        if(getName().equalsIgnoreCase("Hilo Uno")) {
           System.out.println(i + " " + getName() + " <-----"); // IMPRIMIR EL NOMRBE DEL PROCESO DEL MOMENTO
        } else {
            System.out.println(i + " " + getName() + " ----->");
        }
      }      
      System.out.println("Inicia Hilo: " + getName()); // ESTO ES LO QUE SE VA A EJECUTAR CUANDO LLAMEMOS AL METODO START
    }
  }
```

### Dormir un hilo
```java
  public class HijoEjemplo extends Thread{
    public void run(){
      for(int i = 0; i < 10; i++) {
        if(getName().equalsIgnoreCase("Hilo Uno")) {
           System.out.println(i + " " + getName() + " <-----"); // IMPRIMIR EL NOMRBE DEL PROCESO DEL MOMENTO
        } else {
            System.out.println(i + " " + getName() + " ----->");
        }
        try{
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }      
      System.out.println("Inicia Hilo: " + getName()); // ESTO ES LO QUE SE VA A EJECUTAR CUANDO LLAMEMOS AL METODO START
    }
  }
```


### Crear un hilo implemtando la Interface Runnable
No hay dependencia de la Herencia, se usa cuando no es posible extender la clase Thread ya que necesitamos heredar de otra clase de nuestro programa, ya que java no permite la herencia multiple. <br>
1. Implementar el método run()
```java
public class HiloEjemplo implements Runnable {
  @Override
  public void run() {
    for(int i = 0; i < 10; i++) {
      System.out.println(i + " " + Thread.currentThread().getName()); 
    }
  }
}
```
2. Intanciar la clase
```java
HiloEjemplo miHilo = new HiloEjemplo();
// AL NO USAR LA CLASE THREAD DIRECTAMENTE, NO PODEMOS USAR EL METODO strat()
Thread hilo = new Thread(miHilo); // TENEMOS QUE CREAR UN OBJETO Y LE MANDAMOS LA INSTANCIA DEL HILO QUE SE CREA ANTERIORMENTE

// DE ESTA MANERA VAMOS A PONER A EJECUTAR A ESTE HILO
hilo.start()

```





---
>_IES Ribera de Castilla 24/25._
