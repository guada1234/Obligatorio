package uy.edu.um.prog2.adt.tree;

public class BinaryTree<K extends Comparable<K>, T> implements MyBinarySearchTree<K , T>{
    private NodeBST<K,T> raiz;
    @Override
    public T find(K key) {
        raiz.findNode(raiz, key);
        return null;
    }


    @Override
    public void insert(K key, T data) throws  InformacionInvalida{
        NodeBST<K,T> temp = raiz;
       NodeBST<K,T> addNode = new NodeBST<>(key, data);
        if (temp.getKey().compareTo(key)==0){
           throw new InformacionInvalida();
       }
        if(temp.getKey().compareTo(key)<0 && temp.getLeftChild()==null){
           temp.setLeftChild(addNode);
        }
        if (temp.getKey().compareTo(key)>0 && temp.getRightChild()==null){
            temp.setRightChild(addNode);
        }
       else if (temp.getKey().compareTo(key)<0 && temp.getLeftChild()!=null ) {
           temp = temp.getLeftChild();
           insert(key, data);
       } else if (temp.getKey().compareTo(key)>0 && temp.getRightChild()!=null ) {
            temp = temp.getRightChild();
            insert(key,data);
       }
    }

    @Override
    public void delete(K key) {
        T data = find(key);
        NodeBST<K,T> deleteNode = new NodeBST<K,T>(key, data);
        NodeBST<K,T> changeNode = null;
        NodeBST<K,T> leftChildNode = null;
        if (raiz.getKey().compareTo(key)==0){
            deleteNode = raiz;
            if (deleteNode.getLeftChild() == null && deleteNode.getRightChild() == null){
                raiz = null;
            }
            else if (deleteNode.getLeftChild()!=null) {
                leftChildNode = deleteNode.getLeftChild();
                changeNode = deleteNode.getLeftChild();
                while (changeNode.getRightChild()!= null){
                    changeNode = changeNode.getRightChild();
                    K changeKey = changeNode.getKey();
                }
                leftChildNode.setRightChild(changeNode.getLeftChild());
                raiz.setKey(changeNode.key);
                raiz.setData(changeNode.data);
            }
        }
    }
}
