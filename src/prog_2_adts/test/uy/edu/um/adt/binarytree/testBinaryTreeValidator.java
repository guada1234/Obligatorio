package prog_2_adts.test.uy.edu.um.adt.binarytree;

import com.sun.source.tree.Tree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog_2_adts.src.uy.edu.um.adt.binarytree.*;
import prog_2_adts.src.uy.edu.um.adt.binarytree.BinaryTree;
import prog_2_adts.src.uy.edu.um.adt.queue.EmptyQueueException;

import static org.junit.jupiter.api.Assertions.*;

public class testBinaryTreeValidator {

    private BinaryTree<Integer,String> binaryTree;

    @Test
    public void testSearchAdd() throws InvalidKey, EmptyTree {
        SearchBinaryTreeImpl<Integer,String> binaryTree = new SearchBinaryTreeImpl<Integer,String>();
        binaryTree.add(2,"K");
        binaryTree.add(12, "M");
        assertEquals("K", binaryTree.serch(2));
    }
    @Test
    public void testSerachNode() throws InvalidKey, EmptyTree {
        SearchBinaryTreeImpl<Integer,String> binaryTree = new SearchBinaryTreeImpl<Integer,String>();
        TreeNode<Integer,String> newNode = new TreeNode<>(1,"M");
        binaryTree.addNode(newNode);
        assertEquals(newNode, binaryTree.serchNode(1));

    }
    @Test
    public void testDelete() throws InvalidKey, EmptyTree {
        SearchBinaryTreeImpl<Integer,String> binaryTree = new SearchBinaryTreeImpl<Integer,String>();
        TreeNode<Integer,String> node1 = new TreeNode<>(1,"R");
        TreeNode<Integer,String> node2 = new TreeNode<>(10,"E");
        TreeNode<Integer,String> node3 = new TreeNode<>(4,"L");
        TreeNode<Integer,String> node4 = new TreeNode<>(6,"N");
        TreeNode<Integer,String> node5 = new TreeNode<>(9,"K");
        binaryTree.addNode(node1);
        binaryTree.addNode(node2);
        binaryTree.addNode(node3);
        binaryTree.addNode(node4);
        binaryTree.addNode(node5);
        binaryTree.delete(4);
        assertThrows(InvalidKey.class, ()->{binaryTree.serch(4);});
    }










}