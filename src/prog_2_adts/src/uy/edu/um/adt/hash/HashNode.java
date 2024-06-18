package prog_2_adts.src.uy.edu.um.adt.hash;

import java.util.Objects;
public class HashNode<K,V>{
    private K value;
    private V data;
    private HashNode<K, V> next;
    public K getValue() {
        return value;
    }
    public void setValue(K value) {
        this.value = value;
    }
    public HashNode(K value, V data) {
        this.value = value;
        this.data = data;
        this.next = next;
    }

    public HashNode<K, V> getNext() {
        return next;
    }

    public void setNext(HashNode<K, V> next) {
        this.next = next;
    }

    public V getData() {return data;}
    public void setData(V data) {this.data = data;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashNode<?, ?> hashNode = (HashNode<?, ?>) o;
        return Objects.equals(value, hashNode.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}


