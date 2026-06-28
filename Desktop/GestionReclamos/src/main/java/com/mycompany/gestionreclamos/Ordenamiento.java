package com.mycompany.gestionreclamos;
import java.util.ArrayList;

public class Ordenamiento {

    // Método Burbuja 
    public static void burbuja(ArrayList<Reclamo> lista) {
        int n = lista.size();
        
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                
                // Comparamos el código único de un reclamo con el siguiente
                if (lista.get(j).getCodigoUnico() > lista.get(j + 1).getCodigoUnico()) {
                    
                    // Intercambio de los objetos Reclamo completos
                    Reclamo temp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, temp);
                }
            }
        }
        System.out.println("\n* Lista ordenada por Código *");
    }

    // Método Quick Sort 
    public static void quick(ArrayList<Reclamo> lista, int primero,int ultimo) {
        int i, j, indice;
        Reclamo auxiliar;

        i = primero;
        j = ultimo;
        // Obtenemos el código único del reclamo que está en la mitad de la lista
        indice = lista.get((primero + ultimo) / 2).getCodigoUnico();
        
        do {
            // Buscamos un elemento mayor al indice por la izquierda
            while (lista.get(i).getCodigoUnico() < indice) {
                i++;
            }
            // Buscamos un elemento menor al indice por la derecha
            while (lista.get(j).getCodigoUnico() > indice) {
                j--;
            }
            
            // Acá se hace el cambio 
            if (i <= j) {
                auxiliar = lista.get(i);
                lista.set(i, lista.get(j));
                lista.set(j, auxiliar);
                i++;
                j--;
            }
        } while (i <= j);
        
        // Recursividad (Divide and Conquer)
        if (primero < j) {
            quick(lista, primero, j);
        }
        if (i < ultimo) {
            quick(lista, i, ultimo);
        }
    }
    // Metodo Burbuja para ordenar por fecha límite 
public static void burbujaPorFechaLimite(ArrayList<Reclamo> lista) {
    int n = lista.size();
    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < n - i - 1; j++) {
            if (lista.get(j).getFechaLimite()
                    .isAfter(lista.get(j + 1).getFechaLimite())) {
                Reclamo temp = lista.get(j);
                lista.set(j, lista.get(j + 1));
                lista.set(j + 1, temp);
            }
        }
    }
    System.out.println("\n*Lista ordenada por Fecha Límite*");
}

// Metodo Quick Sort por prioridad

// Método auxiliar para darle un peso numérico a la prioridad
    private static int obtenerPesoPrioridad(String prioridad) {
        if (prioridad == null) return 0;
        
        // Convertimos a minúsculas para evitar errores si alguien escribió "ALTA" o "Alta"
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

    // Método Quick Sort por prioridad
    public static void quickPorPrioridad(ArrayList<Reclamo> lista, int primero, int ultimo) {
        int i = primero, j = ultimo;
        
        // Obtenemos el peso numérico del indice  en lugar del String
        int indice = obtenerPesoPrioridad(lista.get((primero + ultimo) / 2).getPrioridad());
        
        do {
            // Ordenamos de mayor a menor (3 -> 2 -> 1) para que "Alta" quede al inicio
            while (obtenerPesoPrioridad(lista.get(i).getPrioridad()) > indice) i++;
            while (obtenerPesoPrioridad(lista.get(j).getPrioridad()) < indice) j--;
            
            if (i <= j) {
                // Intercambio (Swap)
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
    }   