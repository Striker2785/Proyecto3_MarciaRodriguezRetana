package com.example.proyecto3;

public class Partida {
    private int cantidadNodos;
    private int puntajeAzules;
    private int puntajeVerdes;
    protected int data;
    protected Partida next, prev;


    public Partida()
    {
        next = null;
        prev = null;
        data = 0;
    }

    public Partida(int d, Partida n, Partida p)
    {
        data = d;
        next = n;
        prev = p;
    }

    public void setPartida(int nodos, int puntajeA, int puntajeV){
        this.cantidadNodos = nodos;
        this.puntajeAzules = puntajeA;
        this.puntajeVerdes = puntajeV;

    }


    public void setLinkNext(Partida n)
    {
        next = n;
    }


    public void setLinkPrev(Partida p)
    {
        prev = p;
    }


    public Partida getLinkNext()
    {
        return next;
    }


    public Partida getLinkPrev()
    {
        return prev;
    }


    public void setData(int d)
    {
        data = d;
    }


    public int getData()
    {
        return data;
    }
}


