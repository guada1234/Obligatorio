package prog_2_adts.classes;

import prog_2_adts.src.uy.edu.um.adt.hash.HashImpl;
import prog_2_adts.src.uy.edu.um.adt.linkedlist.MyLinkedListImpl;
import prog_2_adts.src.uy.edu.um.adt.linkedlist.MyList;
import java.time.LocalDate;
import java.util.Scanner;

public class SpotifyFunctions {
    private MyList<Cancion> canciones = new MyLinkedListImpl<>();

    public void mostrarTop10() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el país:");
        String pais = scanner.nextLine();
        System.out.println("Ingrese la fecha (YYYY-MM-DD):");
        LocalDate fecha = LocalDate.parse(scanner.nextLine());
        MyList<Cancion> cancionesTop10 = new MyLinkedListImpl();
        cancionesTop10 = obtenerTop10(pais, fecha);

        System.out.println("Top 10 canciones en " + pais + " el " + fecha + ":");
        for (int i = 0; i<cancionesTop10.size(); i++) {
            System.out.println(cancionesTop10.get(i).getPosicion() + ". " + cancionesTop10.get(i).getTitulo() + " - " + cancionesTop10.get(i).getArtista());
        }
    }
    public HashImpl<LocalDate,String> obtenerTop10(String pais, LocalDate fecha){
        //tenemos que sacar los datos del hash grande y lo llamamos hashData
        HashImpl<LocalDate, String> hashTop10PaisDia = new HashImpl();
        for(int i =0;i<hashData.lenght;i++){
            if(hashData[i]!=null){
                if(hashData[i].getkey().equals(fecha) && hashData[i].getpais().equals(pais)){
                    hashTop10PaisDia.put(fecha, pais);
                }
            }
        }
        return hashTop10PaisDia;
    }
}


