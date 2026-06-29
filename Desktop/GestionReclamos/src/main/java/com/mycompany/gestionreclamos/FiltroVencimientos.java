package com.mycompany.gestionreclamos;
import java.util.ArrayList;
import java.time.LocalDate;

public class FiltroVencimientos {
    
    public static void aplicarFiltro(ArrayList<Reclamo> lista) {
        // La variable "hoy" almacena la fecha inicial.
        LocalDate hoy = LocalDate.now();
        boolean huboCambios = false;
        
        System.out.println("\n*Aplicando Filtro de Vencimientos Automático *");
        
        // Recorre la lista Reclamo; si el estado del reclamo está "Resuelto", lo ignora y pasa al siguiente. - O(n).
        for (Reclamo reclamo : lista) {
            if (reclamo.getEstadoReclamo().equalsIgnoreCase("Resuelto")) {
                continue;
            }

            // Resta la fecha de hoy de la fecha límite para saber cuánto plazo le queda al reclamo.
            long diasRestantes = reclamo.getFechaLimite().toEpochDay() - hoy.toEpochDay();
            
            // Si faltan 3 días o menos y la prioridad aún no es "Alta"...
            if (diasRestantes <= 3 && !reclamo.getPrioridad().equalsIgnoreCase("Alta")) {
                
                // El reclamo se coloca como prioridad "Alta".
                reclamo.setPrioridad("Alta");
                
                // Registramos este evento automático en la pila de historial del reclamo.
                reclamo.getHistorialEstados().push("La Prioridad escalo a Alta automaticamente por vencimiento proximo (" + hoy + ")");
                
                System.out.println("Reclamo [" + reclamo.getCodigoUnico() + "] priorizado a Alta (Dias para vencer: " + diasRestantes + ")");
                huboCambios = true;
            }
        }
        // Si no hubo cambios, avisar de que no hubo cambios.
        if (!huboCambios) {
            System.out.println("No se encontraron reclamos proximos a vencer que requieran priorizacion.");
        }
    }
}
