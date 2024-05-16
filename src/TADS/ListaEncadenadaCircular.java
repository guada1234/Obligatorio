public class ListaEncadenadaCircular <T extends Comparable<T>>{
    private Node<T> primero;
    private Node<T> ultimo;

    public ListaEncadenadaCircular() {}

    public Node<T> getPrimero() {
        return primero;
    }

    public Node<T> getUltimo() {
        return ultimo;
    }

    public void setPrimero(Node<T> primero) {
        this.primero = primero;
    }

    public void setUltimo(Node<T> ultimo) {
        this.ultimo = ultimo;
    }

    public void insertar(T valor) {
        Node<T> nuevoNodo = new Node<>(valor);
        if (primero == null) {
            primero = nuevoNodo;
            primero.setSiguiente(nuevoNodo);
            ultimo = nuevoNodo;
            nuevoNodo.setAnterior(nuevoNodo);
            ;
        } else {
            ultimo.setSiguiente(nuevoNodo);
            nuevoNodo.setAnterior(ultimo);
            setUltimo(nuevoNodo);
            nuevoNodo.setSiguiente(primero);
            primero.setAnterior(nuevoNodo);
        }
    }

    public int getSize(){
        Node<T> temp = this.getPrimero();
        if (primero== null){
            return 0 ;
        }else {
            int size=1;
            while (temp.getSiguiente() != primero ){
                size+=1;
                temp= (Node<T>) temp.getSiguiente();
            }return size;
        }}

    public Node<T> getNode(int value){
        Node<T> temp = this.getPrimero();
        if ( temp == null|| value<0  ){
            return null ;
        }
        int count = 0 ;
        while ( temp.getSiguiente() != primero && count <value ){
            count++;
            temp= (Node<T>) temp.getSiguiente();
        }
        return temp ;
    }




    public void removeNode(Node<T> nodeToRemove) {
        if (nodeToRemove == null) {
            return; // Si el nodo a eliminar es nulo, no hay nada que hacer
        }

        // Si el nodo a eliminar es el único nodo en la lista
        if (nodeToRemove == primero && nodeToRemove == ultimo) {
            primero = null;
            ultimo = null;
        } else {
            // Si el nodo a eliminar es el primero de la lista
            if (nodeToRemove == primero) {
                primero = primero.getSiguiente();
            }
            // Si el nodo a eliminar es el último de la lista
            if (nodeToRemove == ultimo) {
                ultimo = ultimo.getAnterior();
            }

            // Actualizar los enlaces del nodo anterior y siguiente para eliminar el nodo de la lista
            Node<T> anterior = nodeToRemove.getAnterior();
            Node<T> siguiente = nodeToRemove.getSiguiente();
            anterior.setSiguiente(siguiente);
            siguiente.setAnterior(anterior);
        }
    }
}



