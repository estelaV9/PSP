package com.example.cronometrofx.Model;

import com.example.cronometrofx.Controller.CronometroCtrller;
import javafx.scene.control.Label;

public class Cronometro extends Thread{
    private Label eti;
    public Cronometro (Label cronometro){
        this.eti = cronometro;
    }

    @Override
    public void run() {
        int x = 0;
        while(CronometroCtrller.isIniciadoHilo){
            try {
                Thread.sleep(1000);
                System.out.println(x);
                ejecutarHiloCronometro(x);
                x++;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
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
        String segTxt = "", minTxt = "", horaTxt = "";
        segTxt += CronometroCtrller.segundo;
        minTxt += CronometroCtrller.minuto;

        String reloj = horaTxt + " : " + minTxt + " : " + segTxt;
        eti.setText(reloj);
    }
}
