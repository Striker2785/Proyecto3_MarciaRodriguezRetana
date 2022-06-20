package com.example.proyecto3;

import java.util.LinkedList;

/**
 * Esta clase de nodos sigue el mismo estandar de nombre para diferenciarlo a la clase de nodos para estructuras de listas enlazadas
 * Cada nodo tiene una lista de las aristas, o sea de los nodos a los que este se conecta
 * Una propiedad muy util es la de las coordenadas X y Y para poder generar el camino en la interfaz
 */
public class NodeWeighted {
    String name;
    int x;
    int y;
    int n;
    private boolean visited;
    private boolean comida;
    LinkedList<EdgeWeighted> edges;

    NodeWeighted(int n, String name, int x, int y) {
        this.n = n;
        this.name = name;
        this.x = x;
        this.y = y;
        visited = false;
        edges = new LinkedList<>();
    }

    int getX(){
        return x;
    }
    // Indica si este nodo tiene comida
    void setComida(boolean bool){
        this.comida = bool;
    }
    boolean tieneComida(){
        return this.comida;
    }

    int getY(){
        return y;
    }

    boolean isVisited() {
        return visited;
    }

    void visit() {
        visited = true;
    }

    void unvisit() {
        visited = false;
    }
}
