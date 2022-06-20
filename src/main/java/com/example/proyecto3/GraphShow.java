package com.example.proyecto3;

import java.util.HashSet;
import java.util.Set;

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
        Set<NodeWeighted> set = graphWeighted.nodes;
        NodeWeighted[] listaNodos = set.toArray(new NodeWeighted[set.size()]);

        for(int i =0; i < cantidadNodos; i++){
            for(int j=0; j< cantidadNodos; j++){
                if(j!=i){
                    graphWeighted.addEdge(listaNodos[i], listaNodos[j], probabilidad, Math.random()*10);
                }
            }
        }
        return graphWeighted;
    }

}
