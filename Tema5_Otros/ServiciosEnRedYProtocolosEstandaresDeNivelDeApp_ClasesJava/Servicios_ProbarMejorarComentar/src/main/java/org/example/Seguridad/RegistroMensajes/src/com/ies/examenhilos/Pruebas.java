package org.example.Seguridad.RegistroMensajes.src.com.ies.examenhilos;

public class Pruebas implements PruebasMBean {
	// VARIABLE PARA ALMACENAR EL NUMERO DE ESCRITURAS
	int num;

	// CONSTRUCTOR DE LA CLASE PRUEBAS
	public Pruebas() {
		// CONSTRUCTOR VACIO
	}

	// METODO PARA OBTENER EL NUMERO DE ESCRITURAS
	@Override
	public int getEscrituras() {
		// DEVUELVE EL NUMERO DE ESCRITURAS REALIZADAS
		return num;
	}

	// METODO PARA ESTABLECER EL NUMERO DE ESCRITURAS
	@Override
	public void setEscrituras(int num) {
		// ASIGNA UN NUEVO VALOR AL NUMERO DE ESCRITURAS
		this.num=num;
	}

}
