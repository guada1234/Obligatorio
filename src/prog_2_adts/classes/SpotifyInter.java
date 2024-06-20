package prog_2_adts.classes;

import prog_2_adts.src.uy.edu.um.adt.BinaryTree2.InfoIncorrecta;
import prog_2_adts.src.uy.edu.um.adt.hash.InformacionInvalida;
import prog_2_adts.src.uy.edu.um.adt.linkedlist.MyList;

import java.time.LocalDate;

public interface SpotifyInter {
    public void menu() throws InformacionInvalida;
    public void addInfoHash(String spotifyId, String name, MyList<Artist> artists, int dailyRank, String country, LocalDate snapshotDate, float tempo) throws InfoIncorrecta, InformacionInvalida;
    public void readCVS() throws InformacionInvalida;
    public void top10PorPais() throws InformacionInvalida;
    public void top5PorDia() throws InformacionInvalida;
    public void countArtistAppearances() throws InformacionInvalida;
    public void countSongsByTempoRangeAndDateRange() throws InformacionInvalida;
    public void top7PorRango() throws InformacionInvalida;

}
