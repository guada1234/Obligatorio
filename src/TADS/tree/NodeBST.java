package TADS.tree;

public class NodeBST <K extends Comparable<K>, T> {
    K key;
    T data;
    NodeBST <K, T> leftChild;
    NodeBST <K, T> rightChild;

    public NodeBST(K key, T data) {
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

    public NodeBST<K, T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(NodeBST<K, T> leftChild) {
        this.leftChild = leftChild;
    }

    public NodeBST<K, T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(NodeBST<K, T> rightChild) {
        this.rightChild = rightChild;
    }


    public <K extends Comparable<K>> T findNode(NodeBST<K,T> raiz, K key) {
        T res = null;
        //caso base
        if(raiz == null){
            return res;
        }
        if (raiz.getKey() == key){
            res = raiz.getData();
            return res;
        } else if (raiz.getKey().compareTo(key) < 0 && raiz.getLeftChild() != null) {
            raiz = raiz.getLeftChild();
            findNode(raiz,key);
        } else if (raiz.getKey().compareTo(key) > 0 && raiz.getRightChild() != null) {
            raiz = raiz.getRightChild();
            findNode(raiz,key);
        }
        return res;
    }









}
