
public class Node<T> {//tambien trabaja con T
    private T value;
    private Node <T> siguiente;
    private Node<T> anterior;

    public Node(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public Node<T> getSiguiente() {
        return siguiente;
    }

    public Node<T> getAnterior() {return anterior;}

    public void setValue(T value) {
        this.value = value;
    }

    public void setSiguiente(Node<T> siguiente) {this.siguiente = siguiente;}

    public void setAnterior(Node<T> anterior) {
        this.anterior = anterior;
    }

}


