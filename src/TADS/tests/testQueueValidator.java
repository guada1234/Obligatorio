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
        MyQueue queue = new MyLinkedListImpl();
        Node node1 = new Node<>('k');
        Node node2 = new Node<>('m');
        Node node3 = new Node<>('X');
        queue.enqueue(node1);
        queue.enqueue(node2);
        queue.enqueue(node3);
        //check que los agrego a los 3
        assertEquals(3, queue.size());
        //check elementos y dequeue
        try {
            assertEquals(node1, queue.dequeue());
        } catch (EmptyQueueException e) {
            throw new EmptyQueueException();
        }
        try {
            assertEquals(node2, queue.dequeue());
        } catch (EmptyQueueException e) {
            throw new EmptyQueueException();
        }
        try {
            assertEquals(node3, queue.dequeue());
        } catch (EmptyQueueException e) {
            throw new EmptyQueueException();
        }
    }
    public void testContains(){
        MyQueue queue = new MyLinkedListImpl();
        Node node1 = new Node<>('k');
        Node node2 = new Node<>('m');
        Node node3 = new Node<>('X');
        queue.enqueue(node1);
        queue.enqueue(node2);
        queue.enqueue(node3);
        //como lo chequeamos?
    }

    public void testSize(){ //preguntar
        MyQueue queue = new MyLinkedListImpl();
        //paso base
        assertEquals(0,queue.size());

        //agregamos y verificamos
        Node node1 = new Node<>('k');
        Node node2 = new Node<>('m');
        Node node3 = new Node<>('X');
        queue.enqueue(node1);
        queue.enqueue(node2);
        queue.enqueue(node3);
        assertEquals(3,queue.size());
    }
}
