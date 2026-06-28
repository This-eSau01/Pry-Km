package estructuras;

public class ArbolAVL<T extends Comparable<T>> {

    private NodoAVL<T> raiz;

    public ArbolAVL() {
        raiz = null;
    }

    public NodoAVL<T> getRaiz() {
        return raiz;
    }

    private int altura(NodoAVL<T> nodo) {
        if (nodo == null)
            return 0;
        return nodo.altura;
    }

    private int balance(NodoAVL<T> nodo) {
        if (nodo == null)
            return 0;
        return altura(nodo.izquierdo) - altura(nodo.derecho);
    }

    private NodoAVL<T> rotarDerecha(NodoAVL<T> y) {

        NodoAVL<T> x = y.izquierdo;
        NodoAVL<T> t2 = x.derecho;

        x.derecho = y;
        y.izquierdo = t2;

        y.altura = Math.max(altura(y.izquierdo), altura(y.derecho)) + 1;
        x.altura = Math.max(altura(x.izquierdo), altura(x.derecho)) + 1;

        return x;
    }

    private NodoAVL<T> rotarIzquierda(NodoAVL<T> x) {

        NodoAVL<T> y = x.derecho;
        NodoAVL<T> t2 = y.izquierdo;

        y.izquierdo = x;
        x.derecho = t2;

        x.altura = Math.max(altura(x.izquierdo), altura(x.derecho)) + 1;
        y.altura = Math.max(altura(y.izquierdo), altura(y.derecho)) + 1;

        return y;
    }

    public void insertar(T dato) {
        raiz = insertar(raiz, dato);
    }

    private NodoAVL<T> insertar(NodoAVL<T> nodo, T dato) {

        if (nodo == null)
            return new NodoAVL<>(dato);

        int cmp = dato.compareTo(nodo.dato);

        if (cmp < 0)
            nodo.izquierdo = insertar(nodo.izquierdo, dato);
        else if (cmp > 0)
            nodo.derecho = insertar(nodo.derecho, dato);
        else
            return nodo;

        nodo.altura = 1 + Math.max(altura(nodo.izquierdo), altura(nodo.derecho));

        int balance = balance(nodo);

        if (balance > 1 && dato.compareTo(nodo.izquierdo.dato) < 0)
            return rotarDerecha(nodo);

        if (balance < -1 && dato.compareTo(nodo.derecho.dato) > 0)
            return rotarIzquierda(nodo);

        if (balance > 1 && dato.compareTo(nodo.izquierdo.dato) > 0) {
            nodo.izquierdo = rotarIzquierda(nodo.izquierdo);
            return rotarDerecha(nodo);
        }

        if (balance < -1 && dato.compareTo(nodo.derecho.dato) < 0) {
            nodo.derecho = rotarDerecha(nodo.derecho);
            return rotarIzquierda(nodo);
        }

        return nodo;
    }

    public boolean buscar(T dato) {
        return buscar(raiz, dato);
    }

    private boolean buscar(NodoAVL<T> nodo, T dato) {

        if (nodo == null)
            return false;

        int cmp = dato.compareTo(nodo.dato);

        if (cmp == 0)
            return true;

        if (cmp < 0)
            return buscar(nodo.izquierdo, dato);

        return buscar(nodo.derecho, dato);
    }
}