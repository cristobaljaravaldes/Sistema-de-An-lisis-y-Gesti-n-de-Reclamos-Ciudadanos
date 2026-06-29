package com.mycompany.gestionreclamos;

// Clase que implementa un Árbol AVL (autobalanceado) para gestión de reclamos
// A diferencia del BST simple, el AVL se balancea automáticamente en cada inserción
// Complejidad inserción: O(log n) - Complejidad búsqueda: O(log n)
public class ArbolAVL {

    // Nodo interno del árbol AVL
    private static class NodoAVL {
        Reclamo reclamo;
        NodoAVL izquierdo;
        NodoAVL derecho;
        int altura; // Altura del nodo para calcular el balance

        NodoAVL(Reclamo reclamo) {
            this.reclamo = reclamo;
            this.altura = 1;
        }
    }

    private NodoAVL raiz;

    // Retorna la altura de un nodo (0 si es null)
    private int altura(NodoAVL nodo) {
        if (nodo == null) return 0;
        return nodo.altura;
    }

    // Calcula el factor de balance de un nodo
    // Balance = altura izquierda - altura derecha
    // Si el resultado es > 1 o < -1, el árbol está desbalanceado
    private int factorBalance(NodoAVL nodo) {
        if (nodo == null) return 0;
        return altura(nodo.izquierdo) - altura(nodo.derecho);
    }

    // Actualiza la altura de un nodo según sus hijos
    private void actualizarAltura(NodoAVL nodo) {
        nodo.altura = 1 + Math.max(altura(nodo.izquierdo), altura(nodo.derecho));
    }

    // Rotación simple a la derecha - corrige desbalance izquierda-izquierda
    private NodoAVL rotarDerecha(NodoAVL y) {
        NodoAVL x = y.izquierdo;
        NodoAVL T2 = x.derecho;

        // Realizamos la rotación
        x.derecho = y;
        y.izquierdo = T2;

        // Actualizamos alturas
        actualizarAltura(y);
        actualizarAltura(x);

        return x;
    }

    // Rotación simple a la izquierda - corrige desbalance derecha-derecha
    private NodoAVL rotarIzquierda(NodoAVL x) {
        NodoAVL y = x.derecho;
        NodoAVL T2 = y.izquierdo;

        // Realizamos la rotación
        y.izquierdo = x;
        x.derecho = T2;

        // Actualizamos alturas
        actualizarAltura(x);
        actualizarAltura(y);

        return y;
    }

    // Balancea el nodo aplicando las rotaciones necesarias
    private NodoAVL balancear(NodoAVL nodo) {
        actualizarAltura(nodo);
        int balance = factorBalance(nodo);

        // Caso izquierda-izquierda: rotación simple derecha
        if (balance > 1 && factorBalance(nodo.izquierdo) >= 0) {
            return rotarDerecha(nodo);
        }

        // Caso izquierda-derecha: rotación doble
        if (balance > 1 && factorBalance(nodo.izquierdo) < 0) {
            nodo.izquierdo = rotarIzquierda(nodo.izquierdo);
            return rotarDerecha(nodo);
        }

        // Caso derecha-derecha: rotación simple izquierda
        if (balance < -1 && factorBalance(nodo.derecho) <= 0) {
            return rotarIzquierda(nodo);
        }

        // Caso derecha-izquierda: rotación doble
        if (balance < -1 && factorBalance(nodo.derecho) > 0) {
            nodo.derecho = rotarDerecha(nodo.derecho);
            return rotarIzquierda(nodo);
        }

        return nodo;
    }

    // Inserta un reclamo en el árbol AVL y lo balancea automáticamente
    public void insertar(Reclamo reclamo) {
        raiz = insertarRecursivo(raiz, reclamo);
    }

    private NodoAVL insertarRecursivo(NodoAVL nodo, Reclamo reclamo) {
        // Inserción normal como BST
        if (nodo == null) return new NodoAVL(reclamo);

        if (reclamo.getCodigoUnico() < nodo.reclamo.getCodigoUnico()) {
            nodo.izquierdo = insertarRecursivo(nodo.izquierdo, reclamo);
        } else if (reclamo.getCodigoUnico() > nodo.reclamo.getCodigoUnico()) {
            nodo.derecho = insertarRecursivo(nodo.derecho, reclamo);
        } else {
            return nodo; // Código duplicado, no se inserta
        }

        // Balanceamos el nodo después de insertar
        return balancear(nodo);
    }

    // Busca un reclamo por código único - O(log n)
    public Reclamo buscar(int codigo) {
        return buscarRecursivo(raiz, codigo);
    }

    private Reclamo buscarRecursivo(NodoAVL nodo, int codigo) {
        if (nodo == null) return null;

        if (codigo == nodo.reclamo.getCodigoUnico()) {
            return nodo.reclamo;
        } else if (codigo < nodo.reclamo.getCodigoUnico()) {
            return buscarRecursivo(nodo.izquierdo, codigo);
        } else {
            return buscarRecursivo(nodo.derecho, codigo);
        }
    }

    // Recorrido InOrden - muestra reclamos ordenados por código
    public void mostrarInOrden() {
        if (raiz == null) {
            System.out.println("\n*** No hay reclamos en el arbol AVL ***");
        } else {
            System.out.println("\n=== ARBOL AVL - RECORRIDO INORDEN ===");
            inOrdenRecursivo(raiz);
        }
    }

    private void inOrdenRecursivo(NodoAVL nodo) {
        if (nodo != null) {
            inOrdenRecursivo(nodo.izquierdo);
            System.out.println("  " + nodo.reclamo + " [Altura: " + nodo.altura + "]");
            inOrdenRecursivo(nodo.derecho);
        }
    }

    // Recorrido PreOrden
    public void mostrarPreOrden() {
        System.out.println("\n=== ARBOL AVL - RECORRIDO PREORDEN ===");
        preOrdenRecursivo(raiz);
    }

    private void preOrdenRecursivo(NodoAVL nodo) {
        if (nodo != null) {
            System.out.println("  " + nodo.reclamo);
            preOrdenRecursivo(nodo.izquierdo);
            preOrdenRecursivo(nodo.derecho);
        }
    }

    // Recorrido PostOrden
    public void mostrarPostOrden() {
        System.out.println("\n=== ARBOL AVL - RECORRIDO POSTORDEN ===");
        postOrdenRecursivo(raiz);
    }

    private void postOrdenRecursivo(NodoAVL nodo) {
        if (nodo != null) {
            postOrdenRecursivo(nodo.izquierdo);
            postOrdenRecursivo(nodo.derecho);
            System.out.println("  " + nodo.reclamo);
        }
    }
}