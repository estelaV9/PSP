package org.example.SolucionExamen2016_11_15.src.com.examenes;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class ContadorAparicionesPalabraTest {

	@Test
	public void test() {
		long apariciones;

		// COMPROBAMOS QUE LA PALABRA "HOLA" APARECE 2 VECES EN "HOLA HOLA"
		apariciones = ContadorAparicionesPalabra.contarApariciones(
				"hola hola", "hola");
		Assert.assertEquals(2, apariciones);

		// COMPROBAMOS QUE LA PALABRA "HOLA" NO APARECE EN "OLA OLA"
		apariciones = ContadorAparicionesPalabra.contarApariciones(
				"ola ola", "hola");
		Assert.assertEquals(0, apariciones);

		// COMPROBAMOS QUE "JAVA" NO APARECE EN "JAVA ES UN LENGUAJE" (MINUSCULA)
		apariciones = ContadorAparicionesPalabra.contarApariciones(
				"Java es un lenguaje", "java");
		Assert.assertEquals(0, apariciones);

		// COMPROBAMOS QUE "JAVA" APARECE UNA VEZ EN "JAVA ES UN LENGUAJE" (MAYUSCULA)
		apariciones = ContadorAparicionesPalabra.contarApariciones(
				"Java es un lenguaje", "Java");
		Assert.assertEquals(1, apariciones);

		// COMPROBAMOS QUE "LENGUAJE" APARECE UNA VEZ EN "JAVA ES UN LENGUAJE"
		apariciones = ContadorAparicionesPalabra.contarApariciones(
				"Java es un lenguaje ", "lenguaje");
		Assert.assertEquals(1, apariciones);

		// COMPROBAMOS QUE "AA" APARECE 2 VECES EN "AAA"
		apariciones = ContadorAparicionesPalabra.contarApariciones(
				"aaa", "aa");
		Assert.assertEquals(2, apariciones);
	}
}
