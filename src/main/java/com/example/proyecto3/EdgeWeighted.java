package com.example.proyecto3;

/**
 * Clase de arista que contiene 2 propiedades de clase NodeWeighted: source y destination
 * Y una propiedad de weight que es el peso de esa arista
 * Tiene infomracion sobre la conexion entre nodos
 */
public class EdgeWeighted{

    NodeWeighted source;
    NodeWeighted destination;
    double weight;

    EdgeWeighted(NodeWeighted s, NodeWeighted d, double w) {
        source = s;
        destination = d;
        weight = w;
    }
    //Este metodo sirve para hacer tests
    public String toString() {
        return String.format("(%s -> %s, %f)", source.name, destination.name, weight);
    }

}

