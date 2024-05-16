public class JosefoGame {
    public Lista<String> game(int m, ListaEncadenadaCircular<String > collIntegrantes) throws IllegalArgumentException {
        Listaenlazada eliminados = new Listaenlazada();
        if (m > collIntegrantes.getSize()) {
            throw new IllegalArgumentException("el valor de m debe ser menor que el valor de integrantes ");
        }
        Node<String> current=collIntegrantes.getPrimero();
        while (collIntegrantes.getSize() > 1) {
            // Avanzar 'm' pasos
            for (int i = 0; i < m - 1; i++) {
                current = current.getSiguiente();
            }

            // Obtener el siguiente nodo a eliminar
            Node<String> nodoASacar = current.getSiguiente();

            // Agregar el valor del nodo a la lista de eliminados
            eliminados.add(nodoASacar.getValue());

            // Eliminar el nodo de la lista circular
            collIntegrantes.removeNode(nodoASacar);

            // Establecer el nodo actual al siguiente después del nodo eliminado
            current = nodoASacar.getSiguiente();
        }

        return eliminados;
    }
}


