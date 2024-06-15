package prog_2_adts.classes;

import prog_2_adts.src.uy.edu.um.adt.BinaryTree2.InfoIncorrecta;
import prog_2_adts.src.uy.edu.um.adt.BinaryTree2.MyBinarySearchTreeImpl;
import prog_2_adts.src.uy.edu.um.adt.BinaryTree2.TreeNode;
import prog_2_adts.src.uy.edu.um.adt.hash.HashImpl;
import prog_2_adts.src.uy.edu.um.adt.hash.InformacionInvalida;
import prog_2_adts.src.uy.edu.um.adt.linkedlist.MyLinkedListImpl;
import prog_2_adts.src.uy.edu.um.adt.linkedlist.MyList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SpotifyFunctions {

    private HashImpl<LocalDate, HashImpl<String, MyBinarySearchTreeImpl<Integer, Song>>> spotify = new HashImpl<>(210); //hash master con tamano que me dio en test


    public void agregarDatos(String spotifyId, String name, MyList<Artist> artists, int dailyRank, int dailyMovement, int weeklyMovement, String country, LocalDate snapshotDate, int popularity, boolean isExplicit, int durationMs, String albumName, String albumReleaseDate, float danceability, float energy, int key, float loudness, int mode, float speechiness, float acousticness, float instrumentalness, float liveness, float valence, float tempo, int timeSignature) throws InfoIncorrecta, InformacionInvalida {
        int posicion1 = this.spotify.search(snapshotDate);
        if (posicion1 != -1) {
            HashImpl<String, MyBinarySearchTreeImpl<Integer, Song>> hash2 = this.spotify.getData(posicion1);
            int posicion2 = hash2.search(country);
            if(posicion2 != -1) {
                MyBinarySearchTreeImpl<Integer, Song> top50 = hash2.getData(posicion2);
                top50.add(dailyRank,new Song(spotifyId, name, artists, tempo, snapshotDate, dailyRank));
            }
            else{
                this.spotify.getData(posicion1).add(country,new MyBinarySearchTreeImpl<>(new TreeNode<>(dailyRank,new Song(spotifyId, name, artists, tempo, snapshotDate, dailyRank))));
            }
        }
        else {
            HashImpl<String, MyBinarySearchTreeImpl<Integer, Song>> hash2 = new HashImpl<>(75); // num de paises
            this.spotify.add(snapshotDate, hash2);
            hash2.add(country,new MyBinarySearchTreeImpl<>(new TreeNode<>(dailyRank,new Song(spotifyId, name, artists, tempo, snapshotDate, dailyRank))));
        }}


    public void processCVS() throws InformacionInvalida {
        String csvFile = "/Users/guadaluperial/Desktop/Spotify_canciones.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine(); //se saltea la primera linea
            String linea;

            while ((linea = br.readLine()) != null) {
                // Dividir la línea en campos utilizando '","' como delimitador
                String[] lineaSeparada = linea.split("\",\"");
                System.out.println(linea);

                if (lineaSeparada.length >= 24) {
                    String country = "general";
                    MyList<Artist> artistas = new MyLinkedListImpl<>();

                    String[] primeras2Celdas = lineaSeparada[0].split(",\"\"");
                    String[] artistasSeparados = lineaSeparada[1].split(",");

                    String spotifyId = primeras2Celdas[0].replaceAll("\"","");
                    String name = primeras2Celdas[1].replaceAll("\"","").trim();

                    for (int i = 0; i < artistasSeparados.length; i++) {
                        artistas.add(new Artist(artistasSeparados[i].replaceAll("\"","").trim()));
                    }

                    int dailyRank = Integer.parseInt(lineaSeparada[2].replaceAll("\"",""));
                    int dailyMovement = Integer.parseInt(lineaSeparada[3].replaceAll("\"",""));
                    int weeklyMovement = Integer.parseInt(lineaSeparada[4].replaceAll("\"",""));

                    if (!lineaSeparada[5].replaceAll("\"","").trim().isEmpty()) {
                        country = lineaSeparada[5].replaceAll("\"","").trim();
                    }

                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate snapshotDate = LocalDate.parse(lineaSeparada[6].replaceAll("\"",""), formato);

                    int popularity = Integer.parseInt(lineaSeparada[7].replaceAll("\"",""));
                    boolean isExplicit = Boolean.parseBoolean(lineaSeparada[8].replaceAll("\"",""));
                    int durationMs = Integer.parseInt(lineaSeparada[9].replaceAll("\"",""));
                    String albumName = lineaSeparada[10].replaceAll("\"","").trim();
                    String albumReleaseDate = lineaSeparada[11].replaceAll("\"","").trim();
                    float danceability = Float.parseFloat(lineaSeparada[12].replaceAll("\"",""));
                    float energy = Float.parseFloat(lineaSeparada[13].replaceAll("\"",""));
                    int key = Integer.parseInt(lineaSeparada[14].replaceAll("\"",""));
                    float loudness = Float.parseFloat(lineaSeparada[15].replaceAll("\"",""));
                    int mode = Integer.parseInt(lineaSeparada[16].replaceAll("\"",""));
                    float speechiness = Float.parseFloat(lineaSeparada[17].replaceAll("\"",""));
                    float acousticness = Float.parseFloat(lineaSeparada[18].replaceAll("\"",""));
                    float instrumentalness = Float.parseFloat(lineaSeparada[19].replaceAll("\"",""));
                    float liveness = Float.parseFloat(lineaSeparada[20].replaceAll("\"",""));
                    float valence = Float.parseFloat(lineaSeparada[21].replaceAll("\"",""));
                    float tempo = Float.parseFloat(lineaSeparada[22].replaceAll("\"",""));
                    int timeSignature = Integer.parseInt(lineaSeparada[23].replace(";", "").replaceAll("\"", ""));

                    this.agregarDatos(spotifyId, name, artistas, dailyRank, dailyMovement, weeklyMovement, country, snapshotDate, popularity, isExplicit, durationMs, albumName, albumReleaseDate, danceability, energy, key, loudness, mode, speechiness, acousticness, instrumentalness, liveness, valence, tempo, timeSignature);
                }

            }
        } catch (IOException | InfoIncorrecta e) {
            e.printStackTrace();
        }
    }

    public void top10PorPais(String pais, String fecha1) throws  InformacionInvalida {

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fecha = LocalDate.parse(fecha1, formato);

        int posicion1= this.spotify.search(fecha);
        if (posicion1==-1){
            System.out.println("La fecha "+fecha+" no esta registrada.");
        }
        else {
            HashImpl<String,MyBinarySearchTreeImpl<Integer,Song>> hash2= this.spotify.getData(posicion1);
            int posicion2= hash2.search(pais);
            if (posicion2==-1){
                System.out.println("El pais "+pais+" no esta registrado.");
            }
            else {
                System.out.println("El top 10 en "+pais+" el "+fecha1+" fue: ");
                MyBinarySearchTreeImpl<Integer, Song> arbol= hash2.getData(posicion2);
                List<Song> top50= arbol.inOrderV();
                for (int i=0; i<10;i++){
                    Song cancion= top50.get(i);
                    System.out.println(i+1 +") "+ cancion.getName() +" de ");
                    for(int j=0; j<cancion.getArtists().size(); j++){
                        System.out.println( cancion.getArtists().get(j).name);
                    }
                }
            }
        }
    }


}









