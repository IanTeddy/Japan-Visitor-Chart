package org.example.japanvisitorchart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


/*
*   a main class to start a program
* */
public class Main extends Application {

    // static variables title labels for each scene
    public static String label1 = "Visitor Arrivals in Japan (2019 vs 2023)";
    public static String label2 = "Japanese Overseas Travelers (2019 vs 2023)";

    public void start(Stage stage) throws IOException  {
        Parent root = FXMLLoader.load(getClass().getResource("GraphView.fxml"));
        Scene scene = new Scene(root, 800, 600);

        // add stylesheet to scene
        String css = this.getClass().getResource("style.css").toExternalForm();
        scene.getStylesheets().add(css);

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