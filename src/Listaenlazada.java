import java.time.format.TextStyle;

public class Listaenlazada<T extends Comparable<T>> implements Lista<T> { //comparable: hay un metodo especifico para comparar los valores. Es otro tipo de clase,
    // si la extendemos, tenemos un metodo para comparar elementos del mismo tipo.
    private Node<T> primero;    //nosreferimos a la clase por lo que lleva T

    public Node<T> getPrimero() {
        return primero;
    }

    public void setPrimero(Node<T> primero) {
        this.primero = primero;
    }

    public Listaenlazada() {
    }


    @Override
    public void add(T value) {
        Node<T> X = this.primero; //tomamos el primer nodo
        Node<T> nuevo = new Node<T>(value); //creamos el nodo con el valor pasado
        if (X == null) {
            this.setPrimero(nuevo);
        } else {
            while (X.getSiguiente() != null) {
                X = (Node<T>) X.getSiguiente();
            }
            X.setSiguiente(nuevo);
        }

    }

    @Override
    public void remove(int position) {
        Node<T> X = this.getPrimero(); //tomamos el primer nodo
        if (position == 0) {
            this.setPrimero((Node<T>) X.getSiguiente());
        } else {
            for (int i = 0; i < (position - 1); i++) {
                X = (Node<T>) X.getSiguiente(); //es el anterior al que quiero eliminar
            }
            Node<T> tempeliminado = (Node<T>) X.getSiguiente();
            X.setSiguiente(tempeliminado.getSiguiente());
        }
    }


    @Override
    public T get(int posicion) {
        Node<T> X = this.getPrimero();
        for (int i = 0; i <= posicion; i++) ;
        {
            X = (Node<T>) X.getSiguiente();
        }
        return X.getValue();
    }

    public boolean encontrar(T value) {
        Node<T> X = this.getPrimero();
        while (X != null) {
            if (X.getValue() == value) {
                return true;
            } else {
                X = (Node<T>) X.getSiguiente();
            }
        }
        return false;
    }


    public void addFirst(T value) {
        Node<T> nuevo = new Node<T>(value);
        Node<T> X = this.getPrimero();
        nuevo.setSiguiente(X);
        this.setPrimero((Node<T>) nuevo);


    }

    public void addLast(T value) {
        Node<T> X = this.getPrimero();
        Node<T> nuevo = new Node<T>(value);
        if (X == null) {
            this.setPrimero(nuevo);
        } else {
            while (X.getSiguiente() != null) {
                X = (Node<T>) X.getSiguiente();
            }
            X.setSiguiente(nuevo);

        }
    }

    public void AddInOrder(T value) {
        Node<T> newNode = new Node<>(value);
        if (primero == null || value.compareTo(primero.getValue()) < 0) {
            newNode.setSiguiente((Node<T>) primero);
            setPrimero(newNode);
            return;
        }
        Node<T> temp = this.getPrimero();
        while (temp.getSiguiente() != null && value.compareTo(temp.getSiguiente().getValue()) > 0) {
            temp = (Node<T>) temp.getSiguiente();
        }
        if (temp.getSiguiente() == null && value.compareTo(temp.getValue()) > 0) {
            temp.setSiguiente(newNode);
        } else {
            newNode.setSiguiente(temp.getSiguiente());
            temp.setSiguiente(newNode);
        }
    }

    public int getsize() {
        int i = 0;
        if (primero == null) {
            return i;
        } else {
            Node<T> X = getPrimero();
            while (X.getSiguiente() != null) {
                i++;
                X=X.getSiguiente();
            }
            return i;
        }
    }


    public Listaenlazada<T> visualizar(Listaenlazada<Integer> p) throws IllegalArgumentException {
        Listaenlazada<T> resultado = new Listaenlazada<T>(); //la lista con los elementos "sacados"
        Node<Integer> X = p.getPrimero(); //la lista ordenada
        Node<Integer> U = p.getPrimero();
        while (U.getSiguiente() != null) {
            U = U.getSiguiente();
        }
        int valor = U.getValue();
        if (valor <= this.getsize()) {
            while (X != null) {
                int posicion = (int) X.getValue(); //tomamos el valor de p como la posicion q buscamos en this
                Node<T> temp = this.getPrimero();
                for (int i = 0; i < posicion; i++) { //encontramos el nodo en la posicion tomada de p
                    temp = temp.getSiguiente();
                }
                if (resultado.getPrimero() == null) { //creamos y agregamos el valor a la lista resultante
                    Node<T> nuevo=new Node<T>(temp.getValue());
                    resultado.setPrimero(nuevo);
                } else {
                    resultado.addLast(temp.getValue());
                }
                X = X.getSiguiente();
            }
            return resultado;
        } else {
            throw new IllegalArgumentException("indice supera tamanio de la lista");
        }
    }
    //funcion auxiliar para el ejercicio 9 para darnos la posicion del valor
    private  int find(T value){
        if (primero == null){
            return 0;
        }
        int pos = 0;
        Node <T> temp = primero;
        while (temp.getSiguiente()!=null){
            if(primero.getValue().equals(value)){
                return pos;
            }
            pos += 1;
            temp = temp.getSiguiente();
        }
        if (temp.getValue().equals(value)) { //hacemos la modificacion para q verifique el ultimo nodo
         return pos;
        }
        return -1;
    }

    public Listaenlazada<T> compararIguales(Listaenlazada<Integer> p){
        Listaenlazada<T> resultado = new Listaenlazada<T>();
        if(p.getPrimero() != null && this.getPrimero() != null) {
            int posicion1 = p.getsize();
            int posicion2 = this.getsize();
            Node<T> primero2 = this.getPrimero();
            Node<T> primero1 = (Node<T>) p.getPrimero();

            for (int i = 0; i < posicion1; i++) {
                for (int j = 0; j < posicion2; j++) {
                    if (primero2.getValue() == primero1.getValue()) {
                        if (resultado.getPrimero() == null) { //creamos y agregamos el valor a la lista resultante
                            resultado.setPrimero(primero1);
                        } else {
                            resultado.addLast(primero1.getValue());
                        }
                    }
                }
            }
        }
        else {
            return null;
        }

        return resultado;
    }
    //public Listaenlazada<T> compararDistintos(Listaenlazada<Integer> p){
     //   if(this.compararIguales(p) == null){
    //        Listaenlazada<T> resultado1 = new Listaenlazada<T>();
    //        int posicion1 = p.getsize();
    //        int posicion2 = this.getsize();
     //       Node<T> primero2 = this.getPrimero();
       //     Node<T> primero1 = (Node<T>) p.getPrimero();
         //   for (int i = 0; i < posicion1; i++) {
     //           if(resultado1.getPrimero()== null){
     //               resultado1.setPrimero(primero1);
       //         }
      //          else{resultado1.addLast(primero1.getValue());}}

        //    for (int j = 0; j < posicion2; j++) {
          //      if(resultado1.getPrimero()== null){
           //         resultado1.setPrimero(primero2);
             //   }
               // else{resultado1.addLast(primero2.getValue());}}} else if () {

        //}

    }



    //}


//}






//Visualizar:
//como es la interface a nuestros ojosno existe la interface, por lo que podemos hacer un for y no movernos con los nodos
//usamos try y catch, cuando usamos una funcion dentro de otra, que sabemos que se puede romper, sino lo haceos en el main
// public Listaenlazada<T> intercambiar(Object objeto, int direccion){







