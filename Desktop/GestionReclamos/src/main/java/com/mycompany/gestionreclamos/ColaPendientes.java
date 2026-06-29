package com.mycompany.gestionreclamos;
import java.util.LinkedList;
import java.util.Queue;
/**
 * Clase que contiene la lógica de la cola de reclamos pendientes.
 * @author yordan
 */
public class ColaPendientes {
    //Almacena objetos de tipo Reclamo en orden de llegada
    private Queue<Reclamo> cola;
    
    //Constructor: inicializa la cola de reclamos utilizando una lista enlazada - O(1)
    public ColaPendientes(){
        cola = new LinkedList<>();
    }
    
    //Agrega un nuevo reclamo al final de la cola de atención
    public void agregarReclamo(Reclamo reclamo){
        cola.offer(reclamo);
        System.out.println("\n*** Reclamo agregado a la cola de atencion ***");
    }
    
    //Obtiene y atiende el reclamo que está al principio de la cola
    public Reclamo atenderSiguienteReclamo(){
        if(cola.isEmpty()){
            System.out.println("*** No hay reclamos pendientes por atender ***");
            return null;
        }
        Reclamo atendido = cola.poll();
        System.out.println("Reclamo atendido: " + atendido);
        return atendido;
    }
    
    //Muestra la lista completa de reclamos en espera - O(n)
    public void mostrarReclamos(){
        if(cola.isEmpty()){
            System.out.println("\n*** No hay reclamos activos en la cola ***");
        } else {
            System.out.println("\n Reclamos en espera: (en orden de llegada)");
            for (Reclamo reclamo : cola) {
                System.out.println("" + reclamo);
            }
        }
    }
    
    //Busca un reclamo en la cola por su código único - O(n)
    public Reclamo buscarPorCodigo(int codigoUnico) {
        for (Reclamo reclamo : cola) {
            if (reclamo.getCodigoUnico() == codigoUnico) {
                return reclamo;
            }
        }
        return null;
    }
    
   //Verifica si existen reclamos pendientes
    public boolean estaVacia(){
        return cola.isEmpty();
    } 
}