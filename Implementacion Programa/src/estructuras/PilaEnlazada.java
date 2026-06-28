package estructuras;

public class PilaEnlazada<T> {
    private Nodo<T> cima;

    public void push(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.setSiguiente(cima);
        cima = nuevo;
    }

    public T peek() {
        return cima == null ? null : cima.getDato();
    }
}