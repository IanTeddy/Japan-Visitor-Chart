package org.example.japanvisitorchart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    // create a database connector object
    DatabaseConnector dbConnector = new DatabaseConnector();
    TextArea textArea = new TextArea();


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ArrivalTableview.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }


    // main method
    public static void main(String[] args) { launch(args); }




}