package prog_2_adts.classes;

import prog_2_adts.src.uy.edu.um.adt.BinaryTree2.InfoIncorrecta;
import prog_2_adts.src.uy.edu.um.adt.BinaryTree2.MyBinarySearchTree;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SpotifyFunctions {

    private HashImpl<LocalDate, HashImpl<String, MyBinarySearchTreeImpl<Integer, Song>>> spotify = new HashImpl<>(210);
    private MyList<Artist> artistsList = new MyLinkedListImpl<Artist>();

    public HashImpl<LocalDate, HashImpl<String, MyBinarySearchTreeImpl<Integer, Song>>> getSpotify() {
        return spotify;
    }

    //public MyList<Song> songs = new MyLinkedListImpl<>();

    public void addInfoHash(String spotifyId, String name, MyList<Artist> artists, int dailyRank, String country, LocalDate snapshotDate, float tempo) throws InfoIncorrecta, InformacionInvalida {
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
            Song song = new Song(spotifyId, name, artists, tempo, snapshotDate, dailyRank);
            hash2.add(country, new MyBinarySearchTreeImpl<>(new TreeNode<>(dailyRank, song)));
            for (int i = 0; i < song.getArtists().size(); i++) {
                Artist artist = song.getArtists().get(i);
                if (!artistsList.contains(artist)) {
                    artistsList.add(artist);
                }

            }
        }
    }


    public void readCVS() throws InformacionInvalida {
        String csvFile = "/Users/guadaluperial/Desktop/Spotify_canciones.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine(); //se saltea la primera linea
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] lineaSeparada = linea.split("\",\"");
                //System.out.println(linea);

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
                    if (!lineaSeparada[5].replaceAll("\"", "").trim().isEmpty()) {
                        country = lineaSeparada[5].replaceAll("\"", "").trim();
                    }

                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String fechaArreglada = lineaSeparada[6].replaceAll("\"", "");
                    if (fechaArreglada.contains("\"")) {
                        System.out.println(fechaArreglada);
                    }
                    LocalDate snapshotDate = LocalDate.parse(fechaArreglada, formato);
                    float tempo = Float.parseFloat(lineaSeparada[22].replaceAll("\"", ""));
                    this.addInfoHash(spotifyId, name, artistas, dailyRank, country, snapshotDate, tempo);
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

        System.out.print("Ingrese la fecha: ");
        String date = scanner.nextLine();

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fecha = LocalDate.parse(date, formato);

        int datePosition = this.spotify.search(fecha);
        if (datePosition == -1) {
            System.out.println("La fecha " + fecha + " no está registrada.");
        } else {
            HashImpl<String, MyBinarySearchTreeImpl<Integer, Song>> hashCountry = this.spotify.getData(datePosition);
            int countryPosition = hashCountry.search(pais);
            if (countryPosition == -1) {
                System.out.println("El país " + pais + " no está registrado.");
            } else {
                System.out.println("El top 10 en " + pais + " el " + date + " fue: ");
                MyBinarySearchTreeImpl<Integer, Song> tree = hashCountry.getData(countryPosition);
                List<Song> top50 = tree.inOrderValue();
                for (int i = 0; i < 10; i++) {
                    Song song = top50.get(i);
                    System.out.println((i + 1) + ". " + song.getName() + " Artists: ");
                    for (int j = 0; j < song.getArtists().size(); j++) {
                        System.out.println(song.getArtists().get(j).name);
                    }
                }
            }
        }
    }

    public void top5PorDia() throws InformacionInvalida {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la fecha: ");
        String fecha1 = scanner.nextLine();
        scanner.close();

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fecha = LocalDate.parse(fecha1, formato);

        int datePosition = this.spotify.search(fecha);
        if (datePosition == -1) {
            System.out.println("La fecha " + fecha + " no está registrada.");
        } else {
            MyBinarySearchTreeImpl<Integer, String> tree = new MyBinarySearchTreeImpl<>();
            HashImpl<String, Integer> SongsHash = new HashImpl<>(1730);
            HashImpl<String, MyBinarySearchTreeImpl<Integer, Song>> countryHash = this.spotify.getData(datePosition);

            for (int i = 0; i < countryHash.getSize(); i++) {
                HashNode<String, MyBinarySearchTreeImpl<Integer, Song>> countryNode = countryHash.getNode(i);
                if (countryNode != null) {
                    List<Song> canciones = countryNode.getData().inOrderValue();
                    for (int j = 0; j < Math.min(50, canciones.size()); j++) {
                        String cancion = canciones.get(j).getName();
                        int posicion = SongsHash.search(cancion);
                        if (posicion == -1) {
                            SongsHash.add(cancion, 1);
                        } else {
                            HashNode<String, Integer> nodo = SongsHash.getNode(posicion);
                            nodo.setData(nodo.getData() + 1);
                        }
                    }
                }
            }

            for (int k = 0; k < SongsHash.getSize(); k++) {
                HashNode<String, Integer> node = SongsHash.getNode(k);
                if (node != null) {
                    tree.add(node.getData(), node.getValue());
                }
            }

            List<String> topCanciones = tree.inOrderValue();

            System.out.println("Las canciones más escuchadas el día " + fecha + " fueron:");
            int contador = 0;
            for (int z = 0; z < 5; z++) {
                contador++;
                System.out.println(contador + ". " + topCanciones.get(z));
            }
        }
    }


    public void ordenamiento(MyList<HashNode<String, Integer>> newList) {
        for (int i = 0; i < newList.size() - 1; i++) {
            for (int j = 0; j < newList.size() - 1 - i; j++) {
                if (newList.get(j).getData() > newList.get(j + 1).getData()) {
                    HashNode<String, Integer> aux1 = newList.get(j);
                    newList.get(j).setData(newList.get(j + 1).getData());
                    newList.get(j).setValue(newList.get(j + 1).getValue());
                    newList.get(j + 1).setData(aux1.getData());
                    newList.get(j + 1).setValue(aux1.getValue());
                }
            }
        }
    }

    private Artist getArtis(String name) {
        Artist newArtist = new Artist(name);
        if (artistsList.contains(newArtist)) {
            for (int i = 0; i < artistsList.size(); i++) {
                if (artistsList.get(i).equals(newArtist)) {
                    return artistsList.get(i);
                }
            }
        }
        return null;
    }

    public void countArtistAppearances() throws InformacionInvalida {
        int res = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el nombre del artista: ");
        String artistName = scanner.nextLine();

        System.out.print("Ingrese la fecha (yyyy-MM-dd): ");
        String fecha1 = scanner.nextLine();

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fecha = LocalDate.parse(fecha1, formato);

        int datePosition = this.spotify.search(fecha);
        if (datePosition == -1) {
            System.out.println("La fecha " + fecha + " no está registrada.");
        } else {
            HashImpl<String, MyBinarySearchTreeImpl<Integer, Song>> hashDate = this.spotify.getData(datePosition);
            Artist newArtist = getArtis(artistName);
            if (newArtist == null) {
                System.out.println("El artista " + artistName + " no está registrado.");
            } else {
                for (int i = 0; i < hashDate.getSize(); i++) {
                    HashNode<String, MyBinarySearchTreeImpl<Integer, Song>> nodoPais = hashDate.getNode(i);
                    if (nodoPais == null) {
                        continue;
                    }
                    List<Song> countryList = nodoPais.getData().inOrderValue();
                    for (Song song : countryList) {
                        MyList<Artist> artistsList = song.getArtists();
                        for (int l = 0; l < artistsList.size(); l++) {
                            if (artistsList.get(l).equals(newArtist)) {
                                res++;
                            }
                        }
                    }
                }
                System.out.println("El artista " + artistName + " aparece " + res + " veces en el día " + fecha1);
            }
        }
    }
    public void countSongsByTempoRangeAndDateRange() throws InformacionInvalida {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Ingrese la fecha de inicio (yyyy-MM-dd): ");
            String fechaInicioStr = scanner.nextLine();
            LocalDate fechaInicio = LocalDate.parse(fechaInicioStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            System.out.print("Ingrese la fecha de fin (yyyy-MM-dd): ");
            String fechaFinStr = scanner.nextLine();
            LocalDate fechaFin = LocalDate.parse(fechaFinStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            if (fechaInicio.isAfter(fechaFin)) {
                throw new InformacionInvalida("La fecha de inicio debe ser anterior o igual a la fecha de fin.");
            }

            System.out.print("Ingrese el tempo mínimo: ");
            int tempoMin = scanner.nextInt();
            System.out.print("Ingrese el tempo máximo: ");
            int tempoMax = scanner.nextInt();

            if (tempoMin > tempoMax) {
                throw new InformacionInvalida("El tempo mínimo no puede ser mayor que el tempo máximo.");
            }

            int count = 0;
            List<Song> countedSongs = new ArrayList<>(); // Lista para almacenar las canciones contadas

            LocalDate fechaActual = fechaInicio;
            while (!fechaActual.isAfter(fechaFin)) {
                int datePosition = this.spotify.search(fechaActual);
                if (datePosition != -1) {
                    HashImpl<String, MyBinarySearchTreeImpl<Integer, Song>> hashDate = this.spotify.getData(datePosition);
                    for (int i = 0; i < hashDate.getSize(); i++) {
                        HashNode<String, MyBinarySearchTreeImpl<Integer, Song>> nodoPais = hashDate.getNode(i);
                        if (nodoPais != null) {
                            List<Song> countryList = nodoPais.getData().inOrderValue();
                            for (int p =0; p< countryList.size(); p++){
                                float tempo = countryList.get(p).getTempo();
                                boolean alreadyCounted = false;
                                for (int h = 0; h<countedSongs.size() ; h++) {
                                    if (countedSongs.get(h).equals(countryList.get(p))) {
                                        alreadyCounted = true;
                                        break;
                                    }
                                }
                                if (tempo >= tempoMin && tempo <= tempoMax && !alreadyCounted) {
                                    count++;
                                    countedSongs.add(countryList.get(p)); // Agregar la canción a la lista de canciones contadas
                                }
                            }
                        }
                    }
                }
                fechaActual = fechaActual.plusDays(1); // Avanzar al siguiente día
            }

            System.out.println("Cantidad de canciones con tempo en el rango [" + tempoMin + ", " + tempoMax + "] para el período desde "
                    + fechaInicioStr + " hasta " + fechaFinStr + ": " + count);
        } finally {
            scanner.close();
        }
    }
    public void top7PorRango() throws InformacionInvalida {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la fecha de inicio (yyyy-MM-dd): ");
        String fechaInicio = scanner.nextLine();

        System.out.print("Ingrese la fecha de fin (yyyy-MM-dd): ");
        String fechaFin = scanner.nextLine();

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaI = LocalDate.parse(fechaInicio, formato);
        LocalDate fechaF = LocalDate.parse(fechaFin, formato);

        long diferenciaDias = ChronoUnit.DAYS.between(fechaI, fechaF);  // Contar los días entre fechas para iterar.

        HashImpl<String, Integer> hashFrecCanciones = new HashImpl<>(2500);
        MyBinarySearchTree<Integer, String> arbol = new MyBinarySearchTreeImpl<>();
        int contador = 1;

        for (int i = 0; i <= diferenciaDias; i++) {
            int posicion = spotify.search(fechaI.plusDays(i));
            if (posicion != -1) {
                HashImpl<String, MyBinarySearchTreeImpl<Integer, Song>> hash2 = spotify.getData(posicion);
                for (int j = 0; j < hash2.getSize(); j++) {
                    if (hash2.getNode(j) != null) {
                        List<Song> canciones = hash2.getData(j).inOrderValue();
                        for (int l = 0; l < Math.min(canciones.size(), 50); l++) { // Evitar out of bounds si hay menos de 50 canciones
                            Song cancion = canciones.get(l);
                            for (int k = 0; k < cancion.getArtists().size(); k++) {
                                String nombre = cancion.getArtists().get(k).getName();
                                int posicion2 = hashFrecCanciones.search(nombre);
                                if (posicion2 == -1) {
                                    hashFrecCanciones.add(nombre, 1);
                                } else {
                                    HashNode<String, Integer> nodo = hashFrecCanciones.getNode(posicion2);
                                    nodo.setData(nodo.getData() + 1);
                                }
                            }
                        }
                    }
                }
            }
        }

        for (int m = 0; m < hashFrecCanciones.getSize(); m++) {
            if (hashFrecCanciones.getNode(m) != null) {
                HashNode<String, Integer> nodo = hashFrecCanciones.getNode(m);
                arbol.add(nodo.getData(), nodo.getValue());
            }
        }

        List<String> topArtistas = arbol.inOrderValue();
        System.out.println("Los artistas más escuchados del " + fechaInicio + " al " + fechaFin + " fueron:");
        for (int n = topArtistas.size(); n > topArtistas.size() - 7; n--) {
            System.out.println(contador + ") " + topArtistas.get(n - 1));
            contador++;
        }
    }

}














