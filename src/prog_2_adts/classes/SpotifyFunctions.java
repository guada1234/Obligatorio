package prog_2_adts.classes;

import prog_2_adts.src.uy.edu.um.adt.BinaryTree2.InfoIncorrecta;
import prog_2_adts.src.uy.edu.um.adt.BinaryTree2.MyBinarySearchTreeImpl;
import prog_2_adts.src.uy.edu.um.adt.BinaryTree2.TreeNode;
import prog_2_adts.src.uy.edu.um.adt.hash.HashImpl;
import prog_2_adts.src.uy.edu.um.adt.hash.HashNode;
import prog_2_adts.src.uy.edu.um.adt.hash.InformacionInvalida;
import prog_2_adts.src.uy.edu.um.adt.linkedlist.MyLinkedListImpl;
import prog_2_adts.src.uy.edu.um.adt.linkedlist.MyList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

public class SpotifyFunctions {

    private HashImpl<LocalDate, HashImpl<String, MyBinarySearchTreeImpl<Integer, Song>>> spotify = new HashImpl<>(210);

    public HashImpl<LocalDate, HashImpl<String, MyBinarySearchTreeImpl<Integer, Song>>> getSpotify() {
        return spotify;
    }

    public MyList<Song> songs = new MyLinkedListImpl<>();

    public void addInfoHash(String spotifyId, String name, MyList<Artist> artists, int dailyRank, int dailyMovement, int weeklyMovement, String country, LocalDate snapshotDate, int popularity, boolean isExplicit, int durationMs, String albumName, String albumReleaseDate, float danceability, float energy, int key, float loudness, int mode, float speechiness, float acousticness, float instrumentalness, float liveness, float valence, float tempo, int timeSignature) throws InfoIncorrecta, InformacionInvalida {
        int posicion1 = this.spotify.search(snapshotDate);
        if (posicion1 != -1) {
            HashImpl<String, MyBinarySearchTreeImpl<Integer, Song>> hash2 = this.spotify.getData(posicion1);
            int posicion2 = hash2.search(country);
            if (posicion2 != -1) {
                MyBinarySearchTreeImpl<Integer, Song> top50 = hash2.getData(posicion2);
                top50.add(dailyRank, new Song(spotifyId, name, artists, tempo, snapshotDate, dailyRank));
            } else {
                this.spotify.getData(posicion1).add(country, new MyBinarySearchTreeImpl<>(new TreeNode<>(dailyRank, new Song(spotifyId, name, artists, tempo, snapshotDate, dailyRank))));
            }
        } else {
            HashImpl<String, MyBinarySearchTreeImpl<Integer, Song>> hash2 = new HashImpl<>(75); // num de paises
            this.spotify.add(snapshotDate, hash2);
            hash2.add(country, new MyBinarySearchTreeImpl<>(new TreeNode<>(dailyRank, new Song(spotifyId, name, artists, tempo, snapshotDate, dailyRank))));
        }
    }


