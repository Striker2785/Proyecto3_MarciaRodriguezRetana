package com.example.proyecto3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import java.io.IOException;

public class HistorialController {

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label nNodos;
    @FXML
    private Label puntajeAzul;
    @FXML
    private Label puntajeVerde

    public void cargar(ActionEvent event) throws IOException {
        String[] elementosPartida1 = ReadXmlDomParser.lee("Partidas1.xml");
        String[] elementosPartida2 = ReadXmlDomParser.lee("Partidas2.xml");
        String[] elementosPartida3 = ReadXmlDomParser.lee("Partidas3.xml");
        String[] elementosPartida4 = ReadXmlDomParser.lee("Partidas4.xml");
        String[] elementosPartida5 = ReadXmlDomParser.lee("Partidas5.xml");
        String[] elementosPartida6 = ReadXmlDomParser.lee("Partidas6.xml");

        Partida partida1 = new Partida();
        partida1.setPartida(elementosPartida1[0], elementosPartida1[1], elementosPartida1[2]);
        Partida partida2 = new Partida();
        partida2.setPartida(elementosPartida2[0], elementosPartida2[1], elementosPartida2[2]);
        Partida partida3 = new Partida();
        partida3.setPartida(elementosPartida3[0], elementosPartida3[1], elementosPartida3[2]);
        Partida partida4 = new Partida();
        partida4.setPartida(elementosPartida4[0], elementosPartida4[1], elementosPartida4[2]);
        Partida partida5 = new Partida();
        partida5.setPartida(elementosPartida5[0], elementosPartida5[1], elementosPartida5[2]);
        Partida partida6 = new Partida();
        partida6.setPartida(elementosPartida6[0], elementosPartida6[1], elementosPartida6[2]);

        listaDblEnlace nuevaLista = new listaDblEnlace();

        nNodos.setText(partida1.cantidadNodos);
        puntajeAzul.setText(partida1.puntajeAzules);
        puntajeVerde.setText((partida2.puntajeVerdes));



    }

    public void switchToMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load((getClass().getResource("main-menu.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
