package com.example.proyecto3;

import java.util.*;

/**
 * Esta clase mas importante del proyecto, ya que es la que tiene toda la información del grafo a trabajar. Utiliza un HashSet
 * como estructura para guardar los nodos.
 * Tiene una variable booleana que indica si se quiere crear un grafo dirigido o no, para efectos de este proyecto, esta variable es final
 * Pero se deja asi para hacer pruebas
 */
public class GraphWeighted {
    public Set<NodeWeighted> nodes;
    private boolean directed;
    public int nNodos;

    //Constructor
    GraphWeighted(boolean directed, int n) {
        this.nNodos = n;
        this.directed = directed;
        nodes = new HashSet<>(n);
    }

    //Metodo para agregar nodos al set, se utiliza una metodo var arg
    public void addNode(NodeWeighted... n) {
        nodes.addAll(Arrays.asList(n));
    }

    /**
     * Convierte el HashSet en un arreglo para poder acceder a los nodos con un indice
     * @param id indice en el arreglo
     * @return NodeWeighted
     */
    public NodeWeighted obtenerNodo(int id){
        NodeWeighted[] listaNodos = nodes.toArray(new NodeWeighted[nodes.size()]);
        return listaNodos[id];
    }

    /**
     * Se utiliza si se quiere encontrar un nodo cuando solo se sabe el nombre de este
     * @param name nombre hilera del nodo
     * @return NodeWeighted
     */
    public NodeWeighted obtenerNodo(String name){
        for(NodeWeighted nodo: nodes){
            if(nodo.name == name){
                return nodo;
            }
        }
        return null;
    }

    /**
     * Devuleve la cantidad de nodos que tienen comida
     * @return int
     */
    public int nodosConComida(){
        int contador = 0;
        for(NodeWeighted node: nodes){
            if(node.tieneComida()){
                contador++;
            }
        }
        return contador;
    }

    /**
     * Devuelve el nodo que tiene comida ya que se utiliza una conidcion de que solo puede haber uno
     * @return NodeWeighted
     */
    public NodeWeighted nodoConComida(){
        for(NodeWeighted node: nodes){
            if(node.tieneComida()){
                return node;
            }
        }
        return null;
    }

    /**
     * Este metodo utiliza la variable de probabilidad para decidir de manera aleatoria cuando se fomra una arista entre los nodos
     * Crea una arista del source al destination con un peso igual a weight.
     * En caso de que el grafo no sea dirigido, la arista se crea del destination al source
     * Llama a un metodo auxiliar para crear una instancia de EdgeWeighted
     * @param source
     * @param destination
     * @param probabilidad
     * @param weight
     */
    public void addEdge(NodeWeighted source, NodeWeighted destination, double probabilidad, double weight) {
        //HashSet sirve para no repetir nodos cuando ya existen en el set
        if(Math.random()<probabilidad){
            addNode(source);
            addNode(destination);

            addEdgeHelper(source, destination, weight);

            if (!directed && source != destination) {
                addEdgeHelper(destination, source, weight);
            }
        }


    }

    /**
     * Funcion auxiliar que recibe los mismos parametros menos la variable de probabilidad. Se asegura de que no hayan duplicados de
     * aristas
     * @param a
     * @param b
     * @param weight
     */
    private void addEdgeHelper(NodeWeighted a, NodeWeighted b, double weight) {
        for (EdgeWeighted edge : a.edges) {
            if (edge.source == a && edge.destination == b) {
                // Actualiza el peso en caso de ser diferente
                edge.weight = weight;
                return;
            }
        }
        // Se agrega la a rista al nodo a
        a.edges.add(new EdgeWeighted(a, b, weight));
    }


    public void resetNodesVisited() {
        for (NodeWeighted node : nodes) {
            node.unvisit();
        }
    }

    /**
     * Este metodo encuentra el camino mas rapido del nodo start al nodo end del grafo.
     * @param start
     * @param end
     * @return ArrayList<NodeWeighted>
     */
    public ArrayList<NodeWeighted> DijkstraShortestPath(NodeWeighted start, NodeWeighted end) {
        //Se instancia el arreglo que se va a retornar
        ArrayList<NodeWeighted> path = new ArrayList<>();

        // Se tiene un control del camino mas corto que se guarda en changedAt
        // Este Hashmap tiene los valores de todos los nodos del camino con apuntadores a su nodo padre
        HashMap<NodeWeighted, NodeWeighted> changedAt = new HashMap<>();
        changedAt.put(start, null);

        // Tiene un control del peso del camino para llegar hacia cada nodo
        HashMap<NodeWeighted, Double> shortestPathMap = new HashMap<>();

        // El peso de cada camino se escribe como un acercamineto al infinito del lado positivo
        // El peso del camino para el nodo start es 0
        for (NodeWeighted node : nodes) {
            if (node == start)
                shortestPathMap.put(start, 0.0);
            else shortestPathMap.put(node, Double.POSITIVE_INFINITY);
        }

        // se agregan todos los nodos a los que se puede llegar desde el nodo start
        for (EdgeWeighted edge : start.edges) {
            shortestPathMap.put(edge.destination, edge.weight);
            changedAt.put(edge.destination, start);
        }

        start.visit();

        // Este loop corre mientras hayan nodos no visitados
        while (true) {
            NodeWeighted currentNode = closestReachableUnvisited(shortestPathMap);
            //  El metodo closestReachableUnvisited() devuelve null si no existe un camino entre los nodos
            if (currentNode == null) {
                System.out.println("No hay camino entre" + start.name + " y " + end.name);
                return path;
            }

            // Si el mas cercano es el nodo end, se busca guardar el camino qeu se tomó
            if (currentNode == end) {

                System.out.println("El camino con el menor peso entre"
                        + start.name + " y " + end.name + " es:");

                NodeWeighted child = end;

                String pathString = child.name;
                path.add(end);

                while (true) {
                    NodeWeighted parent = changedAt.get(child);
                    if (parent == null) {
                        break;
                    }

                    pathString = parent.name + " " + pathString;
                    path.add(parent);
                    child = parent;
                }
                System.out.println(pathString);
                System.out.println("El camino tiene un peso de: " + shortestPathMap.get(end));
                return path;
            }
            currentNode.visit();

            // Se verifica que no hay mejores caminos tomando los demas nodos no visitados
            for (EdgeWeighted edge : currentNode.edges) {
                if (edge.destination.isVisited())
                    continue;

                if (shortestPathMap.get(currentNode)
                        + edge.weight
                        < shortestPathMap.get(edge.destination)) {
                    shortestPathMap.put(edge.destination,
                            shortestPathMap.get(currentNode) + edge.weight);
                    changedAt.put(edge.destination, currentNode);
                }
            }
        }
    }

    /**
     * Retorna el nodo mas cercano que no haya sido visitado
     * @param shortestPathMap
     * @return
     */
    private NodeWeighted closestReachableUnvisited(HashMap<NodeWeighted, Double> shortestPathMap) {

        double shortestDistance = Double.POSITIVE_INFINITY;
        NodeWeighted closestReachableNode = null;
        for (NodeWeighted node : nodes) {
            if (node.isVisited())
                continue;

            //Si la distancia sigue siendo infinito significa que no hay camino por este nodo, sigue devolviendo null
            double currentDistance = shortestPathMap.get(node);
            if (currentDistance == Double.POSITIVE_INFINITY)
                continue;
            // Si la distancia es mas corta, se actualiza el nodo
            if (currentDistance < shortestDistance) {
                shortestDistance = currentDistance;
                closestReachableNode = node;
            }
        }
        return closestReachableNode;
    }

}

