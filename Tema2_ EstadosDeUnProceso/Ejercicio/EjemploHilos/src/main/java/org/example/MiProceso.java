package org.example;

public class MiProceso extends Thread {
    Contador contadorProceso;

    @Override
    public void run() {
        super.run(); // WSE EJECUTA AUTOMATICAMENTE
        // SE PUEDE PONER EL NOMBRE DEL HILO
        System.out.println(Thread.currentThread().getName() + " Soy el proceso creado");

        // BUCLE QUE SE EJECUTA MIENTRAS EL NUMERO EN CONTADOR SEA MENOR QUE 1000
        for (; contadorProceso.getNumero() < 1000; ) {
            // BLOQUE SINCRONIZADO PARA ASEGURAR QUE SOLO UN HIJO ACCEDA A CONTADOR A LA VEZ
            synchronized (contadorProceso) {
                // IMPRIME EL NOMBRE DEL HILO ACTUAL Y EL VALOR DEL CONTADOR
                System.out.println("Proceso: " + Thread.currentThread().getName() +
                        " - Contador: " + contadorProceso.getNumero());

                /*  PARA DORMIR EL HILO ACTUAL DURANTE 1 SEGUNDO. ESTO SIMULA UNA OPERACION QUE TOMA TIEMPO.
                    PERO IMPLICA QUE EL HILO ACTUAL PIERDA EL USO DE LA CPU *//*
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e); // Manejo de excepción si el hilo es interrumpido.
                } */

                // AUMENTO DE CONTADOR: EL PROCESO PUEDE PERDER LA CPU ENTRE LA LETURA Y LA ESCRITURA LO QUE PUEDE
                // CAUSAR PROBLEMAS DE CONCURRENCIA
                int tmp = contadorProceso.getNumero(); // OBTIENE EL VALOR ACTRUAL DEL CONTADOR
                tmp++; // INCREMENTA EL VALOR TEMPORALMENTE
                contadorProceso.setNumero(tmp); // ESTABLECE EL NUEVO VALOR DEL CONTADOR

            } // FIN BLOQUE SINCRONIZADO, LIBERANDO EL OBJETO COMPARTIDO
            /* ESTE MECANISMO EVITA QUE DOS HILOS ACCEDAN Y MODIFIQUEN EL CONTADOR AL MISMO TIEMPO LO QUE PREVIENE LA POSIBILIDAD
            DE OBTENER EL MISMO NUMERO EN DIFERENTES HILO */
        }

    } // CUANDO CREEMOS UN PROCESO VA A IR A EL METODO RUN


    // THREAD TIENE DISTINTOS CONSTRUCTORES
    // Thread(String name) METE UN NOMBRE AL PROCESO
    public MiProceso(String name, Contador contadorComun) {
        super(name); // LLAMAMOS AL OBJETO PADRE
        contadorProceso = contadorComun;
    }
}
