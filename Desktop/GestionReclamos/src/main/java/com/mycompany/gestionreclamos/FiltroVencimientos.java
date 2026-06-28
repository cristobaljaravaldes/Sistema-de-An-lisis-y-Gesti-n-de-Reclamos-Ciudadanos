package com.mycompany.gestionreclamos;
import java.util.ArrayList;
import java.time.LocalDate;

public class FiltroVencimientos {
    
    // Método para detectar reclamos próximos a vencer (3 días o menos) y subir su prioridad
    public static void aplicarFiltro(ArrayList<Reclamo> lista) {
        LocalDate hoy = LocalDate.now();
        boolean huboCambios = false;
        
        System.out.println("\n*Aplicando Filtro de Vencimientos Automático *");
        
        //Recorre la lista Reclamo, si el estado del reclamo esta "resuelto", se salta todo el filtro
        for (Reclamo reclamo : lista) {
            if (reclamo.getEstadoReclamo().equalsIgnoreCase("Resuelto")) {
                continue;
            }

            // Calculamos los días restantes convirtiendo las fechas a números (días totales)
            long diasRestantes = reclamo.getFechaLimite().toEpochDay() - hoy.toEpochDay();
            
            // Si faltan 3 días o menos, y la prioridad aún no es "Alta"
            if (diasRestantes <= 3 && !reclamo.getPrioridad().equalsIgnoreCase("Alta")) {
                
                reclamo.setPrioridad("Alta");
                // Registramos este evento automático en la pila de historial del reclamo
                reclamo.getHistorialEstados().push("Prioridad escalada a ALTA automáticamente por vencimiento próximo (" + hoy + ")");
                
                System.out.println("-> ¡Alerta! Reclamo [" + reclamo.getCodigoUnico() + "] priorizado a ALTA (Días para vencer: " + diasRestantes + ")");
                huboCambios = true;
            }
        }
        
        if (!huboCambios) {
            System.out.println("-> No se encontraron reclamos próximos a vencer que requieran priorización.");
        }
    }
}
