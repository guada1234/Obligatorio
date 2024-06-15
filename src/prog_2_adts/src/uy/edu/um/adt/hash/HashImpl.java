package prog_2_adts.src.uy.edu.um.adt.hash;

public class HashImpl<K, V> implements HashTable<K, V> {
    private int cantidadElementos;
    private double LOAD_FACTOR = 0.77;
    private int capacity;
    HashNode<K, V>[] table;

    public HashImpl(int capacity) {
        this.capacity = capacity; // cantidad de fechas
        this.table = new HashNode[capacity];
        this.cantidadElementos = 0;
    }

    public int getCantidadElementos() {
        return cantidadElementos;
    }

    public double getLOAD_FACTOR() {
        return LOAD_FACTOR;
    }

    public int getCapacity() {
        return capacity;
    }

    public HashNode<K, V>[] getTable() {
        return table;
    }

    @Override
    public void put(K key, V value) throws InformacionInvalida {
        if (key == null) {
            throw new InformacionInvalida("");
        }
        int hashCode = Math.abs(key.hashCode());
        int posicion = hashCode % table.length;
        if(posicion>capacity){
            resize(posicion);
        }
        while (table[posicion] != null) {
            posicion = posicion++;
            if(posicion == table.length){
                posicion = 0;
            }
        }
        table[posicion] = new HashNode<>(key, value);
    }



    public void resize(int nueva_capacidad) throws InformacionInvalida {
        int newCapacity = nueva_capacidad;
        HashNode<K, V>[] newTable = new HashNode[newCapacity];
        HashNode<K, V>[] oldTable = table;
        this.table=newTable;
        this.cantidadElementos = 0;
        for (int i = 0; i < oldTable.length; i++) {
            if(oldTable[i]!=null) {
                put(oldTable[i].getKey(), oldTable[i].getData());
            }
        }
        capacity = newCapacity;
    }


    @Override
    public boolean contains(K key) throws InformacionInvalida {
        if (key == null) {
            throw new InformacionInvalida("");
        }
        int hashCode = Math.abs(key.hashCode());
        int posicion = hashCode % table.length;
        boolean encontrado = false;
        int contador = 0;
        if (table[posicion] == null) {
            posicion = this.getNotNullPosition(posicion);
            if(table[posicion].getKey().equals(key)){
                encontrado = true;
            }
        }
        if(table[posicion].getKey() == key){
            encontrado = true;
        }
            while (!encontrado) {
                posicion = (posicion+1)% table.length;
                contador++;
                if (table[posicion] == null) {
                    posicion = this.getNotNullPosition(posicion);
                }
                if(table[posicion].getKey().equals(key)){
                    encontrado = true;
                }
                if (contador == table.length) {
                    break;
                }
                if (posicion == table.length && contador < table.length) {
                    posicion = 0;
                }
            }
        return encontrado;
    }

    public int getNotNullPosition(int posicion) {
        while (table[posicion]==null) {
            posicion = (posicion+1)% table.length;
        }
        return posicion;
    }

    @Override
    public void remove(K key) throws InformacionInvalida  {
        if (key == null) {
            throw new InformacionInvalida("");
        }
        int hashCode = Math.abs(key.hashCode());
        int posicion = hashCode % table.length;
        boolean encontrado = false;
        int contador = 0;
        if (table[posicion] == null) {
            posicion = this.getNotNullPosition(posicion);
            if(table[posicion].getKey().equals(key)){
                table[posicion] = null;
            }
        }
        if(table[posicion].getKey().equals(key)){
            encontrado = true;
            table[posicion] = null;
        }
        while (!encontrado) {
            posicion = (posicion+1)% table.length;
            contador++;
            if (table[posicion] == null) {
                posicion = this.getNotNullPosition(posicion);
            }
            if(table[posicion].getKey().equals(key)){
                encontrado = true;
                table[posicion] = null;
            }
            if (contador == table.length) {
                break;
            }
            if (posicion == table.length && contador < table.length) {
                posicion = 0;
            }
        }
    }
    @Override
    public int findPosition(K key) throws InformacionInvalida  {
        int res = -1;
        boolean encontrado = false;
        if (key == null) {
            throw new InformacionInvalida("");
        }
        int hashCode = key.hashCode();
        int posicion = hashCode % table.length;
        int contador = 0;
        if (table[posicion] == null) {
            posicion = this.getNotNullPosition(posicion);
            if(table[posicion].getKey().equals(key)){
                res = posicion;
            }
        }
        if(table[posicion].getKey() == key){
            encontrado = true;
            res = posicion;
        }
        while (!encontrado) {
            posicion = (posicion+1)% table.length;
            contador++;
            if (table[posicion] == null) {
                posicion = this.getNotNullPosition(posicion);
            }
            if(table[posicion].getKey().equals(key)){
                encontrado = true;
                res = posicion;
            }
            if (contador == table.length) {
                break;
            }
            if (posicion == table.length && contador < table.length) {
                posicion = 0;
            }
        }
        if(res == -1){
            throw new InformacionInvalida("");
        }
        return res;
    }
    public V searchT(K key) throws InformacionInvalida{
        V res = null;
        boolean encontrado = false;
        if (key == null) {
            throw new InformacionInvalida("");
        }
        int hashCode = key.hashCode();
        int posicion = hashCode % table.length;
        int contador = 0;
        if (table[posicion] == null) {
            posicion = this.getNotNullPosition(posicion);
            if(table[posicion].getKey().equals(key)){
                res = table[posicion].getData();
            }
        }
        if(table[posicion].getKey().equals(key)){
            encontrado = true;
            res = table[posicion].getData();
        }
        while (!encontrado) {
            posicion = (posicion+1)% table.length;
            contador++;
            if (table[posicion] == null) {
                posicion = this.getNotNullPosition(posicion);
            }
            if(table[posicion].getKey().equals(key)){
                encontrado = true;
                res = table[posicion].getData();
            }
            if (contador == table.length) {
                break;
            }
            if (posicion == table.length && contador < table.length) {
                posicion = 0;
            }
        }
        if(res == null){
            throw new InformacionInvalida("");
        }
        return res;
    }
    public V getData(int position) throws InformacionInvalida {
        if(position>this.capacity){
            throw new InformacionInvalida("");
        }
        return this.table[position].getData();
    }


}






