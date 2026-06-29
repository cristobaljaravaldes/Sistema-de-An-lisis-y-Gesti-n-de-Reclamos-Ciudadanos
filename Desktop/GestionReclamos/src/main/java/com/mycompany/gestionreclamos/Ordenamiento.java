package com.mycompany.gestionreclamos;
import java.util.ArrayList;
import java.time.LocalDate;

public class Ordenamiento {

    // Método Burbuja - O(n²).
    public static void burbuja(ArrayList<Reclamo> lista) {
        int n = lista.size();
        
        // Ciclos externo e interno.
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                
                // Si el código del primer reclamo actual es mayor al código siguiente...
                if (lista.get(j).getCodigoUnico() > lista.get(j + 1).getCodigoUnico()) {
                    
                    // Se realiza el intercambio.
                    Reclamo temp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, temp);
                }
            }
        }
        System.out.println("\n* Lista ordenada por Codigo *");
    }

    // Método Quick Sort - O(n log n).
    public static void quick(ArrayList<Reclamo> lista, int primero,int ultimo) {
        int i, j, pivote;
        Reclamo auxiliar;

        i = primero; // Puntero izquierdo.
        j = ultimo; // Puntero derecho.
        
        // Se calcula la posición central y se obtiene un codigoUnico para usarlo como pivote.
        pivote = lista.get((primero + ultimo) / 2).getCodigoUnico();
        
        do {
            // Mientras que el codigoUnico del lado izquierdo sea menor que el pivote...
            while (lista.get(i).getCodigoUnico() < pivote) {
                // El puntero izquierdo avanza.
                i++;
            }
            // Mientras que el codigoUnico del lado derecho sea mayor que el pivote...
            while (lista.get(j).getCodigoUnico() > pivote) {
                // El puntero derecho retrocede.
                j--;
            }
            
            // Si el puntero izquierdo es menor o igual al puntero derecho...
            if (i <= j) {
                // Se realiza un intercambio.
                auxiliar = lista.get(i);
                lista.set(i, lista.get(j));
                lista.set(j, auxiliar);
                i++;
                j--;
            }
        } 
        
        // Mientras que el puntero izquierdo sea menor o igual al puntero derecho...
        while (i <= j);
        
        // Recursividad (Divide and Conquer).
        if (primero < j) {
            // Ordena la mitad izquierda.
            quick(lista, primero, j);
        }
        if (i < ultimo) {
            // Ordena la mitad derecha.
            quick(lista, i, ultimo);
        }
    }
    
    // Método Burbuja para ordenar por fecha límite - O(n²).
    public static void burbujaPorFechaLimite(ArrayList<Reclamo> lista) {
        int n = lista.size();
        // Ciclos externo e interno.
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                
                // Si la fecha actual es mayor a la siguiente...
                if (lista.get(j).getFechaLimite().isAfter(lista.get(j + 1).getFechaLimite())) {
                    
                    // Se realiza el intercambio.
                    Reclamo temp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, temp);
                }
            }
        }
    System.out.println("\n*Lista ordenada por Fecha limite*");
}

// Método auxiliar para darle un peso numérico a la prioridad O(1).
    private static int obtenerPesoPrioridad(String prioridad) {
        //Si el dato viene vacío, retorna a 0.
        if (prioridad == null) return 0;
        
        // Convierte a minúsculas para evitar errores si tipean "ALTA" o "Alta".
        switch (prioridad.toLowerCase()) {
            case "alta": 
                return 3;
            case "media": 
                return 2;
            case "baja": 
                return 1;
            default: 
                return 0;
        }
    }

    // Método Quick Sort por prioridad - O(n log n).
    public static void quickPorPrioridad(ArrayList<Reclamo> lista, int primero, int ultimo) {
        int i = primero, j = ultimo;
        
        // Obtenemos el peso numérico del pivote en lugar del String.
        int pivote = obtenerPesoPrioridad(lista.get((primero + ultimo) / 2).getPrioridad());
        
        do {
            // Ordenamos de mayor a menor para que "Alta" quede al inicio.
            while (obtenerPesoPrioridad(lista.get(i).getPrioridad()) > pivote) i++;
            while (obtenerPesoPrioridad(lista.get(j).getPrioridad()) < pivote) j--;
            
            if (i <= j) {
                // Se realiza el intercambio.
                Reclamo aux = lista.get(i);
                lista.set(i, lista.get(j));
                lista.set(j, aux);
                i++; j--;
            }
        } while (i <= j);
        
        // Recursividad (Divide and Conquer)
        if (primero < j) quickPorPrioridad(lista, primero, j);
        if (i < ultimo) quickPorPrioridad(lista, i, ultimo);
    
    }
// Método burbuja para ordenar por tipo de reclamo alfabéticamente - O(n²)
    public static void burbujaPorTipo(ArrayList<Reclamo> lista) {
        int n = lista.size();
        // Ciclos externo e interno.
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                
                // Si el tipo actual es alfabéticamente mayor que el siguiente...
                if (lista.get(j).getTipoReclamo().compareToIgnoreCase(lista.get(j + 1).getTipoReclamo()) > 0) {
                    
                    // Se realiza el intercambio.
                    Reclamo temp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, temp);
                }
            }
        }
        System.out.println("\n* Lista ordenada por Tipo de Reclamo *");
    }

    // Quick Sort por fecha de ingreso - O(n log n) 
    public static void quickPorFechaIngreso(ArrayList<Reclamo> lista, int primero, int ultimo) {
        int i = primero, j = ultimo;
        
        // Se extrae la fecha central para usarla como pivote de comparación.
        LocalDate pivote = lista.get((primero + ultimo) / 2).getFechaRegistro();
        
        do {
            // Avanza si la fecha es anterior al pivote.
            while (lista.get(i).getFechaRegistro().isBefore(pivote)) i++;
            // Retrocede si la fecha es posterior al pivote.
            while (lista.get(j).getFechaRegistro().isAfter(pivote)) j--;
            
            if (i <= j) {
                
                // Se realiza el intercambio.
                Reclamo aux = lista.get(i);
                lista.set(i, lista.get(j));
                lista.set(j, aux);
                i++; j--;
            }    
        } 
        // Mientras que i sea mayor o igual a j...
        while (i <= j);
        
        // Recursividad (Divide and Conquer)
        if (primero < j) quickPorFechaIngreso(lista, primero, j);
        if (i < ultimo) quickPorFechaIngreso(lista, i, ultimo);
        
        System.out.println("\n* Lista ordenada por Fecha de Ingreso *");
    }   
}   