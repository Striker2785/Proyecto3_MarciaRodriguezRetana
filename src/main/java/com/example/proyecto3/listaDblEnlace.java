package com.example.proyecto3;

import java.io.File;

public class listaDblEnlace {
    private Node head;
    private Node tail;
    private int size;

    //Constructor
    public listaDblEnlace(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public Node getHead() {
        return head;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty() {
        return this.head == null;
    }


    public void addNode(Object data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = tail = newNode;
            head.setPrevious(null);
            tail.setNext(null);
        } else {
            tail.setNext(newNode);
            newNode.setPrevious(tail);
            tail = newNode;
            tail.setNext(null);
        }
        this.size++;
    }

    public void insertFirst(Object data){
        Node newNode = new Node(data);
        if(this.head != null){
            newNode.setNext(this.head);
            this.head = newNode;
            this.size++;
        }else{
            this.head = newNode;
        }

    }

    public Node deleteFirst(){
        if(this.head != null){
            Node temp = this.head;
            this.head = this.head.getNext();
            this.head.setPrevious(null);
            this.size --;
            return temp;
        }else{
            return null;
        }
    }
    //metodo de prueba para verificar otros metodos
    public void displayList() {
        Node current = this.head;
        while (current != null) {
            System.out.println(current.getData());
            current = current.getNext();
        }
    }

    public void duplicar(){
        int size = this.size;
        Node current = this.getHead();
        for(int i =0; i<size;i++){
            this.addNode(current.getData());
            current = current.getNext();

        }
    }

    public Node find(Object searchValue) {
        Node current = this.head;
        while (current != null) {
            if (current.getData().equals(searchValue)) {
                return current;
            } else {
                current = current.getNext();
            }
        }
        return null;
    }

    public listaDblEnlace addFiles(File[] files){
        for(File file : files){
            this.addNode(file);
        }
        return this;
    }




}
