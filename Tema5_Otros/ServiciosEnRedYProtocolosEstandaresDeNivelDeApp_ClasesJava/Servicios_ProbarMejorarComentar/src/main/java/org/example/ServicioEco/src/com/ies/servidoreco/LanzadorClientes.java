package org.example.ServicioEco.src.com.ies.servidoreco;

public class LanzadorClientes {
	public boolean servidorAtendioClientes(int numClientes){
		// EN ESTE METODO LANZAMOS X CLIENTES Y LUEGO COMPROBAMOS
		// SI TODOS FUNCIONARON BIEN. SI TODO FUE BIEN
		// DEVOLVEMOS TRUE Y SI NO DEVOLVEREMOS FALSE
		Thread[] hilos = new Thread[numClientes];
		Cliente[] clientes = new Cliente[numClientes];

		// INICIAMOS UN CICLO PARA CREAR Y LANZAR CADA CLIENTE EN UN HILO
		for (int i = 0; i < numClientes; i++){
			Cliente cliente = new Cliente();  // CREO UN NUEVO CLIENTE
			cliente.setNumHilo(i);  // ASIGNO UN NUMERO DE HILO AL CLIENTE
			Thread hiloAsociado = new Thread(cliente);  // CREE EL HILO ASOCIADO AL CLIENTE
			hilos[i] = hiloAsociado;  // ALMACENO EL HILO EN EL ARREGLO
			hiloAsociado.start();  // INICIO EL HILO
			clientes[i] = cliente;  // ALMACENO EL CLIENTE EN EL ARREGLO
		}

		System.out.println("Lanzados!");

		// ESPERAMOS QUE TODOS LOS HILOS ACABEN
		// DANDOLLES UN PLAZO MAXIMO MAS BIEN PEQUEÑO
		// SI EN ESE TIEMPO NO SE COMPLETO
		// UNA OPERACION TAN SIMPLE, PROBABLEMENTE
		// EL SERVIDOR FALLO
		for (int i = 0; i < numClientes; i++){
			try {
				hilos[i].join();  // ESPERO A QUE EL HILO TERMINE SU EJECUCION
			} catch (InterruptedException e) {
				// SI SE INTERUPPE UN HILO, SE IMPRIME UN MENSAJE
				System.out.println(
						"Se interrumpio un hilo por parte " +
								"de alguna clase del cliente ");
			}
		} // Fin del for que espera a los hilos

		// COMPROBAMOS SI TODOS LOS HILOS ESTAN BIEN
		// Y EN CUANTO UNO SUFRA UN FALLO PODEMOS
		// ASUMIR QUE EL SERVIDOR NO PUDO ATENDER TANTOS
		// CLIENTES
		for (int i = 0; i < numClientes; i++){
			if (clientes[i].huboFallo()){  // SI EL CLIENTE TUVO UN FALLO
				return false;  // DEVUELVO FALSE, YA QUE HUBO UN FALLO
			}
		} // Fin del for

		// SI LLEGAMOS AQUI ES PORQUE EL SERVIDOR
		// PUDO ATENDER A TODOS LOS CLIENTES A LA VEZ
		return true;  // DEVUELVO TRUE YA QUE TODOS LOS CLIENTES FUERON ATENDIDOS CORRECTAMENTE
	}

	public static void main(String[] args) {
		LanzadorClientes lanzador = new LanzadorClientes();

		// VAMOS PROBANDO A LANZAR MUCHOS CLIENTES
		// HASTA QUE FORZAMOS UN FALLO
		for (int i = 1; i < 1000; i++){
			boolean todoOK;
			int numClientes = i * 1000;  // AUMENTO EL NUMERO DE CLIENTES A CADA ITERACION
			System.out.println("Lanzando " +
					numClientes + " clientes...");
			todoOK = lanzador.servidorAtendioClientes(numClientes);  // LLAMO AL METODO PARA COMPROBAR SI EL SERVIDOR LOS ATIENDE

			// SI ALGO NO FUE BIEN, INDICAMOS LA CANTIDAD
			// DE CLIENTES CON QUE SE PRODUJO EL FALLO
			if (!todoOK){
				System.out.println(
						"El servidor parecio fallar con:" +
								numClientes);  // IMPRIMO EL NUMERO DE CLIENTES CON EL QUE FALLO
				return;  // TERMINO LA EJECUCION DEL PROGRAMA
			} // Fin del if
		} // Fin del main
	}
}
