package com.example.cronometrofx.Controller;

import com.example.cronometrofx.Model.Cronometro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CronometroCtrller {
    @FXML
    private Label cronometroLabel; // MUESTRA EL CRONOMETRO
    @FXML
    private Button iniciarBtt;
    @FXML
    private Button pararBtt;

    public static int hora = 0, minuto = 0, segundo = 0; // INICIALIZAMOS LAS VARIABLES QUE VAMOS A USAR EN EL CRONOMETRO
    public static boolean isIniciadoHilo = true; // VARIBALE PARA CONTROLAR SI EL HILO DEL CRONOMETRO ESTA ACTIVO
    boolean ejecutando = false; // CONTROLA SI EL CRONOMETRO ESTA EN EJECUCION

    private Cronometro cronometro; // VARIABLE PARA MANEJAR EL HILO DEL CRONOMETRO

    @FXML
    void onIniciarAction(ActionEvent event) {
        if (!ejecutando) {
            isIniciadoHilo = true; // HILO EN EJECUCION
            ejecutando = true; // CRONOMETRO EN MARCHA
            iniciarCronometro(); // INICIAR CRONOMETRO
        } // SOLO SE INICIA SI EL CRONOMETRO NO ESTA EN EJECUCION
    } // METODO INICIAR CRONOMETRO

    @FXML
    void onPararAction(ActionEvent event) {
        ejecutando = false; // CRONOMETRO DEJA DE EJECUTARSE
        isIniciadoHilo = false; // DETIENE EL HILO
        if (cronometro != null) {
            cronometro.interrupt(); // INTERRUMPE EL HILO SI ESTA EN EJECUCION
        } // SI EL CRONOMETRO ESTA EJECUTANDOSE, LO INTERRUMPE
    } // METODO PARA

    private void iniciarCronometro() {
        if (cronometro == null || !cronometro.isAlive()) { // SOLO SE INICIA SI NO HAY UN HILO EJECUTANDOSE
            cronometro = new Cronometro(cronometroLabel);
            cronometro.start(); // INICIA EL HILO
        } // SOLO INCIIA SI NO EXISTE UN HILO O EL ANTERIOR HA TERMINADO
    } // METODO QUE INICIA EL CRONOMETRO SI NO HAY UN HILO ACTIVO
}