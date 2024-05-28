package prog_2_adts.classes;

import java.util.List;
import java.util.Scanner;

public class SpotifyMenu {
    public SpotifyMenu(String filePath) {

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filePath = "/Users/guadaluperial/Desktop/Spotify_canciones.csv"; // Ruta del archivo CSV

        // Cargar datos desde el archivo CSV
       // CSVReader dataset = new CSVReader (filePath);
        while (true) {
            System.out.println("Menú:");
            System.out.println("0. Salir");
            System.out.println("1. Top 10 canciones en un país en un día dado.");
            System.out.println("2. Top 5 canciones que aparecen en más top 50 en un día dado.");
            System.out.println("3. Top 7 artistas que más aparecen en los top 50 para un rango de fechas dado.");
            System.out.println("4. Cantidad de veces que aparece un artista específico en un top 50 en una fecha dada.");
            System.out.println("5. Cantidad de canciones con un tempo en un rango específico para un rango específico de fechas.");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    //
                    break;
                case 2:
                    //
                    break;
                case 3:
                    //
                    break;
                case 4:
                    //
                    break;
                case 5:
                    //
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    return;
                default:
                    System.out.println("Opción inválida. Por favor, ingrese una opción válida.");
            }SpotifyMenu menu = new SpotifyMenu(filePath);
            menu.mostrarTop10();
        }

    }

    public void mostrarTop10() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el país:");
        String pais = scanner.nextLine();
        System.out.println("Ingrese la fecha (YYYY-MM-DD):");
        String fecha = scanner.nextLine();

        List<Cancion> top10 = SpotifyData.obtenerTop10(pais, fecha);

        System.out.println("Top 10 canciones en " + pais + " el " + fecha + ":");
        for (Cancion cancion : top10) {
            System.out.println(cancion.getPosicion() + ". " + cancion.getTitulo() + " - " + cancion.getArtista());
        }
    }

}




