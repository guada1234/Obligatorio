package prog_2_adts.src.uy.edu.um.adt.hash;

public class HashImpl<K,V> implements HashTable<K,V> {
    private int size;
    private int has;
    private HashNode<K,V>[] list;

    public HashImpl(int size) {
        this.size = size;
        this.list = (HashNode<K,V>[]) new HashNode[size];
        this.has = 0;
    }

    public HashNode<K,V>[] getList() {
        return list;
    }

    public int getHas() {
        return has;
    }

    public void setHas(int has) {
        this.has = has;
    }

    public void addHas() {
        this.setHas(has+1);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setList(HashNode<K,V>[] list) {
        this.list = list;
    }


    public void add(K value, V data) {
        int hashcode = value.hashCode();
        int hc = (Math.abs(hashcode)) % size;

        // Verificar si el valor ya existe
        if (this.search(value) >= 0) {
            System.out.println("El valor ya existe en el hash");
            return;
        }

        // Redimensionar si es necesario
        if (this.getHas() == this.getSize()) {
            this.resize(this.size * 2);
            System.out.println("Se agrandó el hash a tamaño " + this.size + " porque no había suficiente espacio.");
        }

        // Encontrar la posición adecuada para insertar el nodo
        while (this.list[hc] != null) {
            hc = (hc + 1) % size;
        }

        // Insertar el nodo
        this.list[hc] = new HashNode<>(value, data);
        this.addHas();
    }

    public void add2(K value,V data) {
        HashNode<K,V> nodo= new HashNode<K,V>(value, data);
        int hashcode = value.hashCode();
        int hc = (Math.abs(hashcode))%size;
        if (list[hc] == null) {
            list[hc] = nodo;
            this.addHas();
        }
        else{
            if(this.getHas()==this.getSize()){
                this.resize(this.size*2);
                System.out.println("Se agrando el hash a tamano "+ this.size+" porque no habia suficiente esapcio.");
                list[size/2] = nodo;
                this.addHas();
            }
            else{
                if(this.search(value)>=0){
                    System.out.println("El valor ya existe en el hash");
                }
                else{
                    while (this.list[hc%size] != null) {
                        hc++;
                    }
                    this.list[hc%size] = nodo;
                    this.addHas();
                }
            }
        }
    }

    public void resize(int newSize) {
        int min= 0;
        if (newSize < size) {
            System.out.println("Valores se van a perder");
            min= newSize;
        }
        else{
            min= size;
        }
        HashNode<K,V>[] newList = new HashNode[newSize];
        for (int i = 0; i < min; i++) {
            newList[i] = list[i];
        }
        this.setSize(newSize);
        this.setList(newList);
    }

    public int search(K value) {
        int posicion = Math.abs(value.hashCode()) % size;
        int n = 0;

        while (list[(posicion + n) % size] != null && !list[(posicion + n) % size].getValue().equals(value) && n < this.size) {
            n++;
        }

        if (n == this.size || list[(posicion + n) % size] == null) {
            return -1;  // Devuelve -1 para indicar que el valor no fue encontrado
        } else {
            return (posicion + n) % size;  // Devuelve la posición donde se encontró el valor
        }
    }

    public void delete(int position)throws InformacionInvalida {
        if(position>this.size||position<0){
            throw new InformacionInvalida("");
        }
        this.list[position] = null;
        this.setHas(has-1);
    }

    public V getData(int position) throws InformacionInvalida {
        if(position>this.size){
            throw new InformacionInvalida("el tamaño es mayor al del hash");
        }
        return this.list[position].getData();
    }

    public HashNode<K, V> getNode(int position) throws InformacionInvalida {
        if(position>this.size){
            throw new InformacionInvalida("");
        }
        return this.list[position];
    }
    public boolean containsKey(Object key) {
        int position = Math.abs(key.hashCode()) % this.size;
        HashNode<K, V> node = this.getList()[position];

        while (node!= null) {
            if (node.getValue().equals(key)) {
                return true;
            }
            node = node. getNext();
        }

        return false;
    }
    public V get(K key) {
        for (HashNode<K, V> node : list) {
            if (node != null && node.getValue().equals(key)) {
                return node.getData();
            }
        }
        return null; // Clave no encontrada
    }
    public boolean contains(K value) {
        return search(value) != -1;
    }

}









