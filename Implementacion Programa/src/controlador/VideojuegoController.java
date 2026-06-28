package controlador;

import estructuras.ListaDobleEnlazada;
import estructuras.Videojuego;
import modelo.Catalogo;

//Controlador VideoJuego
public class VideojuegoController {

    private final Catalogo catalogo;

    public VideojuegoController(Catalogo catalogo) {
        this.catalogo = catalogo;
    }

    public VideojuegoController(String rutaCSV) {
        this.catalogo = new Catalogo(rutaCSV);
    }

    public boolean registrarVideojuego(Videojuego juego) {
        return catalogo.registrar(juego);
    }

    public boolean modificarVideojuego(String id, Videojuego datosNuevos) {
        return catalogo.modificar(id, datosNuevos);
    }

    public boolean eliminarVideojuego(String id) {
        return catalogo.eliminar(id);
    }

    public Object[][] obtenerFilasParaTabla() {

        ListaDobleEnlazada<Videojuego> lista = catalogo.listarTodos();
        Object[][] filas = new Object[catalogo.totalVideojuegos()][13];

        int i = 0;

        for (Videojuego v : lista) {

            filas[i][0] = v.getId();
            filas[i][1] = v.getNombre();
            filas[i][2] = v.getGenero();
            filas[i][3] = v.getPlataforma();
            filas[i][4] = v.getPuntuacion();
            filas[i][5] = v.getDescargas();
            filas[i][6] = v.getFechaLanzamiento();
            filas[i][7] = v.getDesarrollador();
            filas[i][8] = v.getEditor();
            filas[i][9] = v.getPrecio();
            filas[i][10] = v.getModoJuego();
            filas[i][11] = v.getMultijugador();
            filas[i][12] = v.getTamanoGb();

            i++;
        }

        return filas;
    }

    public static String[] columnasTabla() {
        return new String[]{
            "ID", "Nombre", "Género", "Plataforma", "Puntuación", "Descargas",
            "Fecha Lanzamiento", "Desarrollador", "Editor", "Precio",
            "Modo de Juego", "Multijugador", "Tamaño (GB)"
        };
    }

    public Videojuego buscarPorId(String id) {
        return catalogo.buscarPorId(id);
    }

    public boolean buscarNombre(String nombre) {
        return catalogo.buscarNombre(nombre);
    }

    public boolean buscarGenero(String genero) {
        return catalogo.buscarGenero(genero);
    }

    public boolean buscarDesarrollador(String desarrollador) {
        return catalogo.buscarDesarrollador(desarrollador);
    }

    public boolean buscarEditor(String editor) {
        return catalogo.buscarEditor(editor);
    }

    public int totalVideojuegos() {
        return catalogo.totalVideojuegos();
    }

    public Catalogo getCatalogo() {
        return catalogo;
    }
}