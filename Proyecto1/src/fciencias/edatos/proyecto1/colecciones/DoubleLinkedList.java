package fciencias.edatos.proyecto1.colecciones;
import java.util.Iterator;

/**
* Metodos genericos para ListasTDA.
* @author Carlos Cruz Rangel, Juan Carlos Zenteno Pompa.
* @version 3.0 Octubre 2021.
* @since Estructuras de datos 2022-1. Prática 2.
*/

public class DoubleLinkedList<T> implements TDAList<T> {

    public Nodo primero;
    public Nodo ultimo;
    public int length;

    protected class Nodo {
        public T elemento;
        public Nodo anterior;
        public Nodo siguiente;

        public Nodo(T elemento) {
            this.elemento = elemento;
        }
    }

    //Clase interna que implementa la interfaz Iterator. Ayuda a iterar la lista.
    public class Iterador implements Iterator<T> {
        private Nodo actual;

        public Iterador(DoubleLinkedList<T> lista) {
            actual = lista.primero;
        }

        @Override
        public T next() {
            Nodo guardado = this.actual;
            this.actual = actual.siguiente;

            return guardado.elemento;
        }

        @Override
        public boolean hasNext() {
            return actual != null;
        }
    }

//Los métodos agregados en esta sección no fueron pedidos. Sin embargo, nos pareces métodos INDISPENSABLES para cualquier lista, y por ende, aquí están.
    /** Anexa al final de la lista el elemento dado. */ 
    public void push(T elemento) {
        Nodo nuevo = new Nodo(elemento);

        this.length++;
        if (this.isEmpty()) {
            this.primero = nuevo;
            this.ultimo = nuevo;
            return;
        }

        nuevo.anterior = this.ultimo;
        this.ultimo.siguiente = nuevo;

        this.ultimo = nuevo;
    }

    /** Anexa al inicio de la lista el elemento dado. */
    public void append(T elemento) {
        Nodo nuevo = new Nodo(elemento);

        this.length++;
        if (this.isEmpty()) {
            this.primero = nuevo;
            this.ultimo = nuevo;
            return;
        }

        nuevo.siguiente = this.primero;
        this.primero.anterior = nuevo;

        this.primero = nuevo;
    }

    /** Regresa el elemento al inicio de la lista y lo elimina de la misma */
    public T pop() {
        //Caso lista vacía, se regresa null.
        if (this.isEmpty())
            return null;
        T guardado = this.primero.elemento;
        //Caso lista de un único elemento. Se vacía la lista.
        if (this.size() == 1) {
            this.clear();
            return guardado;
        } 
        
        primero = primero.siguiente;
        primero.anterior = null;

        length--;
        return guardado;
    }

    /** Regresa el elemento al final de la lista y lo elimina de la misma */
    public T pop_back() {
        //Caso lista vacía, se regresa null.
        if (this.isEmpty())
            return null;
        T guardado = this.ultimo.elemento;
        //Caso lista de un único elemento. Se vacía la lista.
        if (this.length == 1) {
            this.clear();
            return guardado;
        }
        ultimo = ultimo.anterior;
        ultimo.siguiente = null;

        length--;
        return guardado;
    }
//Terminan los métodos agregados por conveniencia.


    @Override
    public Iterator<T> listIterador() {
        return new Iterador(this);
    }

    @Override
    public TDAList<T> cut(boolean side) {
        DoubleLinkedList<T> cercenada = new DoubleLinkedList<T>();

        if (side) {
            Nodo apuntador = this.ultimo;
            for (int i = 0; i < this.length/2; i++) {
                cercenada.append(apuntador.elemento);
                apuntador = apuntador.anterior;
            }
        } else {
            Nodo apuntador = this.primero;
            for (int i = 0; i < this.length/2; i++) {
                cercenada.push(apuntador.elemento);
                apuntador = apuntador.siguiente;
            }
        }

        return cercenada;
    }

    @Override
    public void revert() {
        Iterador iterador = (Iterador)this.listIterador();
        DoubleLinkedList<T> revertida = new DoubleLinkedList<T>();

        while (iterador.hasNext())
            revertida.append(iterador.next());

        this.primero = revertida.primero;
        this.ultimo = revertida.ultimo;
        //Esto desperdicia mucha memoria. Estoy (casi) seguro de que hay un mejor método, pero mi crush me está hablando y no soy bueno asignando prioridades.
    }

