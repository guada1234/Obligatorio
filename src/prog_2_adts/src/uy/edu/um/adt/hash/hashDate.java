package prog_2_adts.src.uy.edu.um.adt.hash;

import prog_2_adts.classes.Cancion;
import prog_2_adts.src.uy.edu.um.adt.binarytree.BinaryTree;
import prog_2_adts.src.uy.edu.um.adt.binarytree.InvalidKey;
import prog_2_adts.src.uy.edu.um.adt.binarytree.SearchBinaryTreeImpl;

import java.time.LocalDate;

public class hashDate extends HashImpl<LocalDate, HashImpl<String, BinaryTree<Integer,Cancion>>> {
    private int cantidadElementos=0;
    private double LOAD_FACTOR = 0.77;
    private int capacity;
    HashNode<LocalDate,  HashImpl<String, BinaryTree<Integer,Cancion>>>[] table;

    public int getCantidadElementos() {
        return cantidadElementos;
    }

    public double getLOAD_FACTOR() {
        return LOAD_FACTOR;
    }

    public int getCapacity() {
        return capacity;
    }


    public HashNode<LocalDate, HashImpl<String,BinaryTree<Integer,Cancion>>>[] getTable() {
        return table;
    }

    public hashDate(int cantidadElementos, double LOAD_FACTOR, int capacity) {
        this.cantidadElementos = cantidadElementos;
        this.LOAD_FACTOR = LOAD_FACTOR;
        this.capacity = capacity;
    }

    public void putPais(LocalDate key, Cancion cancion) throws InformacionInvalida, InvalidKey {
        if (key == null) {
            throw new InformacionInvalida();
        }
        int hashCode = key.hashCode();
        int posicion = hashCode % table.length;
        if(posicion > table.length){
            resize(posicion); 
        }
        if(table[posicion] == null){
            BinaryTree<Integer, Cancion> binaryTree = new SearchBinaryTreeImpl<>();
            HashImpl<String,BinaryTree<Integer, Cancion>> hashPais = new HashImpl<>();
            binaryTree.add(cancion.getPosicion(),cancion);
            hashPais.put(cancion.getPais(),binaryTree);
        } else if (table[posicion].getKey().equals(key)){
            table[posicion].getData().searchT(cancion.getPais()).add(cancion.getPosicion(),cancion);
        }
        else{
            while (table[posicion]!=null && !table[posicion].getKey().equals(key)){
                posicion = (posicion+1)%capacity;
            }
            table[posicion].getData().searchT(cancion.getPais()).add(cancion.getPosicion(),cancion);
        }
        cantidadElementos++;
        if(cantidadElementos==capacity-3){
            resize(capacity+1);
        }
    }
}
