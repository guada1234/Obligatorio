package prog_2_adts.src.uy.edu.um.adt.BinaryTree2;

import java.util.List;
public interface MyBinarySearchTree<T extends Comparable<T>,K> {

    void add(T oElement, K value);
    void remove(T oElement);
    boolean contains(T oElement);
    T find(T oElement);
    List<T> preOrder();
    List<T> postOrder();
    List<T> inOrder();
    List<K> preOrderV();
    List<K> postOrderV();
    List<K> inOrderV();

}
