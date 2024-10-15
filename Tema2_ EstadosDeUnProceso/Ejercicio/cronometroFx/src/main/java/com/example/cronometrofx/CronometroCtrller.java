package com.example.cronometrofx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CronometroCtrller {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}