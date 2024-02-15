package org.example.japanvisitorchart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;

/*
*   FXML control class to control a table view of Arrival visitors with implementing Initialize
 */
public class ArrivalTableViewController implements Initializable {

    // table elements
    @FXML
    private TableView<ArrivalVisitors> arrivalTable;
    @FXML
    private TableColumn<ArrivalVisitors, Integer> month;
    @FXML
    private TableColumn<ArrivalVisitors, Integer> previousYear;
    @FXML
    private TableColumn<ArrivalVisitors, Integer> currentYear;
    @FXML
    private TableColumn<ArrivalVisitors, Double> changeRate;


    // ObservableList that will store all data which are retrieved from database
    ObservableList<ArrivalVisitors> listview = FXCollections.observableArrayList();

    // initialize a Arrival Visitors table view
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        month.setCellValueFactory(new PropertyValueFactory<>("month"));
        previousYear.setCellValueFactory(new PropertyValueFactory<>("previousYear"));
        currentYear.setCellValueFactory(new PropertyValueFactory<>("currentYear"));
        changeRate.setCellValueFactory(new PropertyValueFactory<>("changeRate"));

        try {
            // create database connector object and build connection
            DatabaseConnector dbConnector = new DatabaseConnector();
            Connection connection = dbConnector.connect();

            // create statement and execute SQL query of displaying table
            String sql = "SELECT * FROM ArrivalVisitors";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                // add retrieved data from each column to listview
                listview.add(new ArrivalVisitors(
                        resultSet.getInt("month"),
                        resultSet.getInt("year2019"),
                        resultSet.getInt("year2023"),
                        resultSet.getDouble("changeRate")
                ));
                // add listview to ArrivalTable
                arrivalTable.setItems(listview);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }


    // A button to switch scene to Chart view
    public void switchToArrivalGraph(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ArrivalGraph.fxml")));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}