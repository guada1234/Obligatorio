package prog_2_adts.src.uy.edu.um.adt.hash;

public interface HashTable<K, V> {
    public void put(K key, V value) throws InformacionInvalida;
    public boolean contains(K key) throws InformacionInvalida;
    public void remove(K clave) throws InformacionInvalida ;
}
