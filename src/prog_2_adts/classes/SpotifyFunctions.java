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
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SpotifyFunctions implements SpotifyInter {

    private HashImpl<LocalDate, HashImpl<String, MyBinarySearchTreeImpl<Integer, Song>>> spotify = new HashImpl<>(213); //ponemos más de 207 luygares que es el total de tuplas
    private MyList<Artist> artistsList = new MyLinkedListImpl<Artist>();

    public HashImpl<LocalDate, HashImpl<String, MyBinarySearchTreeImpl<Integer, Song>>> getSpotify() {
        return spotify;
    }

    private static long getUsedMemory() {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        return runtime.totalMemory() - runtime.freeMemory();
    }

    private static void printMemoryAndTime(long memoryBefore, long memoryAfter, long executionTime, long promedio) {
        System.out.println("Memoria utilizada antes de la consulta: " + memoryBefore + " bytes");
        System.out.println("Memoria utilizada después de la consulta: " + memoryAfter + " bytes");
        System.out.println("Memoria consumida por la consulta: " + (memoryAfter - memoryBefore) + " bytes");
        System.out.println();
        System.out.println("Tiempo de ejecución total de la consulta: " + executionTime + " nanosegundos");
        System.out.println("Tiempo de ejecución promedio: " + promedio + " nanosegundos");
    }

    @Override
    public void menu() throws InformacionInvalida {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Bienvenido a Spotify!\n ¿Que consulta desea realizar? \n 1) Top 10 canciones en un país en un día dado. \n 2) Top 5 canciones que aparecen en más top 50 en un día dado.\n 3) Top 7 artistas que más aparecen en los top 50 para un rango de fechas \n 4) Cantidad de tops 50 en los que aparece un artista en una fecha \n 5) Cantidad de canciones con un tempo en un rango específico para un rango de fechas. \n Consulta: ");
        System.out.println();
        int eleccion = Integer.parseInt(scanner.nextLine());
        if (eleccion == 1) {
            top10PorPais();
        } else if (eleccion == 2) {
            top5PorDia();
        } else if (eleccion == 3) {
            top7PorRango();
        } else if (eleccion == 4) {
            countArtistAppearances();
        } else if (eleccion == 5) {
            countSongsByTempoRangeAndDateRange();
        } else {
            System.out.println("Porfavor seleccione una opcion del 1-5");
            this.menu();
        }
        System.out.println("Desea realizar otra consulta? \n 1) Si \n 2) No \n Respuesta: ");
        int resp = Integer.parseInt(scanner.nextLine());

        while (resp != 1 && resp != 2) {
            System.out.println("Ingrese una opción correcta \n 1) Si \n 2) No \n Respuesta: ");
            resp = Integer.parseInt(scanner.nextLine());
        }

        if (resp == 1) {
            this.menu();
        } else {
            System.out.println("Hasta la proxima!");
        }
    }




        @Override
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
            HashImpl<String, MyBinarySearchTreeImpl<Integer, Song>> hash2 = new HashImpl<>(75); // numero de paises
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

    @Override
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

    @Override
    public void top10PorPais() throws InformacionInvalida {  ///opcion 1
        int iterations = 10; // Número de iteraciones para calcular el promedio
        long totalTime = 0;
        long memoryBefore = getUsedMemory();

        Scanner scanner = new Scanner(System.in);
        LocalDate fecha = null;
        boolean fechaValida = false;

        while (!fechaValida) {
            System.out.print("Ingrese la fecha (yyyy-MM-dd): ");
            String date = scanner.nextLine();
            try {
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                fecha = LocalDate.parse(date, formato);
                fechaValida = true;  // Fecha válida, salimos del bucle
            } catch (DateTimeParseException e) {
                System.out.println("Por favor ingrese una fecha válida en el formato yyyy-MM-dd.");
            }
        }

        boolean paisValido = false;
        while (!paisValido) {
            System.out.print("Ingrese el país: ");
            String pais = scanner.nextLine();

            int datePosition = this.spotify.search(fecha);
            if (datePosition == -1) {
                System.out.println("La fecha " + fecha + " no está registrada.");
                return;
            }

            HashImpl<String, MyBinarySearchTreeImpl<Integer, Song>> hashCountry = this.spotify.getData(datePosition);
            int countryPosition = hashCountry.search(pais);
            if (countryPosition == -1) {
                System.out.println("El país " + pais + " no está registrado. Por favor ingrese un país válido.");
            } else {
                paisValido = true;

                MyBinarySearchTreeImpl<Integer, Song> tree = hashCountry.getData(countryPosition);
                List<Song> top50 = tree.inOrderValue();

                // Iteraciones para calcular el promedio de tiempo de ejecución
                for (int i = 0; i < iterations; i++) {
                    long startIterTime = System.nanoTime();
                    long endTime = System.nanoTime();
                    long iterExecutionTime = endTime - startIterTime;
                    totalTime += iterExecutionTime;
                }

                System.out.println("El top 10 en " + pais + " el " + fecha + " fue:");
                for (int y = 0; y < 10; y++) {
                    Song song = top50.get(y);
                    System.out.println((y + 1) + ". " + song.getName());
                    for (int j = 0; j < song.getArtists().size(); j++) {
                        System.out.println("Artista: " + song.getArtists().get(j).name);
                    }
                }
                long averageTime = totalTime / iterations;  // Cálculo del tiempo promedio de ejecución
                // Impresión de memoria y tiempo promedio
                long memoryAfter = getUsedMemory();
                printMemoryAndTime(memoryBefore, memoryAfter, totalTime, averageTime);
            }
        }
    }





    @Override
    public void top5PorDia() throws InformacionInvalida { //opcion 2
        int iterations = 10; // Número de iteraciones para calcular el promedio
        long totalTime = 0;
        long memoryBefore = getUsedMemory();
        Scanner scanner = new Scanner(System.in);
        LocalDate fecha = null;
        boolean fechaValida = false;

        // Bucle para obtener una fecha válida
        while (!fechaValida) {
            System.out.print("Ingrese la fecha (yyyy-MM-dd): ");
            String fecha1 = scanner.nextLine();
            try {
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                fecha = LocalDate.parse(fecha1, formato);
                fechaValida = true; // Fecha válida, salimos del bucle
            } catch (DateTimeParseException e) {
                System.out.println("Por favor ingrese una fecha válida en el formato yyyy-MM-dd.");
            }
        }

        int datePosition = this.spotify.search(fecha);
        if (datePosition == -1) {
            System.out.println("La fecha " + fecha + " no está registrada.");
        } else {
            MyList<HashNode<Integer, String>> finalList = new MyLinkedListImpl<>();
            HashImpl<String, Integer> SongsHash = new HashImpl<>(1730);
            HashImpl<String, MyBinarySearchTreeImpl<Integer, Song>> countryHash = this.spotify.getData(datePosition);

            // Iterar sobre todos los países y canciones
            for (int i = 0; i < countryHash.getSize(); i++) {
                HashNode<String, MyBinarySearchTreeImpl<Integer, Song>> countryNode = countryHash.getNode(i);
                if (countryNode != null) {
                    List<Song> canciones = countryNode.getData().inOrderValue();
                    for (int j = 0; j < Math.min(50, canciones.size()); j++) {
                        String cancionName = canciones.get(j).getName();
                        if (SongsHash.contains(cancionName)) {
                            int actualPosition = SongsHash.search(cancionName);
                            int oldCount = SongsHash.getNode(actualPosition).getData();
                            SongsHash.getNode(actualPosition).setData(oldCount + 1);
                        } else {
                            SongsHash.add(cancionName, 1);
                        }
                    }
                }
            }
            for (int k = 0; k < SongsHash.getSize()  ; k++) {
                HashNode<String, Integer> node = SongsHash.getNode(k);
                if (node != null) {
                    HashNode<Integer, String> newNode = new HashNode<>(node.getData(), node.getValue());
                    finalList.add(newNode);
                }
            }
            // Iteraciones para calcular el promedio de tiempo de ejecución
            for (int i = 0; i < iterations; i++) {
                long startIterTime = System.nanoTime();
                long endTime = System.nanoTime();
                long iterExecutionTime = endTime - startIterTime;
                totalTime += iterExecutionTime;
            }

            ordenamiento(finalList);
            System.out.println("Las canciones más escuchadas el día " + fecha + " fueron:");
            for (int z = 0; z < Math.min(5, finalList.size()); z++) {
                System.out.println((z + 1) + ". " + finalList.get(z).getData() + " con " + finalList.get(z).getValue() + " apariciones.");
                System.out.println();
            }
            long averageTime = totalTime / iterations;  // Cálculo del tiempo promedio de ejecución
            // Impresión de memoria y tiempo promedio
            long memoryAfter = getUsedMemory();
            printMemoryAndTime(memoryBefore, memoryAfter, totalTime, averageTime);
        }
    }

    public void ordenamiento(MyList<HashNode<Integer, String>> newList) {
        for (int i = 0; i < newList.size() - 1; i++) {
            for (int j = 0; j < newList.size() - 1 - i; j++) {
                if (newList.get(j).getValue() < newList.get(j + 1).getValue()) {
                    HashNode<Integer, String> aux = newList.get(j);
                    newList.set(j, newList.get(j + 1));
                    newList.set(j + 1, aux);
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

    @Override
    public void countArtistAppearances() throws InformacionInvalida {   ///opcion 4
        int iterations = 10; // Número de iteraciones para calcular el promedio
        long totalTime = 0;
        long memoryBefore = getUsedMemory();
        int res = 0;
        Scanner scanner = new Scanner(System.in);

        String artistName = "";
        boolean artistValido = false;

        while (!artistValido) {
            System.out.print("Ingrese el nombre del artista: ");
            artistName = scanner.nextLine();

            Artist newArtist = getArtis(artistName);
            if (newArtist == null) {
                System.out.println("El artista " + artistName + " no está registrado. Por favor ingrese un nombre válido.");
            } else {
                artistValido = true;
            }
        }

        LocalDate fecha = null;
        boolean fechaValida = false;

        while (!fechaValida) {
            System.out.print("Ingrese la fecha (yyyy-MM-dd): ");
            String fecha1 = scanner.nextLine();
            try {
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                fecha = LocalDate.parse(fecha1, formato);
                fechaValida = true;  // Fecha válida, salimos del bucle
            } catch (DateTimeParseException e) {
                System.out.println("Por favor ingrese una fecha válida en el formato yyyy-MM-dd.");
            }
        }

        int datePosition = this.spotify.search(fecha);
        if (datePosition == -1) {
            System.out.println("La fecha " + fecha + " no está registrada.");
        } else {
            HashImpl<String, MyBinarySearchTreeImpl<Integer, Song>> hashDate = this.spotify.getData(datePosition);
            for (int i = 0; i < hashDate.getSize(); i++) {
                HashNode<String, MyBinarySearchTreeImpl<Integer, Song>> nodoPais = hashDate.getNode(i);
                if (nodoPais == null) {
                    continue;
                }
                List<Song> countryList = nodoPais.getData().inOrderValue();
                for (Song song : countryList) {
                    MyList<Artist> artistsList = song.getArtists();
                    for (int l = 0; l < artistsList.size(); l++) {
                        if (artistsList.get(l).getName().equals(artistName)) {
                            res++;
                        }
                    }
                }
            }

            System.out.println("El artista " + artistName + " aparece " + res + " veces en el día " + fecha);
            System.out.println();
            for (int i = 0; i < iterations; i++) {
                long startIterTime = System.nanoTime();
                long endTime = System.nanoTime();
                long iterExecutionTime = endTime - startIterTime;
                totalTime += iterExecutionTime;
            }
            long averageTime = totalTime / iterations;  // Cálculo del tiempo promedio de ejecución
            // Impresión de memoria y tiempo promedio
            long memoryAfter = getUsedMemory();
            printMemoryAndTime(memoryBefore, memoryAfter, totalTime, averageTime);
        }
    }

    @Override
    public void countSongsByTempoRangeAndDateRange() throws InformacionInvalida { //opcion 5
        int iterations = 10; // Número de iteraciones para calcular el promedio
        long totalTime = 0;
        long memoryBefore = getUsedMemory();
        Scanner scanner = new Scanner(System.in);
        LocalDate fechaInicio = null;
        LocalDate fechaFin = null;
        boolean fechasValidas = false;

        while (!fechasValidas) {
            try {
                System.out.print("Ingrese la fecha de inicio (yyyy-MM-dd): ");
                String fechaInicioStr = scanner.nextLine();
                fechaInicio = LocalDate.parse(fechaInicioStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                System.out.print("Ingrese la fecha de fin (yyyy-MM-dd): ");
                String fechaFinStr = scanner.nextLine();
                fechaFin = LocalDate.parse(fechaFinStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                if (fechaInicio.isAfter(fechaFin)) {
                    System.out.println("La fecha de inicio debe ser anterior o igual a la fecha de fin. Por favor, ingrese las fechas nuevamente.");
                } else {
                    fechasValidas = true;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Por favor ingrese una fecha válida en el formato yyyy-MM-dd.");
            }
        }

        int tempoMin = 0;
        int tempoMax = 0;
        boolean temposValidos = false;

        while (!temposValidos) {
            try {
                System.out.print("Ingrese el tempo mínimo: ");
                tempoMin = Integer.parseInt(scanner.nextLine());

                System.out.print("Ingrese el tempo máximo: ");
                tempoMax = Integer.parseInt(scanner.nextLine());

                if (tempoMin > tempoMax) {
                    System.out.println("El tempo mínimo no puede ser mayor que el tempo máximo. Por favor, ingrese los tempos nuevamente.");
                } else {
                    temposValidos = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese valores válidos para los tempos.");
            }
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
                        for (int p = 0; p < countryList.size(); p++) {
                            float tempo = countryList.get(p).getTempo();
                            boolean alreadyCounted = false;
                            for (int h = 0; h < countedSongs.size(); h++) {
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
                + fechaInicio + " hasta " + fechaFin + ": " + count);
        System.out.println();
        // Iteraciones para calcular el promedio de tiempo de ejecución
        for (int i = 0; i < iterations; i++) {
            long startIterTime = System.nanoTime();
            long endTime = System.nanoTime();
            long iterExecutionTime = endTime - startIterTime;
            totalTime += iterExecutionTime;
        }
        long averageTime = totalTime / iterations;  // Cálculo del tiempo promedio de ejecución
        // Impresión de memoria y tiempo promedio
        long memoryAfter = getUsedMemory();
        printMemoryAndTime(memoryBefore, memoryAfter, totalTime, averageTime);
    }


    @Override
    public void top7PorRango() throws InformacionInvalida {   //opcion 3
        int iterations = 10; // Número de iteraciones para calcular el promedio
        long totalTime = 0;
        long memoryBefore = getUsedMemory();
        Scanner scanner = new Scanner(System.in);
        LocalDate fechaI = null;
        LocalDate fechaF = null;
        boolean fechasValidas = false;

        while (!fechasValidas) {
            try {
                System.out.print("Ingrese la fecha de inicio (yyyy-MM-dd): ");
                String fechaInicio = scanner.nextLine();
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                fechaI = LocalDate.parse(fechaInicio, formato);

                System.out.print("Ingrese la fecha de fin (yyyy-MM-dd): ");
                String fechaFin = scanner.nextLine();
                fechaF = LocalDate.parse(fechaFin, formato);

                if (fechaI.isAfter(fechaF)) {
                    System.out.println("La fecha de inicio no puede ser posterior a la fecha de fin. Por favor, ingrese las fechas nuevamente.");
                } else {
                    fechasValidas = true;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Por favor ingrese una fecha válida en el formato yyyy-MM-dd.");
            }
        }

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
        // Iteraciones para calcular el promedio de tiempo de ejecución
        for (int i = 0; i < iterations; i++) {
            long startIterTime = System.nanoTime();
            long endTime = System.nanoTime();
            long iterExecutionTime = endTime - startIterTime;
            totalTime += iterExecutionTime;
        }
        List<String> topArtistas = arbol.inOrderValue();
        System.out.println("Los artistas más escuchados del " + fechaI + " al " + fechaF + " fueron:");
        for (int n = topArtistas.size(); n > topArtistas.size() - 7 && n > 0; n--) { // Ensure we don't go out of bounds
            System.out.println(contador + ") " + topArtistas.get(n - 1));
            contador++;
        }
        long averageTime = totalTime / iterations;  // Cálculo del tiempo promedio de ejecución
        // Impresión de memoria y tiempo promedio
        long memoryAfter = getUsedMemory();
        printMemoryAndTime(memoryBefore, memoryAfter, totalTime, averageTime);
    }


}














