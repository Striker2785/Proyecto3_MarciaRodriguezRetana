package com.example.proyecto3;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    @FXML
    private Label textMensaje;
    @FXML
    private ImageView comida;


    /**
     * Esta funcion crea un grafo con la cantidad de nodos que el usuario determine. El programa tendra una variable de
     * probabilidad de 0.9 para asegurarse que es un grafo conectado. Para cada nodo se crean botones y un EventHandler
     * para dicho boton.
     * @param event
     * @throws IOException
     */
    public void addButton(ActionEvent event) throws IOException {
        gamePane.getChildren().clear(); // Es importante borrar los nodos de la partida anterior
        int numeroNodos = Integer.parseInt(cantidadNodos.getText());
        graphWeighted = GraphShow.creaGrafo(numeroNodos, 0.6);
        for (NodeWeighted node: graphWeighted.nodes) {
            Button newButton = new Button(node.name);
            newButton.setLayoutX(node.getX());
            newButton.setLayoutY(node.getY());
            newButton.setTextFill(Color.WHITE);

            EventHandler<ActionEvent> poneComida = new EventHandler<>() {
                /**
                 * Se propone una condicion donde solo un nodo puede tener la propiedad de comida como true
                 * En caso de que se cumpla la condicion, se agrega un nodo imagen de la comida y se cambia el estado
                 * de tieneComida del nodo a true
                 * @param event
                 */
                @Override
                public void handle(ActionEvent event) {
                    NodeWeighted nodeAccessed = graphWeighted.obtenerNodo(newButton.getText());
                    if (graphWeighted.nodosConComida() <= 1) {
                        nodeAccessed.setComida(true);
                        gamePane.getChildren().add(comida);
                        comida.setLayoutX(nodeAccessed.getX());
                        comida.setLayoutY(nodeAccessed.getY());
                    } else {
                        textMensaje.setText("No mas comida");
                    }
                }
            };

            newButton.setOnAction(poneComida);

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
            // se dibujan las aristas
            for(EdgeWeighted edge: node.edges){
                Line newLine = new Line(node.getX()+10, node.getY()+10, edge.destination.getX()+10, edge.destination.getY()+10);
                gamePane.getChildren().add(newLine);

            }

            gamePane.getChildren().add(newButton);
        }

        botonEmpezar.setDisable(false);
    }

    /**
     * Funcion que arranca el movimiento de la hormiga a traves del grafo una vez se haya puesto la comida
     * @param event
     * @throws IOException
     * @throws InterruptedException
     */
    public void comienzaJuego(ActionEvent event) throws IOException, InterruptedException {
        gamePane.getChildren().add(greenAnt);
        NodeWeighted start = graphWeighted.obtenerNodo(0); //inicia con el primer nodo de la lista
        NodeWeighted end = graphWeighted.nodoConComida(); // el nodo objetivo es el que tiene comida

        //se obtiene la lista del camino a seguir segun el algoritmo de Dijkstra
        ArrayList<NodeWeighted> pathNodos = graphWeighted.DijkstraShortestPath(start, end);
        int size = pathNodos.size();
        Path path = new Path();

        // Coordenadas iniciales de la hormiga
        MoveTo moveTo = new MoveTo(start.getX()-210, start.getY()-550);
        path.getElements().add(moveTo);

        // Si el arreglo no tiene nodos, significa que no hay conexion entre los nodos
        if(size == 0){
            textMensaje.setText("No hay conexion entre los nodos");
        }else{
            for(int i = 1; i <= size; i++) {
                int coordX = pathNodos.get(size - i).getX() - 210; //offset de coordenadas
                int coordY = pathNodos.get(size - i).getY() - 550;
                LineTo newLine = new LineTo(coordX, coordY);
                path.getElements().add(newLine);
            }
        }
        // Camino en coordenadas en las que el nodo de la hormiga se mueve
        PathTransition transition = new PathTransition();
        transition.setNode(greenAnt);
        transition.setDuration(Duration.seconds(6));
        transition.setPath(path);
        transition.setCycleCount(1);
        transition.play();


        end.setComida(false);
        end = graphWeighted.nodoConComida();
        gamePane.getChildren().remove(comida);




    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*


         */

    }
}