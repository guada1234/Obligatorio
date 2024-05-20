package TADS.tree;


import TADS.listas.linkedlist.MyLinkedListImpl;
import TADS.listas.linkedlist.MyList;

public class TreeNode<K, T> {
    K key;
    T data;

    TreeNode<K, T> leftChild;
    TreeNode<K, T> rightChild;

    public TreeNode(K key, T data) {
        this.key = key;
        this.data = data;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public int size(TreeNode<K,T> raiz){
        if (raiz == null){
            return 0;
        }
        return (1 + size(raiz.getLeftChild()) + size(raiz.getRightChild()));
    }


    public T find(TreeNode<K,T> raiz, K key){
        T res = null;
        //caso base
        if(raiz.getKey().equals(key)){
            res = raiz.getData();
            return res;
        }
        else if (raiz.getLeftChild() != null){
            raiz = raiz.getLeftChild();
            find(raiz, key);
        }
        else if (raiz.getRightChild() != null) {
            raiz = raiz.getRightChild();
            find(raiz, key);
        }
        return null;
    }
    public int countLeaf(TreeNode<K,T> raiz){
        int res = 0;
        if (raiz.getRightChild() == null && raiz.getLeftChild() == null){
            res = 1;
            return res;
        }
        else if(raiz.getLeftChild() != null){
            raiz = raiz.getLeftChild();
            countLeaf(raiz);
        }
        else if(raiz.getRightChild() != null){
            raiz = raiz.getRightChild();
            countLeaf(raiz);
        }
        return 0;
    }
    public int countCompleteElements(TreeNode<K,T> raiz){
        int res = 0;
        if(raiz.getRightChild() != null && raiz.getLeftChild() != null){
            res = 1;
            return res;
        }
        else if(raiz.getLeftChild() != null && raiz.getRightChild() == null){ //tiene hijo izq pero no derecho
            raiz = raiz.getLeftChild();
            raiz.countCompleteElements(raiz);
        }
        else if (raiz.getLeftChild() == null && raiz.getRightChild() != null) { //tiene hijo der pero no izq
            raiz = raiz.getRightChild();
            raiz.countCompleteElements(raiz);
        }
        return 0;
    }

    public TreeNode<K, T> findParent(TreeNode<K,T> raiz, K key){
        TreeNode res = null;
        //caso base
        if(raiz.getKey().equals(key)){
            res = raiz;
            return res;
        }
        else if (raiz.getLeftChild() != null){
            raiz = raiz.getLeftChild();
            find(raiz, key);
        }
        else if (raiz.getRightChild() != null) {
            raiz = raiz.getRightChild();
            find(raiz, key);
        }
        return null;
    }

    public MyList<K> inOrder(TreeNode<K,T> raiz) {
        MyList<K> lista = new MyLinkedListImpl<>();
        TreeNode<K,T> nuevoNodo = raiz;
        if(raiz== null){
            return null;
        }else{
            while(nuevoNodo.getLeftChild()!=null){
                nuevoNodo = nuevoNodo.getLeftChild();
            }
            if(nuevoNodo.getLeftChild() == null){
                lista.add(nuevoNodo.getKey());
                K agregarRaiz = nuevoNodo.findParent(raiz,nuevoNodo.getKey()).getKey();
                lista.add(agregarRaiz);
            }
            if(nuevoNodo.findParent(raiz,nuevoNodo.getKey()).getRightChild() !=null){
                K agregarDerecho = nuevoNodo.findParent(raiz,nuevoNodo.getKey()).getRightChild().getKey();
                lista.add(agregarDerecho);
            }
        }
        return lista;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public TreeNode<K, T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TreeNode<K, T> leftChild) {
        this.leftChild = leftChild;
    }

    public TreeNode<K, T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(TreeNode<K, T> rightChild) {
        this.rightChild = rightChild;
    }
}
