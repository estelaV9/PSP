package org.example.SimulacionFilosofos.src;

/* CONTROLADOR DE ACCESO A LOS RECURSOS COMPARTIDOS, EN ESTE CASO LOS PALILLOS */
public class GestorPalillos {

	/* UN PALILLO FALSE ES UNO QUE NO ESTA COGIDO */
	private boolean vectorPalillos[];

	/* CONSTRUCTOR QUE INICIALIZA EL VECTOR DE PALILLOS Y LOS DEJA EN FALSE (NO COGIDOS) */
	public GestorPalillos(int numPalillos){
		vectorPalillos = new boolean[numPalillos];
		// INICIALIZAR TODOS LOS PALILLOS COMO NO COGIDOS
		for (int i = 0; i < numPalillos; i++) {
			vectorPalillos[i] = false;
		}
	}

	/* METODO SINCRONIZADO QUE INTENTA ASIGNAR UNA PAREJA DE PALILLOS */
	public synchronized ParejaPalillos cogerPalillos() {
		ParejaPalillos pareja;
		int pos1, pos2;
		pos1 = -1;
		pos2 = -1;
		boolean seguirBuscando = true;
		int pos = 0;

		/* BUSCAR UNA PAREJA DE PALILLOS LIBRES */
		while (seguirBuscando) {
			if (vectorPalillos[pos] == false) {
				if (pos1 == -1) {
					pos1 = pos;
				} else {
					if (pos2 == -1) {
						pos2 = pos;
					}
				}
			}
			pos = pos + 1;
			/* SI LLEGAMOS AL FINAL DEL VECTOR DE PALILLOS, TERMINAMOS DE BUSCAR */
			if (pos >= vectorPalillos.length) {
				seguirBuscando = false;
			}
			/* SI ENCONTRAMOS UNA PAREJA DE PALILLOS, TERMINAMOS LA BUSQUEDA */
			if ((pos1 != -1) && (pos2 != -1)) {
				seguirBuscando = false;
			}
		}

		/* SI ENCONTRAMOS UNA PAREJA DE PALILLOS, LOS MARCAMOS COMO COGIDOS */
		if ((pos1 != -1) && (pos2 != -1)) {
			vectorPalillos[pos1] = true;
			vectorPalillos[pos2] = true;
			pareja = new ParejaPalillos();
			pareja.setPalilloDer(pos1);
			pareja.setPalilloIzq(pos2);
			return pareja;
		}
		/* SI NO ENCONTRAMOS UNA PAREJA DISPONIBLE, RETORNAMOS NULL */
		return null;
	}

	/* METODO SINCRONIZADO QUE LIBERA LOS PALILLOS ASIGNADOS A UNA PAREJA */
	public synchronized void liberarPalillos(ParejaPalillos pareja) {
		int pos1 = pareja.getPalilloDer();
		int pos2 = pareja.getPalilloIzq();
		// LIBERAR LOS PALILLOS, MARCANDOLOS COMO NO COGIDOS
		vectorPalillos[pos1] = false;
		vectorPalillos[pos2] = false;
	}
}
