  package com.mycompany.gestionreclamos;

/**
 *
 * @author yordan
 */
public class ArbolReclamos {
    
    public static class NodoReclamo {
        private Reclamo reclamo; 
        private NodoReclamo izquierdo; 
        private NodoReclamo derecho;   

        public NodoReclamo(Reclamo reclamo) {
            this.reclamo = reclamo;
            this.izquierdo = null;
            this.derecho = null;
        }

        public Reclamo getReclamo() { 
            return reclamo;
        } 
        public NodoReclamo getIzquierdo() { 
            return izquierdo; 
        }
        public void setIzquierdo(NodoReclamo izquierdo) { 
            this.izquierdo = izquierdo;
        } 
        public NodoReclamo getDerecho() { 
            return derecho;
        } 
        public void setDerecho(NodoReclamo derecho) { 
            this.derecho = derecho;
        } 
    }

    private NodoReclamo raiz;

    public ArbolReclamos() {
        this.raiz = null;
    }
    
    public void insertar(Reclamo nuevoReclamo) {
        raiz = insertarRecursivo(raiz, nuevoReclamo);
    }

    private NodoReclamo insertarRecursivo(NodoReclamo actual, Reclamo nuevoReclamo) {
        if (actual == null) {
            return new NodoReclamo(nuevoReclamo);
        }

        if (nuevoReclamo.getCodigoUnico() < actual.getReclamo().getCodigoUnico()) {
            actual.setIzquierdo(insertarRecursivo(actual.getIzquierdo(), nuevoReclamo));
        } else if (nuevoReclamo.getCodigoUnico() > actual.getReclamo().getCodigoUnico()) {
            actual.setDerecho(insertarRecursivo(actual.getDerecho(), nuevoReclamo));
        }
        return actual;
    }
    
    public Reclamo buscarPorCodigo(int codigo) {
        return buscarRecursivo(raiz, codigo);
    }

    private Reclamo buscarRecursivo(NodoReclamo actual, int codigo) {
        if (actual == null) {
            return null;
        }

        if (codigo == actual.getReclamo().getCodigoUnico()) {
            return actual.getReclamo();
        }

        if (codigo < actual.getReclamo().getCodigoUnico()) {
            return buscarRecursivo(actual.getIzquierdo(), codigo);
        } else {
            return buscarRecursivo(actual.getDerecho(), codigo);
        }
    }

    public void mostrarInOrden() {
        if (raiz == null) {
            System.out.println("\n*** No existen reclamos registrados en el sistema ***");
        } else {
            mostrarInOrdenRecursivo(raiz);
        }
    }

    private void mostrarInOrdenRecursivo(NodoReclamo actual) {
        if (actual != null) {
            mostrarInOrdenRecursivo(actual.getIzquierdo());
            System.out.println("  " + actual.getReclamo());
            mostrarInOrdenRecursivo(actual.getDerecho());
        }
    }  
}