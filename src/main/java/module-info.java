module com.example.proyecto3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires gs.core;


    opens com.example.proyecto3 to javafx.fxml;
    exports com.example.proyecto3;
}