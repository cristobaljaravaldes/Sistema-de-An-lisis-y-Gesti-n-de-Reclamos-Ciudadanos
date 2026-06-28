    package com.mycompany.gestionreclamos;
import java.time.LocalDate;
import java.util.Stack;

/**
 *
 * @author yordan
 */
public class Reclamo {
    private int codigoUnico;
    private String nombreCiudadano;
    private String rutCiudadano;
    private String tipoReclamo;
    private String descripcion;
    private String prioridad;
    private String estadoReclamo;
    private LocalDate fechaRegistro;
    private LocalDate fechaLimite;
    
    private Stack<String> historialEstados;

    public Reclamo(int codigoUnico, String nombreCiudadano, String rutCiudadano, String tipoReclamo, String descripcion, int diasParaResolver) {
        this.codigoUnico = codigoUnico;
        this.nombreCiudadano = nombreCiudadano;
        this.rutCiudadano = rutCiudadano;
        this.tipoReclamo = tipoReclamo;
        this.descripcion = descripcion;
        this.prioridad = "Media"; 
        this.estadoReclamo = "Pendiente";
        this.fechaRegistro = LocalDate.now();
        this.fechaLimite = LocalDate.now().plusDays(diasParaResolver);
        
        this.historialEstados = new Stack<>();
        this.historialEstados.push("Caso Creado - Estado: Pendiente (" + fechaRegistro + ")");
    }

    public void actualizarEstado(String nuevoEstado) {
        this.estadoReclamo = nuevoEstado;
        this.historialEstados.push("Modificado a: " + nuevoEstado + " (" + LocalDate.now() + ")");
    }

    public int getCodigoUnico() { 
        return codigoUnico; 
    } 
    public String getNombreCiudadano() { 
        return nombreCiudadano; 
    }
    public String getRutCiudadano() { 
        return rutCiudadano; 
    }
    public String getTipoReclamo() { 
        return tipoReclamo; 
    }
    public String getDescripcion() { 
        return descripcion; 
    }
    public String getPrioridad() { 
        return prioridad; 
    }
    public void setPrioridad(String prioridad) {  
        this.prioridad = prioridad;
    }
    public String getEstadoReclamo() { 
        return estadoReclamo; 
    }
    public LocalDate getFechaRegistro() { 
        return fechaRegistro;
    }
    public LocalDate getFechaLimite() { 
        return fechaLimite; 
    }
    public Stack<String> getHistorialEstados() { 
        return historialEstados; 
    }

    @Override
    public String toString() {
        return "[" + codigoUnico + "] " + tipoReclamo + " - " + nombreCiudadano + " (" + prioridad + ") -> " + estadoReclamo;
    }
}