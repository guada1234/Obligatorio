package TADS.tree;

public interface MyBinarySearchTree <K extends Comparable<K>, T> {

    T find(K key);

    void insert(K key, T data)throws  InformacionInvalida;

    void delete(K key);
}