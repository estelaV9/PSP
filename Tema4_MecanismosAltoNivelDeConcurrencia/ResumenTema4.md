# Tema 4: Mecanismos de alto nivel de concurrencia
## **Programación Concurrente**
La **programación concurrente** permite ejecutar más de una actividad al mismo tiempo. Aunque, en sistemas de una sola CPU, la concurrencia es solo virtual, ya que se intercala el tiempo de ejecución entre procesos. En sistemas con múltiples CPUs, la concurrencia es física y las tareas pueden ejecutarse simultáneamente.

   - **Secuencialidad**: A diferencia de los programas secuenciales donde las instrucciones se ejecutan de manera estricta (una después de otra), en los programas concurrentes, las instrucciones siguen un "orden parcial". La sincronización de los procesos se gestiona a través de mecanismos específicos.
     
   - **Procesos en la Concurrencia**: Un **proceso** es un programa secuencial con una única línea de control, y en un entorno concurrente, múltiples procesos se ejecutan de manera asíncrona. Estos pueden requerir comunicarse entre sí para intercambiar datos o sincronizar sus flujos de ejecución.

Para validar un **programa concurrente**, se requiere comprobar los mismos aspectos que en los **programas secuenciales**, pero con los siguientes nuevos aspectos:
   - Las sentencias se pueden validar individualmente si no comparten variables.
   - Cuando hay variables compartidas, puede haber interferencia entre tareas, lo que hace más difícil la validación.
   - Si se usan sentencias explícitas de sincronización, el tiempo no afectará el resultado.

## **Aplicaciones de la Programación Concurrente**
Tradicionalmente existen tres tipos de aplicaciones en las que se ha utilizado los programas concurrentes:
- Los programas concurrentes que se necesitan para programar las `arquitecturas multicomputadoras`.
  
- Los `sistemas operativos` para multiprogramación tienen por objeto la ejecución concurrente de múltiples programas compartiendo, CPU, periféricos, y sistema de almacenamiento de información.
  
- En los programas dedicados a `control o supervisión de entornos físicos reales` (que tienen naturaleza concurrente y no determinista), su diseño basado en un programa concurrente conduce a una mayor modularidad y claridad de diseño.

Aplicaciones actuales:
- Servicios WEB. 
- Sistemas multimedia. 
- Cálculo numérico. 
- Procesamientos entrada/salida. 
- Simulación de sistemas dinámicos. 
- Interacción operador/máquina (GUIs) 
- Tecnologías de componentes. 
- Código móvil. 
- Sistemas embebidos. 



## **Beneficios de la Programación Concurrente**
Los beneficios que se obtienen al adoptar un modelo de programa concurrente son:
   - **Modelo Natural**: Los programas concurrentes reflejan más fielmente la concurrencia que ocurre en el mundo real, lo que hace que el diseño de aplicaciones sea más intuitivo y fácil de comprender.
     
   - **Optimización de Recursos**: Permiten compartir recursos complejos y optimizar el uso de la CPU, especialmente en sistemas monoprocesador, donde los tiempos de entrada/salida de un proceso pueden solaparse con los tiempos de ejecución de otro.
     
   - **Reducción del Tiempo de Ejecución**: En sistemas multiprocesador, la concurrencia permite que los procesos se ejecuten realmente al mismo tiempo, lo que reduce el tiempo de ejecución total.
     
   - **Flexibilidad en Programación en Tiempo Real**: Los procesos se pueden planificar según su urgencia, lo que facilita la ejecución de procesos con plazos más estrictos.


## **Entornos Hardware para Programación Concurrente**
Un programa concurrente correcto debe ser funcionalmente independiente de la plataforma hardware sobre la que se instala y ejecuta. Sin embargo, buscando los posibles modelos de fallo que se pueden presentar en un programa concurrente, es conveniente considerar algunos conceptos relativos a las posibles arquitecturas hardware que se pueden utilizar. 

