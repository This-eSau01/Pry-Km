package estructuras;

public class ListaAristas<T> {

    public static class Nodo<T> {
        public T destino;
        public double peso;
        public Nodo<T> siguiente;

        public Nodo(T destino, double peso) {
            this.destino = destino;
            this.peso = peso;
            this.siguiente = null;
        }
    }

    private Nodo<T> cabeza;
    private int tamanio;

    public ListaAristas() {
        this.cabeza = null;
        this.tamanio = 0;
    }

    public void agregar(T destino, double peso) {
        Nodo<T> nuevo = new Nodo<>(destino, peso);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
        tamanio++;
    }

    public Nodo<T> getCabeza() {
        return cabeza;
    }

    public int getTamanio() {
        return tamanio;
    }

    public boolean estaVacia() {
        return tamanio == 0;
    }
}