package estructuras;

public class SistemaRecomendaciones {

    private static class NodoJuego {
        Videojuego juego;
        NodoJuego siguiente;

        NodoJuego(Videojuego juego) {
            this.juego = juego;
            this.siguiente = null;
        }
    }

    private final GrafoPonderado<String> grafoSimilitud;
    private NodoJuego cabezaCatalogo;
    private int totalJuegos;

    public SistemaRecomendaciones() {
        this.grafoSimilitud  = new GrafoPonderado<>();
        this.cabezaCatalogo  = null;
        this.totalJuegos     = 0;
    }

    public void agregarJuego(Videojuego juego) {
        NodoJuego nuevo = new NodoJuego(juego);
        nuevo.siguiente = cabezaCatalogo;
        cabezaCatalogo  = nuevo;
        grafoSimilitud.agregarVertice(juego.getId());
        totalJuegos++;
    }

    public void construirRelaciones() {
        NodoJuego i = cabezaCatalogo;
        while (i != null) {
            NodoJuego j = i.siguiente;
            while (j != null) {
                double similitud = calcularSimilitud(i.juego, j.juego);
                if (similitud > 0.0) {
                    grafoSimilitud.agregarArista(i.juego.getId(), j.juego.getId(), similitud);
                }
                j = j.siguiente;
            }
            i = i.siguiente;
        }
    }

    public Videojuego[] recomendar(String idJuego, int top) {
        ListaAristas<String> vecinos = grafoSimilitud.getVecinos(idJuego);

        int tam = vecinos.getTamanio();
        String[] ids   = new String[tam];
        double[] pesos = new double[tam];

        ListaAristas.Nodo<String> actual = vecinos.getCabeza();
        int idx = 0;
        while (actual != null) {
            ids[idx]   = actual.destino;
            pesos[idx] = actual.peso;
            actual = actual.siguiente;
            idx++;
        }

        for (int i = 0; i < tam - 1; i++) {
            for (int j = 0; j < tam - i - 1; j++) {
                if (pesos[j] < pesos[j + 1]) {
                    double tmpP = pesos[j]; pesos[j] = pesos[j+1]; pesos[j+1] = tmpP;
                    String tmpI = ids[j];   ids[j]   = ids[j+1];   ids[j+1]   = tmpI;
                }
            }
        }

        int cantidad = top < tam ? top : tam;
        Videojuego[] resultado = new Videojuego[cantidad];
        for (int i = 0; i < cantidad; i++) {
            resultado[i] = buscarPorId(ids[i]);
        }
        return resultado;
    }

    private double calcularSimilitud(Videojuego a, Videojuego b) {
        double similitud = 0.0;
        if (a.getGenero().equalsIgnoreCase(b.getGenero()))           similitud += 0.5;
        if (a.getPlataforma().equalsIgnoreCase(b.getPlataforma()))   similitud += 0.3;
        if (a.getModoJuego().equalsIgnoreCase(b.getModoJuego()))     similitud += 0.2;
        return Math.round(similitud * 10.0) / 10.0;
    }

    private Videojuego buscarPorId(String id) {
        NodoJuego actual = cabezaCatalogo;
        while (actual != null) {
            if (actual.juego.getId().equals(id)) return actual.juego;
            actual = actual.siguiente;
        }
        return null;
    }

    public GrafoPonderado<String> getGrafoSimilitud() { return grafoSimilitud; }
    public int getCantidadJuegos()                    { return totalJuegos; }
    public int getCantidadRelaciones()                { return grafoSimilitud.getNumAristas(); }
}