package TADS.tests;
import TADS.tree.InformacionInvalida;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import TADS.hashCerrado.HashImpl;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class testHashlmplValidator {

    private HashImpl<String, Integer> hashTable;
@BeforeEach
    public void setUp() {
        hashTable = new HashImpl<>();
    }
@Test
    public void testPutAndContains() throws InformacionInvalida {
        hashTable.put("key", 1);
        assertTrue(hashTable.contains("key"));
        assertFalse(hashTable.contains("key2"));
    }
@Test
    public void testPutWithResize() throws InformacionInvalida {
        for (int i = 0; i < 20; i++) {
            assertTrue(hashTable.contains("key" + i));
        }
    }
@Test
    public void testRemove() throws InformacionInvalida {
        hashTable.put("key1", 1);
        assertTrue(hashTable.contains("key1"));
        hashTable.remove("key1");
        assertFalse(hashTable.contains("key1"));
    }
@Test
    public void testRemoveNoExisteKey() {
        assertThrows(InformacionInvalida.class, () -> {
            hashTable.remove("noExisteKey");
        });
    }
@Test
    public void testPutNullKey() {
        assertThrows(InformacionInvalida.class, () -> {
            hashTable.put(null, 1);
        });
    }
@Test
    public void testContainsNullKey() {
        assertThrows(InformacionInvalida.class, () -> {
            hashTable.contains(null);
        });
    }
@Test
    public void testRemoveNullKey() {
        assertThrows(InformacionInvalida.class, () -> {
            hashTable.remove(null);
        });
    }
}



