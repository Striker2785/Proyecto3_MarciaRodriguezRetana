package com.example.proyecto3;

import java.io.File;

public class listaDblEnlace {
    protected Partida start;
    protected Partida end ;
    public int size;


    public listaDblEnlace() {
        start = null;
        end = null;
        size = 0;
    }

    public boolean isEmpty() {
        return start == null;
    }


    public int getSize() {
        return size;
    }

    public void insertAtStart(int val) {
        Partida nptr = new Partida(val, null, null);
        if (start == null) {
            nptr.setLinkNext(nptr);
            nptr.setLinkPrev(nptr);
            start = nptr;
            end = start;
        }
        else {
            nptr.setLinkPrev(end);
            end.setLinkNext(nptr);
            start.setLinkPrev(nptr);
            nptr.setLinkNext(start);
            start = nptr;
        }
        size++ ;
    }

    public void insertAtEnd(int val){
        Partida nptr = new Partida(val, null, null);
        if (start == null)
        {
            nptr.setLinkNext(nptr);
            nptr.setLinkPrev(nptr);
            start = nptr;
            end = start;
        }
        else
        {
            nptr.setLinkPrev(end);
            end.setLinkNext(nptr);
            start.setLinkPrev(nptr);
            nptr.setLinkNext(start);
            end = nptr;
        }
        size++;
    }

    public void insertAtPos(int val , int pos)
    {
        Partida nptr = new Partida(val, null, null);
        if (pos == 1)
        {
            insertAtStart(val);
            return;
        }
        Partida ptr = start;
        for (int i = 2; i <= size; i++)
        {
            if (i == pos)
            {
                Partida tmp = ptr.getLinkNext();
                ptr.setLinkNext(nptr);
                nptr.setLinkPrev(ptr);
                nptr.setLinkNext(tmp);
                tmp.setLinkPrev(nptr);
            }
            ptr = ptr.getLinkNext();
        }
        size++ ;
    }

    public void deleteAtPos(int pos)
    {
        if (pos == 1)
        {
            if (size == 1)
            {
                start = null;
                end = null;
                size = 0;
                return;
            }
            start = start.getLinkNext();
            start.setLinkPrev(end);
            end.setLinkNext(start);
            size--;
            return ;
        }
        if (pos == size)
        {
            end = end.getLinkPrev();
            end.setLinkNext(start);
            start.setLinkPrev(end);
            size-- ;
        }
        Partida ptr = start.getLinkNext();
        for (int i = 2; i <= size; i++)
        {
            if (i == pos)
            {
                Partida p = ptr.getLinkPrev();
                Partida n = ptr.getLinkNext();

                p.setLinkNext(n);
                n.setLinkPrev(p);
                size-- ;
                return;
            }
            ptr = ptr.getLinkNext();
        }
    }

    public void display()
    {
        System.out.print("\nCircular Doubly Linked List = ");
        Partida ptr = start;
        if (size == 0)
        {
            System.out.print("empty\n");
            return;
        }
        if (start.getLinkNext() == start)
        {
            System.out.print(start.getData()+ " <-> "+ptr.getData()+ "\n");
            return;
        }
        System.out.print(start.getData()+ " <-> ");
        ptr = start.getLinkNext();
        while (ptr.getLinkNext() != start)
        {
            System.out.print(ptr.getData()+ " <-> ");
            ptr = ptr.getLinkNext();
        }
        System.out.print(ptr.getData()+ " <-> ");
        ptr = ptr.getLinkNext();
        System.out.print(ptr.getData()+ "\n");
    }
}


