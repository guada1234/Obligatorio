package prog_2_adts.classes;

import org.junit.Test;
import prog_2_adts.src.uy.edu.um.adt.BinaryTree2.InfoIncorrecta;
import prog_2_adts.src.uy.edu.um.adt.hash.InformacionInvalida;
import prog_2_adts.src.uy.edu.um.adt.linkedlist.MyLinkedListImpl;
import prog_2_adts.src.uy.edu.um.adt.linkedlist.MyList;

import java.io.UnsupportedEncodingException;
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

        @Test
        public void testTop10PorPais_PaisYFechaValidos() throws InformacionInvalida, InfoIncorrecta, UnsupportedEncodingException {
            // Arrange
            SpotifyFunctions spotify = new SpotifyFunctions(); // Asumo que Spotify es la clase que contiene el método top10PorPais
            MyList<Artist> lista = new MyLinkedListImpl<>();
            Artist artista1 = new Artist("Taylor");
            lista.add(artista1);
            MyList<Artist> lista2 = new MyLinkedListImpl<>();
            Artist artista2 = new Artist("Ariana");
            lista2.add(artista2);
            MyList<Artist> lista3 = new MyLinkedListImpl<>();
            Artist artista3 = new Artist("Ariana");
            lista3.add(artista3);
            MyList<Artist> lista4 = new MyLinkedListImpl<>();
            Artist artista4 = new Artist("Ariana");
            lista4.add(artista4);
            MyList<Artist> lista5 = new MyLinkedListImpl<>();
            Artist artista5 = new Artist("Ariana");
            lista5.add(artista5);
            MyList<Artist> lista6 = new MyLinkedListImpl<>();
            Artist artista6 = new Artist("Ariana");
            lista6.add(artista6);
            MyList<Artist> lista7 = new MyLinkedListImpl<>();
            Artist artista7 = new Artist("Ariana");
            lista7.add(artista7);
            MyList<Artist> lista8 = new MyLinkedListImpl<>();
            Artist artista8 = new Artist("Ariana");
            lista8.add(artista8);
            MyList<Artist> lista9 = new MyLinkedListImpl<>();
            Artist artista9 = new Artist("Ariana");
            lista9.add(artista9);
            MyList<Artist> lista10 = new MyLinkedListImpl<>();
            Artist artista10 = new Artist("Ariana");
            lista10.add(artista10);
            spotify.addInfoHash("song1", "artist1", lista2, 1, 12,34,"general", LocalDate.parse("2023-10-10"), 12, false, 12,"album", "2023-10-9", 12,34,56,78,5,4,3,2,1,2,3,5);
            spotify.addInfoHash("song2", "artist2", lista, 2, 12,34,"general", LocalDate.parse("2023-10-10"), 12, false, 12,"album", "2023-10-9", 12,34,56,78,5,4,3,2,1,2,3,5);
            spotify.addInfoHash("song3", "artist3", lista3, 3, 12,34,"general", LocalDate.parse("2023-10-10"), 12, false, 12,"album", "2023-10-9", 12,34,56,78,5,4,3,2,1,2,3,5);
            spotify.addInfoHash("song4", "artist4", lista4, 4, 12,34,"general", LocalDate.parse("2023-10-10"), 12, false, 12,"album", "2023-10-9", 12,34,56,78,5,4,3,2,1,2,3,5);
            spotify.addInfoHash("song5", "artist5", lista5, 5, 12,34,"general", LocalDate.parse("2023-10-10"), 12, false, 12,"album", "2023-10-9", 12,34,56,78,5,4,3,2,1,2,3,5);
            spotify.addInfoHash("song6", "artist6", lista6, 7, 12,34,"general", LocalDate.parse("2023-10-10"), 12, false, 12,"album", "2023-10-9", 12,34,56,78,5,4,3,2,1,2,3,5);
            spotify.addInfoHash("song7", "artist7", lista7, 6, 12,34,"general", LocalDate.parse("2023-10-10"), 12, false, 12,"album", "2023-10-9", 12,34,56,78,5,4,3,2,1,2,3,5);
            spotify.addInfoHash("song8", "artist8", lista8, 8, 12,34,"general", LocalDate.parse("2023-10-10"), 12, false, 12,"album", "2023-10-9", 12,34,56,78,5,4,3,2,1,2,3,5);
            spotify.addInfoHash("song9", "artist9", lista9, 9, 12,34,"general", LocalDate.parse("2023-10-10"), 12, false, 12,"album", "2023-10-9", 12,34,56,78,5,4,3,2,1,2,3,5);
            spotify.addInfoHash("song10", "artist10", lista10, 10, 12,34,"general", LocalDate.parse("2023-10-10"), 12, false, 12,"album", "2023-10-9", 12,34,56,78,5,4,3,2,1,2,3,5);
            //preguntar como probar
        }


    }



