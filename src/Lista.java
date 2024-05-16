
//la interface trabaja con un tipo solo "T" que es el tipo
//queremos que siempre agregue del tipo T
public interface Lista<T> {

    public void add (T value);
    //<T> nombrando la clase
    public void remove(int position);
    public T get (int position);

}


