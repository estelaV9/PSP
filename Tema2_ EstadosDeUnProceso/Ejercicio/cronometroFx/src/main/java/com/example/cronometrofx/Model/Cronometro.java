package com.example.cronometrofx.Model;

import com.example.cronometrofx.Controller.CronometroCtrller;
import javafx.application.Platform;
import javafx.scene.control.Label;

public class Cronometro extends Thread {
    private Label eti; // ETIQUETA DONDE SE MOSTRARA EL TIEMPO DEL CRONOMETRO

    public Cronometro(Label cronometro) {
        this.eti = cronometro;
    }

    @Override
    public void run() {
        int x = 0;
        // BUCLE QUE SIGUE EJECUTANDOSE MIENTRAS EL CRONOMETRO ESTE INICIADO Y NO SE INTERRUMPA EL HILO
        while (CronometroCtrller.isIniciadoHilo && !Thread.currentThread().isInterrupted()) {
            try {
                // PAUSA EL HILO POR 1 SEGUNDO
                Thread.sleep(1000);
                ejecutarHiloCronometro(); // CRONOMETRO
            } catch (InterruptedException e) {
                // SI EL HILO ES INTERRUMPIDO, FINALIZA EL HILO
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    private void ejecutarHiloCronometro() {
        CronometroCtrller.segundo++; // INCREMENTA LOS SEGUNDOS

        if (CronometroCtrller.segundo > 59) {
            CronometroCtrller.segundo = 0; // SE REINICIA A 0
            CronometroCtrller.minuto++; // AUMENTA EL MINUTO
        }

        if (CronometroCtrller.minuto > 59) {
            CronometroCtrller.minuto = 0;
            CronometroCtrller.hora++;
        }

        // FORMATEAR LA HORA, MINUTOS Y SEGUNDOS EN EL FORMATO "hh : mm : ss"
        String reloj = String.format("%02d : %02d : %02d",
                CronometroCtrller.hora, CronometroCtrller.minuto, CronometroCtrller.segundo);

        // ASEGURARNOS DE QUE LA ACTUALIZACION DEL LABEL OCURRA EN EL HILO DE LA INTERFAZ GRAFICA
        Platform.runLater(() -> {
            eti.setText(reloj); // Actualizamos el texto del Label en el hilo de JavaFX
        });
        // eti.setText(reloj); SI SETTEAMOS DIRECTAMENTE OCURRE UN ERROR YA QUE SE ESTA
        // INTENTANDO ACTUALIZAR UN LABEL DESDE UN HILO DIFERENTE
        // POR ESO SE USA 'Platform.runLater()': ESTE METODO GARANTIZA QUE EL CODIGO DENTRO
        // DEL BLOQUE SE EJECUTE EN EL HILO DE LA INTERFAZ.
    } // METODO QUE ACTUALIZA LOS DATOS DEL CRONOMETRO
}