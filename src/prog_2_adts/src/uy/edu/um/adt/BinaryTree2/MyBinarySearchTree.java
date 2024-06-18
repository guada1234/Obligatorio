package prog_2_adts.src.uy.edu.um.adt.BinaryTree2;

import java.util.List;
public interface MyBinarySearchTree<k extends Comparable<k>, T> {

    void add(k oElement, T value);
    void remove(k oElement);
    boolean contains(k oElement);
    T find(k oElement);
    List<k> preOrder();
    List<k> postOrder();
    List<k> inOrder();
    List<T> preOrderV();
    List<T> postOrderV();
    List<T> inOrderV();
    TreeNode search(k key);

}