Entornos hardware de programas concurrentes son: 
   - **Entorno monoprocesador con multiprogramación**: Los procesos se ejecutan en una sola CPU utilizando concurrencia virtual. Aunque la ejecución concurrente no es más eficiente que un programa secuencial en este caso, es útil para optimizar el uso de recursos y ofrecer servicios interactivos.
     
   - **Entorno multiprocesador con memoria compartida**: En sistemas de múltiples CPUs, los procesos se ejecutan simultáneamente en diferentes procesadores, lo que reduce el tiempo de ejecución. Sin embargo, el acceso concurrente a la memoria compartida puede causar problemas si los procesos no se sincronizan correctamente.
     
   - **Sistema distribuido**: Los sistemas distribuidos están formados por varios computadoras geográficamente dispersas que no comparten memoria, sino que se comunican a través de una red. La programación concurrente en estos sistemas requiere intercambiar mensajes entre nodos y coordinar la coherencia de las variables replicadas.


## **Problemas en la Programación Concurrente**
En programación concurrente se presentan problemas específicos que pueden conducir a 
errores que no existen en programación secuencial. 

Los dos problemas básicos propios de programación concurrente son: 
   - **Actualizaciones concurrentes de variables compartidas**: Si dos procesos intentan actualizar una variable compartida al mismo tiempo, pueden interferir entre sí y corromper el valor de la variable.
   - **Sincronización en la ejecución de tareas**: Los procesos concurrentes a menudo necesitan esperar a que otro proceso termine de ejecutar antes de continuar. La sincronización adecuada es crucial para evitar errores.


## Programa secuencial y concurrente
Existen tres formas de implementar un programa concurrente:
1. Mediante un `lenguaje concurrente` (Ada, Java, etc.). 
2. A través de un `sistema operativo` (UNIX, POSIX, Windows, etc.), definiendo 
independientemente los procesos que constituyen el programa mediante un lenguaje 
secuencial (como el lenguaje C). 
3. Haciendo uso de la capacidad de `multiprocesado` que proporciona un sistema operativo 
multiprocesador (como UNIX) o un software de comunicación (como MMS, CORBA).

### Programa secuencial:
```java
P;
Q;
R;
```
**Ejecución**: P -> Q -> R
Para todas las ejecuciones válidas del programa, `P` debe concluir antes que comience `Q`, `Q` debe finalizar antes de que comience `R`.

### Programa concurrente
```java
parbegin 
P; 
Q; 
R;
parend
````
**Ejecución**: {not(P→Q) and not(P→R) and not(Q→P) and not(Q→R) and not(R→P) and not(R→Q)} 

Los procesos `P`, `Q` y `R` son concurrentes si existe al menos una ejecución válida en la que durante algún intervalo de tiempo, los tres procesos se estén ejecutando simultáneamente. 

### Modo de inicialización de los procesos
La **tarea de inicialización de un proceso**, es la actividad por la que en la fase de creación del proceso, se les pasa a las variables de cada proceso unos valores iniciales. Tener esta capacidad es muy útil, en especial cuando se generan dinámicamente o siguiendo estructuras tipo array, ya que a través de la inicialización, se puede particularizar cada proceso respecto de los demás, que son clónicos con él. 

- En **UNIX** cada proceso es inicialmente una réplica del padre, los procesos heredan de forma completa el entorno que su proceso padre tiene en el instante de creación, lo que a todos los efectos es una forma de inicialización. 

- En **Ada o Java** en la creación se establecen unos parámetros de inicialización.
  
- Cuando no se dispone de la capacidad de inicialización de los procesos, se necesita que tras ser estos creados, establezcan explícitamente un proceso de comunicación con otro proceso existente (habitualmente su padre) a fin de recibir de él la información de inicialización.

#### Declaración de los procesos concurrentes
1. **Declaración mediante sentencia estructurada**: en estos casos el proceso padre queda suspendido, hasta que todos los procesos que se han declarado concluyen:
```java
parbegin 
  proceso_1, proceso_2, ..., proceso_n 
