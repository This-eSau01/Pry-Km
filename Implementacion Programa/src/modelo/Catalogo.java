package modelo;

import estructuras.ListaDobleEnlazada;
import estructuras.Videojuego;
import estructuras.ArbolAVL;
import main.CargadorCSV;

public class Catalogo {

    private ListaDobleEnlazada<Videojuego> videojuegos;

    // Árboles AVL para las búsquedas
    private ArbolAVL<String> arbolNombre;
    private ArbolAVL<String> arbolGenero;
    private ArbolAVL<String> arbolDesarrollador;
    private ArbolAVL<String> arbolEditor;

    public Catalogo() {
        this.videojuegos = new ListaDobleEnlazada<>();

        arbolNombre = new ArbolAVL<>();
        arbolGenero = new ArbolAVL<>();
        arbolDesarrollador = new ArbolAVL<>();
        arbolEditor = new ArbolAVL<>();
    }

    public Catalogo(String rutaCSV) {

        this.videojuegos = CargadorCSV.cargar(rutaCSV);

        arbolNombre = new ArbolAVL<>();
        arbolGenero = new ArbolAVL<>();
        arbolDesarrollador = new ArbolAVL<>();
        arbolEditor = new ArbolAVL<>();

        reconstruirAVL();
    }

    public boolean registrar(Videojuego juego) {

        if (juego == null || juego.getId() == null) {
            return false;
        }

        if (buscarPorId(juego.getId()) != null) {
            return false; // id duplicado
        }

        videojuegos.insertar(juego);

        if (juego.getNombre() != null)
            arbolNombre.insertar(juego.getNombre());

        if (juego.getGenero() != null)
            arbolGenero.insertar(juego.getGenero());

        if (juego.getDesarrollador() != null)
            arbolDesarrollador.insertar(juego.getDesarrollador());

        if (juego.getEditor() != null)
            arbolEditor.insertar(juego.getEditor());

        return true;
    }

    public boolean modificar(String id, Videojuego juegoActualizado) {

        if (id == null || juegoActualizado == null) {
            return false;
        }

        int posicion = buscarPosicionPorId(id);

        if (posicion == -1) {
            return false;
        }

        boolean modificado = videojuegos.modificarEnPosicion(posicion, juegoActualizado);

        if (modificado) {
            reconstruirAVL();
        }

        return modificado;
    }

    public boolean eliminar(String id) {

        if (id == null)
            return false;

        int posicion = buscarPosicionPorId(id);

        if (posicion == -1) {
            return false;
        }

        boolean eliminado = videojuegos.eliminarEnPosicion(posicion);

        if (eliminado) {
            reconstruirAVL();
        }

        return eliminado;
    }

    public ListaDobleEnlazada<Videojuego> listarTodos() {
        return videojuegos;
    }

    public Videojuego buscarPorId(String id) {

        if (id == null)
            return null;

        for (Videojuego v : videojuegos) {

            if (v.getId() != null && v.getId().equals(id)) {
                return v;
            }
        }

        return null;
    }

    public boolean buscarNombre(String nombre) {
        return arbolNombre.buscar(nombre);
    }

    public boolean buscarGenero(String genero) {
        return arbolGenero.buscar(genero);
    }

    public boolean buscarDesarrollador(String desarrollador) {
        return arbolDesarrollador.buscar(desarrollador);
    }

    public boolean buscarEditor(String editor) {
        return arbolEditor.buscar(editor);
    }

    public int totalVideojuegos() {
        return videojuegos.size();
    }

    public boolean estaVacio() {
        return videojuegos.estaVacia();
    }

    private int buscarPosicionPorId(String id) {

        int posicion = 0;

        for (Videojuego v : videojuegos) {

            if (v.getId() != null && v.getId().equals(id)) {
                return posicion;
            }

            posicion++;
        }

        return -1;
    }

    private void reconstruirAVL() {

        arbolNombre = new ArbolAVL<>();
        arbolGenero = new ArbolAVL<>();
        arbolDesarrollador = new ArbolAVL<>();
        arbolEditor = new ArbolAVL<>();

        for (Videojuego v : videojuegos) {

            if (v.getNombre() != null)
                arbolNombre.insertar(v.getNombre());

            if (v.getGenero() != null)
                arbolGenero.insertar(v.getGenero());

            if (v.getDesarrollador() != null)
                arbolDesarrollador.insertar(v.getDesarrollador());

            if (v.getEditor() != null)
                arbolEditor.insertar(v.getEditor());
        }
    }
}