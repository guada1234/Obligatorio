package TADS.tree;



import TADS.listas.linkedlist.MyLinkedListImpl;

import java.util.List;

public interface MyTree<K, T> {

    T find(K key);

    void insert(K key, T data, K parentKey) throws EntidadNoExiste, InformacionInvalida;

    void delete(K key) throws InformacionInvalida;
    MyLinkedListImpl<K> inOrder();
    MyLinkedListImpl<K> preOrder();
    MyLinkedListImpl<K> postOrder();
}