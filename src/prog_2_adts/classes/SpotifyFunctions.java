package prog_2_adts.classes;

import prog_2_adts.src.uy.edu.um.adt.binarytree.BinaryTree;
import prog_2_adts.src.uy.edu.um.adt.binarytree.EmptyTree;
import prog_2_adts.src.uy.edu.um.adt.binarytree.InvalidKey;
import prog_2_adts.src.uy.edu.um.adt.hash.HashImpl;
import prog_2_adts.src.uy.edu.um.adt.hash.InformacionInvalida;
import prog_2_adts.src.uy.edu.um.adt.linkedlist.MyLinkedListImpl;
import prog_2_adts.src.uy.edu.um.adt.linkedlist.MyList;

import java.time.LocalDate;
import java.util.Scanner;

public class SpotifyFunctions {
    private MyList<Cancion> canciones = new MyLinkedListImpl<>();
    public MyLinkedListImpl<Cancion> Top10(HashImpl<LocalDate,HashImpl<String, BinaryTree<Integer, Cancion>>> hashData) throws InformacionInvalida, EmptyTree, InvalidKey {
        MyLinkedListImpl<Cancion> listaTop10 = new MyLinkedListImpl<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el país:");
        String pais = scanner.nextLine();
        System.out.println("Ingrese la fecha (YYYY-MM-DD):");
        LocalDate fecha = LocalDate.parse(scanner.nextLine());
        if(!hashData.contains(fecha)){
            throw new InformacionInvalida();
        }else {
            int hashFecha = fecha.hashCode();
            int posicionFecha = hashFecha % hashData.getCapacity();
            if(!hashData.searchT(fecha).contains(pais)){
                throw new InformacionInvalida();
            }else{
                int hashPais = pais.hashCode();
                int posicionPais = hashPais % hashData.getCapacity();
                BinaryTree<Integer,Cancion> binaryTree = hashData.searchT(fecha).searchT(pais);
                int contador = 0;
                while(contador <= 10){
                    contador ++;
                    listaTop10.add(binaryTree.getMin().getData());
                    int min = binaryTree.getMin().getKey();
                    binaryTree.delete(min);
                }
                return listaTop10;
            }
        }

    }
    public MyLinkedListImpl<Cancion> Top50(HashImpl<LocalDate,HashImpl<String, BinaryTree<Integer, Cancion>>> hashData) throws InformacionInvalida {
        MyLinkedListImpl<Cancion> listaTop10 = new MyLinkedListImpl<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la fecha (YYYY-MM-DD):");
        LocalDate fecha = LocalDate.parse(scanner.nextLine());
        if(!hashData.contains(fecha)){
            throw new InformacionInvalida();
        }else {
            int hashFecha = fecha.hashCode();
            int posicionFecha = hashFecha % hashData.getCapacity();
            int i;
            for (i=0;i<hashData.searchT(fecha).getTable().length;i++){

            }
        }
    }
}


