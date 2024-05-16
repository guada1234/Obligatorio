//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Listaenlazada<Integer> milistaenlazada = new Listaenlazada<>();

        // Agregar elementos a la lista
        milistaenlazada.add(1);
        milistaenlazada.add(2);
        milistaenlazada.add(3);
        milistaenlazada.add(4);

        // Mostrar la lista
        System.out.println("Lista:");
        mostrarListaInteger(milistaenlazada);

        // Eliminar un elemento en la posición 2 (tercer elemento)
        milistaenlazada.remove(2);

        // Mostrar la lista después de eliminar
        System.out.println("\nLista después de eliminar el elemento en la posición 2:");
        mostrarListaInteger(milistaenlazada);

        // Obtener un elemento en la posición 1 (segundo elemento)
        System.out.println("\nElemento en la posición 1 (segundo elemento): " + milistaenlazada.get(1));

        // Buscar un elemento
        System.out.println("¿El valor 3 está en la lista? " + milistaenlazada.encontrar(3));
        System.out.println("¿El valor 5 está en la lista? " + milistaenlazada.encontrar(5));

        // Agregar un elemento al principio
        milistaenlazada.addFirst(0);

        // Mostrar la lista después de agregar al principio
        System.out.println("\nLista después de agregar 0 al principio:");
        mostrarListaInteger(milistaenlazada);

        // Agregar un elemento al final
        milistaenlazada.addLast(5);

        // Mostrar la lista después de agregar al final
        System.out.println("\nLista después de agregar 5 al final:");
        mostrarListaInteger(milistaenlazada);


        ListaDobleEnlazada<Integer> milistadobleenlazada = new ListaDobleEnlazada<>();

        // Agregar elementos a la lista
        milistadobleenlazada.add(3);
        milistadobleenlazada.add(1);
        milistadobleenlazada.add(5);
        milistadobleenlazada.add(2);

        // Mostrar la lista hacia adelante
        System.out.println("Lista hacia adelante:");
        mostrarListaAdelante(milistadobleenlazada);

        ListaEncadenadaCircular<Integer> milistacircular = new ListaEncadenadaCircular<>();

        // Insertar elementos en la lista
        milistacircular.insertar(6);
        milistacircular.insertar(7);
        milistacircular.insertar(8);
        milistacircular.insertar(9);

        // Mostrar la lista
        System.out.println("Lista encadenada circular:");
        mostrarLista(milistacircular);

        ListaEncadenadaCircular<String> integrantes = new ListaEncadenadaCircular<>();
        integrantes.insertar("Juan");
        integrantes.insertar("María");
        integrantes.insertar("Pedro");
        integrantes.insertar("Ana");
        integrantes.insertar("Carlos");
        mostrarListaString(integrantes);

        JosefoGame juego = new JosefoGame();
        try {
            Lista<String> eliminados = juego.game(2, integrantes);
            mostrarListaString((Listaenlazada<String>) eliminados);
            mostrarListaString(integrantes);

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }


        Listaenlazada<Integer> milista = new Listaenlazada<Integer>();
        milista.AddInOrder(4);
        milista.AddInOrder(8);
        milista.AddInOrder(3);
        milista.AddInOrder(6);

        // Imprimir la lista después de agregar en orden
        System.out.println("\nLista después de agregar en orden:");
        mostrarListaInteger(milista);

        Listaenlazada<String> listaOriginal = new Listaenlazada<>();
        listaOriginal.add("Objeto1");
        listaOriginal.add("Objeto2");
        listaOriginal.add("Objeto3");
        listaOriginal.add("Objeto4");
        mostrarListaString(listaOriginal);

        // Crear una lista de posiciones
        Listaenlazada<Integer> listaPosiciones = new Listaenlazada<Integer>();
        listaPosiciones.AddInOrder(0);
        listaPosiciones.AddInOrder(2);
        listaPosiciones.AddInOrder(1);
        mostrarListaInteger(listaPosiciones);


        // Visualizar la lista original según las posiciones indicadas
        try{
            Listaenlazada<String> listaVisualizada = listaOriginal.visualizar(listaPosiciones);
            System.out.println("Lista visualizada:");
            mostrarListaString(listaVisualizada);
        }catch(IllegalArgumentException e){
            System.out.println("posicion invalida");}

        // Imprimir la lista visualizada




    }


    // Método para mostrar los elementos de la lista
    public static void mostrarListaInteger (Listaenlazada <Integer> lista) {
        Node<Integer> current = lista.getPrimero();
        while (current != null) {
            System.out.print(current.getValue() + " ");
            current = current.getSiguiente();
        }
        System.out.println();
    }
    public static void mostrarListaString (Listaenlazada <String> lista) {
        Node<String> current = lista.getPrimero();
        while (current != null) {
            System.out.print(current.getValue() + " ");
            current = current.getSiguiente();
        }
        System.out.println();
    }

    // Método para mostrar la lista hacia adelante
    public static void mostrarListaAdelante (ListaDobleEnlazada < Integer > lista) {
        Node<Integer> current = lista.getPrimero();
        while (current != null) {
            System.out.print(current.getValue() + " ");
            current = current.getSiguiente();
        }
        System.out.println();
    }
    public static void mostrarLista(ListaEncadenadaCircular<Integer> lista) {
        Node<Integer> current = lista.getPrimero();
        if (current != null) {
            do {
                System.out.print(current.getValue() + " ");
                current = current.getSiguiente();
            } while (current != lista.getPrimero());
        }
        System.out.println();
    }

    public static void mostrarListaString(ListaEncadenadaCircular<String> lista) {
        Node<String> current = lista.getPrimero();
        if (current != null) {
            do {
                System.out.print(current.getValue() + " ");
                current = current.getSiguiente();
            } while (current != lista.getPrimero());
        }
        System.out.println();
    }



}