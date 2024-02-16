package org.example.japanvisitorchart;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
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

public class ArrivalGraphController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private BarChart<String, Integer> ArrivalbarChart;

    /*
     * Initializes the controller class of Arrival Visitors graph.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            // create database connection
            DatabaseConnector dbConnector = new DatabaseConnector();
            Connection connection = dbConnector.connect();

            // retrieve month and number of each year from database
            String sql = "SELECT month, year2019, year2023 FROM ArrivalVisitors";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            // create three ArrayList to store data
            ArrayList<String> month = new ArrayList<>();
            ArrayList<Integer> dataOf2019 = new ArrayList<>();
            ArrayList<Integer> dataOf2023 = new ArrayList<>();

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
            ArrivalbarChart.getData().addAll(dataSeries1, dataSeries2);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    // a button function to switch Graph scene to Tableview scene
    public void switchToArrivalTableview(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ArrivalTableView.fxml")));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
