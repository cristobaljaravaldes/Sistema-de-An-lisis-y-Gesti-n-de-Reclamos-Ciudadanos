package com.mycompany.gestionreclamos;
import java.util.ArrayList;

// Clase que gestiona la lista dinámica principal de reclamos del sistema
public class ListaReclamos {
    private ArrayList<Reclamo> registroMunicipal;

    public ListaReclamos() {
        this.registroMunicipal = new ArrayList<>();
    }

    // Agrega un nuevo reclamo al registro
    public void agregarReclamo(Reclamo nuevoReclamo) {
        this.registroMunicipal.add(nuevoReclamo);
    }

    // Búsqueda secuencial por RUT - O(n) - usar cuando la lista no está ordenada
    public Reclamo buscarPorRut(String rutBuscar) {
        for (int i = 0; i < registroMunicipal.size(); i++) {
            if (registroMunicipal.get(i).getRutCiudadano().equals(rutBuscar)) {
                return registroMunicipal.get(i);
            }
        }
        return null;
    }

    // Búsqueda secuencial por código - O(n) - usar cuando la lista no está ordenada
    public Reclamo buscarPorCodigo(int codigo) {
        for (int i = 0; i < registroMunicipal.size(); i++) {
            if (registroMunicipal.get(i).getCodigoUnico() == codigo) {
                return registroMunicipal.get(i);
            }
        }
        return null;
    }

    // Elimina un reclamo por código - O(n)
    public boolean eliminarReclamo(int codigo) {
        for (int i = 0; i < registroMunicipal.size(); i++) {
            if (registroMunicipal.get(i).getCodigoUnico() == codigo) {
                registroMunicipal.remove(i);
                return true; // Eliminado con éxito
            }
        }
        return false; // No encontrado
    }

    // Modifica la descripción y tipo de un reclamo existente - O(n)
    public boolean modificarReclamo(int codigo, String nuevoTipo, String nuevaDescripcion) {
        Reclamo r = buscarPorCodigo(codigo);
        if (r != null) {
            r.setTipoReclamo(nuevoTipo);
            r.setDescripcion(nuevaDescripcion);
            r.getHistorialEstados().push("Reclamo modificado (" + java.time.LocalDate.now() + ")");
            return true;
        }
        return false;
    }

    // Retorna la lista completa de reclamos
    public ArrayList<Reclamo> getRegistroMunicipal() {
        return registroMunicipal;
    }

    // Muestra todos los reclamos registrados
    public void mostrarTodos() {
        if (registroMunicipal.isEmpty()) {
            System.out.println("\n*** No hay reclamos registrados ***");
        } else {
            System.out.println("\n=== RECLAMOS REGISTRADOS ===");
            for (Reclamo r : registroMunicipal) {
                System.out.println(r);
            }
        }
    }
}