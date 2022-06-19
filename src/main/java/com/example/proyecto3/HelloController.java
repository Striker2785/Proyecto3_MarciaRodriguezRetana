package com.example.proyecto3;

import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable{
    @FXML
    private ImageView greenAnt;
    @FXML
    private ImageView blueAnt;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private AnchorPane gamePane;
    @FXML
    private TextField cantidadNodos;
    @FXML
    private Button botonEmpezar;


    public void addButton(ActionEvent event) throws IOException {
        gamePane.getChildren().clear();
        GraphWeighted graphWeighted = GraphShow.creaGrafo(Integer.parseInt(cantidadNodos.getText()), 0.1);

        for (int i = 0; i < graphWeighted.nNodos; i++) {
            NodeWeighted unNodo = graphWeighted.nodes.get(i);

            Button newButton = new Button(unNodo.name);
            newButton.setLayoutX(unNodo.getX());
            newButton.setLayoutY(unNodo.getY());
            newButton.setTextFill(Color.WHITE);
            newButton.setStyle(
                    "-fx-background-radius: 5em; " +
                            "-fx-min-width: 20px; " +
                            "-fx-min-height: 20px; " +
                            "-fx-max-width: 10px; " +
                            "-fx-max-height: 10px; " +
                            "-fx-background-color: blue;" +
                            "-fx-background-insets: 0px; " +
                            "-fx-padding: 0px;"
            );
            for(EdgeWeighted edge: unNodo.edges){
                Line newLine = new Line(unNodo.getX()+10, unNodo.getY()+10, edge.destination.getX()+10, edge.destination.getY()+10);
                gamePane.getChildren().add(newLine);

            }

            gamePane.getChildren().add(newButton);
        }
        gamePane.getChildren().add(greenAnt);
        greenAnt.setLayoutX(graphWeighted.nodes.get(0).getX());
        greenAnt.setLayoutY(graphWeighted.nodes.get(0).getY());
        botonEmpezar.setDisable(false);
    }

    public void comienzaJuego(ActionEvent event)throws IOException{
        Circle circle = new Circle(100);
        Path path = new Path();

        //Moving to the starting point
        MoveTo moveTo = new MoveTo(108, 71);

        LineTo line1 = new LineTo(321, 161);
        LineTo line2 = new LineTo(126,232);
        LineTo line3 = new LineTo(232,52);
        LineTo line4 = new LineTo(269, 250);
        LineTo line5 = new LineTo(108, 71);

        //Adding all the elements to the path
        path.getElements().add(moveTo);
        path.getElements().addAll(line1, line2, line3, line4, line5);

        //Creating the path transition
        PathTransition transition = new PathTransition();
        transition.setNode(greenAnt);
        transition.setDuration(Duration.seconds(6));
        transition.setPath(path);
        transition.setCycleCount(PathTransition.INDEFINITE);
        transition.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*


         */

    }
}