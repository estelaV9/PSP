# Test de Repaso de la Primera Evaluación
## Cuestionario
**1.	¿Qué información se guarda en el Bloque de Control de Procesos (PCB):**

o	a) Nombre o identificador <br>
o	b) Estado actual <br>
o	c) Contador del programa <br>
o	d) Todas las anteriores ✔️ <br>

**2.	¿Qué se llama al proceso que crea a otro proceso:**

o	a) Hijo <br>
o	b) Padre ✔️ <br>
o	c) Proceso concurrente <br>
o	d) Proceso zombie <br>

**3.	¿Qué tipo de planificación permite que un proceso sea sacado de la CPU si entra un proceso de mayor prioridad:**

o	a) Planificación no apropiativa <br>
o	b) Planificación apropiativa ✔️ <br>
o	c) Planificación de tiempo compartido <br>
o	d) Planificación de procesos <br>

**4.	¿Qué ocurre cuando un proceso finaliza:**

o	a) Se queda en memoria <br>
o	b) Se convierte en zombie <br>
o	c) Se eliminan las tablas del sistema ✔️ <br>
o	d) Se suspende ❌ <br>

**5.	¿Cuál es un beneficio de la programación concurrente:**

o	a) Aumenta el tiempo de ejecución <br>
o	b) Facilita la programación de tiempo real ✔️ <br>
o	c) Reduce el uso de recursos <br>
o	d) Complica la sincronización <br>

**6.	¿Qué es la multiprogramación y cómo se diferencia de la multitarea:**

o	a) La multiprogramación permite la ejecución de varias tareas en diferentes núcleos, mientras que la multitarea se ejecuta en un único procesador. <br>
o	b) La multiprogramación permite la ejecución de varias tareas en un único procesador, mientras que la multitarea se ejecuta en diferentes núcleos. ✔️ <br>
o	c) La multiprogramación y la multitarea son lo mismo. <br>
o	d) Ninguna de las anteriores. <br>

**7.	¿Qué es el PLP y cuál es su función principal:**

o	a) Planificador a largo plazo, suministra procesos a la cola de preparados. ✔️ <br>
o	b) Planificador a corto plazo, asigna o desasigna procesos a la CPU. <br>
o	c) Planificador a medio plazo, intercambia procesos entre memoria principal y secundaria. <br>
o	d) Ninguna de las anteriores. <br>

**8.	¿Qué sucede durante un cambio de contexto:**

o	a) Se guarda el estado del proceso actual y se carga el estado del nuevo proceso. ✔️ <br>
o	b) Se elimina el proceso actual de la CPU. <br>
o	c) Se reinicia el sistema operativo. <br>
o	d) Ninguna de las anteriores. <br>

**9.	¿Qué es un proceso zombie:**

o	a) Un proceso que ha terminado pero cuyo PCB no ha sido eliminado. ✔️ <br>
o	b) Un proceso que está en ejecución. <br>
o	c) Un proceso que nunca se ejecutó. <br>
o	d) Ninguna de las anteriores. <br>

**10.	¿Qué es la sincronización en la programación concurrente:**

o	a) La secuencialización entre procesos concurrentes. ✔️ <br>
o	b) La ejecución de un solo proceso a la vez. <br>
o	c) La eliminación de procesos. <br>
o	d) Ninguna de las anteriores. <br>

**11.	¿Qué es la programación distribuida:**

o	a) La ejecución de programas en varios ordenadores distribuidos en red. ✔️ <br>
o	b) La ejecución de programas en un único procesador. <br>
o	c) La ejecución de programas sin multiprogramación. <br>
o	d) Ninguna de las anteriores. <br>

**12.	¿Qué es la gestión de procesos en un sistema operativo:**

o	a) La capacidad de crear, destruir, suspender, reanudar, y cambiar la prioridad de los procesos. ✔️ <br>
o	b) La capacidad de solo crear procesos. <br>
o	c) La capacidad de solo destruir procesos. <br>
o	d) Ninguna de las anteriores. <br>

**13.	¿Qué es la planificación sin desalojo o cooperativa:**

o	a) Un método donde un proceso en ejecución solo cambia si está bloqueado o termina. ✔️ <br>
o	b) Un método donde los procesos se sacan de la CPU si hay procesos de mayor prioridad. <br>
o	c) Un método que no permite la ejecución concurrente. <br>
o	d) Ninguna de las anteriores. <br>

**14.	Describe la acción que se muestra en esta imagen.**
La imagen muestra la acción del `ciclo de cambio de contexto` (**tiempo de conmutación**) entre dos procesos, P0 y P1, 
gestionados por el sistema operativo. <br>

En este proceso, el SO alterna entre los procesos, guardando y recuperando el estado de cada uno en sus respectivos DBP0, 
permitiendo que se compartan los recursos de la CPU de manera ordenada y controlada. En la ejecución inicial de P0 el proceso P0 está 
en ejecución y utiliza la CPU. <br>

Primero, interrumpe o llama al sistema. Cuando ocurre una interrupción o P0 realiza una llamada al sistema que requiere la intervención del SO. 
Puede ocurre cuando el proceso necesita esperar por un recurso o porque un evento externo interrumpe su ejecución. <br>

El SO guarda el estado de P0 en su BCP, incluyendo el contenido de registros y contador de programa permitiendo así que el sistema pueda reanudad 
P0 más adelante. Luego, el SO recupera el estado del proceso P1 desde su BCP1 y cargarlo en la CPU. Así el P1 puede continuar o empezar a ejecutarse. 
A continuación, P1 se esta ejecutando mientras que P0 permanece en estado ocioso. Posteriormente, hay otra interrupción o llamada al sistema, 
dando lugar a que el SO vuelva a tomar el control y guarde el estado de P1 en su BCP1 para conservar su progreso. <br>

Finalmente, el SO recupera el estado del procesador P0 de su BCP0, así P0 puede volver a ejecutarse desde el punto en el que fue 
interrumpido inicialmente.


---
>_IES Ribera de Castilla 24/25._
