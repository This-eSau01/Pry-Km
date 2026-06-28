package modelo;

import estructuras.ListaDobleEnlazada;
import estructuras.Videojuego;
import main.CargadorCSV;


public class Catalogo {

    private ListaDobleEnlazada<Videojuego> videojuegos;

    public Catalogo() {
        this.videojuegos = new ListaDobleEnlazada<>();
    }

    public Catalogo(String rutaCSV) {
        this.videojuegos = CargadorCSV.cargar(rutaCSV);
    }

    public boolean registrar(Videojuego juego) {
        if (juego == null || juego.getId() == null) {
            return false;
        }
        if (buscarPorId(juego.getId()) != null) {
            return false; // id duplicado
        }
        videojuegos.insertar(juego);
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
        return videojuegos.modificarEnPosicion(posicion, juegoActualizado);
    }

    public boolean eliminar(String id) {
        if (id == null) return false;
        int posicion = buscarPosicionPorId(id);
        if (posicion == -1) {
            return false;
        }
        return videojuegos.eliminarEnPosicion(posicion);
    }

    public ListaDobleEnlazada<Videojuego> listarTodos() {
        return videojuegos;
    }

    public Videojuego buscarPorId(String id) {
        if (id == null) return null;
        for (Videojuego v : videojuegos) {
            if (v.getId() != null && v.getId().equals(id)) {
                return v;
            }
        }
        return null;
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
}