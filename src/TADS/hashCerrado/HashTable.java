package TADS.hashCerrado;

import TADS.tree.InformacionInvalida;

public interface HashTable<K, V> {
    public void put(K key, V value) throws InformacionInvalida;
    public boolean contains(K key);
    public void remove(K clave);
}
