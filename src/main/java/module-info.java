module com.example.proyecto3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires gs.core;
    requires java.xml;
    requires java.logging;


    opens com.example.proyecto3 to javafx.fxml;
    exports com.example.proyecto3;
}