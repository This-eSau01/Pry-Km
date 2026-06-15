package main;

import estructuras.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class CargadorCSV {

    public static ListaDobleEnlazada<Videojuego> cargar(String ruta) {
        ListaDobleEnlazada<Videojuego> lista = new ListaDobleEnlazada<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(ruta));
            String linea = br.readLine();

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");

                Videojuego juego = new Videojuego(
                        Integer.parseInt(datos[0]),
                        datos[1],
                        datos[2],
                        datos[3],
                        Double.parseDouble(datos[4]),
                        Integer.parseInt(datos[5]),
                        datos[6],
                        datos[7],
                        datos[8],
                        Double.parseDouble(datos[9]),
                        datos[10],
                        datos[11],
                        Double.parseDouble(datos[12])
                );

                lista.insertar(juego);
            }

            br.close();

        } catch (Exception e) {
            System.out.println("Error al cargar CSV");
        }

        return lista;
    }
}