package prog_2_adts.test.uy.edu.um.adt.binarytree;

import org.junit.Test;
import prog_2_adts.src.uy.edu.um.adt.BinaryTree2.MyBinarySearchTreeImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class testBinaryTreeValidator {


    @Test
    public void testAddAndContains() {
        MyBinarySearchTreeImpl tree = new MyBinarySearchTreeImpl<>();
        tree.add(5, "five");
        assertTrue(tree.contains(5));
        assertFalse(tree.contains(4));
    }

    @Test
    public void testRemove() {
        MyBinarySearchTreeImpl tree = new MyBinarySearchTreeImpl<>();
        tree.add(5, "five");
        tree.add(6, "V");
        tree.remove(6);
        assertFalse(tree.contains(6));
    }

    @Test
    public void testFind() {
        MyBinarySearchTreeImpl tree = new MyBinarySearchTreeImpl<>();
        tree.add(5, "five");
        assertEquals("five", tree.find(5));
        assertNull(tree.find(4));
    }

    @Test
    public void testFindNodeAtPosition() {
        MyBinarySearchTreeImpl tree = new MyBinarySearchTreeImpl<>();
        tree.add(5, "five");
        tree.add(3, "three");
        tree.add(7, "seven");
        assertEquals("five", tree.findNodeAtPosition(1).getValue());
        assertEquals("three", tree.findNodeAtPosition(0).getValue());
        assertEquals("seven", tree.findNodeAtPosition(2).getValue());
    }

    @Test
    public void testPreOrder() {
        MyBinarySearchTreeImpl tree = new MyBinarySearchTreeImpl<>();
        tree.add(5, "five");
        tree.add(3, "three");
        tree.add(7, "seven");

        List<Integer> expectedList = new ArrayList<>();
        expectedList.add(5);
        expectedList.add(3);
        expectedList.add(7);
        List<Integer> lista = tree.preOrder();
        assertEquals(expectedList, lista);
    }

    @Test
    public void testPostOrder() {
        MyBinarySearchTreeImpl tree = new MyBinarySearchTreeImpl<>();
        tree.add(5, "five");
        tree.add(3, "three");
        tree.add(7, "seven");

        List<Integer> expectedList = new ArrayList<>();
        expectedList.add(3);
        expectedList.add(7);
        expectedList.add(5);
        List<Integer> lista = tree.postOrder();
        assertEquals(expectedList, lista);
    }

    @Test
    public void testInOrder() {
        MyBinarySearchTreeImpl tree = new MyBinarySearchTreeImpl<>();
        tree.add(5, "five");
        tree.add(3, "three");
        tree.add(7, "seven");

        List<Integer> expectedList = new ArrayList<>();
        expectedList.add(3);
        expectedList.add(5);
        expectedList.add(7);
        List<Integer> lista = tree.inOrder();
        assertEquals(expectedList, lista);
    }
}
