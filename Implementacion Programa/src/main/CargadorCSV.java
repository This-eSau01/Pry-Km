package main;

import estructuras.ListaDobleEnlazada;
import estructuras.Videojuego;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class CargadorCSV {

    private static final int COLUMNAS_ESPERADAS = 13;

    public static ListaDobleEnlazada<Videojuego> cargar(String ruta) {
        ListaDobleEnlazada<Videojuego> lista = new ListaDobleEnlazada<>();
        int lineasOk = 0;
        int lineasError = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {

            String encabezado = br.readLine();
            if (encabezado == null) {
                return lista;
            }

            String linea;
            int numeroLinea = 1;

            while ((linea = br.readLine()) != null) {
                numeroLinea++;

                if (linea.trim().isEmpty()) {
                    continue; 
                }

                Videojuego juego = parsearLinea(linea, numeroLinea);
                if (juego != null) {
                    lista.insertar(juego);
                    lineasOk++;
                } else {
                    lineasError++;
                }
            }

        } catch (IOException e) {
            System.out.println("Error al abrir/leer el archivo CSV: " + e.getMessage());
        }

        System.out.println("Carga de CSV finalizada: " + lineasOk + " videojuegos cargados, "
                + lineasError + " filas descartadas por errores de formato.");

        return lista;
    }

    private static Videojuego parsearLinea(String linea, int numeroLinea) {
        String[] d = linea.split(",");

        if (d.length < COLUMNAS_ESPERADAS) {
            System.out.println("Línea " + numeroLinea + " descartada: se esperaban "
                    + COLUMNAS_ESPERADAS + " columnas y se encontraron " + d.length + ".");
            return null;
        }

        try {
            return new Videojuego(
                    d[0].trim(),
                    d[1].trim(),
                    d[2].trim(),
                    d[3].trim(),
                    Double.parseDouble(d[4].trim()),
                    Long.parseLong(d[5].trim()),
                    d[6].trim(),
                    d[7].trim(),
                    d[8].trim(),
                    Double.parseDouble(d[9].trim()),
                    d[10].trim(),
                    d[11].trim(),
                    Double.parseDouble(d[12].trim())
            );
        } catch (NumberFormatException e) {
            System.out.println("Línea " + numeroLinea + " descartada: valor numérico inválido ("
                    + e.getMessage() + ").");
            return null;
        }
    }
}