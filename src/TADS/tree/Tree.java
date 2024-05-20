package TADS.tree;

import TADS.listas.linkedlist.*;

public class Tree<K,T> implements MyTree<K,T> {

    private TreeNode<K,T> raiz;



    @Override
    public T find(K key) {
        raiz.find(raiz, key);
        return null;
    }

    @Override
    public void insert(K key, T data, K parentKey) throws EntidadNoExiste, InformacionInvalida {
        TreeNode<K,T> temp = raiz;
        TreeNode<K, T> parent = raiz.findParent(temp, key);
        TreeNode<K,T> newNode = new TreeNode<K,T>(key, data);
        if(parent == null){
            throw new EntidadNoExiste();
        }
        if(parent.getLeftChild() == null && parent.getRightChild() == null){
            parent.setLeftChild(newNode);
        }
        if(parent.getLeftChild() != null && parent.getRightChild() == null){
            parent.setRightChild(newNode);
        }
        if(parent.getLeftChild() != null && parent.getRightChild() != null){
            throw new InformacionInvalida();
        }
    }


    @Override
    public void delete(K key) throws InformacionInvalida {
        TreeNode<K,T> temp = raiz;
        TreeNode<K, T> nodeToDelete = (TreeNode<K, T>) this.raiz.find(raiz, key);
        TreeNode<K, T> parent = this.raiz.findParent(raiz, key);
        if(nodeToDelete.getLeftChild() == null && nodeToDelete.getRightChild() == null){
            if(parent.getRightChild() == nodeToDelete){
                parent.setRightChild(null);
            } else if (parent.getLeftChild() == nodeToDelete) {
                parent.setLeftChild(null);
            }
        }
        while(nodeToDelete.getLeftChild() != null){
            parent.setKey(nodeToDelete.getKey());
            nodeToDelete.setKey(nodeToDelete.getLeftChild().getKey());
        } parent.setLeftChild(null);

    }

    @Override
    public MyLinkedListImpl<K> inOrder() {
        return null;
    }

    @Override
    public MyLinkedListImpl<K> preOrder() {
        return null;
    }

    @Override
    public MyLinkedListImpl<K> postOrder() {
        return null;
    }

    public int countLeaf(TreeNode<K,T> raiz){
        raiz.countLeaf(raiz);
        return 0;
    }
    public int countCompleteElements(TreeNode<K,T> raiz){
        raiz.countCompleteElements(raiz);
        return 0;
    }

    public int size(TreeNode<K,T> raiz){
        return raiz.size(raiz);
    }
    
}
