package prog_2_adts.test.uy.edu.um.adt.binarytree;

import org.junit.jupiter.api.Test;
import prog_2_adts.src.uy.edu.um.adt.binarytree.EmptyTree;
import prog_2_adts.src.uy.edu.um.adt.binarytree.InvalidKey;
import prog_2_adts.src.uy.edu.um.adt.binarytree.SearchBinaryTreeImpl;
import prog_2_adts.src.uy.edu.um.adt.binarytree.TreeNode;

import static org.junit.jupiter.api.Assertions.*;

public class testBinaryTreeValidator {



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
        binaryTree.delete(node1.getKey());
        assertThrows(InvalidKey.class, ()->{binaryTree.serch(4);});
    }
    @Test
    public void testGetMin() throws InvalidKey, EmptyTree {
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
        assertEquals(1,binaryTree.getMin().getKey());
        binaryTree.delete(1);
        assertThrows(InvalidKey.class, ()->{binaryTree.serch(1);});
       // assertEquals(4,binaryTree.getMin().getKey());
    }










}