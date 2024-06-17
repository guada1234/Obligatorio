package prog_2_adts.src.uy.edu.um.adt.BinaryTree2;

import java.util.ArrayList;
import java.util.List;

public class MyBinarySearchTreeImpl<k extends Comparable<k>, T> implements MyBinarySearchTree<k, T> {
    private TreeNode<k, T> root;
    public TreeNode<k, T> getRoot() {
        return root;
    }
    public void setRoot(TreeNode<k, T> root) {
        this.root = root;
    }


    public void add(k oElement, T value) {
        TreeNode<k, T> oElementToAdd = new TreeNode<k, T>(oElement, value);
        if (root == null) {
            root = oElementToAdd;
        }
        else {
            root.add(oElement,value);
        }
    }

    public boolean contains(k oElement) {
        return contains(oElement, root);
    }

    private boolean contains(k oElementToSearch, TreeNode<k, T> oTree) {
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

    public void remove(k oElement) {
        if (root != null) {
            root.remove(oElement);
        }
    }

    public T find(k oElement) {
        return find(oElement, root);
    }

    private T find(k oElementToSearch, TreeNode<k, T> oTree) {
        T oSearchedElement = null;
        if (oTree != null) {
            int nValue = oElementToSearch.compareTo(oTree.getKey());
            if (nValue == 0) {
                oSearchedElement = oTree.getValue();
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

    public TreeNode findNodeAtPosition(int position) {
        int[] currentPos = {0}; // Variable auxiliar para llevar la cuenta de la posición actual
        return findNodeAtPositionRec(root, position, currentPos);
    }

    public TreeNode findNodeAtPositionRec(TreeNode node, int position, int[] currentPos) {
        if (node == null) {
            return null;
        }

        // Buscar en el subárbol izquierdo
        TreeNode left = findNodeAtPositionRec(node.getLeft() , position, currentPos);
        if (left != null) {
            return left;
        }

        // Verificar la posición actual
        if (currentPos[0] == position) {
            return node;
        }
        currentPos[0]++;

        // Buscar en el subárbol derecho
        return findNodeAtPositionRec(node.getRight(), position, currentPos);
    }


    public List<k> preOrder() {
        List<k> colValues = new ArrayList<k>();
        if (root != null) {
            colValues.addAll(root.preOrderTraverse());
        }
        return colValues;
    }

    public List<k> postOrder() {
        List<k> colValues = new ArrayList<k>();
        if (root != null) {
            colValues.addAll(root.postOrderTraverse());
        }
        return colValues;
    }

    public List<k> inOrder() {
        List<k> colValues = new ArrayList<k>();
        if (root != null) {
            colValues.addAll(root.inOrderTraverse());
        }
        return colValues;
    }

    public List<T> preOrderV() {
        List<T> colValues = new ArrayList<T>();
        if (root != null) {
            colValues.addAll(root.preOrderTraverseV());
        }
        return colValues;
    }

    public List<T> postOrderV() {
        List<T> colValues = new ArrayList<T>();
        if (root != null) {
            colValues.addAll(root.postOrderTraverseV());
        }
        return colValues;
    }

    public List<T> inOrderV() {
        List<T> colValues = new ArrayList<T>();
        if (root != null) {
            colValues.addAll(root.inOrderTraverseV());
        }
        return colValues;
    }

    public MyBinarySearchTreeImpl(TreeNode<k, T> root) {
        this.root = root;
    }

    public MyBinarySearchTreeImpl() {
        this.root = null;
    }
}
