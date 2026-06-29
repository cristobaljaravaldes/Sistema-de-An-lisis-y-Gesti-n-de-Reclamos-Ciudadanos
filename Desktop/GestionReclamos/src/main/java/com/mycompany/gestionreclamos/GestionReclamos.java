package com.mycompany.gestionreclamos;
import java.util.ArrayList;
import java.util.Scanner;

// Clase principal del sistema - menú de gestión de reclamos municipales
public class GestionReclamos {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Estructuras principales del sistema
        ListaReclamos lista = new ListaReclamos();
        ColaPendientes cola = new ColaPendientes();
        ArbolReclamos arbol = new ArbolReclamos();
        
        int opcion;

        do {
            System.out.println("\n========================================");
            System.out.println("  MUNICIPALIDAD SAN RAFAEL");
            System.out.println("  Sistema de Gestion de Reclamos");
            System.out.println("========================================");
            System.out.println("1.  Registrar nuevo reclamo");
            System.out.println("2.  Mostrar todos los reclamos");
            System.out.println("3.  Buscar por codigo (Busqueda Binaria)");
            System.out.println("4.  Buscar por RUT (Busqueda Secuencial)");
            System.out.println("5.  Modificar reclamo");
            System.out.println("6.  Eliminar reclamo");
            System.out.println("7.  Actualizar estado de reclamo");
            System.out.println("8.  Ordenar por codigo (Burbuja)");
            System.out.println("9.  Ordenar por prioridad (Quick Sort)");
            System.out.println("10. Ordenar por fecha limite");
            System.out.println("11. Ordenar por fecha de ingreso");
            System.out.println("12. Ordenar por tipo de reclamo");
            System.out.println("13. Ver cola de pendientes");
            System.out.println("14. Atender siguiente reclamo");
            System.out.println("15. Ver arbol BST (InOrden)");
            System.out.println("16. Aplicar filtro de vencimientos");
            System.out.println("17. Ver historial de un reclamo");
            System.out.println("0.  Salir");
            System.out.println("========================================");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1:
                    // Registrar nuevo reclamo
                    System.out.print("Codigo unico: ");
                    int codigo = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nombre ciudadano: ");
                    String nombre = scanner.nextLine();
                    System.out.print("RUT ciudadano: ");
                    String rut = scanner.nextLine();
                    System.out.print("Tipo de reclamo: ");
                    String tipo = scanner.nextLine();
                    System.out.print("Descripcion: ");
                    String descripcion = scanner.nextLine();
                    System.out.print("Dias para resolver: ");
                    int dias = scanner.nextInt();
                    scanner.nextLine();

                    Reclamo nuevo = new Reclamo(codigo, nombre, rut, tipo, descripcion, dias);
                    lista.agregarReclamo(nuevo);
                    cola.agregarReclamo(nuevo);
                    arbol.insertar(nuevo);
                    System.out.println("\n*** Reclamo registrado exitosamente ***");
                    break;

                case 2:
                    // Mostrar todos los reclamos
                    lista.mostrarTodos();
                    break;

                case 3:
                    // Búsqueda binaria por código - requiere lista ordenada
                    System.out.print("Ingrese codigo a buscar: ");
                    int codBinaria = scanner.nextInt();
                    scanner.nextLine();

                    // Ordenamos primero con burbuja para garantizar la precondición
                    Ordenamiento.burbuja(lista.getRegistroMunicipal());
                    Reclamo encontradoBinaria = BusquedaBinaria.buscarPorCodigo(lista.getRegistroMunicipal(), codBinaria);

                    if (encontradoBinaria != null) {
                        System.out.println("\nReclamo encontrado: " + encontradoBinaria);
                    } else {
                        System.out.println("\n*** Reclamo no encontrado ***");
                    }
                    break;

                case 4:
                    // Búsqueda secuencial por RUT
                    System.out.print("Ingrese RUT a buscar: ");
                    String rutBuscar = scanner.nextLine();
                    Reclamo encontradoRut = lista.buscarPorRut(rutBuscar);

                    if (encontradoRut != null) {
                        System.out.println("\nReclamo encontrado: " + encontradoRut);
                    } else {
                        System.out.println("\n*** No se encontro reclamo con ese RUT ***");
                    }
                    break;

                case 5:
                    // Modificar reclamo
                    System.out.print("Codigo del reclamo a modificar: ");
                    int codModificar = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nuevo tipo de reclamo: ");
                    String nuevoTipo = scanner.nextLine();
                    System.out.print("Nueva descripcion: ");
                    String nuevaDesc = scanner.nextLine();

                    boolean modificado = lista.modificarReclamo(codModificar, nuevoTipo, nuevaDesc);
                    if (modificado) {
                        System.out.println("\n*** Reclamo modificado exitosamente ***");
                    } else {
                        System.out.println("\n*** Reclamo no encontrado ***");
                    }
                    break;

                case 6:
                    // Eliminar reclamo
                    System.out.print("Codigo del reclamo a eliminar: ");
                    int codEliminar = scanner.nextInt();
                    scanner.nextLine();

                    boolean eliminado = lista.eliminarReclamo(codEliminar);
                    if (eliminado) {
                        System.out.println("\n*** Reclamo eliminado exitosamente ***");
                    } else {
                        System.out.println("\n*** Reclamo no encontrado ***");
                    }
                    break;

                case 7:
                    // Actualizar estado
                    System.out.print("Codigo del reclamo: ");
                    int codEstado = scanner.nextInt();
                    scanner.nextLine();
                    Reclamo reclamoEstado = lista.buscarPorCodigo(codEstado);

                    if (reclamoEstado != null) {
                        System.out.println("Estados disponibles: Pendiente / En proceso / Resuelto");
                        System.out.print("Nuevo estado: ");
                        String nuevoEstado = scanner.nextLine();
                        reclamoEstado.actualizarEstado(nuevoEstado);
                        System.out.println("\n*** Estado actualizado exitosamente ***");
                    } else {
                        System.out.println("\n*** Reclamo no encontrado ***");
                    }
                    break;

                case 8:
                    // Ordenar por código con Burbuja - O(n²)
                    Ordenamiento.burbuja(lista.getRegistroMunicipal());
                    lista.mostrarTodos();
                    break;

                case 9:
                    // Ordenar por prioridad con Quick Sort - O(n log n)
                    ArrayList<Reclamo> listaOrdenada = lista.getRegistroMunicipal();
                    if (!listaOrdenada.isEmpty()) {
                        Ordenamiento.quickPorPrioridad(listaOrdenada, 0, listaOrdenada.size() - 1);
                        System.out.println("\n* Lista ordenada por Prioridad (Alta -> Media -> Baja) *");
                        lista.mostrarTodos();
                    } else {
                        System.out.println("\n*** No hay reclamos registrados ***");
                    }
                    break;

                case 10:
                    // Ordenar por fecha límite con Burbuja
                    Ordenamiento.burbujaPorFechaLimite(lista.getRegistroMunicipal());
                    lista.mostrarTodos();
                    break;

                case 11:
                    // Ordenar por fecha de ingreso con Quick Sort
                    ArrayList<Reclamo> listaFecha = lista.getRegistroMunicipal();
                    if (!listaFecha.isEmpty()) {
                        Ordenamiento.quickPorFechaIngreso(listaFecha, 0, listaFecha.size() - 1);
                        lista.mostrarTodos();
                    } else {
                        System.out.println("\n*** No hay reclamos registrados ***");
                    }
                    break;

                case 12:
                    // Ordenar por tipo de reclamo con Burbuja
                    Ordenamiento.burbujaPorTipo(lista.getRegistroMunicipal());
                    lista.mostrarTodos();
                    break;

                case 13:
                    // Ver cola de pendientes
                    cola.mostrarReclamos();
                    break;

                case 14:
                    // Atender siguiente reclamo de la cola
                    cola.atenderSiguienteReclamo();
                    break;

                case 15:
                    // Ver árbol BST recorrido InOrden
                    System.out.println("\n=== ARBOL BST - RECORRIDO INORDEN ===");
                    arbol.mostrarInOrden();
                    break;

                case 16:
                    // Aplicar filtro automático de vencimientos
                    FiltroVencimientos.aplicarFiltro(lista.getRegistroMunicipal());
                    break;

                case 17:
                    // Ver historial de cambios de un reclamo (Pila)
                    System.out.print("Codigo del reclamo: ");
                    int codHistorial = scanner.nextInt();
                    scanner.nextLine();
                    Reclamo reclamoHistorial = lista.buscarPorCodigo(codHistorial);

                    if (reclamoHistorial != null) {
                        System.out.println("\n=== HISTORIAL DE CAMBIOS ===");
                        // Mostramos la pila sin destruirla
                        Object[] historial = reclamoHistorial.getHistorialEstados().toArray();
                        for (int i = historial.length - 1; i >= 0; i--) {
                            System.out.println("  " + historial[i]);
                        }
                    } else {
                        System.out.println("\n*** Reclamo no encontrado ***");
                    }
                    break;

                case 0:
                    System.out.println("\n*** Cerrando el sistema. Hasta luego. ***");
                    break;

                default:
                    System.out.println("\n*** Opción no valida ***");
            }

        } while (opcion != 0);

        scanner.close();
    }
}