package com.example.proyecto3;

import java.util.HashSet;
import java.util.Set;

/**
 * Esta clase crea un grafo con pesos aleatorio.
 * Tiene 2 metodos auxiliares que generan coordenadas aleatorias tomando en cuenta el tamaño de pantalla
 */
public class GraphShow {

    private static int generaXRandom(){
        return (int)Math.floor(Math.random()*630);
    }

    private static int generaYRandom(){
        return (int)Math.floor(Math.random()*600);
    }

    /**
     * Este metodo crea una instancia GraphWeighted con una cantidad de nodos definida y un parametro de probabilidad
     Se convierte el HashSet a un arreglo para poder acceder a los nodos y crear aristas
     * @param cantidadNodos indica cuantos nodos se agregan al grafo
     * @param probabilidad este es una variable que cambia el chance de que se cree una arista entre 2 nodos
     * @return GraphWeighted
     */
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
                //No pueden haber aristas que van de un nodo hacia el mismo
                if(j!=i){
                    graphWeighted.addEdge(listaNodos[i], listaNodos[j], probabilidad, Math.random()*10);
                }
            }
        }
        return graphWeighted;
    }

}
