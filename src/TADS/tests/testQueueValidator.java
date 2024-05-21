package TADS.tests;

import TADS.listas.linkedlist.MyLinkedListImpl;
import TADS.listas.linkedlist.Node;
import TADS.listas.queue.EmptyQueueException;
import TADS.listas.queue.MyQueue;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class testQueueValidator {
    @Test
    public void testEnqueueDequeue() throws EmptyQueueException {
        MyQueue<String> queue = new MyLinkedListImpl<>();
        queue.enqueue("k");
        queue.enqueue("m");
        queue.enqueue("X");
        //check que los agrego a los 3
        assertEquals(3, queue.size());
        //check elementos y dequeue
        try {
            assertEquals("k", queue.dequeue());
        } catch (EmptyQueueException e) {
            throw new EmptyQueueException();
        }
        try {
            assertEquals("m", queue.dequeue());
        } catch (EmptyQueueException e) {
            throw new EmptyQueueException();
        }
        try {
            assertEquals("X", queue.dequeue());
        } catch (EmptyQueueException e) {
            throw new EmptyQueueException();
        }
    }
    public void testContains(){
        MyQueue queue = new MyLinkedListImpl();
        Node node1 = new Node<>("X");
        queue.enqueue(node1);
        assertEquals("True", queue.contains("X"));

    }

    public void testSize(){ //preguntar
        MyQueue queue = new MyLinkedListImpl();
        //paso base
        assertEquals(0,queue.size());

        //agregamos y verificamos
        queue.enqueue("L");
        queue.enqueue("P");
        assertEquals(2,queue.size());
    }
}
