package estructuras;

public class ArbolAVL<T extends Comparable<T>> {

    private NodoAVL<T> raiz;

    public ArbolAVL() {

        raiz = null;

    }

    public NodoAVL<T> getRaiz() {

        return raiz;

    }

}