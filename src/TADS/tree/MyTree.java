package uy.edu.um.prog2.adt.tree;



import uy.edu.um.prog2.adt.MyList;

import java.util.List;

public interface MyTree<K, T> {

    T find(K key);

    void insert(K key, T data, K parentKey) throws EntidadNoExiste, InformacionInvalida;

    void delete(K key) throws InformacionInvalida;
    MyList<K> inOrder();
    MyList<K> preOrder();
    MyList<K> postOrder();
}