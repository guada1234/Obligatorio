package TADS.tests;
import TADS.listas.linkedlist.MyLinkedListImpl;
import TADS.listas.linkedlist.Node;
import TADS.listas.queue.EmptyQueueException;
import TADS.listas.queue.MyQueue;
import TADS.listas.stack.EmptyStackException;
import TADS.listas.stack.MyStack;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class testStackValidator {
    public void testPush(){
        MyStack<String> stack = new MyLinkedListImpl<>();
        stack.push("X");
        assertEquals(1,stack.size());
    }

    public void peekPush() throws EmptyStackException {
        MyStack<String> stack = new MyLinkedListImpl<>();
        stack.push("X");
        assertEquals("X",stack.peek());
        try {
            stack.pop();
        } catch (EmptyStackException e) {
            throw new EmptyStackException();
        }
        assertEquals(0,stack.size());
    }
}
