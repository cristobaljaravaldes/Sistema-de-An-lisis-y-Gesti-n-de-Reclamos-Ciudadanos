package com.mycompany.gestionreclamos;
import java.util.ArrayList;

/**
 *
 * @author yordan
 */
public class ListaReclamos {
    private ArrayList<Reclamo> registroMunicipal;

    public ListaReclamos() {
        this.registroMunicipal = new ArrayList<>();
    }

    public void agregarReclamo(Reclamo nuevoReclamo) {
        this.registroMunicipal.add(nuevoReclamo);
    }

    public Reclamo buscarPorRut(String rutBuscar) {
        for (int i = 0; i < registroMunicipal.size(); i++) {
            if (registroMunicipal.get(i).getRutCiudadano().equals(rutBuscar)) {
                return registroMunicipal.get(i);
            }
        }
        return null;
    }

    public ArrayList<Reclamo> getRegistroMunicipal() { 
        return registroMunicipal; 
    }
}