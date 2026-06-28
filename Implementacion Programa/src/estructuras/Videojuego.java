package estructuras;

public class Videojuego {

    private String id;
    private String nombre;
    private String genero;
    private String plataforma;
    private double puntuacion;
    private long   descargas;
    private String fechaLanzamiento;
    private String desarrollador;
    private String editor;
    private double precio;
    private String modoJuego;
    private String multijugador;
    private double tamanoGb;

    public Videojuego(String id, String nombre, String genero, String plataforma,
                      double puntuacion, long descargas, String fechaLanzamiento,
                      String desarrollador, String editor, double precio,
                      String modoJuego, String multijugador, double tamanoGb) {
        this.id               = id;
        this.nombre           = nombre;
        this.genero           = genero;
        this.plataforma       = plataforma;
        this.puntuacion       = puntuacion;
        this.descargas        = descargas;
        this.fechaLanzamiento = fechaLanzamiento;
        this.desarrollador    = desarrollador;
        this.editor           = editor;
        this.precio           = precio;
        this.modoJuego        = modoJuego;
        this.multijugador     = multijugador;
        this.tamanoGb         = tamanoGb;
    }

    public String getId()               { return id; }
    public String getNombre()           { return nombre; }
    public String getGenero()           { return genero; }
    public String getPlataforma()       { return plataforma; }
    public double getPuntuacion()       { return puntuacion; }
    public long   getDescargas()        { return descargas; }
    public String getFechaLanzamiento() { return fechaLanzamiento; }
    public String getDesarrollador()    { return desarrollador; }
    public String getEditor()           { return editor; }
    public double getPrecio()           { return precio; }
    public String getModoJuego()        { return modoJuego; }
    public String getMultijugador()     { return multijugador; }
    public double getTamanoGb()         { return tamanoGb; }

    public void setId(String id)                      { this.id = id; }
    public void setNombre(String nombre)              { this.nombre = nombre; }
    public void setGenero(String genero)              { this.genero = genero; }
    public void setPlataforma(String plataforma)      { this.plataforma = plataforma; }
    public void setPuntuacion(double puntuacion)      { this.puntuacion = puntuacion; }
    public void setDescargas(long descargas)          { this.descargas = descargas; }
    public void setFechaLanzamiento(String f)         { this.fechaLanzamiento = f; }
    public void setDesarrollador(String desarrollador){ this.desarrollador = desarrollador; }
    public void setEditor(String editor)              { this.editor = editor; }
    public void setPrecio(double precio)              { this.precio = precio; }
    public void setModoJuego(String modoJuego)        { this.modoJuego = modoJuego; }
    public void setMultijugador(String multijugador)  { this.multijugador = multijugador; }
    public void setTamanoGb(double tamanoGb)          { this.tamanoGb = tamanoGb; }

    @Override
    public String toString() {
        return "Videojuego{" +
                "id='"              + id               + '\'' +
                ", nombre='"        + nombre           + '\'' +
                ", genero='"        + genero           + '\'' +
                ", plataforma='"    + plataforma       + '\'' +
                ", puntuacion="     + puntuacion       +
                ", descargas="      + descargas        +
                ", fechaLanz='"     + fechaLanzamiento + '\'' +
                ", desarrollador='" + desarrollador    + '\'' +
                ", editor='"        + editor           + '\'' +
                ", precio="         + precio           +
                ", modoJuego='"     + modoJuego        + '\'' +
                ", multijugador='"  + multijugador     + '\'' +
                ", tamanoGb="       + tamanoGb         +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Videojuego)) return false;
        Videojuego otro = (Videojuego) obj;
        return id != null && id.equals(otro.id);
    }

    @Override
    public int hashCode() {
        return (id != null) ? id.hashCode() : 0;
    }

    public String toCSV() {
        return id + "," + nombre + "," + genero + "," + plataforma + "," +
               puntuacion + "," + descargas + "," + fechaLanzamiento + "," +
               desarrollador + "," + editor + "," + precio + "," +
               modoJuego + "," + multijugador + "," + tamanoGb;
    }
}