package fciencias.edatos.proyecto1.colecciones;

public class Queue<T> implements TDAQueue<T> {
    private class Nodo {
        protected T elemento;
        protected Nodo siguiente;

        public Nodo(T e) {
            this.elemento = e;
        }
    }

    private Nodo head;
    private Nodo tail;
    private int length;

    public void clear() {
        this.head = null;
        this.tail = null;
    }

    public T dequeue() {
        if (isEmpty()) return null;
        length--;
        T returnable = this.head.elemento;
        if (head == tail) { //caso 1 elemento
            head = null;
            tail = null;
            return returnable;
        }
        head = head.siguiente;
        return returnable;
    }

    public void enqueue(T e) {
        Nodo nuevo = new Nodo(e);
        if (isEmpty()) {
            this.tail = nuevo;
            this.head = nuevo;
        }
        this.tail.siguiente = nuevo;
        this.tail = nuevo;
        length++;
    }

    public T first() {
        if (isEmpty()) return null;
        return head.elemento;
    }

    public boolean isEmpty() {
        return head == null && tail == null;
    }

    public int size() {
        return length;
    }
}
