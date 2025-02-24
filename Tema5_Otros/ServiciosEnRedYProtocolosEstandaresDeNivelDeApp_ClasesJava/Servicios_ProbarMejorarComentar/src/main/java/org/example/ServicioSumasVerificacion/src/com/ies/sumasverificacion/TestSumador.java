package org.example.ServicioSumasVerificacion.src.com.ies.sumasverificacion;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

public class TestSumador {

	// METODO DE TEST PARA VERIFICAR EL CORRECTO FUNCIONAMIENTO DEL METODO sumaSimple
	@Test
	public void test() {
		// SE DEFINE UNA CADENA DE PRUEBA
		String cad1 = "ABC";
		// SE DEFINE EL RESULTADO ESPERADO PARA LA CADENA "ABC"
		int sumaEsperada = 198;
		// SE CALCULA LA SUMA UTILIZANDO EL METODO sumaSimple
		int suma = Sumador.sumaSimple(cad1);
		// SE COMPRUEBA QUE EL RESULTADO CALCULADO SEA IGUAL AL ESPERADO
		assertEquals(sumaEsperada, suma);

		// SE DEFINE OTRA CADENA DE PRUEBA
		cad1 = "ZZ";
		// SE DEFINE EL RESULTADO ESPERADO PARA LA CADENA "ZZ"
		sumaEsperada = 180;
		// SE CALCULA LA SUMA PARA LA NUEVA CADENA
		suma = Sumador.sumaSimple(cad1);
		// SE COMPRUEBA QUE EL RESULTADO CALCULADO SEA IGUAL AL ESPERADO
		assertEquals(sumaEsperada, suma);
	}
}
