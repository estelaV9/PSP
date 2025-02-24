package org.example.PlantillaAplicacionMultihilo.src.com.ies.plantilla;


/* Ejemplo de recurso compartido y de su implementación*/

public class RecursoCompartido {
	/* Todo objeto compartido tendrá uno o varios valores
	 * de importancia. Deberían declarse aquí */
	int valorImportante;
	public RecursoCompartido() {
		/* El constructor debería inicializar todos los valores
		 * de importancia y todos los que necesite para
		 * funcionar
		 */
	}
	/* Cualquier modificación o consulta
	 * de un valor compartido por varios hilos
	 * debería usar "synchronized" */
	public synchronized void setValor(int nuevoValor){
		valorImportante=nuevoValor;
	}
	public synchronized int getValor(){
		return valorImportante;
	}
	

}