    public void readCVS() throws InformacionInvalida {
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

                    String spotifyId = primeras2Celdas[0].replaceAll("\"", "");
                    String name = primeras2Celdas[1].replaceAll("\"", "").trim();

                    for (int i = 0; i < artistasSeparados.length; i++) {
                        artistas.add(new Artist(artistasSeparados[i].replaceAll("\"", "").trim()));
                    }

                    int dailyRank = Integer.parseInt(lineaSeparada[2].replaceAll("\"", ""));
                    int dailyMovement = Integer.parseInt(lineaSeparada[3].replaceAll("\"", ""));
                    int weeklyMovement = Integer.parseInt(lineaSeparada[4].replaceAll("\"", ""));

                    if (!lineaSeparada[5].replaceAll("\"", "").trim().isEmpty()) {
                        country = lineaSeparada[5].replaceAll("\"", "").trim();
                    }

                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String fechaArreglada = lineaSeparada[6].replaceAll("\"", "");
                    if (fechaArreglada.contains("\"")) {
                        System.out.println(fechaArreglada);
                    }
                    LocalDate snapshotDate = LocalDate.parse(fechaArreglada, formato);
//                    snapshotDate = snapshotDate.

                    int popularity = Integer.parseInt(lineaSeparada[7].replaceAll("\"", ""));
                    boolean isExplicit = Boolean.parseBoolean(lineaSeparada[8].replaceAll("\"", ""));
                    int durationMs = Integer.parseInt(lineaSeparada[9].replaceAll("\"", ""));
                    String albumName = lineaSeparada[10].replaceAll("\"", "").trim();
                    String albumReleaseDate = lineaSeparada[11].replaceAll("\"", "").trim();
                    float danceability = Float.parseFloat(lineaSeparada[12].replaceAll("\"", ""));
                    float energy = Float.parseFloat(lineaSeparada[13].replaceAll("\"", ""));
                    int key = Integer.parseInt(lineaSeparada[14].replaceAll("\"", ""));
                    float loudness = Float.parseFloat(lineaSeparada[15].replaceAll("\"", ""));
                    int mode = Integer.parseInt(lineaSeparada[16].replaceAll("\"", ""));
                    float speechiness = Float.parseFloat(lineaSeparada[17].replaceAll("\"", ""));
                    float acousticness = Float.parseFloat(lineaSeparada[18].replaceAll("\"", ""));
                    float instrumentalness = Float.parseFloat(lineaSeparada[19].replaceAll("\"", ""));
                    float liveness = Float.parseFloat(lineaSeparada[20].replaceAll("\"", ""));
                    float valence = Float.parseFloat(lineaSeparada[21].replaceAll("\"", ""));
                    float tempo = Float.parseFloat(lineaSeparada[22].replaceAll("\"", ""));
                    int timeSignature = Integer.parseInt(lineaSeparada[23].replace(";", "").replaceAll("\"", ""));

                    this.addInfoHash(spotifyId, name, artistas, dailyRank, dailyMovement, weeklyMovement, country, snapshotDate, popularity, isExplicit, durationMs, albumName, albumReleaseDate, danceability, energy, key, loudness, mode, speechiness, acousticness, instrumentalness, liveness, valence, tempo, timeSignature);
                }

            }
        } catch (IOException | InfoIncorrecta e) {
            e.printStackTrace();
        }
    }


    public void top10PorPais() throws InformacionInvalida {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el país: ");
        String pais = scanner.nextLine();

        System.out.print("Ingrese la fecha (yyyy-MM-dd): ");
        String fecha1 = scanner.nextLine();

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fecha = LocalDate.parse(fecha1, formato);

        int posicion1 = this.spotify.search(fecha);
        if (posicion1 == -1) {
            System.out.println("La fecha " + fecha + " no está registrada.");
        } else {
            HashImpl<String, MyBinarySearchTreeImpl<Integer, Song>> hash2 = this.spotify.getData(posicion1);
            int posicion2 = hash2.search(pais);
            if (posicion2 == -1) {
                System.out.println("El país " + pais + " no está registrado.");
            } else {
                System.out.println("El top 10 en " + pais + " el " + fecha1 + " fue: ");
                MyBinarySearchTreeImpl<Integer, Song> arbol = hash2.getData(posicion2);
                List<Song> top50 = arbol.inOrderV();
                for (int i = 0; i < 10; i++) {
                    Song cancion = top50.get(i);
                    System.out.println((i + 1) + ") " + cancion.getName() + " de ");
                    for (int j = 0; j < cancion.getArtists().size(); j++) {
                        System.out.println(cancion.getArtists().get(j).name);
                    }
                }
            }
        }
    }

    public void top5Global() throws InformacionInvalida {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la fecha (yyyy-MM-dd): ");
        String fecha1 = scanner.nextLine();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fecha = LocalDate.parse(fecha1, formato);
        int posicion1 = this.spotify.search(fecha);
        if (posicion1 == -1) {
            System.out.println("La fecha " + fecha + " no está registrada.");
        } else {
            HashImpl<String, MyList<Integer>> dailySongsHash = new HashImpl<>(100);
            for (int i = 0; i < this.spotify.getData(posicion1).getList().length; i++) { //recorremos paises con la i
                HashNode<String, MyBinarySearchTreeImpl<Integer, Song>> nodoPais = this.spotify.getData(posicion1).getList()[i]; //SpotifyId como Key
                for (int j = 0; j < nodoPais.getData().inOrderV().size(); j++) { //con la j recorremos canciones
                    String spotifyId = this.spotify.getData(posicion1).getData(i).find(j).getSpotifyId();
                    int hashCode = spotifyId.hashCode();
                    int position = (Math.abs(hashCode)) / dailySongsHash.getSize();
                    if (dailySongsHash.getData(position) == null) {
                        MyList<Integer> newList = new MyLinkedListImpl<>();
                        newList.add(1);
                        dailySongsHash.add(spotifyId, newList);
                    } else {
                        if (dailySongsHash.getNode(position).getValue().equals(spotifyId)) {
                            dailySongsHash.getNode(position).getData().add(1);
                        } else {
                            int contPosiciones = 0;
                            while (!dailySongsHash.getNode(position).getValue().equals(spotifyId)) {
                                position++;
                                contPosiciones++;
                                if (contPosiciones == dailySongsHash.getList().length) {
                                    break;
                                }
                                if (position == dailySongsHash.getSize()) {
                                    contPosiciones = 0;
                                }
                            }
                            if (dailySongsHash.getNode(position).getValue().equals(spotifyId)) {
                                dailySongsHash.getNode(position).getData().add(1);
                            }
                        }
                    }
                }
            }
            MyList<HashNode<String, Integer>> newList = new MyLinkedListImpl<>();
            for (int i = 0; i < dailySongsHash.getSize(); i++) {
                int contador = dailySongsHash.getNode(i).getData().size();
                HashNode<String, Integer> newNode = new HashNode<>(dailySongsHash.getNode(i).getValue(), contador);
                newList.add(newNode);
            }
            ordenamiento(newList);
            MyList<String> res = new MyLinkedListImpl<>();
            for (int i = 0; i < 5; i++) {
                res.add(newList.get(i).getValue());
            }
            System.out.println("El top 5 global en  " + fecha+ " fue: ");
            for (int i = 0; i < res.size(); i++) {
                System.out.println(res.get(i));
            }







            }
        }


    public void ordenamiento(MyList<HashNode<String, Integer>> newList){
        for (int i = 0; i < newList.size() -1; i++) {
            for (int j = 0; j < newList.size() -1 -i; j++) {
                if(newList.get(j).getData() >newList.get(j+1).getData()){
                    HashNode<String, Integer> aux1 = newList.get(j);
                    newList.get(j).setData(newList.get(j+1).getData());
                    newList.get(j).setValue(newList.get(j+1).getValue());
                    newList.get(j+1).setData(aux1.getData());
                    newList.get(j+1).setValue(aux1.getValue());
                }
            }
        }
    }
















    public void top7PorRango(String fechaInicio, String fechaFin) throws InformacionInvalida {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaI = LocalDate.parse(fechaInicio, formato);
        LocalDate fechaF = LocalDate.parse(fechaFin, formato);
        int posicion= 0;

        long diferenciaDias = ChronoUnit.DAYS.between(fechaI, fechaF);  //cuento los dias entre fechas xq voy a iterar.

        HashImpl<String,Integer> hashFrecCanciones= new HashImpl<>(2500);
        MyBinarySearchTreeImpl<Integer,String> arbol= new MyBinarySearchTreeImpl<>();
        int contador=1;

        for (int i=0; i<diferenciaDias; i++){
            posicion= spotify.search(fechaI.plusDays(i));
            HashImpl<String,MyBinarySearchTreeImpl<Integer,Song>> hash2=spotify.getData(posicion);
            for (int j=0; j<hash2.getSize(); j++){
                if(hash2.getNode(j)!=null){
                    List<Song> canciones= hash2.getData(j).inOrderV();
                    for(int l=0; l<50;l++){
                        Song cancion= canciones.get(l);
                        for(int k=0; k<cancion.getArtists().size(); k++){
                            String nombre=cancion.getArtists().get(k).getName();
                            int posicion2=hashFrecCanciones.search(nombre);
                            if(posicion2 == -1){
                                hashFrecCanciones.add(nombre,1);
                            }
                            else{
                                HashNode<String,Integer> nodo=hashFrecCanciones.getNode(posicion2);
                                nodo.setData(nodo.getData()+1);
                            }
                        }
                    }
                }
            }
        }
        for(int m=0; m<hashFrecCanciones.getSize(); m++){
            if(hashFrecCanciones.getNode(m)!=null){
                HashNode<String,Integer> nodo= hashFrecCanciones.getNode(m);
                arbol.add(nodo.getData(),nodo.getValue());
            }
        }
        List<String> topArtistas= arbol.inOrderV();
        System.out.println("Los artistas mas esuchados del "+fechaInicio+" al "+ fechaFin+" fueron:");
        for (int n = topArtistas.size(); n>topArtistas.size()-7 ;n--){
            System.out.println(contador+") "+topArtistas.get(n-1));
            contador++;
        }
    }

}














