package com.example.proyecto3;

public class Node {
    private Object data;
    public Node next;
    public Node previous;

    //Constructor
    public Node(Object data){
        this.data = data;
        this.next = null;
        this.previous = null;
    }

    //retorna datos del nodo
    public Object getData() {
        return data;
    }

    //Configura los datos del nodo
    public void setData(Object data){
        this.data = data;
    }

    //retorna el siguiente nodo en la estructura
    public Node getNext() {
        return next;
    }

    //configura el orden de nodos
    public void setNext(Node node){
        this.next = node;
    }

    public Node getPrevious(){
        return previous;
    }

    public void setPrevious(Node node){
        this.previous = node;
    }
}