parend; 
```

2. **Bifurcación (`fork`)**:  En este caso la ejecución de una sentencia fork en el proceso padre induce la creación de un proceso hijo clónico que continua ejecutándose concurrentemente con el proceso padre, ambos a partir de la sentencia siguiente a la sentencia fork. Esta es la forma habitual de crear procesos en un entorno UNIX:
```java
.... 
{if (fork() == 0 ) 
  {.... } /*Se ejecuta sólo en el proceso hijo 
else 
  {.... } /*Se ejecuta sólo en el proceso padre y 
pid_hijo /*contiene el pid del nuevo proceso hijo 
}
.... 
```

## Pasos para transformar un código secuencial a concurrente**
Si tenemos varias instancias de un mismo proceso y un código secuencial, se debe analizar dicho código para detectar posibles paralelismos que permitan dividir el código secuencial en código paralelo y así dar trabajo a las diferentes instancias de un mismo recurso como por ejemplo la CPU. 

### 1. Condiciones de Berstein
**Lectura y Escritura en Variables**: Dos instrucciones son concurrentes si los conjuntos de lectura y escritura no se intersectan. Si se cumplen tres condiciones de Bernstein sobre los accesos a las variables, las instrucciones pueden ejecutarse de manera concurrente.
(...)

### 2. De código secuencial a grafo de precedencia 
Grafo acíclico orientado cuyos nodos corresponden a sentencias individuales. 
Cada representa un conjunto de sentencias que se unen mediante arcos que indican precedencia de orden de ejecución. 
Cuando se abren dos arcos se indica `concurrencia`. Cuando se unen dos arcos se indica un `punto de sincronización`. 
Un arco desde una sentencia hasta el nodo que representa el nodo significa que solamente puede ejecutarse cuando haya finalizado.


### 3. De grafo de precedencia a código concurrente
- `Fork`: sirve para generar 2 instrucciones (ejecuciones) concurrentes en un programa. 
- `Join`: permite recombinar 2 computaciones diferentes en una sola. 

El **fork** lleva una etiqueta asociada, por ejemplo `L` y, cuando el sistema encuentra el fork ejecuta por un lado la instrucción que se encuentra asociada  a la etiqueta `L`, y por otro lado ejecuta de forma concurrente (a la vez) la instrucción siguiente a **fork**. 
```java
S1 
Fork L1 (genera 2 caminos: lo que contenga la etiqueta L1) 
S2 (junto con la sentencia siguiente al fork) 
L1: S3 
Join 
  S4 
```


**Mejoras al código**:
El `Join` lleva asociado siempre un contador que se inicializa a un valor que es igual al número de instrucciones concurrentes que se quiere unir, porque es necesario que las bifurcaciones lleguen a la vez para poder ejecutarse y para eso sirve el contador, de esta manera se desechan los procesos que lleguen antes. 
```java
  BEGIN 
    CONT=2; 
    S1;  
    FORK L1 
    S2;  
    GOTO L2; 
L1: S3;
L2: JOIN CONT; 
    S4; 
  END
```

El Join decrementa en una unidad el contador y comprueba su valor: 
- Si el contador no ha llegado a 0  el proceso que ejecuta Join termina. 
- Si el contador ha llegado a 0 entonces continúa y ejecuta la siguiente instrucción. 
```java
cont= cont – 1 
if cont ≠ 0 then quit
```


#### Instrucción concurrente de DijKstra: parbegin/parend
```java
BEGIN 
  S1; 
  PARBEGIN 
    S2; 
    S3; 
  PAREND 
  S4; 
END 
```
Todas las instrucciones entre parbegin y parend se ejecutan de forma concurrente.

**Solución a problemas de concurrencia: SEMAFOROS BINARIOS**:
El **semáforo binario** permite que los procesos gestionen el acceso a recursos críticos. A través de las operaciones `P(s)` (espera) y `V(s)` (señal), se controla cuándo un proceso puede acceder a un recurso.

1. La variable se inicializa a 0
Operaciones internas del semáforo:
`P(s)` 
Preguntar si el semáforo está abierto. Si no lo está esperar. En sistemas operativos equivale a la función: `wait().`
`while (s=0) {};`
  
`V(s)`
Abrir el semáforo. `s:=1;`



--- 

Resumen sacado de la página de <a href="https://blog.softtek.com/es/java-concurrency">Softtek</a>.

## Introducción a la Concurrencia en Java(I)
La **concurrencia** es la capacidad de realizar múltiples tareas dentro de un mismo intervalo de tiempo, aunque no necesariamente de forma simultánea. En cambio, el **paralelismo** implica ejecutar múltiples tareas exactamente al mismo tiempo. Estos conceptos están estrechamente relacionados, pero son distintos.

### Conceptos básicos
- `Proceso`: Programa en ejecución con su propio espacio de memoria y que está aislado de otros procesos.
- `Hilo`: Camino de ejecución dentro de un proceso que comparte memoria y recursos con otros hilos del mismo proceso. Cada hilo tiene su propia pila de llamadas (**call stack**).

Ejemplo:
Una computadora con un solo **núcleo** (core) puede realizar tareas concurrentes (intercaladas en el tiempo). Sin embargo, para el paralelismo real, se necesitan múltiples núcleos.

### Creación de Hilos en Java
Existen dos formas principales de crear hilos:

1. **Extendiendo la clase Thread**:
    - Sobrescribir el método `run()` con la tarea a ejecutar.
  
2. **Implementando la interfaz Runnable**:
    - Crear un objeto de esta interfaz y pasarlo al constructor de `Thread`.
    - Más flexible y menos acoplado.
    
```java
Runnable tarea = () -> {
    System.out.println("Empieza la tarea");
    Thread.sleep(5000); // SIMULA UN TRABAJO DE 5 SEGUNDOS
    System.out.println("Termina la tarea");
};

Thread hilo = new Thread(tarea);
hilo.start();
// ESPERA AL HILO CON UN TIMEOUT DE 3 SEGUNDOS (sincroniza)
hilo.join(3000); 
```

### API `java.util.concurrent`
Para manejar tareas concurrentes de forma eficiente, Java ofrece el **framework Executor**, que gestiona hilos en un "pool" (grupo) de manera automática.

`Executor` es un framework para la ejecución de tareas de manera concurrente. Desacopla la creación y manejo de los hilos de las tareas.
Se encarga de crear un pool de hilos que llevarán a cabo las tareas. Desde fuera, enviaremos las tareas que se deseen realizar y éstas se aadirán a una cola. El `Executor` se encargará de pasarlas a ejecución, gestionando la utilización de los hilos disponibles.

#### Tipos de Executor:
1. **Executor**:
    - Interfaz básica con el método `execute(Runnable)`.
    
2. **ExecutorService**:
    - Añade control del ciclo de vida (inicio y cierre de tareas).
    
3. **ScheduledExecutorService**:
    - Permite programar tareas a futuro o repetitivas.

> [!WARNING]
> Es importante apagar el `executor` una vez que ya no se vayan a usar más, sino se quedará activo esperando indefinidamente consumiendo recursos:
> ```java
> // EL executor DEJA DE ACEPTAR NUEVAS TAREAS Y CUANDO FINALIZA
> // SE CIERRA
> executor.shutdown(); 
> ```
> o mediante `shutdownNow()`
> ```java
> // SE PARA DE MANERA ABRUPTA, FINALIZANDO EN ESE MISMO MOMENTO
> // EL executor
> executor.awaitTermination(2, TimeUnit.SECONDS);
> ```

Ejemplo: 
```java
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceRunnable {
	// VARIABLE CONSTANTE QUE GUARDA EL MOMENTO DE INICIO DEL PROGRAMA
	private static final Instant INICIO = Instant.now(); 
	public static void main(String[] args) {
		// CREA UN EXECUTOR SERVICE CON UN SOLO HILO (SINGLE THREAD)
		ExecutorService executor = Executors.newSingleThreadExecutor();
		
		// DEFINIMOS UNA TAREA COMO UNA LAMBDA QUE IMPLEMENTA Runnable
		Runnable tarea = () -> {
			Log("Inicio de la tarea"); // REGISTRA EL INICIO DE LA TAREA
			try {
				TimeUnit.SECONDS.sleep(1); // SIMULA UNA PAUSA DE 1 SEGUNDO
			} catch (InterruptedException e) {
				e.printStackTrace(); // IMPRIME EL ERROR EN CASO DE INTERRUPCIÓN
			}
			Log("Finaliza la tarea"); // REGISTRA EL FINAL DE LA TAREA
		};
		
		// EJECUTA LA TAREA USANDO EL EXECUTOR
		executor.submit(tarea);
		
		// CIERRA EL EXECUTOR, IMPIDIENDO NUEVAS TAREAS
		executor.shutdown();
    executor.awaitTermination(2, TimeUnit.SECONDS); // OTRA FORMA
	}
	
	// MÉTODO PARA REGISTRAR MENSAJES CON DURACIÓN, NOMBRE DEL HILO Y EL MENSAJE
	private static void Log(Object mensaje) {
		System.out.println(String.format("%s [%s] %s", 
			Duration.between(INICIO, Instant.now()), // TIEMPO TRANSCURRIDO DESDE EL INICIO
			Thread.currentThread().getName(), // NOMBRE DEL HILO ACTUAL
			mensaje.toString() // MENSAJE A REGISTRAR
		));
	}
}

/* SALIDA ESPERADA (EL TIEMPO PUEDE VARIAR)
	PT0.078S [pool-1-thread-1] Inicio de la tarea
	PT1.125S [pool-1-thread-1] Finaliza la tarea
*/
```


#### Gestionar grupos de tareas:
- `invokeAll`: se envía una colección de tareas al executor y éste devuelve una lista con los correspondientes `futures`. Esta llamada es bloqueante, es decir, se espera a que todas las tareas estén completadas (ya sea de manera exitosa o devolviendo excepción).
  
- `invokeAny`: se envía una colección de tareas al `executor` y éste devuelve la primera tarea completada de manera exitosa, si es que la hubiera. Si ninguna terminara correctamente se devolvería una `ExecutionException`. En el momento que el `executor` valida un resultado exitoso, cancela el resto de tareas en ejecución. Este método también es bloqueante.

```java
private static final Instant INICIO = Instant.now(); // INSTANTE DE INICIO PARA CALCULAR DURACIONES
private static int contadorTareas = 1; // CONTADOR PARA IDENTIFICAR LAS TAREAS

public static void main(String[] args) throws InterruptedException, ExecutionException {
    
    // GENERAMOS UNA LISTA DE TAREAS USANDO Stream.generate Y LIMITAMOS A 5 TAREAS
    List<Callable<String>> tareas = 
            Stream.generate(ExecutorServiceInvokeAllAny::getTareaSleepUnSegundo) // CREA UNA TAREA
                .limit(5) // LIMITAMOS A 5 TAREAS
                .collect(Collectors.toList()); // RECOLECTAMOS LAS TAREAS EN UNA LISTA
    
    // CREAMOS UN ExecutorService CON UN POOL DE 2 HILOS
    ExecutorService executor = Executors.newFixedThreadPool(2);
    
    // USAMOS EL MÉTODO invokeAll PARA EJECUTAR TODAS LAS TAREAS Y OBTENER FUTURES
    List<Future<String>> futures = executor.invokeAll(tareas);
    for (Future<String> future : futures) {
        String resultado = future.get(); // ESPERAMOS A QUE LA TAREA TERMINE Y OBTENEMOS SU RESULTADO
        Log(resultado); // REGISTRAMOS EL RESULTADO
    }
    
    Log("El hilo principal continúa..."); // INDICAMOS QUE EL HILO PRINCIPAL CONTINÚA DESPUÉS DE invokeAll
    
    // USAMOS EL MÉTODO invokeAny PARA OBTENER EL RESULTADO DE LA PRIMERA TAREA EXITOSA
    String resultadoAny = executor.invokeAny(tareas);
    Log(resultadoAny); // REGISTRAMOS EL RESULTADO DE LA PRIMERA TAREA COMPLETADA
    
    Log("El hilo principal continúa..."); // INDICAMOS QUE EL HILO PRINCIPAL CONTINÚA DESPUÉS DE invokeAny
    
    executor.shutdown(); // CERRAMOS EL EXECUTOR PARA EVITAR QUE ACEPTE NUEVAS TAREAS
}

// MÉTODO PARA CREAR UNA TAREA QUE DUERME POR 1 SEGUNDO Y DEVUELVE SU RESULTADO
private static Callable<String> getTareaSleepUnSegundo() {
    int numeroTarea = contadorTareas++; // ASIGNAMOS UN NÚMERO A LA TAREA
    
    return () -> { // LAMBDA QUE IMPLEMENTA Callable
        Log("Inicio de la tarea " + numeroTarea); // REGISTRAMOS EL INICIO DE LA TAREA
        try {
            TimeUnit.SECONDS.sleep(1); // LA TAREA DUERME POR 1 SEGUNDO
            Log("Finaliza la tarea " + numeroTarea); // REGISTRAMOS EL FINAL DE LA TAREA
            return "resultado de la tarea " + numeroTarea; // DEVOLVEMOS EL RESULTADO DE LA TAREA
        } catch (InterruptedException e) { // SI LA TAREA ES INTERRUMPIDA
            Log("sleep ha sido interrumpido en tarea " + numeroTarea); // REGISTRAMOS LA INTERRUPCIÓN
            return null; // DEVOLVEMOS null EN CASO DE INTERRUPCIÓN
        }
    };
}

// MÉTODO PARA REGISTRAR MENSAJES CON DURACIÓN, NOMBRE DEL HILO Y EL MENSAJE
private static void Log(Object mensaje) {
    System.out.println(String.format("%s [%s] %s", 
        Duration.between(INICIO, Instant.now()), // DURACIÓN DESDE EL INICIO
        Thread.currentThread().getName(), // NOMBRE DEL HILO ACTUAL
        mensaje.toString() // MENSAJE A MOSTRAR
    ));
}
```

#### Programación de Tareas con `ScheduledExecutorService`
Este servicio permite programar tareas para ejecutarse en un futuro o de manera repetitiva. Métodos Principales:
- `schedule`: Ejecuta una tarea tras un retraso inicial.
- `scheduleAtFixedRate`: Ejecuta una tarea a intervalos fijos.
- `scheduleWithFixedDelay`: Espera a que una tarea termine antes de iniciar la siguiente.

En el siguiente ejemplo, lanzamos las tres funcionalidades por separado:
```java
// CREAMOS UN ScheduledExecutorService CON UN POOL DE 1 HILO
ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(1);

// DEFINIMOS UNA TAREA COMO UN Runnable
Runnable tarea = () -> {
    try {
        TimeUnit.SECONDS.sleep(1); // SIMULA UN TRABAJO QUE DURA 1 SEGUNDO
    } catch (InterruptedException e) {
        e.printStackTrace(); // IMPRIME EL ERROR SI LA TAREA ES INTERRUMPIDA
    }
    Log("Ejecución de tarea"); // REGISTRA LA EJECUCIÓN DE LA TAREA
};

// PROGRAMAMOS UNA TAREA CON RETRASO DE 3 SEGUNDOS
scheduledExecutor.schedule(tarea, 3, TimeUnit.SECONDS);

// PROGRAMAMOS UNA TAREA REPETITIVA QUE SE EJECUTA CADA 1 SEGUNDO, DESPUÉS DE UN RETRASO INICIAL DE 2 SEGUNDOS
scheduledExecutor.scheduleAtFixedRate(tarea, 2, 1, TimeUnit.SECONDS);

// PROGRAMAMOS UNA TAREA REPETITIVA CON UN RETRASO FIJO DE 1 SEGUNDO ENTRE EL FINAL DE UNA EJECUCIÓN Y EL INICIO DE LA SIGUIENTE
scheduledExecutor.scheduleWithFixedDelay(tarea, 2, 1, TimeUnit.SECONDS);
}

