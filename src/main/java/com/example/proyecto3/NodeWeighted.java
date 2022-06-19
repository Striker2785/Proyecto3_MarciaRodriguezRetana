package com.example.proyecto3;

import java.util.LinkedList;

public class NodeWeighted {
    // The int n and String name are just arbitrary attributes
    // we've chosen for our nodes these attributes can of course
    // be whatever you need
    String name;
    int x;
    int y;
    int n;
    private boolean visited;
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
