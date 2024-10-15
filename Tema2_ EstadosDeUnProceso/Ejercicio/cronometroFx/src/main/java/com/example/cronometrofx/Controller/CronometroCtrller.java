package com.example.cronometrofx.Controller;

import com.example.cronometrofx.Model.Cronometro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CronometroCtrller {
    @FXML
    private Label cronometroLabel;
    @FXML
    private Button iniciarBtt;
    @FXML
    private Button pararBtt;

    public static int hora = 0, minuto = 0, segundo = 0; // INICIALIZAMOS LAS VARIABLES QUE VAMOS A USAR EN EL CRONOMETRO
    public static boolean isIniciadoHilo = true; // VARIBALE PARA SABER SI SE HA INIDICADO EL HILO
    boolean ejecutando = false; // CONTROLA LA LOGICA DE CUANDO VA A INICIAR Y CUANDO VA A PARAR

    @FXML
    void onIniciarAction(ActionEvent event) {
        if(!ejecutando){
            isIniciadoHilo = true;
            ejecutando = true;
            iniciarCronometro();
        }
    }

    @FXML
    void onPararAction(ActionEvent event) {
        ejecutando = false;
        isIniciadoHilo = false;
    }

    private void iniciarCronometro(){
        if(isIniciadoHilo){
            Cronometro cronometro = new Cronometro(cronometroLabel);
            cronometro.start();
        }
    }
}