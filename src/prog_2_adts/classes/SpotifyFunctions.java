package prog_2_adts.classes;

import prog_2_adts.src.uy.edu.um.adt.binarytree.*;
import prog_2_adts.src.uy.edu.um.adt.hash.HashImpl;
import prog_2_adts.src.uy.edu.um.adt.hash.InformacionInvalida;
import prog_2_adts.src.uy.edu.um.adt.hash.hashDate;
import prog_2_adts.src.uy.edu.um.adt.linkedlist.MyLinkedListImpl;
import prog_2_adts.src.uy.edu.um.adt.linkedlist.MyList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class SpotifyFunctions {
    private MyList<Cancion> canciones = new MyLinkedListImpl<>();
    //private HashImpl<LocalDate,HashImpl<String, Se<Integer, Cancion>>> hashData = new HashImpl<>(210);
    hashDate hashData = new hashDate(210, 100);
    public hashDate processCVS(){
        //hashDate hashData = new hashDate(210, 100);
        String csvFile = "/Users/guadaluperial/Desktop/Spotify_canciones.csv";
        int contador = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine(); //se saltea la primera linea
            String linea;

            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
                // Dividir la línea en campos utilizando '","' como delimitador
                String[] lineaSeparada = linea.split("\",\"");

                if (lineaSeparada.length >= 24) {
                    contador++;
                    String country = "general";
                    MyList<Artista> artistas = new MyLinkedListImpl<>();
                    String[] primeras2Celdas = lineaSeparada[0].split(",\"\"");
                    String[] artistasSeparados = lineaSeparada[1].split(",");
                    String spotifyId = primeras2Celdas[0].replaceAll("\"", "");
                    String name = primeras2Celdas[1].replaceAll("\"", "").trim();
                    for (int i = 0; i < artistasSeparados.length; i++) {
                        artistas.add(new Artista(artistasSeparados[i].replaceAll("\"", "").trim()));
                    }

                    int dailyRank = Integer.parseInt(lineaSeparada[2].replaceAll("\"", ""));
                    int dailyMovement = Integer.parseInt(lineaSeparada[3].replaceAll("\"", ""));
                    int weeklyMovement = Integer.parseInt(lineaSeparada[4].replaceAll("\"", ""));

                    if (!lineaSeparada[5].replaceAll("\"", "").trim().isEmpty()) {
                        country = lineaSeparada[5].replaceAll("\"", "").trim();
                    }

                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate snapshotDate = LocalDate.parse(lineaSeparada[6].replaceAll("\"", ""), formato);

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
                    //LocalDate timeSignature = LocalDate.parse(lineaSeparada[23].replace(";", "").replaceAll("\"", ""));
                    Cancion cancion = new Cancion(name, artistas, dailyRank, snapshotDate, tempo, country);
                    hashData.putPais(snapshotDate, cancion, 210);
                }

            }
        } catch (IOException | InformacionInvalida | InvalidKey e) {
            e.printStackTrace();
        }return hashData;
    }




    public MyLinkedListImpl<Cancion> Top10(HashImpl<LocalDate,HashImpl<String, SearchBinaryTreeImpl<Integer, Cancion>>> hashData, String pais, LocalDate fecha) throws InformacionInvalida, EmptyTree, InvalidKey {
        MyLinkedListImpl<Cancion> listaTop10 = new MyLinkedListImpl<>();
        Scanner scanner = new Scanner(System.in);
        //System.out.println("Ingrese el país:");
        //String pais = scanner.nextLine();
        //System.out.println("Ingrese la fecha (YYYY-MM-DD):");
        //LocalDate fecha = LocalDate.parse(scanner.nextLine());
        if(!hashData.contains(fecha)){
            throw new InformacionInvalida("Fecha no encontrada en el hash.");
        }else {
            int hashFecha = Math.abs(fecha.hashCode());
            int posicionFecha = hashFecha % hashData.getCapacity();
            if(!hashData.searchT(fecha).contains(pais)){
                throw new InformacionInvalida("Pais no encontrado en el hash.");
            }else{
                int hashPais = Math.abs(pais.hashCode());
                int posicionPais = hashPais % hashData.getCapacity();
                SearchBinaryTreeImpl<Integer,Cancion> binaryTree = hashData.searchT(fecha).searchT(pais);
                System.out.println("Tamaño del árbol: " + binaryTree.size());
                if (binaryTree. size() < 10) {
                    throw new InformacionInvalida("El árbol contiene menos de 10 elementos.");
                }

                int contador = 0;
                while(contador <= 10 && binaryTree.size() != 0){
                    contador ++;
                    TreeNode<Integer, Cancion> minNode = binaryTree.getMin();
                    if (minNode != null) {
                        listaTop10.add(minNode.getData());
                        binaryTree.delete(minNode.getKey());
                }else{
                        break;
                    }

            }}
        }
        System.out.println("Lista Top 10:");
        for (int i = 0; i < listaTop10.size(); i++) {
            System.out.println(listaTop10.get(i));
        }

        return listaTop10;
    }





    public MyLinkedListImpl<Cancion> Top50(HashImpl<LocalDate,HashImpl<String, BinaryTree<Integer, Cancion>>> hashData) throws InformacionInvalida {
        MyLinkedListImpl<Cancion> listaTop10 = new MyLinkedListImpl<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la fecha (YYYY-MM-DD):");
        LocalDate fecha = LocalDate.parse(scanner.nextLine());
        if(!hashData.contains(fecha)){
            throw new InformacionInvalida("");
        }else {
            int hashFecha = fecha.hashCode();
            int posicionFecha = hashFecha % hashData.getCapacity();
            int i;
            for (i=0;i<hashData.searchT(fecha).getTable().length;i++){
                
            }
        }
        return null;
    }


    }



