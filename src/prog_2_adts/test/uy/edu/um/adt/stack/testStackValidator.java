package prog_2_adts.test.uy.edu.um.adt.stack;
import org.junit.jupiter.api.Test;
import prog_2_adts.src.uy.edu.um.adt.linkedlist.MyLinkedListImpl;
import prog_2_adts.src.uy.edu.um.adt.stack.MyStack;
import prog_2_adts.src.uy.edu.um.adt.stack.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

public class testStackValidator {
    @Test
    public void testSize(){
        MyStack<String> stack = new MyLinkedListImpl<>();
        assertEquals(0,stack.size());
        stack.push("L");
        stack.push("P");
        assertEquals(2, stack.size());
    }
    @Test
    public void testIsEmpty() {
        MyStack<String> stack = new MyLinkedListImpl<>();
        assertTrue( stack.isEmpty());
        stack.push("X");
        assertFalse(stack.isEmpty());
    }
    @Test
    public void testPush(){
        MyStack<String> stack = new MyLinkedListImpl<>();
        stack.push("X");
        assertEquals(1,stack.size());
    }

    @Test
    public void testPeekPop() throws EmptyStackException {
        MyStack<String> stack = new MyLinkedListImpl<>();
        stack.push("X");
        stack.push("L");
        assertEquals("L",stack.peek());
        try {
            stack.pop();
        } catch (EmptyStackException e) {;
        }
        assertEquals(1,stack.size());
    }
    @Test
    public void testException() throws EmptyStackException{
        MyStack<String> stack = new MyLinkedListImpl<>();
        assertThrows(EmptyStackException.class,()->{stack.pop();});
    }
}
