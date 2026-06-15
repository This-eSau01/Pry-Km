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

}