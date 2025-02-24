package ejemplohilosswing;

import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

public class TareaMuyLarga extends SwingWorker<Integer, Void> {
    JProgressBar barraProgreso; // DECLARAMOS UNA BARRA DE PROGRESO

    // CONSTRUCTOR DE LA CLASE TAREAMUYLARGA QUE RECIBE UNA BARRA DE PROGRESO
    public TareaMuyLarga(JProgressBar barraProgreso) {
        this.barraProgreso = barraProgreso; // ASIGNAMOS LA BARRA DE PROGRESO
    }

    @Override
    // METODO QUE SE EJECUTA EN UN HILO DE FONDO, AQUÍ INICIAMOS LA TAREA LARGA
    protected Integer doInBackground() throws Exception {
        int resultado = tareaLarguiiiisima(); // EJECUTAMOS LA TAREA LARGA Y OBTENEMOS EL RESULTADO
        return resultado; // RETORNAMOS EL RESULTADO DE LA TAREA
    }

    // METODO QUE SIMULA UNA TAREA LARGA Y ACTUALIZA EL PROGRESO CADA SEGUNDO
    public int tareaLarguiiiisima() throws InterruptedException {
        int MAX = 5; // DEFINIMOS EL NUMERO MAXIMO DE PASOS
        int incremento = 100 / (MAX - 1); // CALCULAMOS EL INCREMENTO POR CADA PASO
        int progresoActual = 0; // INICIALIZAMOS EL PROGRESO ACTUAL EN 0
        for (int i = 0; i < 5; i++) { // BUCLE QUE SIMULA LOS PASOS DE LA TAREA
            System.out.println("Progreso actual:" + progresoActual + "%"); // IMPRIMIMOS EL PROGRESO ACTUAL
            barraProgreso.setValue(progresoActual); // ACTUALIZAMOS EL VALOR DE LA BARRA DE PROGRESO
            Thread.sleep(1000); // PAUSAMOS EL HILO DURANTE 1 SEGUNDO PARA SIMULAR EL TIEMPO DE TRABAJO
            progresoActual += incremento; // AUMENTAMOS EL PROGRESO ACTUAL SEGUN EL INCREMENTO
        }
        System.out.println("Progreso completado"); // IMPRIMIMOS MENSAJE CUANDO TERMINA EL PROGRESO
        return 42; // RETORNAMOS UN RESULTADO SIMBOLICO (42)
    }
}
