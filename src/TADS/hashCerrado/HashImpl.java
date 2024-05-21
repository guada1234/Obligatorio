package TADS.hashCerrado;

import TADS.listas.linkedlist.MyLinkedListImpl;
import TADS.listas.linkedlist.MyList;
import TADS.tree.InformacionInvalida;

public class HashImpl<K, V> implements HashTable<K, V> {
    private int cantidadElementos;
    private double LOAD_FACTOR = 0.77;
    private int capacity;
    HashNode<K, V>[] table;

    public HashImpl() {
        this.capacity = 10; // Tamaño inicial de la tabla
        this.table = new HashNode[capacity];
        this.cantidadElementos = 0;
    }


    @Override
    public void put(K key, V value) throws InformacionInvalida {
        if ((double) (cantidadElementos + 1) / capacity >= LOAD_FACTOR) {
            resize();
        }
        if (key == null) {
            throw new InformacionInvalida();
        }
        int hashCode = key.hashCode();
        int posicion = hashCode % table.length;
        while (table[posicion] != null) {
            posicion++;
            if(posicion == table.length){
                posicion = 0;
            }
        }
        table[posicion] = new HashNode<>(key, value);




    }


    private void resize() throws InformacionInvalida {
        int newCapacity = capacity * 2;
        HashNode<K, V>[] newTable = new HashNode[newCapacity];
        HashNode<K, V>[] oldTable = table;
        this.table=newTable;
        for (int i = 0; i < oldTable.length; i++) {
            put(oldTable[i].getKey(), oldTable[i].getData());
        }
        capacity = newCapacity;
    }


    @Override
    public boolean contains(K key) {
        return false;
    }

    @Override
    public void remove(K clave) {

    }
}
