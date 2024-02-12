module org.example.japanvisitorchart {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.japanvisitorchart to javafx.fxml;
    exports org.example.japanvisitorchart;
}