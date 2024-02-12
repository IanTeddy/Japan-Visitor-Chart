package org.example.japanvisitorchart;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ArrivalTableViewController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}