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
public class TableViewController implements Initializable {

    @FXML
    private Label titleLabel;

    // table elements
    @FXML
    private TableView<ArrivalVisitors> tableView;
    @FXML
    private TableColumn<ArrivalVisitors, String> month;
    @FXML
    private TableColumn<ArrivalVisitors, Integer> previousYear;
    @FXML
    private TableColumn<ArrivalVisitors, Integer> currentYear;
    @FXML
    private TableColumn<ArrivalVisitors, Double> changeRate;


    // create database connector object and build connection
    DatabaseConnector dbConnector = new DatabaseConnector();

    // SQL Queries
    String sqlArrivalTable = "SELECT * FROM ArrivalVisitors";
    String sqlTravelersTable = "SELECT * FROM OverseasTravelers";

    // ObservableList that will store all data which are retrieved from database
    ObservableList<ArrivalVisitors> listview = FXCollections.observableArrayList();


    // Implemented initialize method to display a table and set a title depends on which data is chosen
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(GraphController.data == 0 || GraphController.data == 1 ){
            displayTable(sqlArrivalTable);
            titleLabel.setText(Main.label1);
        } else if (GraphController.data == 2) {
            displayTable(sqlTravelersTable);
            titleLabel.setText(Main.label2);
        }
    }

    // a function that accept one SQL query and display a table view of corresponding data
    public void displayTable(String sqlQuery){
        month.setCellValueFactory(new PropertyValueFactory<>("month"));
        previousYear.setCellValueFactory(new PropertyValueFactory<>("previousYear"));
        currentYear.setCellValueFactory(new PropertyValueFactory<>("currentYear"));
        changeRate.setCellValueFactory(new PropertyValueFactory<>("changeRate"));

        // connect to database
        try (Connection connection = dbConnector.connect();){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                // add retrieved data from each column to listview
                listview.add(new ArrivalVisitors(
                        resultSet.getString("month"),
                        resultSet.getInt("year2019"),
                        resultSet.getInt("year2023"),
                        resultSet.getDouble("changeRate")
                ));
                // add listview to ArrivalTable
                tableView.setItems(listview);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }


    // A button to switch scene to Graph view
    public void switchToGraph(ActionEvent event)throws IOException {
        // to go back to the graph of the same data
        if(GraphController.data == 1 || GraphController.data == 0){
            GraphController.data = 1;
        }

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("GraphView.fxml")));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}