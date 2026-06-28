package estructuras;

public class NodoAVL<T> {

    T dato;
    NodoAVL<T> izquierdo;
    NodoAVL<T> derecho;
    int altura;

    public NodoAVL(T dato) {
        this.dato = dato;
        this.altura = 1;
    }

    public T getDato() {
        return dato;
    }

    public NodoAVL<T> getIzquierdo() {
        return izquierdo;
    }

    public NodoAVL<T> getDerecho() {
        return derecho;
    }

    public int getAltura() {
        return altura;
    }

    public void setIzquierdo(NodoAVL<T> izquierdo) {
        this.izquierdo = izquierdo;
    }

    public void setDerecho(NodoAVL<T> derecho) {
        this.derecho = derecho;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
}