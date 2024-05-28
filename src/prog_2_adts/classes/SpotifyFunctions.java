package prog_2_adts.classes;

import java.util.List;
import java.util.Scanner;

public class SpotifyFunctions {
    private List<Cancion> canciones;


    public static List<Cancion> obtenerTop10(String pais, String fecha) {
        return null;
    }

    public void mostrarTop10() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el país:");
        String pais = scanner.nextLine();
        System.out.println("Ingrese la fecha (YYYY-MM-DD):");
        String fecha = scanner.nextLine();

        List<Cancion> top10 = SpotifyFunctions.obtenerTop10(pais, fecha);

        System.out.println("Top 10 canciones en " + pais + " el " + fecha + ":");
        for (Cancion cancion : top10) {
            System.out.println(cancion.getPosicion() + ". " + cancion.getTitulo() + " - " + cancion.getArtista());
        }
    }
}


