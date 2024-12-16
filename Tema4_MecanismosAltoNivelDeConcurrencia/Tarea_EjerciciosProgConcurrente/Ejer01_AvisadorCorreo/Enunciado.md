# Ejercicio 1: Avisador de correo

Un **avisador de correo** es una pequeña aplicación que (generalmente en un entorno de ventanas)
avisa al usuario de si ha recibido correo electrónico. La figura muestra el aspecto de la ventanita del
avisador, en los dos estados posibles: sin o con correo.

Lo que tiene que hacer el programa es, más o menos, lo siguiente:
<ul>
    <li>Al empezar, si el fichero de correo no está vacío se muestra el icono del correo; en caso contrario el de sin correo.</li>
    <li>Cada 30 segundos (aprocimadamente: el tiempo no es crítico aquí) se mira si ha variado el tamaño del fichero
de correo: si ha creacido desde la última vez ha de pintarse el icono de correo; si ha decrecido se asume que el usuario
está leyendo ya que los mensajes con algún programma y se pintará el icono de sin correo.</li>
    <li>El usuario puede quitar el aviso de correo en cualquier momento haciendo click con el ratón en el icono.</li>
</ul>

Se asumen como **ya programados** los siguientes procedimientos:

```java
procedure Fichero_Correo() return TipoFichero;
-- Devuelve el fichero donde se almacena el correo del usuario
-- que lo ejecuta.

procedure Tam(f: in TipoFichero) return Natural;
-- Devuelve el tamaño del fichero que se le pasa como argumento.

procedure Esperar(seg : in Natural);
-- No retorna hasta que han pasado seg! segundos.

procedure Leer_Clic();
-- No retorna hasta que el usuario hace clic con el ratón en la
-- ventana de la aplicación.

procedure Pintar_Icono_Correo();
-- Pinta el icono de que ha llegado correo en la ventana
-- de la aplicación.

procedure Pintar_Icono_Sin_Correo();
-- Pinta el icono de que no hay correo en la
-- ventana de la aplicación.
```

Las operaciones de pintado **no** se pueden ejecutar de manera concurente.