    @Override
    public int size() {
        Iterator<T> iterador = this.listIterador();
        int tamaño = 0;
        while (iterador.hasNext()) {
            iterador.next();
            tamaño++;
        }
        return tamaño;
    }

    @Override
    public T remove(int i) throws IndexOutOfBoundsException {
        if(i < 0 || this.length <= i)
            throw new IndexOutOfBoundsException();
        
        T elemento;
        if(i == 0) return this.pop();
        else {
            Nodo aux = primero;
            for(int j = 0; j < i-1; j++){
                aux = aux.siguiente;
            }
            elemento = aux.siguiente.elemento;
            aux.siguiente = aux.siguiente.siguiente;

        }
        this.length--;
        return elemento;
    }

    @Override
    public boolean isEmpty() {
        return this.primero == null && this.ultimo == null;
    }

    @Override
    public T get(int i) throws IndexOutOfBoundsException {
        if( i < 0 || i > this.length){
            throw new IndexOutOfBoundsException();
        } else if(isEmpty()) {
            return null;
        } else if(i == 0) {
            return primero.elemento;
        }else{
            Nodo apuntador = this.primero;
            for(int j = 0; j < i; j++){
                apuntador = apuntador.siguiente;
            }
            return (T) apuntador.elemento;
        }
    }

    @Override
    public boolean contains(T e) {
        int counter = (this.length + (this.length%2))/2;
        //apuntadores
        Nodo frontal = this.primero;
        Nodo trasero = this.ultimo;
        System.out.println(counter);
        for (int i = 0; i < counter; i++) {
            //Revisamos en la parte trasera y frontal a la vez
            if (e.equals(frontal.elemento)) return true;
            if (e.equals(trasero.elemento)) return true;
            //Avanzamos ambos apuntadores.
            frontal = frontal.siguiente;
            trasero = trasero.anterior;
        }

        return false;
    }

    @Override
    public void clear() {
        this.primero = null;
        this.ultimo = null;
        this.length = 0;
    }

    @Override
    public void add(int i, T e) throws IndexOutOfBoundsException {
        if (this.length == i) {this.push(e); return;}
        if (this.length <= i || i < 0) throw new IndexOutOfBoundsException();
        if (0 == i) {this.append(e); return;}

        int delta = length - i;
        // Usamos la delta anterior para encontrar que extremo de la lista está más cerca del índice.
        if (i <= delta) { //en este caso i está más cerca de la cabeza de la lista.
            Nodo apuntador = this.primero;
            for (int j = 0; j < i; j++) apuntador = apuntador.siguiente;
            Nodo nuevo = new Nodo(e);
            // El nodo nuevo va a sustituir en el orden de la lista al nodo apuntador, por ende,
            // tiene a este como siguiente, y sustituye a su anterior.
            nuevo.anterior = apuntador.anterior;
            nuevo.siguiente = apuntador;

            nuevo.anterior.siguiente = nuevo;
            apuntador.anterior = nuevo;

        } else { //en este caso i está más cerca de la cola de la lista.
            Nodo apuntador = this.ultimo;
            for (int j = 0; j < delta - 1; j++) apuntador = apuntador.anterior;
            Nodo nuevo = new Nodo(e);
            //Ésto es homólogo al caso anterior.
            nuevo.anterior = apuntador.anterior;
            nuevo.siguiente = apuntador;

            nuevo.anterior.siguiente = nuevo;
            apuntador.anterior = nuevo;
        }
        this.length++;
    }

    /**
     * Intercambia los valores en los indices dados.
     * Este metodo fue añadido para el proyecto.
     * @param i primer indice.
     * @param j segundo indice.
     */
    public void intercambiar(int i, int j) {
        if (this.size() <= i || i < 0 || this.size() <= j|| j < 0) 
			throw new IndexOutOfBoundsException();
        Nodo apuntador = this.primero;

        //Nodos que intercambiarán sus valores
        Nodo primero = null;
        Nodo segundo = null;

        for (int k = 0; k <= Math.max(i, j); k++) {
            if (k == i) 
                primero = apuntador;
            if (k == j)
                segundo = apuntador;

            apuntador = apuntador.siguiente;
        }

        T coso = primero.elemento;
        primero.elemento = segundo.elemento;
        segundo.elemento = coso;
    }

    @Override
    public String toString() {
        String str = "[";
        Nodo nodo = this.primero;
        while (nodo != null) {
            str += nodo.elemento.toString();
            if (nodo.siguiente != null) str += ", ";
            nodo = nodo.siguiente;
        }
        str += "]";
        return str;
    }
}
