package estructuras;

public class ListaDobleEnlazada<T> {

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
}
