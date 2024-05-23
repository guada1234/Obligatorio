package prog_2_adts.test.uy.edu.um.adt.linkedlist;

import org.junit.Test;
import prog_2_adts.src.uy.edu.um.adt.linkedlist.MyLinkedListImpl;

import static org.junit.jupiter.api.Assertions.*;

public class testLinkedListValidator {

    @Test
    public void testContains(){
        MyLinkedListImpl<String> lista = new MyLinkedListImpl<>();
        lista.add("M");
        assertTrue(lista.contains("M"));
        assertFalse(lista.contains("P"));
    }
    @Test
    public void testAddSize(){
        MyLinkedListImpl<String> lista = new MyLinkedListImpl<>();
        assertEquals(0,lista.size());
        lista.add("M");
        assertEquals(1,lista.size());
    }

    @Test
    public void testGet(){
        MyLinkedListImpl<String> lista = new MyLinkedListImpl<>();
        lista.add("M");
        lista.add("P");
        assertEquals("P",lista.get(1));
        assertEquals("M",lista.get(0));
    }

    @Test
    public void testRemove(){
        MyLinkedListImpl<String> lista = new MyLinkedListImpl<>();
        lista.add("M");
        lista.add("P");
        lista.add("O");
        assertEquals(3,lista.size());
        lista.remove("P");
        assertEquals(2,lista.size());
    }
    @Test
    public void testIsEmpty(){
        MyLinkedListImpl<String> lista = new MyLinkedListImpl<>();
        assertTrue(lista.isEmpty());
        lista.add("O");
        assertFalse(lista.isEmpty());
    }
    @Test
    public void testGetFirst(){
        MyLinkedListImpl<String> lista = new MyLinkedListImpl<>();
        lista.add("M");
        lista.add("P");
        lista.add("O");
        assertEquals("M", lista.getFirst().getValue());
    }

}
