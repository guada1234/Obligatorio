package prog_2_adts.classes;


import prog_2_adts.src.uy.edu.um.adt.hash.InformacionInvalida;

public class Main {
    public static void main(String[] args) throws InformacionInvalida {
        SpotifyFunctions miClase = new SpotifyFunctions();
        miClase.readCVS();
        miClase.top10PorPais();
     
    }
}
