package estructuras;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaDobleEnlazada<T> implements Iterable<T> {

    private NodoDoble<T> head;
    private NodoDoble<T> tail;
    private int size;

    public ListaDobleEnlazada() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void insertar(T dato) {
        NodoDoble<T> nuevo = new NodoDoble<>(dato);
        if (head == null) {
            head = tail = nuevo;
        } else {
            tail.setSiguiente(nuevo);
            nuevo.setAnterior(tail);
            tail = nuevo;
        }
        size++;
    }

    public int size() {
        return size;
    }

    public boolean estaVacia() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        NodoDoble<T> cur = head;
        while (cur != null) {
            sb.append(cur.getDato());
            if (cur.getSiguiente() != null) sb.append(" <-> ");
            cur = cur.getSiguiente();
        }
        return sb.toString();
    }

    public T getFirst() {
        return (head != null) ? head.getDato() : null;
    }

    public T getLast() {
        return (tail != null) ? tail.getDato() : null;
    }

    /**
     * Devuelve el dato almacenado en la posición indicada (0-based).
     * Complejidad O(n) por ser recorrido secuencial.
     */
    public T obtenerEnPosicion(int posicion) {
        NodoDoble<T> cur = obtenerNodoEnPosicion(posicion);
        return (cur != null) ? cur.getDato() : null;
    }

    /**
     * Reemplaza el dato almacenado en la posición indicada (0-based).
     * Se usa para la operación de modificar dentro del CRUD.
     */
    public boolean modificarEnPosicion(int posicion, T nuevoDato) {
        NodoDoble<T> cur = obtenerNodoEnPosicion(posicion);
        if (cur == null) return false;
        cur.setDato(nuevoDato);
        return true;
    }

    /**
     * Elimina el nodo en la posición indicada (0-based).
     * Reconecta los enlaces anterior/siguiente sin dejar huecos.
     */
    public boolean eliminarEnPosicion(int posicion) {
        NodoDoble<T> cur = obtenerNodoEnPosicion(posicion);
        if (cur == null) return false;
        return eliminarNodo(cur);
    }

    /**
     * Elimina la primera ocurrencia de un dato (usa equals()).
     */
    public boolean eliminar(T dato) {
        NodoDoble<T> cur = head;
        while (cur != null) {
            if (cur.getDato() != null && cur.getDato().equals(dato)) {
                return eliminarNodo(cur);
            }
            cur = cur.getSiguiente();
        }
        return false;
    }

    /**
     * Indica si la lista contiene un dato (usa equals()).
     */
    public boolean contiene(T dato) {
        NodoDoble<T> cur = head;
        while (cur != null) {
            if (cur.getDato() != null && cur.getDato().equals(dato)) {
                return true;
            }
            cur = cur.getSiguiente();
        }
        return false;
    }

    /**
     * Busca la posición (0-based) de la primera ocurrencia de un dato.
     * Devuelve -1 si no se encuentra.
     */
    public int indiceDe(T dato) {
        NodoDoble<T> cur = head;
        int idx = 0;
        while (cur != null) {
            if (cur.getDato() != null && cur.getDato().equals(dato)) {
                return idx;
            }
            cur = cur.getSiguiente();
            idx++;
        }
        return -1;
    }

    // ---------- Métodos internos de apoyo ----------

    private NodoDoble<T> obtenerNodoEnPosicion(int posicion) {
        if (posicion < 0 || posicion >= size) return null;
        NodoDoble<T> cur = head;
        for (int i = 0; i < posicion; i++) {
            cur = cur.getSiguiente();
        }
        return cur;
    }

    private boolean eliminarNodo(NodoDoble<T> nodo) {
        NodoDoble<T> anterior = nodo.getAnterior();
        NodoDoble<T> siguiente = nodo.getSiguiente();

        if (anterior != null) {
            anterior.setSiguiente(siguiente);
        } else {
            head = siguiente;
        }

        if (siguiente != null) {
            siguiente.setAnterior(anterior);
        } else {
            tail = anterior;
        }

        nodo.setSiguiente(null);
        nodo.setAnterior(null);
        size--;
        return true;
    }

    /**
     * Permite recorrer la lista con for-each: for (T dato : lista) { ... }
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private NodoDoble<T> actual = head;

            @Override
            public boolean hasNext() {
                return actual != null;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T dato = actual.getDato();
                actual = actual.getSiguiente();
                return dato;
            }
        };
    }
}