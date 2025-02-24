package org.example.PlantillaAplicacionMultihilo.src.com.ies.plantilla;

public class Lanzador {

	public Lanzador() {
		/* Poner aquí todo el código que
		 * se necesite para inicializar los atributos
		 */
	}
	
	/* Nadie de fuera puede llamar a este método. Ni el main*/
	protected void crearRecursosCompartidos(){
		/* Crear aquí los recursos compartidos. Esto se
		 * hará ANTES DE LANZAR LOS HILOS */
	}
	/* Nadie de fuera puede llamar a este método. Ni el main*/
	protected void crearHilos(){
		/* Crear aquí los hilos. Esto se ejecutará
		 * DESPUES DE CREAR LOS RECURSOS COMPARTIDOS */
		
	}
	
	/* Este método pone en marcha todo el proceso...*/
	public void lanzar(){
		/* Para ello PRIMERO CREA LOS RECURSOS COMPARTIDOS*/
		crearRecursosCompartidos();
		
		/* Y despues crea los hilos, pasándoles, si es necesario
		 * el o los objetos compartidos */
		crearHilos();
	}

	public static void main(String[] args) {
		Lanzador lanzador=new Lanzador();
		lanzador.lanzar();

	}

}
