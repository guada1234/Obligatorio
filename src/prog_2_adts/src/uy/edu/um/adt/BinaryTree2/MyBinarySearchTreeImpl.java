package prog_2_adts.src.uy.edu.um.adt.BinaryTree2;

import java.util.ArrayList;
import java.util.List;

public class MyBinarySearchTreeImpl<T extends Comparable<T>,K> implements MyBinarySearchTree<T,K> {
    private TreeNode<T,K> root;
    public TreeNode<T,K> getRoot() {
        return root;
    }
    public void setRoot(TreeNode<T,K> root) {
        this.root = root;
    }

    public void add(T oElement, K value) {
        TreeNode<T,K> oElementToAdd = new TreeNode<T,K>(oElement, value);
        if (root == null) {
            root = oElementToAdd;
        }
        else {
            root.add(oElement,value);
        }
    }

    public boolean contains(T oElement) {
        return contains(oElement, root);
    }

    private boolean contains(T oElementToSearch, TreeNode<T,K> oTree) {
        boolean bContains = false;
        if (oTree != null) {
            int nValue = oElementToSearch.compareTo(oTree.getKey());
            if (nValue == 0) {
                bContains = true;
            }
            else if (nValue > 0) {
                bContains = contains(oElementToSearch, oTree.getRight());
            }
            else {
                bContains = contains(oElementToSearch, oTree.getLeft());
            }
        }
        return bContains;
    }

    public void remove(T oElement) {
        if (root != null) {
            root.remove(oElement);
        }
    }

    public T find(T oElement) {
        return find(oElement, root);
    }

    private T find(T oElementToSearch, TreeNode<T,K> oTree) {
        T oSearchedElement = null;
        if (oTree != null) {
            int nValue = oElementToSearch.compareTo(oTree.getKey());
            if (nValue == 0) {
                oSearchedElement = oTree.getKey();
            }
            else if (nValue > 0) {
                oSearchedElement = find(oElementToSearch, oTree.getRight());
            }
            else {
                oSearchedElement = find(oElementToSearch, oTree.getLeft());
            }
        }
        return oSearchedElement;
    }

    public List<T> preOrder() {
        List<T> colValues = new ArrayList<T>();
        if (root != null) {
            colValues.addAll(root.preOrderTraverse());
        }
        return colValues;
    }

    public List<T> postOrder() {
        List<T> colValues = new ArrayList<T>();
        if (root != null) {
            colValues.addAll(root.postOrderTraverse());
        }
        return colValues;
    }

    public List<T> inOrder() {
        List<T> colValues = new ArrayList<T>();
        if (root != null) {
            colValues.addAll(root.inOrderTraverse());
        }
        return colValues;
    }

    public List<K> preOrderV() {
        List<K> colValues = new ArrayList<K>();
        if (root != null) {
            colValues.addAll(root.preOrderTraverseV());
        }
        return colValues;
    }

    public List<K> postOrderV() {
        List<K> colValues = new ArrayList<K>();
        if (root != null) {
            colValues.addAll(root.postOrderTraverseV());
        }
        return colValues;
    }

    public List<K> inOrderV() {
        List<K> colValues = new ArrayList<K>();
        if (root != null) {
            colValues.addAll(root.inOrderTraverseV());
        }
        return colValues;
    }

    public MyBinarySearchTreeImpl(TreeNode<T, K> root) {
        this.root = root;
    }

    public MyBinarySearchTreeImpl() {
        this.root = null;
    }
}
