package com.mycompany.gestionreclamos;

/**
 * Clase que implementa un árbol binario de búsqueda
 * para gestionar los reclamos de forma eficiente
 * @author yordan
 */
public class ArbolReclamos {
    
    public static class NodoReclamo {
        private Reclamo reclamo; 
        private NodoReclamo izquierdo; 
        private NodoReclamo derecho;   

        // Constructor: Inicializa el nodo con su reclamo y los hijos vacíos
        public NodoReclamo(Reclamo reclamo) {
            this.reclamo = reclamo;
            this.izquierdo = null;
            this.derecho = null;
        }
 
        // Métodos getters y setters para un acceso seguro a los atributos del nodo
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

    // Nodo principal que sirve como punto de entrada al árbol
    private NodoReclamo raiz;

    // Constructor del árbol: Inicializa el árbol como vacío
    public ArbolReclamos() {
        this.raiz = null;
    }
    
    //Complejidad: O(log n) caso promedio si el árbol se ramifica y O(n)como peor caso si entra ordenado y se carga a un lado
    public void insertar(Reclamo nuevoReclamo) {
        raiz = insertarRecursivo(raiz, nuevoReclamo);
    }

    // Determina mediante recursividad la posición correcta del nuevo nodo según su código
    private NodoReclamo insertarRecursivo(NodoReclamo actual, Reclamo nuevoReclamo) {
        // Caso base: Si encuentra una posición vacía, se crea e inserta el nuevo nodo
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
    
    //Complejidad: O(log n) caso promedio si el árbol se ramifica y O(n)como peor caso si entra ordenado y se carga a un lado
    public Reclamo buscarPorCodigo(int codigo) {
        return buscarRecursivo(raiz, codigo);
    }

    // Busca de forma recursiva dividiendo el árbol a la mitad en cada comparación
    private Reclamo buscarRecursivo(NodoReclamo actual, int codigo) {
        // Caso base: Retorna null si no lo encuentra, o el reclamo si el código coincide
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

    // Metodo público que valida si el árbol tiene datos antes de mostrar
    // Complejidad: O(n) en todos los casos, ya que es obligación recorrer cada nodo para imprimirlo
    public void mostrarInOrden() {
        if (raiz == null) {
            System.out.println("\n*** No existen reclamos registrados en el sistema ***");
        } else {
            mostrarInOrdenRecursivo(raiz);
        }
    }
    // Recorre el árbol en orden izquierda-raíz-derecha para mostrar los datos de menor a mayor
    private void mostrarInOrdenRecursivo(NodoReclamo actual) {
        if (actual != null) {
            mostrarInOrdenRecursivo(actual.getIzquierdo());
            System.out.println("  " + actual.getReclamo());
            mostrarInOrdenRecursivo(actual.getDerecho());
        }
    }  
}