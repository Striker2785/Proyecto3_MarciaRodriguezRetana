package com.example.proyecto3;

public class GraphShow {
    public static void main(String[] args) {
        GraphWeighted graphWeighted = new GraphWeighted(true, 7, 0.99);

        NodeWeighted zero = new NodeWeighted(0, "0", 30, 50);
        NodeWeighted one = new NodeWeighted(1, "1", 60, 50);
        NodeWeighted two = new NodeWeighted(2, "2", 90, 80);
        NodeWeighted three = new NodeWeighted(3, "3", 120, 50);
        NodeWeighted four = new NodeWeighted(4, "4", 150, 50);
        NodeWeighted five = new NodeWeighted(5, "5", 180, 50);
        NodeWeighted six = new NodeWeighted(6, "6", 210, 50);



        // Our addEdge method automatically adds Nodes as well.
        // The addNode method is only there for unconnected Nodes,
        // if we wish to add any
        graphWeighted.addEdge(zero, one, 8);
        graphWeighted.addEdge(zero, two, 11);
        graphWeighted.addEdge(one, three, 3);
        graphWeighted.addEdge(one, four, 8);
        graphWeighted.addEdge(one, two, 7);
        graphWeighted.addEdge(two, four, 9);
        graphWeighted.addEdge(three, four, 5);
        graphWeighted.addEdge(three, five, 2);
        graphWeighted.addEdge(four, six, 6);
        graphWeighted.addEdge(five, four, 1);
        graphWeighted.addEdge(five, six, 8);

        graphWeighted.DijkstraShortestPath(zero, four);

    }


}