/* ScheduledExecutorServiceExample Output
--schedule(tarea, 3, TimeUnit.SECONDS);
PT4.094S [pool-1-thread-1] Ejecución de tarea

--scheduleAtFixedRate(tarea, 2, 1, TimeUnit.SECONDS);
PT3.094S [pool-1-thread-1] Ejecución de tarea
PT4.128S [pool-1-thread-1] Ejecución de tarea
PT5.132S [pool-1-thread-1] Ejecución de tarea
PT6.136S [pool-1-thread-1] Ejecución de tarea
PT7.138S [pool-1-thread-1] Ejecución de tarea
PT8.142S [pool-1-thread-1] Ejecución de tarea
PT9.144S [pool-1-thread-1] Ejecución de tarea
PT10.147S [pool-1-thread-1] Ejecución de tarea

--scheduleWithFixedDelay(tarea, 2, 1, TimeUnit.SECONDS);
PT3.094S [pool-1-thread-1] Ejecución de tarea
PT5.146S [pool-1-thread-1] Ejecución de tarea
PT7.165S [pool-1-thread-1] Ejecución de tarea
PT9.183S [pool-1-thread-1] Ejecución de tarea */
```

#### Selección del Executor Correcto
1. **`newCachedThreadPool`**: Crea hilos según sea necesario. Reutiliza los que están libres.
2. **`newFixedThreadPool`**: Número fijo de hilos. Ideal para un límite conocido de tareas concurrentes.
3. **`newSingleThreadExecutor`**: Un solo hilo. Tareas en cola se ejecutan secuencialmente.
4. **`newWorkStealingPool`**: Ajusta los hilos según los procesadores lógicos disponibles.
5. **`newScheduledThreadPool`**: Programación de tareas repetitivas.

En el siguiente ejemplo, veremos que los resultado en consola constatan el nivel de multihilo. Se ve cómo para el mismo grupo de tareas los tiempos son sensiblemente distintos:
```java
private static final Instant INICIO = Instant.now(); // INSTANTE DE INICIO PARA MEDIR EL TIEMPO DE EJECUCIÓN
private static int contadorTareas = 1; // CONTADOR PARA NUMERAR LAS TAREAS

