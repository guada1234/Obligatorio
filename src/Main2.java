public class Main2 {
    //ejercicio 10a
    public static void main(String[] args) {
        Listaenlazada<Integer> lista1 = new Listaenlazada<>();
        Listaenlazada<Integer> lista2 = new Listaenlazada<>();
        Listaenlazada<Integer> resultado = lista2.compararIguales(lista1);


        // Caso 4: Elementos iguales en ambas listas
        lista2.addLast(2);
        lista2.addLast(3);
        resultado = lista2.compararIguales(lista1);
        System.out.println("\nCaso 4: Elementos iguales en ambas listas");
        System.out.println("Resultado esperado: Lista con elementos [2, 3]");
        System.out.println("Resultado obtenido: " + resultado);

        // Caso 5: Múltiples elementos iguales en ambas listas
        lista1.addLast(2);
        lista1.addLast(3);
        resultado = lista2.compararIguales(lista1);
        System.out.println("\nCaso 5: Múltiples elementos iguales en ambas listas");
        System.out.println("Resultado esperado: Lista con elementos [2, 3, 2, 3]");
        System.out.println("Resultado obtenido: " + resultado);

        // Caso 6: Elementos iguales en diferentes posiciones de las listas
        lista2.addFirst(1);
        lista2.addLast(4);
        resultado = lista2.compararIguales(lista1);
        System.out.println("\nCaso 6: Elementos iguales en diferentes posiciones de las listas");
        System.out.println("Resultado esperado: Lista con elementos [2, 3]");
        System.out.println("Resultado obtenido: " + resultado);
    }
}
