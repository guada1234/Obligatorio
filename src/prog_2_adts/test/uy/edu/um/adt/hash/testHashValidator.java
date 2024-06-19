package prog_2_adts.test.uy.edu.um.adt.hash;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog_2_adts.src.uy.edu.um.adt.hash.HashImpl;
import prog_2_adts.src.uy.edu.um.adt.hash.InformacionInvalida;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class testHashValidator {
    private HashImpl<Integer, String> hash;

    @BeforeEach
    void setUp() {
        hash = new HashImpl<>(10);
    }

    @Test
    void testAddAndContainsKey() {
        hash.add(5, "cinco");
        assertTrue(hash.containsKey(5));
        assertFalse(hash.containsKey(4));
    }

    @Test
    void testDelete() {
        hash.add(5, "cinco");
        try {
            hash.delete(5);
        } catch (InformacionInvalida e) {
            fail("Unexpected exception thrown: " + e.getMessage());
        }
        assertFalse(hash.containsKey(5));
    }

    @Test
    void testGet() {
        hash.add(5, "cinco");
        assertEquals("five", hash.get(5));
        assertNull(hash.get(4));
    }

    @Test
    void testDeleteException() {
        assertThrows(InformacionInvalida.class, () -> hash.delete(11));
    }

    @Test
    void testGetDataException() {
        assertThrows(InformacionInvalida.class, () -> hash.getData(11));
    }

    @Test
    void testGetNodeException() {
        assertThrows(InformacionInvalida.class, () -> hash.getNode(11));
    }


}