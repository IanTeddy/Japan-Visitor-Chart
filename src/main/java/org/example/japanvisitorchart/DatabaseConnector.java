package org.example.japanvisitorchart;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


// A class to connect a database and other classes
public class DatabaseConnector {

    private static final String URL = "jdbc:mysql://localhost:3306/Japan-Visitors";
    private static final String USER = "root";
    private static final String PASS = "Teddy1019";

    public Connection connect() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }
}

