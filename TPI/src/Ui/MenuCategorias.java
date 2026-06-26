/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ui;
import Gestores.GestorCategorias;
import Entities.Categoria;
import Exceptions.EntidadNoEncontradaException;
import Exceptions.NombreDuplicadoException;
import Exceptions.ProductosAsociadosException;

/**
 *
 * @author luciana
 */
public class MenuCategorias {
    
    private final Menu menu;
    
    public MenuCategorias(Menu menu) {
        this.menu = menu;
    }
    
    public void mostrar() {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE CATEGORÍAS ---");
            System.out.println("1. Listar Categorías");
            System.out.println("2. Crear Categoría");
            System.out.println("3. Editar Categoría");
            System.out.println("4. Eliminar Categoría");
            System.out.println("0. Volver al Menú Principal");
            System.out.println("-----------------------------");
            
            opcion = menu.leerInt("Seleccione una opción: ");

           
            switch (opcion) {
                case 1:
                    System.out.println("\n--- LISTADO DE CATEGORÍAS ---");
                    GestorCategorias.mostrarCategorias();
                    break;
                    
                case 2:
                    System.out.println("\n--- ALTA DE CATEGORÍA ---");
                    String nombre = menu.leerTexto("Ingrese Nombre: ");
                    String descripcion = menu.leerTexto("Ingrese Descripción: ");
    
                    if (nombre.isBlank() || descripcion.isBlank()) {
                    System.out.println("[ERROR] El nombre y la descripción no pueden estar vacíos.");
                    } else {
                    try {
                    GestorCategorias.crearCategoria(nombre, descripcion);
                    } catch (NombreDuplicadoException e) { 
                    System.out.println("[ERROR DE NEGOCIO] " + e.getMessage());
                    }
                    }
                    break;
                   
                    
                case 3:
                     System.out.println("\n--- MODIFICAR CATEGORÍA ---");
                     Long idCatMod = menu.leerLong("ID de Categoría a editar: ");
                     try {
                        Categoria catMod = GestorCategorias.buscarPorId(idCatMod);
                        
                        System.out.println("\nCategoría encontrada: " + catMod.getNombre());
                        String nuevoNombre = menu.leerTexto("Nuevo Nombre: ");
                        
                        if (nuevoNombre.isBlank()) {
                            System.out.println("El nombre no puede estar vacío. Operación cancelada.");
                        } else {
                            String desc = menu.leerTexto("Nueva Descripción: ");
                            GestorCategorias.modificarCategoria(idCatMod, nuevoNombre, desc);
                        }
                        
                    } catch (EntidadNoEncontradaException e) {
                         System.out.println("[ERROR] " + e.getMessage());
                    }
                    break;
                      
                case 4:
                    System.out.println("\n--- BAJA DE CATEGORÍA ---");
                    Long idEliminar = menu.leerLong("ID de Categoría a eliminar: ");
                    try {
                        GestorCategorias.buscarPorId(idEliminar);
        
                        String confirma = menu.leerTexto("¿Está seguro que desea eliminar la categoría? (S/N): ");
                        if (confirma.equalsIgnoreCase("S")) {
                        GestorCategorias.eliminarCategoria(idEliminar);   
                        } else {
                        System.out.println("Operación cancelada.");
                        }
                        } catch (EntidadNoEncontradaException e) {
                        System.out.println("[ERROR] " + e.getMessage());
                        } catch (ProductosAsociadosException e) { 
       
                        System.out.println("[REGLA DE NEGOCIO] " + e.getMessage());
                        }
                        break;
                    
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                    
                default:
                    System.out.println("\n Opción fuera de rango (0 a 4). Intente de nuevo.");
            }
        } while (opcion != 0);
    }
}
    

