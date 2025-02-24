package org.example.MultiProceso1.src.es.ies.multiproceso;

import static org.junit.Assert.*;
import org.junit.Test;
import junit.framework.Assert;

public class SumadorTest {

	// METODO DE PRUEBA PARA VERIFICAR LA FUNCIONALIDAD DEL METODO SUMAR
	@Test
	public void test() {
		// INICIALIZAMOS LOS VALORES DE ENTRADA
		int n1 = 1;
		int n2 = 5;
		int resultadoObtenido;

		// LLAMAMOS AL METODO SUMAR Y COMPROBAMOS QUE EL RESULTADO SEA CORRECTO
		resultadoObtenido = Sumador.sumar(n1, n2);
		// VERIFICAMOS QUE LA SUMA DE 1 Y 5 SEA 15
		Assert.assertEquals(15, resultadoObtenido);

		// LLAMAMOS AL METODO SUMAR CON LOS PARAMETROS AL REVES Y VERIFICAMOS QUE EL RESULTADO SEA EL MISMO
		resultadoObtenido = Sumador.sumar(n2, n1);
		// VERIFICAMOS QUE LA SUMA DE 5 Y 1 SEA 15 (DEBE DAR EL MISMO RESULTADO QUE LA ANTERIOR)
		Assert.assertEquals(15, resultadoObtenido);

		// PROBAMOS CON NUMEROS NEGATIVOS
		n1 = -5;
		n2 = -1;

		// LLAMAMOS AL METODO SUMAR CON LOS NUMEROS NEGATIVOS
		resultadoObtenido = Sumador.sumar(n2, n1);
		// VERIFICAMOS QUE LA SUMA DE -5 Y -1 SEA -15
		Assert.assertEquals(-15, resultadoObtenido);

		// LLAMAMOS AL METODO SUMAR CON LOS PARAMETROS AL REVES Y VERIFICAMOS QUE EL RESULTADO SEA EL MISMO
		resultadoObtenido = Sumador.sumar(n1, n2);
		// VERIFICAMOS QUE LA SUMA DE -1 Y -5 SEA -15 (DEBE DAR EL MISMO RESULTADO QUE LA ANTERIOR)
		Assert.assertEquals(-15, resultadoObtenido);
	}

}