public static void main(String[] args) throws InterruptedException, ExecutionException {

    // MOSTRAMOS EL NÚMERO DE PROCESADORES LÓGICOS DISPONIBLES EN EL SISTEMA
    Log(Runtime.getRuntime().availableProcessors());
    
    // MOSTRAMOS EL NÚMERO DE HILOS EN EL POOL COMÚN DE ForkJoinPool
    Log(ForkJoinPool.commonPool().getParallelism());
    
    // CREAMOS UNA LISTA DE DIFERENTES TIPOS DE EXECUTOR SERVICE CON DIFERENTES CONFIGURACIONES
    List<ExecutorService> executorServices = Arrays.asList( 
        Executors.newCachedThreadPool(), // CREADOR DE HILOS CON UNA CANTIDAD ILIMITADA DE HILOS
        Executors.newFixedThreadPool(3), // CREADOR DE UN POOL DE HILOS CON UN TAMAÑO FIJO DE 3 HILOS
        Executors.newSingleThreadExecutor(), // CREADOR DE UN EXECUTOR CON UN SOLO HILO
        Executors.newWorkStealingPool(), // CREADOR DE UN POOL DE HILOS CON STRATEGY DE ROBO DE TRABAJO
        Executors.newScheduledThreadPool(5), // CREADOR DE UN POOL DE HILOS PARA EJECUCIÓN DE TAREAS PROGRAMADAS
        ForkJoinPool.commonPool() // USO DEL POOL COMÚN DE ForkJoinPool (TAREAS RECURSIVAS)
    );
    
    // GENERAMOS UNA LISTA DE 40 TAREAS QUE SIMULAN UN RETRASO DE 1 SEGUNDO CADA UNA
    List<Callable<Object>> tareas = 
        Stream.generate(ExecutorServicesNumberOfThreads::getTareaSleepUnSegundo) // GENERA LAS TAREAS
            .limit(40) // LIMITAMOS A 40 TAREAS
            .collect(Collectors.toList()); // RECOLECTAMOS LAS TAREAS EN UNA LISTA
    
    // PARA CADA TIPO DE EXECUTOR, EJECUTAMOS LAS TAREAS Y MEDIMOS EL TIEMPO DE EJECUCIÓN
    for (ExecutorService executorService : executorServices) {
        Instant inicio = Instant.now(); // REGISTRAMOS EL MOMENTO DE INICIO
        executorService.invokeAll(tareas); // EJECUTAMOS TODAS LAS TAREAS
        Log(Duration.between(inicio, Instant.now())); // REGISTRAMOS EL TIEMPO DE EJECUCIÓN DE LAS TAREAS
    }
    
    // CERRAMOS TODOS LOS EXECUTOR SERVICES PARA LIBERAR LOS RECURSOS
    executorServices.stream().forEach(ExecutorService::shutdown);
}

