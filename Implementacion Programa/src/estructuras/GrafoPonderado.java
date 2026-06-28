package estructuras;

public class GrafoPonderado<T> {

    private static class NodoVertice<T> {
        T valor;
        ListaAristas<T> aristas;
        NodoVertice<T> siguiente;

        NodoVertice(T valor) {
            this.valor = valor;
            this.aristas = new ListaAristas<>();
            this.siguiente = null;
        }
    }

    private NodoVertice<T> cabeza;
    private int numVertices;
    private int numAristas;

    public GrafoPonderado() {
        this.cabeza = null;
        this.numVertices = 0;
        this.numAristas = 0;
    }

    public void agregarVertice(T valor) {
        if (buscarNodoVertice(valor) == null) {
            NodoVertice<T> nuevo = new NodoVertice<>(valor);
            nuevo.siguiente = cabeza;
            cabeza = nuevo;
            numVertices++;
        }
    }

    public void agregarArista(T origen, T destino, double peso) {
        agregarVertice(origen);
        agregarVertice(destino);
        NodoVertice<T> nOrigen = buscarNodoVertice(origen);
        NodoVertice<T> nDestino = buscarNodoVertice(destino);
        nOrigen.aristas.agregar(destino, peso);
        nDestino.aristas.agregar(origen, peso);
        numAristas++;
    }

    public boolean existeVertice(T valor) {
        return buscarNodoVertice(valor) != null;
    }

    public ListaAristas<T> getVecinos(T valor) {
        NodoVertice<T> nodo = buscarNodoVertice(valor);
        if (nodo == null) return new ListaAristas<>();
        return nodo.aristas;
    }

    public int getNumVertices() {
        return numVertices;
    }

    public int getNumAristas() {
        return numAristas;
    }

    private NodoVertice<T> buscarNodoVertice(T valor) {
        NodoVertice<T> actual = cabeza;
        while (actual != null) {
            if (actual.valor.equals(valor)) return actual;
            actual = actual.siguiente;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("GrafoPonderado {\n");
        NodoVertice<T> actual = cabeza;
        while (actual != null) {
            sb.append("  ").append(actual.valor).append(": ");
            ListaAristas.Nodo<T> arista = actual.aristas.getCabeza();
            while (arista != null) {
                sb.append("-(" + arista.peso + ")-> " + arista.destino + " ");
                arista = arista.siguiente;
            }
            sb.append("\n");
            actual = actual.siguiente;
        }
        sb.append("}");
        return sb.toString();
    }
}