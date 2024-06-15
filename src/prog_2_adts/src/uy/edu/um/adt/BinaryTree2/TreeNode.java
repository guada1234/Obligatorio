package prog_2_adts.src.uy.edu.um.adt.BinaryTree2;

import java.util.ArrayList;
import java.util.List;

public class TreeNode<T extends Comparable<T>,K> {
    private T key;
    private K value;
    private TreeNode<T,K> left;
    private TreeNode<T,K> right;

    public TreeNode(T key, K value) {
        this.key = key;
        this.value = value;
    }

    public void add(T oElement, K value) {
        int nValue = oElement.compareTo(key);
        TreeNode<T,K> oElementToAdd = new TreeNode<T, K>(oElement, value);
        if (nValue > 0) {
            if (right == null) {
                right = oElementToAdd;
            }
            else {
                right.add(oElement, value);
            }
        }
        else {
            if (left == null) {
                left = oElementToAdd;
            }
            else {
                left.add(oElement, value);
            }
        }
    }

    public TreeNode<T,K> remove(T oElement) {
        int nValue = ((Comparable<T>) oElement).compareTo(key);
        if (nValue > 0) {
            if (right != null) {
                right = right.remove(oElement);
            }
        }
        else if (nValue < 0) {
            if (left != null) {
                left = left.remove(oElement);
            }
        }
        else if (left != null && right != null) { // Encontre el elemento a eliminar
            key = right.findMin();
            right = right.remove(key);
        }
        else {
            if (left != null) {
                return left;
            } else {
                return right;
            }
        }
        return this;
    }

    public List<T> inOrderTraverse() {
        List<T> colList = new ArrayList<T>();
        if (left != null) {
            colList.addAll(left.inOrderTraverse());
        }
        colList.add(key);
        if (right != null) {
            colList.addAll(right.inOrderTraverse());
        }
        return colList;
    }

    public List<T> preOrderTraverse() {
        List<T> colList = new ArrayList<T>();
        colList.add(getKey());
        if (left != null) {
            colList.addAll(left.preOrderTraverse());
        }
        if (right != null) {
            colList.addAll(right.preOrderTraverse());
        }
        return colList;
    }

    public List<T> postOrderTraverse() {
        List<T> colList = new ArrayList<T>();
        if (left != null) {
            colList.addAll(left.preOrderTraverse());
        }
        if (right != null) {
            colList.addAll(right.preOrderTraverse());
        }
        colList.add(getKey());
        return colList;
    }


    public T findMin() {
        T oReturn = key;
        if (left != null) {
            oReturn = left.findMin();
        }
        return oReturn;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public K getValue() {
        return value;
    }

    public void setValue(K value) {
        this.value = value;
    }

    public TreeNode<T, K> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<T, K> left) {
        this.left = left;
    }

    public TreeNode<T, K> getRight() {
        return right;
    }

    public void setRight(TreeNode<T, K> right) {
        this.right = right;
    }

    public List<K> inOrderTraverseV() {
        List<K> colList = new ArrayList<K>();
        if (left != null) {
            colList.addAll(left.inOrderTraverseV());
        }
        colList.add(getValue());
        if (right != null) {
            colList.addAll(right.inOrderTraverseV());
        }
        return colList;
    }

    public List<K> preOrderTraverseV() {
        List<K> colList = new ArrayList<K>();
        colList.add(getValue());
        if (left != null) {
            colList.addAll(left.preOrderTraverseV());
        }
        if (right != null) {
            colList.addAll(right.preOrderTraverseV());
        }
        return colList;
    }

    public List<K> postOrderTraverseV() {
        List<K> colList = new ArrayList<K>();
        if (left != null) {
            colList.addAll(left.preOrderTraverseV());
        }
        if (right != null) {
            colList.addAll(right.preOrderTraverseV());
        }
        colList.add(getValue());
        return colList;
    }
}
