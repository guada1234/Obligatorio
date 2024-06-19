package prog_2_adts.classes;

import prog_2_adts.src.uy.edu.um.adt.BinaryTree2.InfoIncorrecta;
import prog_2_adts.src.uy.edu.um.adt.hash.InformacionInvalida;

import java.util.Scanner;

public class SpotifyMenu {
    public void menu() throws InfoIncorrecta, InformacionInvalida {
        Scanner scanner= new Scanner(System.in);
        System.out.print("Bienvenido a Spotify!\n ¿Que consulta desea realizar? \n 1) Top 10 canciones en un país en un día dado. \n 2) Top 5 canciones que aparecen en más top 50 en un día dado.\n 3) Top 7 artistas que más aparecen en los top 50 para un rango de fechas \n 4) Cantidad de tops 50 en los que aparece un artista en una fecha \n 5) Cantidad de canciones con un tempo en un rango específico para un rango de fechas. \n Consulta: ");
        int eleccion = Integer.parseInt(scanner.nextLine());
        SpotifyFunctions miClase = new SpotifyFunctions();
        if (eleccion==1){
            miClase.top10PorPais();
        }
        else if (eleccion==2){
            miClase.top5PorDia();
        }
        else if (eleccion==3){
            miClase.top7PorRango();
        }
        else if (eleccion==4){
            miClase.countArtistAppearances();
        }
        else if (eleccion==5){
            miClase.countSongsByTempoRangeAndDateRange();
        }
        else{
            System.out.println("Porfavor seleccione una opcion del 1-5");
            this.menu();
        }
        System.out.println("Desea realizar otra consulta? \n 1) Si \n 2) No \n Respuesta: ");
        int resp = Integer.parseInt(scanner.nextLine());
        if (resp==1){
            this.menu();
        }
        else if (resp==0){
            System.out.println("Hasta la proxima!");
        }
    }


}




