package org.example.japanvisitorchart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;


/*
*   a main class to start a program
* */
public class Main extends Application {

    public void start(Stage stage) throws IOException  {
        Parent root = FXMLLoader.load(getClass().getResource("GraphView.fxml"));
        Scene scene = new Scene(root, 320, 240);
        // Load the icon image
        Image iconImage = new Image(getClass().getResource("/images/fuji-icon.png").toExternalForm());
        // Set the stage icon
        stage.getIcons().add(iconImage);
        stage.setTitle("2023 Visitor Arrivals & Japanese Overseas Travelers");
        stage.setScene(scene);
        stage.show();
    }

    // main method
    public static void main(String[] args) {
        launch(args);
    }


}