// MÉTODO QUE DEVUELVE UNA TAREA QUE DUERME DURANTE 1 SEGUNDO Y SIMULA UN TRABAJO
private static Callable<Object> getTareaSleepUnSegundo() {
    return Executors.callable(() -> { // CREAMOS UNA TAREA QUE IMPLEMENTA Callable
        try {
            TimeUnit.SECONDS.sleep(1); // SIMULA UNA TAREA QUE TOMA 1 SEGUNDO
        } catch (InterruptedException e) {
            e.printStackTrace(); // MANEJA LAS INTERRUPCIONES DE LA TAREA
        }
    });
}

// MÉTODO PARA REGISTRAR MENSAJES CON EL TIEMPO TRANSCURRIDO Y EL HILO QUE LO EJECUTA
private static void Log(Object mensaje) {
    System.out.println(String.format("%s", mensaje.toString())); // MUESTRA EL MENSAJE EN CONSOLA
}
}

/* EJEMPLO DE SALIDA ESPERADA
-- El número de procesadores disponibles en el sistema -> 8 
-- El número de hilos en el ForkJoinPool común -> 8
-- TIEMPO DE EJECUCIÓN PARA CADA TIPO DE EXECUTOR SERVICE
PT1.008S
PT14.004S
PT40.011S
PT5.004S
PT8.003S
PT6.005S */
```


---
>_Estela de Vega | IES Ribera de Castilla 24/25._
