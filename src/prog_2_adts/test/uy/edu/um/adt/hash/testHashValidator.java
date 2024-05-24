package prog_2_adts.test.uy.edu.um.adt.hash;

import org.junit.Test;
import prog_2_adts.src.uy.edu.um.adt.binarytree.InvalidKey;
import prog_2_adts.src.uy.edu.um.adt.hash.HashImpl;
import prog_2_adts.src.uy.edu.um.adt.hash.InformacionInvalida;

import static org.junit.jupiter.api.Assertions.*;

public class testHashValidator {


    @Test
    public void testPutContains() throws InformacionInvalida {
        HashImpl<Integer,String> hash = new HashImpl<>();
        hash.put(1,"H");
        hash.put(2,"L");
        hash.contains(1);
        assertTrue(hash.contains(1));
        assertTrue(hash.contains(2));
        assertFalse(hash.contains(9));
    }

    @Test
    public void testRemove() throws InformacionInvalida {
        HashImpl<Integer,String> hash = new HashImpl<>();
        hash.put(1,"H");
        hash.put(2,"L");
        hash.put(3,"T");
        hash.put(9,"O");
        hash.remove(3);
        assertFalse(hash.contains(3));
    }

    @Test
    public void testeExcepctions() throws InformacionInvalida {
        HashImpl<Integer,String> hash = new HashImpl<>();
        assertThrows(InformacionInvalida.class, ()->{hash.remove(null);});
    }
    @Test
    public void testFindPosition()throws InformacionInvalida{
        HashImpl<Integer,String> hash = new HashImpl<>();
        hash.put(1,"H");
        hash.put(2,"L");
        hash.put(3,"T");
        hash.put(9,"O");
        assertEquals(0,hash.findPosition(9));
    }
}
