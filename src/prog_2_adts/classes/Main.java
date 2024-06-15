package prog_2_adts.classes;

import prog_2_adts.src.uy.edu.um.adt.binarytree.EmptyTree;
import prog_2_adts.src.uy.edu.um.adt.binarytree.InvalidKey;
import prog_2_adts.src.uy.edu.um.adt.hash.InformacionInvalida;
import prog_2_adts.src.uy.edu.um.adt.hash.hashDate;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws InformacionInvalida, EmptyTree, InvalidKey {
        SpotifyFunctions miClase = new SpotifyFunctions();
        miClase.processCVS();
        hashDate miHash = new hashDate(0,0);
        miHash = miClase.processCVS();
        miClase.Top10(miHash, "AR", LocalDate.ofEpochDay(2023-10-20));
    }
}
