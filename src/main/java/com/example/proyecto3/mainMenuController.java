package com.example.proyecto3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class mainMenuController {
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button botonJugar;

    public void switchToGame(ActionEvent event) throws IOException {
        root = FXMLLoader.load((getClass().getResource("hello-view.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToHistorial(ActionEvent event) throws IOException {
        root = FXMLLoader.load((getClass().getResource("Historial.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();



    }
}
