package org.example.japanvisitorchart;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class GraphController implements Initializable {

    // labels for each data
    @FXML
    private Label titleLabel;

    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private RadioButton arrivalBtn, travelerBtn;

    // Create a ToggleGroup for radio buttons
    ToggleGroup toggleGroup = new ToggleGroup();

    // create database connection
    DatabaseConnector dbConnector = new DatabaseConnector();

    // SQL queries to retrieve month and number of each year from database
    String sqlArrival = "SELECT month, year2019, year2023 FROM ArrivalVisitors";
    String sqlTravelers = "SELECT month, year2019, year2023 FROM OverseasTravelers";

    // create three ArrayList to store data
    ArrayList<String> month = new ArrayList<>();
    ArrayList<Integer> dataOf2019 = new ArrayList<>();
    ArrayList<Integer> dataOf2023 = new ArrayList<>();

    // create a static variable to distinguish between the data of Visitors Arrival & Overseas Travelers
    public static int data = 0;

    // Implemented initialize method
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Add radio buttons to the ToggleGroup
        arrivalBtn.setToggleGroup(toggleGroup);
        travelerBtn.setToggleGroup(toggleGroup);

        if(data == 1 || data == 0){
            displayGraph(sqlArrival);
            arrivalBtn.setSelected(true);
            titleLabel.setText(Main.label1);
        } else if(data == 2){
            displayGraph(sqlTravelers);
            travelerBtn.setSelected(true);
            titleLabel.setText(Main.label2);
        }
    }



    // A function that accept a sql query and display the graphs
    public void displayGraph(String sqlQuery){
        try(Connection connection = dbConnector.connect()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            // Clear existing data from the chart
            barChart.getData().clear();

            // store each data to corresponding array
            while (resultSet.next()){
                month.add(resultSet.getString(1));
                dataOf2019.add(resultSet.getInt(2));
                dataOf2023.add(resultSet.getInt(3));
            }

            // create each series for 2019 & 2023 data and set names
            XYChart.Series<String, Integer> dataSeries1 = new XYChart.Series<>();
            dataSeries1.setName("2019");

            XYChart.Series<String, Integer> dataSeries2 = new XYChart.Series<>();
            dataSeries2.setName("2023");

            // loop through all data and add to each series
            for(int i = 0; i < month.size(); i++){
                dataSeries1.getData().add(new XYChart.Data<>(month.get(i), dataOf2019.get(i)));
                dataSeries2.getData().add(new XYChart.Data<>(month.get(i), dataOf2023.get(i)));
            }
            // add two series to the barchart object
            barChart.getData().addAll(dataSeries1, dataSeries2);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }


    // a button function to switch Graph scene to Tableview scene
    public void switchToTableview(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TableView.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    // a radio button that allows to change between two data
    public void changeGraph(ActionEvent event){
        if(arrivalBtn.isSelected()){
            titleLabel.setText(Main.label1);
            displayGraph(sqlArrival);
            data = 1;              // set Visitor Arrivals data as 1
        } else if (travelerBtn.isSelected()) {
            titleLabel.setText(Main.label2);
            displayGraph(sqlTravelers);
            data = 2;              // set Overseas Travelers data as 2
        }
    }

}
