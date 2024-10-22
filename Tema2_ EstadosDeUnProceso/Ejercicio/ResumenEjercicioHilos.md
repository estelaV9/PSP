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

## Ejercicio Cronómetro
### Controller
1. Se maneja si se ha `iniciado el hilo` y si el contrómetro esta en `ejecución`. Además se inicializa las `variables a usar` del cronómetro.
```java
public static int hora = 0, minuto = 0, segundo = 0; // INICIALIZAMOS LAS VARIABLES QUE VAMOS A USAR EN EL CRONOMETRO
public static boolean isIniciadoHilo = true; // VARIBALE PARA CONTROLAR SI EL HILO DEL CRONOMETRO ESTA ACTIVO
boolean ejecutando = false; // CONTROLA SI EL CRONOMETRO ESTA EN EJECUCION
```

2. Se crea la **variable para manejar el hilo** del `cronómetro`.
```java
private Cronometro cronometro; // VARIABLE PARA MANEJAR EL HILO DEL CRONOMETRO
```

3. Se crea la función para **iniciar el cronómetro**. Solo se iniciara si no hay un `hilo ejecutándose` o el `cronómetro es null`. <br>
Se `instancia una` clase de tipo cronometro y se le pasa el label donde se va a mostrar el cronómetro. Luego, se `inicia` el hilo.
```java
private void iniciarCronometro() {
    if (cronometro == null || !cronometro.isAlive()) { // SOLO SE INICIA SI NO HAY UN HILO EJECUTANDOSE
        cronometro = new Cronometro(cronometroLabel); 
        cronometro.start(); // INICIA EL HILO
    } // SOLO INICIA SI NO EXISTE UN HILO O EL ANTERIOR HA TERMINADO
} // METODO QUE INICIA EL CRONOMETRO SI NO HAY UN HILO ACTIVO
```
4. **Botones de iniciar y parar.**
- **Iniciar**: se comprueba si cronómetro no esta en ejecución. Sino esta en ejecución, cambia los valores de `iniciarHilo` y `ejecutando`. <br>
Después, llama a la función de `iniciarCronometro`.
```java
@FXML
void onIniciarAction(ActionEvent event) {
    if (!ejecutando) {
        isIniciadoHilo = true; // HILO EN EJECUCION
        ejecutando = true; // CRONOMETRO EN MARCHA
        iniciarCronometro(); // INICIAR CRONOMETRO
    } // SOLO SE INICIA SI EL CRONOMETRO NO ESTA EN EJECUCION
} // METODO INICIAR CRONOMETRO
```

- **Parar**: se cambia los valores de `iniciarHilo` y `ejecutando` a false. Si el `cronometro` no es nulo, entonces interrumpe el hilo.
```java
@FXML
void onPararAction(ActionEvent event) {
    ejecutando = false; // CRONOMETRO DEJA DE EJECUTARSE
    isIniciadoHilo = false; // DETIENE EL HILO
    if (cronometro != null) {
        cronometro.interrupt(); // INTERRUMPE EL HILO SI ESTA EN EJECUCION
    } // SI EL CRONOMETRO ESTA EJECUTANDOSE, LO INTERRUMPE
} // METODO PARAR
```

### Clase Cronómetro
1. Se extiende la clase `Thread`. Se añade el `atributo para mostrar el tiempo` del cronometro y se hace un `constructor` con ese atributo.
```java
public class Cronometro extends Thread {
    private Label eti; // ETIQUETA DONDE SE MOSTRARA EL TIEMPO DEL CRONOMETRO
    
    public Cronometro(Label cronometro) {
        this.eti = cronometro;
    }
}
```

2. Hacer el método `ejecutarHiloCronometro` donde se irán **cambiando los valores**. (Ejercicio simple de contadores e ir mostrando el resultado).
```java
private void ejecutarHiloCronometro() {
    CronometroCtrller.segundo++; // INCREMENTA LOS SEGUNDOS

    if (CronometroCtrller.segundo > 59) {
        CronometroCtrller.segundo = 0; // SE REINICIA A 0
        CronometroCtrller.minuto++; // AUMENTA EL MINUTO
    }

    if (CronometroCtrller.minuto > 59) {
        CronometroCtrller.minuto = 0;
        CronometroCtrller.hora++;
    }

    // FORMATEAR LA HORA, MINUTOS Y SEGUNDOS EN EL FORMATO "hh : mm : ss"
    String reloj = String.format("%02d : %02d : %02d",
            CronometroCtrller.hora, CronometroCtrller.minuto, CronometroCtrller.segundo);
}
```

> [!WARNING]
> Es **importante** que nos aseguremos de que la actualización del label ocurra dentro de la interfaz gráfica ya que si setteamos directamente el valor ocurre un error porque se intenta actualizar un label desde un hilo diferente. <br>
> Por eso se usa el metodo `Platform.runLater()` ya que nos garantiza que el código dentro de este bloque se actualice en el hilo de la interfaz.
> ```java
> // ASEGURARNOS DE QUE LA ACTUALIZACION DEL LABEL OCURRA EN EL HILO DE LA INTERFAZ GRAFICA
> Platform.runLater(() -> {
>     eti.setText(reloj); // Actualizamos el texto del Label en el hilo de JavaFX
> });
> ```

3. Una vez hecho el método para ir **cambiando los valores**, creamos el método `run()`. <br>
Este método lo que va a hacer va a ser un bucle de que mientras este `iniciado el hilo` y `no se interrumpa` entonces en paso de 1 segundo ira llamando a este método de `ejecutarHiloCronometro` donde irá cambiando los valores. <br>
Este bloque de código tiene que ir en un `try-catch`, siendo que si se `interrumpe` el hilo se finaliza.
```java
@Override
public void run() {
    int x = 0;
    // BUCLE QUE SIGUE EJECUTANDOSE MIENTRAS EL CRONOMETRO ESTE INICIADO Y NO SE INTERRUMPA EL HILO
    while (CronometroCtrller.isIniciadoHilo && !Thread.currentThread().isInterrupted()) {
        try {
            // PAUSA EL HILO POR 1 SEGUNDO
            Thread.sleep(1000);
            ejecutarHiloCronometro(); // CRONOMETRO
        } catch (InterruptedException e) {
            // SI EL HILO ES INTERRUMPIDO, FINALIZA EL HILO
            Thread.currentThread().interrupt();
            return;
        }
    }
}
```



---
>_IES Ribera de Castilla 24/25._
