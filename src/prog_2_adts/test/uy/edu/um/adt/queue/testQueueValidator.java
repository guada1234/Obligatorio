package prog_2_adts.test.uy.edu.um.adt.queue;
import prog_2_adts.src.uy.edu.um.adt.queue.MyQueue;
import prog_2_adts.src.uy.edu.um.adt.queue.EmptyQueueException;
import prog_2_adts.src.uy.edu.um.adt.linkedlist.MyLinkedListImpl;
import prog_2_adts.src.uy.edu.um.adt.linkedlist.Node;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class testQueueValidator {
    @Test
    public void testSize(){
        MyQueue<String> queue = new MyLinkedListImpl<String>();
        assertEquals(0,queue.size());
        queue.enqueue("L");
        queue.enqueue("P");
        assertEquals(2,queue.size());
    }
    @Test
    public void testContains(){
        MyQueue<String>queue = new MyLinkedListImpl<String>();
        queue.enqueue("X");
        assertTrue(queue.contains("X"));
        assertFalse(queue.contains("L"));
    }
    @Test
    public void testEnqueueDequeue() throws EmptyQueueException {
        MyQueue<String> queue = new MyLinkedListImpl<>();
        queue.enqueue("k");
        queue.enqueue("m");
        queue.enqueue("X");
        assertEquals(3, queue.size());
        //check elementos y dequeue
        try {
            assertEquals("k", queue.dequeue());
        } catch (EmptyQueueException e) {;
        }
        try {
            assertEquals("m", queue.dequeue());
        } catch (EmptyQueueException e) {;
        }
        try {
            assertEquals("X", queue.dequeue());
        } catch (EmptyQueueException e) {;
        }
    }

    @Test
    public void testIsEmpty(){
        MyQueue<String> queue = new MyLinkedListImpl<String>();
        assertTrue(queue.isEmpty());
        queue.enqueue("L");
        queue.enqueue("P");
        assertFalse(queue.isEmpty());
    }

    @Test
    public void testGet(){
        MyQueue<String> queue = new MyLinkedListImpl<String>();
        queue.enqueue("L");
        queue.enqueue("P");
        assertEquals("P", queue.get(0));
        assertEquals("L", queue.get(1));
    }
    @Test
    public void testExceptions() {
        MyQueue<String> queue = new MyLinkedListImpl<String>();
        assertThrows(EmptyQueueException.class, ()-> {queue.dequeue();});
    }


}
