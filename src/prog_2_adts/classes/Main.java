package prog_2_adts.classes;


import prog_2_adts.src.uy.edu.um.adt.BinaryTree2.InfoIncorrecta;
import prog_2_adts.src.uy.edu.um.adt.hash.InformacionInvalida;

public class Main {
    public static void main(String[] args) throws InformacionInvalida, InfoIncorrecta {
        SpotifyFunctions miClase = new SpotifyFunctions();
        miClase.readCVS();
        //miClase.top5PorDia();
        miClase.menu();

    }
}
