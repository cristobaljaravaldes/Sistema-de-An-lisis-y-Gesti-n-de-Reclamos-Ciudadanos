package com.mycompany.gestionreclamos;
import java.time.LocalDate;
import java.util.Stack;

// Clase que representa un reclamo ciudadano con su historial de cambios (Pila - LIFO)
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
    
    // Pila para mantener historial de cambios 
    private Stack<String> historialEstados;

    public Reclamo(int codigoUnico, String nombreCiudadano, String rutCiudadano, String tipoReclamo, String descripcion, String prioridad, String estadoReclamo, LocalDate fechaRegistro, LocalDate fechaLimite, Stack<String> historialEstados) {
        this.codigoUnico = codigoUnico;
        this.nombreCiudadano = nombreCiudadano;
        this.rutCiudadano = rutCiudadano;
        this.tipoReclamo = tipoReclamo;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.estadoReclamo = estadoReclamo;
        this.fechaRegistro = fechaRegistro;
        this.fechaLimite = fechaLimite;
        this.historialEstados = historialEstados;
    }
    // Actualiza el estado del reclamo y registra el cambio en la pila
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

    public void setTipoReclamo(String tipoReclamo) {
        this.tipoReclamo = tipoReclamo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }


    @Override
    public String toString() {
        return "[" + codigoUnico + "] " + tipoReclamo + " - " + nombreCiudadano + " (" + prioridad + ") -> " + estadoReclamo;
    }
}