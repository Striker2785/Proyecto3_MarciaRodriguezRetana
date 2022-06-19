package com.example.proyecto3;

import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable{
    @FXML
    private Label welcomeText;
    @FXML
    private ImageView greenAnt;
    @FXML
    private ImageView blueAnt;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private AnchorPane gamePane;

    public void addButton(ActionEvent event) throws IOException {
        GraphWeighted graphWeighted = new GraphWeighted(true, 3, 0.99);

        NodeWeighted zero = new NodeWeighted(0, "0", 30, 50);
        NodeWeighted one = new NodeWeighted(1, "1", 100, 300);
        NodeWeighted two = new NodeWeighted(2, "2", 200, 80);

        graphWeighted.addNode(zero);
        graphWeighted.addNode(one);
        graphWeighted.addNode(two);

        graphWeighted.addEdge(zero, one, 8);

        for (int i = 0; i < graphWeighted.nNodos; i++) {
            NodeWeighted unNodo = graphWeighted.nodes.get(i);

            Button newButton = new Button();
            newButton.setLayoutX(unNodo.getX());
            newButton.setLayoutY(unNodo.getY());
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
            if(i < graphWeighted.nNodos - 1){
                NodeWeighted siguienteNodo = graphWeighted.nodes.get(i+1);
                if(graphWeighted.hasEdge(unNodo, siguienteNodo)){
                    Line newLine = new Line(unNodo.getX()+10, unNodo.getY()+10, siguienteNodo.getX()+10, siguienteNodo.getY()+10);
                    gamePane.getChildren().add(newLine);
                }
            }


            gamePane.getChildren().add(newButton);
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Circle circle = new Circle(100);
        PathTransition transition = new PathTransition();
        transition.setNode(greenAnt);
        transition.setDuration(Duration.seconds(2));
        transition.setPath(circle);
        transition.setCycleCount(PathTransition.INDEFINITE);
        transition.play();

    }
}