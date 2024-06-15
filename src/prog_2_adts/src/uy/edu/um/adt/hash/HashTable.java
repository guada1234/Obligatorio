package prog_2_adts.src.uy.edu.um.adt.hash;

public interface HashTable<K, V> {
    void add(K key, V data);
    int getSize();
    int getHas();
    HashNode<K,V>[] getList();
    void resize(int newSize);
    int search(K key);
    void delete(int position) throws InformacionInvalida;
    V getData(int position) throws InformacionInvalida;
    HashNode<K, V> getNode(int position) throws InformacionInvalida;

}
