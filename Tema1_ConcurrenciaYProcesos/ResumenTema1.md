# TEMA 1: Concurrencia y Procesos

## Proceso vs Programa
Para que un programa se ejecute, el SO debe crear un proceso para él.
- `Programa`: Secuencia estática de instrucciones escrita en un lenguaje dado, es una instancia de ejecución de un programa.
- `Proceso`: Instancia dinámica de la ejecución de un programa, caracterizado por su contador de programa, estado, registros, pila, datos, etc.
- Un **programa** puede ser ejecutado por varios usuarios, lo que crea varios **procesos** independientes que comparten código pero tienen su propio estado.
Un `sistema` es una coleción de **procesos** que podrían ejecutarse concurrentemente.

## Concepto de Proceso
Un proceso es cualquier programa en ejecución el cual, no solo incluye el código, sino también:
- `Sección de texto`: Código del programa.
- `Actividad actual`: Incluye el contador de programa y los registros del procesador.
- `Imagen de memoria`:
  - **Pila (stack)**: Contiene datos temporales.
  - **Sección de datos**: Contiene _variables globales_ y _memoria dinámica_.

## Recursos de un Proceso
Un proceso necesita ciertos recursos para completar su tarea:
- `CPU`, `Memoria`, `Archivos`, `Dispositivos de E/S`
- Los recursos se asignan cuando se **crea** o **durante su ejecución**.

## Hilos y Concurrencia
Un proceso puede dividirse en `tareas independientes (hilos)` que se ejecutan de manera concurrente en una misma máquina. 
El sistema operativo gestiona la concurrencia con funciones como:
- `Creación y eliminación de procesos`.
- `Planificación de procesos` para maximizar el uso del procesador.
- `Sincronización y comunicación entre procesos`.
- `Manejo de excepciones e interrupciones`: control de errores.
- `Asignación de recursos hardware a diferentes procesos`, son compartidos tanto por los programas como por los diferentes usuarios.
  
## Programación Concurrente
Permite ejecutar múltiples tareas al mismo tiempo:
- `Multiprogramación` en un solo procesador: El sistema operativo intercambia los procesos rápidamente,
  creando la ilusión en el usuario de que se ejecutan simultáneamente. <br>
  Si existe un único procesador, solo un **proceso** puede estar en un **momento determinado en ejecución**.
- `Multitarea` en varios núcleos: Cada núcleo puede ejecutar diferentes instrucciones al mismo tiempo,
  y los núcleos comparten la misma memoria, lo que permite la **programación paralela**.
La `programación paralela` permite mejorar el rendimiento de un programa si se ejecuta de forma paralela en diferentes núcleas
ya que permite que se ejecuten varias instrucciones a la vez.

## Tipos de Concurrencia
1. `Concurrencia Real`: Las instrucciones de varios procesos se solapan en el tiempo. Requiere múltiples procesadores físicos.
2. `Concurrencia Simulada` (**Pseudoparalelismo**): Las instrucciones de varios procesos se intercalan en el tiempo en un mismo procesador.
   Los procesadores se multiplexan entre los procesos.

## Programación Distribuida
Se ejecutan programas en múltiples ordenadores conectados en red, cada uno con su propio procesador y memoria. 
La comunicación entre procesos se realiza mediante redes, lo que es más complejo que el intercambio de memoria compartida.





---

>_IES Ribera de Castilla 24/25._

