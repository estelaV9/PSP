# Control de las puertas de entrada a un aparcamiento

El esquema de la figura 2 corresponde a un aparcamiento gratuito con 
capacidad para **N** coches, cuyo acceso se realiza a través de **E** 
barreras automáticas de entrada y **S** de salida. 

Dichas barreras están numeradas de la siguiente forma:
- Desde la barrera **0** hasta **E - 1** son de **entrada**.
- Desde la barrera **E** hasta **E + S - 1** son de **salida**.

## Descripción del sistema
Para poder controlar el acceso al área de carga y descarga, 
un equipo ha programado el paquete **Barreras** en el que 
están definidas las constantes **E** y **S**, así como el tipo:

```java
type Tipo_Num_Puerta is Natural range 0..E+S-1;
Esperar_Llegada(i: in Tipo_Num_Puerta)
Elevar_Barrera(i: in Tipo_Num_Puerta)
```
Con la siguiente semantica:
- Cunado el procedimiento `Esperar_Llegada(i)` es ejecutado por un proceso,
este queda bloqueado hasta que se detecta la llegada de un coche a la puerta i.
- Al ejecutar `Elevar_Barrera(i)` la barrera se eleva, permanece
elevada mientras esta pasando el coche y luego desciende automaticamente,
momento en el que termina la llamada.

Se necesitan desarrollar un programa que gestione el acceso al aparcamiento
cumpliendo los siguientes requisitos:
- Todas las barreras deben poder funcionar simultaneamente.
- Solo se permitira la entrada de coches si hay plazas libres.
- Si el aparcameinto se llena, y se detectan coches esperando en mas
de una entrada, se ira dando paso sucesivamente a los coches de cada
entrada, es decir, se dara paso a la entrada `n`, despues a la `n + 1`, etc

**NOTA**: Se trata de desarrollar el programa real de control, no de simular el sistema. 
No existirian, por tanto, procesos dedicados a simular el comportamiento 
de los coches. La interaccion del programa de control con su entorno 
se realiza exclusivamente mediante los procedimientos `Esperar_Llegada` y
`Elevar_Barrera`.
