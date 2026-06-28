package com.mycompany.gestionreclamos;
import java.util.LinkedList;
import java.util.Queue;
/**
 *
 * @author yordan
 */
public class ColaPendientes {
    private Queue<Reclamo> cola;
    
    public ColaPendientes(){
        cola = new LinkedList<>();
    }
    
    public void agregarReclamo(Reclamo reclamo){
        cola.offer(reclamo);
        System.out.println("\n*** Reclamo agregado a la cola de atención ***");
    }
    
    public Reclamo atenderSiguienteReclamo(){
        if(cola.isEmpty()){
            System.out.println("*** No hay reclamos pendientes por atender ***");
            return null;
        }
        Reclamo atendido = cola.poll();
        System.out.println("Reclamo atendido: " + atendido);
        return atendido;
    }
    
    
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
    
    public Reclamo buscarPorCodigo(int codigoUnico) {
        for (Reclamo reclamo : cola) {
            if (reclamo.getCodigoUnico() == codigoUnico) {
                return reclamo;
            }
        }
        return null;
    }
   
    public boolean estaVacia(){
        return cola.isEmpty();
    } 
}