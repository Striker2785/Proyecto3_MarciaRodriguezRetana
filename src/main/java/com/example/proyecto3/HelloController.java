package com.example.proyecto3;

import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController implements Initializable{
    public GraphWeighted graphWeighted;
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
        int numeroNodos = Integer.parseInt(cantidadNodos.getText());
        graphWeighted = GraphShow.creaGrafo(numeroNodos, 0.99);
        for (NodeWeighted node: graphWeighted.nodes) {
            System.out.println(node.getX());
            Button newButton = new Button(node.name);
            newButton.setLayoutX(node.getX());
            newButton.setLayoutY(node.getY());
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
            for(EdgeWeighted edge: node.edges){
                Line newLine = new Line(node.getX()+10, node.getY()+10, edge.destination.getX()+10, edge.destination.getY()+10);
                gamePane.getChildren().add(newLine);

            }

            gamePane.getChildren().add(newButton);
        }

        botonEmpezar.setDisable(false);
    }

    public void comienzaJuego(ActionEvent event)throws IOException{
        gamePane.getChildren().add(greenAnt);
        int numeroNodos = Integer.parseInt(cantidadNodos.getText());
        NodeWeighted start = graphWeighted.obtenerNodo(0);
        NodeWeighted end = graphWeighted.obtenerNodo(numeroNodos-1);
        ArrayList<NodeWeighted> pathNodos = graphWeighted.DijkstraShortestPath(start, end);
        int size = pathNodos.size();
        Path path = new Path();

        //Moving to the starting point
        MoveTo moveTo = new MoveTo(start.getX()-90, start.getY()-110);
        LineTo line1 = new LineTo(pathNodos.get(size-2).getX()-90, pathNodos.get(size-2).getY()-110);


        //Adding all the elements to the path
        path.getElements().add(moveTo);
        path.getElements().add(line1);
        //path.getElements().addAll(line1, line2, line3, line4, line5);

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