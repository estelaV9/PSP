package com.example.cronometrofx.Model;

import com.example.cronometrofx.Controller.CronometroCtrller;
import javafx.application.Platform;
import javafx.scene.control.Label;

public class Cronometro extends Thread{
    private Label eti;
    public Cronometro (Label cronometro){
        this.eti = cronometro;
    }

    @Override
    public void run() {
        int x = 0;
        while(CronometroCtrller.isIniciadoHilo && !Thread.currentThread().isInterrupted()){
            try {
                Thread.sleep(1000);
                System.out.println(x);
                ejecutarHiloCronometro(x);
                x++;
            } catch (InterruptedException e) {
                // MaANEJAR LA INTERRUPCION Y SALIDA DEL HILO
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    private void ejecutarHiloCronometro(int x){
        CronometroCtrller.segundo++;
        System.out.println(CronometroCtrller.segundo);
        if(CronometroCtrller.segundo > 59){
            CronometroCtrller.segundo = 0;
            CronometroCtrller.minuto++;
        }
        if(CronometroCtrller.minuto > 59){
            CronometroCtrller.minuto = 0;
            CronometroCtrller.hora++;
        }
        String segTxt = "", minTxt = "", horaTxt = "00";
        segTxt += CronometroCtrller.segundo;
        minTxt += CronometroCtrller.minuto;

        String reloj;
        if(CronometroCtrller.segundo < 10){
            if(CronometroCtrller.minuto < 10) {
                reloj = horaTxt + " : 0" + minTxt + " : 0" + segTxt;
            } else {
                reloj = horaTxt + " : " + minTxt + " : 0" + segTxt;
            }
        } else {
            if(CronometroCtrller.minuto < 10) {
                reloj = horaTxt + " : 0" + minTxt + " : " + segTxt;
            } else {
                reloj = horaTxt + " : " + minTxt + " : " + segTxt;
            }
        }


        Platform.runLater(() -> {
            eti.setText(reloj); // Actualizamos el texto del Label en el hilo de JavaFX
        });
        // eti.setText(reloj); SI SETTEAMOS DIRECTAMENTE OCURRE UN ERROR YA QUE SE ESTA
        // INTENTANDO ACTUALIZAR UN LABEL DESDE UN HILO DIFERENTE
        // POR ESO SE USA 'Platform.runLater()': ESTE METODO GARANTIZA QUE EL CODIGO DENTRO
        // DEL BLOQUE SE EJECUTE EN EL HILO DE LA INTERFAZ.
    }
}
