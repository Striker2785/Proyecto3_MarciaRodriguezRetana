package com.example.proyecto3;

import java.util.ArrayList;

public class GraphShow {

    private static int generaXRandom(){
        return (int)Math.floor(Math.random()*630);
    }

    private static int generaYRandom(){
        return (int)Math.floor(Math.random()*600);
    }

    public static GraphWeighted creaGrafo(int cantidadNodos, double probabilidad) {
        GraphWeighted graphWeighted = new GraphWeighted(true, cantidadNodos);
        for (int i = 0; i < cantidadNodos; i++) {
            NodeWeighted node = new NodeWeighted(i, String.valueOf(i), generaXRandom(), generaYRandom());
            graphWeighted.addNode(node);
        }

        for(int i =0; i < cantidadNodos; i++){
            for(int j=0; j< cantidadNodos; j++){
                graphWeighted.addEdge(graphWeighted.nodes.get(i), graphWeighted.nodes.get(j), probabilidad, Math.random()*10);
            }
        }
        //graphWeighted.DijkstraShortestPath(graphWeighted.nodes.get(0), graphWeighted.nodes.get(cantidadNodos-1));
        return graphWeighted;
    }


}
