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
    
    // Pila para mantener historial de cambios - estructura LIFO
    private Stack<String> historialEstados;

    // Constructor - genera fecha de registro automática y calcula fecha límite - O(1)
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

    // Actualiza el estado del reclamo y registra el cambio en la pila - O(1)
    public void actualizarEstado(String nuevoEstado) {
        this.estadoReclamo = nuevoEstado;
        this.historialEstados.push("Modificado a: " + nuevoEstado + " (" + LocalDate.now() + ")");
    }

    // Getters - O(1)
    public int getCodigoUnico() { return codigoUnico; }
    public String getNombreCiudadano() { return nombreCiudadano; }
    public String getRutCiudadano() { return rutCiudadano; }
    public String getTipoReclamo() { return tipoReclamo; }
    public String getDescripcion() { return descripcion; }
    public String getPrioridad() { return prioridad; }
    public String getEstadoReclamo() { return estadoReclamo; }
    public LocalDate getFechaRegistro() { return fechaRegistro; }
    public LocalDate getFechaLimite() { return fechaLimite; }
    public Stack<String> getHistorialEstados() { return historialEstados; }

    // Setters - O(1)
    public void setPrioridad(String prioridad) { this.prioridad = prioridad; }
    public void setTipoReclamo(String tipoReclamo) { this.tipoReclamo = tipoReclamo; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    @Override
    public String toString() {
        return "[" + codigoUnico + "] " + tipoReclamo + " - " + nombreCiudadano + " (" + prioridad + ") -> " + estadoReclamo;
    }
}