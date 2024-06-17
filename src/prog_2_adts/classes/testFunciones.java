package prog_2_adts.classes;

import org.junit.Test;
import prog_2_adts.src.uy.edu.um.adt.BinaryTree2.InfoIncorrecta;
import prog_2_adts.src.uy.edu.um.adt.hash.InformacionInvalida;
import prog_2_adts.src.uy.edu.um.adt.linkedlist.MyLinkedListImpl;
import prog_2_adts.src.uy.edu.um.adt.linkedlist.MyList;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class testFunciones {
    @Test
    public void testAdd() throws InformacionInvalida, InfoIncorrecta {
        SpotifyFunctions miClase = new SpotifyFunctions();
        //HashImpl<LocalDate, HashImpl<String, MyBinarySearchTreeImpl<Integer, Song>>> spotify = new HashImpl<>(210);
        MyList<Artist> lista = new MyLinkedListImpl<>();
        Artist artista1 = new Artist("Taylor");
        lista.add(artista1);
        miClase.addInfoHash("AS1", "name1", lista, 12, 12,34,"general", LocalDate.parse("2023-10-10"), 12, false, 12,"album", "2023-10-9", 12,34,56,78,5,4,3,2,1,2,3,5);
        assertEquals(34, miClase.getSpotify().search(LocalDate.parse("2023-10-10")));
        assertEquals(-1, miClase.getSpotify().search(LocalDate.parse("2023-10-12")));
    }



}

