public class ListaDobleEnlazada<T extends Comparable<T>> {
    private Node<T> primero;
    private Node<T> ultimo;

    public Node<T> getPrimero() {
        return primero;
    }

    public void setPrimero(Node<T> primero) {
        this.primero = primero;
    }

    public Node<T> getUltimo() {
        return ultimo;
    }

    public void setUltimo(Node<T> ultimo) {
        this.ultimo = ultimo;
    }

    public ListaDobleEnlazada() {
    }

    public void add (T value){
        Node<T> elnuevo =new Node<>(value);
        if (primero==null){
            setPrimero(elnuevo);
            setUltimo(elnuevo);
        }else{
            Node<T> X =this.getPrimero();
            while(X.getSiguiente()!=null && (X.getValue()).compareTo(value)<0){
                X= (Node<T>) X.getSiguiente();
            }if(X.getSiguiente()==null && (X).getValue().compareTo(value)<0){
                X.setSiguiente(elnuevo);
                setUltimo(elnuevo);
                elnuevo.setAnterior(X);
            }else{
                Node<T> anterior= (Node<T>) X.getAnterior();
                elnuevo.setSiguiente(X);
                elnuevo.setAnterior(anterior);
                X.setAnterior(elnuevo);
                if (anterior!=null) {
                    anterior.setSiguiente(elnuevo);
                }else{
                    setPrimero((elnuevo));
                    elnuevo.setSiguiente(X);
                    X.setAnterior(elnuevo);
                }
            }

        }
    }}






