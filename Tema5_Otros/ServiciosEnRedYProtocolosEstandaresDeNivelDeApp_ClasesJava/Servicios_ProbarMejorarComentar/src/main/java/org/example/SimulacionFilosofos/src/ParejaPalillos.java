package org.example.SimulacionFilosofos.src;

public class ParejaPalillos {

	/* DEFINIMOS LOS PALILLOS QUE ESTAN ASOCIADOS A UNA PAREJA */
	/* CADA FILOSOFO NECESITA DOS PALILLOS: UNO A LA IZQUIERDA Y OTRO A LA DERECHA */
	int palilloIzq, palilloDer;

	/* OBTENEMOS EL PALILLO A LA IZQUIERDA */
	public int getPalilloIzq() {
		return palilloIzq;
	}

	/* ESTABLECEMOS EL PALILLO A LA IZQUIERDA */
	public void setPalilloIzq(int palilloIzq) {
		this.palilloIzq = palilloIzq;
	}

	/* OBTENEMOS EL PALILLO A LA DERECHA */
	public int getPalilloDer() {
		return palilloDer;
	}

	/* ESTABLECEMOS EL PALILLO A LA DERECHA */
	public void setPalilloDer(int palilloDer) {
		this.palilloDer = palilloDer;
	}

}
