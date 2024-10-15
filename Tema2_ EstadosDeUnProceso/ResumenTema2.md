# TEMA 2: ESTADOS DE UN PROCESO
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
