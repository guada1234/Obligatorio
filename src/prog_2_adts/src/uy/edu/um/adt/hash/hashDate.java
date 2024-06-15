package prog_2_adts.src.uy.edu.um.adt.hash;

import prog_2_adts.classes.Cancion;
import prog_2_adts.src.uy.edu.um.adt.binarytree.BinaryTree;
import prog_2_adts.src.uy.edu.um.adt.binarytree.InvalidKey;
import prog_2_adts.src.uy.edu.um.adt.binarytree.SearchBinaryTreeImpl;

import java.time.LocalDate;

public class hashDate extends HashImpl<LocalDate, HashImpl<String, SearchBinaryTreeImpl<Integer,Cancion>>> {
    private int cantidadElementos=0;
    private double LOAD_FACTOR = 0.77;
    private int capacity;
    HashNode<LocalDate,  HashImpl<String, SearchBinaryTreeImpl<Integer,Cancion>>>[] table;

    public int getCantidadElementos() {
        return cantidadElementos;
    }

    public double getLOAD_FACTOR() {
        return LOAD_FACTOR;
    }

    public int getCapacity() {
        return capacity;
    }


    public HashNode<LocalDate, HashImpl<String,SearchBinaryTreeImpl<Integer,Cancion>>>[] getTable() {
        return table;
    }

    public hashDate(int capacity, int capacity1) {
        super(capacity);
        this.cantidadElementos = 0;
        this.LOAD_FACTOR = 0.77;
        this.capacity = capacity1;
        this.table =  (HashNode<LocalDate, HashImpl<String, SearchBinaryTreeImpl<Integer, Cancion>>>[]) new HashNode[capacity1];
    }

    public void putPais(LocalDate key, Cancion cancion, int capacity) throws InformacionInvalida, InvalidKey {
        if (key == null) {
            throw new InformacionInvalida("");
        }
        int hashCode = Math.abs(key.hashCode());
        int posicion = hashCode % table.length;
        if(posicion > table.length){
            resize(posicion); 
        }
        if(table[posicion] == null){
            BinaryTree<Integer, Cancion> binaryTree = new SearchBinaryTreeImpl<>();
            HashImpl<String,BinaryTree<Integer, Cancion>> hashPais = new HashImpl<>(capacity);
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
