package estructuras;

public class TablaHashEncadenada<K,V> {
    private EntradaHash<K,V>[] tabla;

    @SuppressWarnings("unchecked")
    public TablaHashEncadenada(int capacidad){
        tabla = new EntradaHash[capacidad];
    }

    private int hash(K clave){
        return Math.abs(clave.hashCode()) % tabla.length;
    }

    public void insertar(K clave, V valor){
        int i = hash(clave);
        EntradaHash<K,V> nuevo = new EntradaHash<>(clave, valor);
        nuevo.setSiguiente(tabla[i]);
        tabla[i] = nuevo;
    }

    public V buscar(K clave){
        int i = hash(clave);
        EntradaHash<K,V> actual = tabla[i];

        while(actual != null){
            if(actual.getClave().equals(clave)){
                return actual.getValor();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
}