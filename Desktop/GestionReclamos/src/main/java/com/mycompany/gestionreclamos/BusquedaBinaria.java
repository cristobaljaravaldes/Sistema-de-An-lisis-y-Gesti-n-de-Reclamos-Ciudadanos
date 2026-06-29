package com.mycompany.gestionreclamos;
import java.util.ArrayList;

// Clase que implementa el algoritmo de búsqueda
public class BusquedaBinaria {

    // Búsqueda binaria por código único - O(log n)
    // Usar cuando: la lista está ordenada y el volumen de reclamos es grande
    public static Reclamo buscarPorCodigo(ArrayList<Reclamo> lista, int codigoBuscado) {
        int izquierda = 0;
        int derecha = lista.size() - 1;

        while (izquierda <= derecha) {
            // Calculamos el índice del elemento central
            int medio = (izquierda + derecha) / 2;
            int codigoMedio = lista.get(medio).getCodigoUnico();

            if (codigoMedio == codigoBuscado) {
                return lista.get(medio); // Elemento encontrado
            } else if (codigoBuscado > codigoMedio) {
                izquierda = medio + 1; // Buscar en mitad derecha
            } else {
                derecha = medio - 1; // Buscar en mitad izquierda
            }
        }
        return null; // No encontrado
    }
}
