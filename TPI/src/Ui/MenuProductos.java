/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ui;

import Gestores.GestorCategorias;
import Gestores.GestorProductos;
import Entities.Categoria;
import Entities.Producto;
import Exceptions.EntidadNoEncontradaException;
import Exceptions.PrecioInvalidoException;
import Exceptions.StockInvalidoException;

/**
 *
 * @author luciana
 */
public class MenuProductos {
    private final Menu menu;

    public MenuProductos(Menu menu) {
        this.menu = menu;
    }
    
    public void mostrar() {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE PRODUCTOS ---");
            System.out.println("1. Listar Productos");
            System.out.println("2. Crear Producto");
            System.out.println("3. Editar Producto");
            System.out.println("4. Eliminar Producto");
            System.out.println("0. Volver al Menú Principal");
            System.out.println("-----------------------------");
            
            opcion = menu.leerInt("Seleccione una opción (0-4): ");

            switch (opcion) {
                case 1:
                    System.out.println("\n--- LISTADO DE PRODUCTOS ---");
                    System.out.println("1. Listado General");
                    System.out.println("2. Filtrar por Categoría");
                    int tipoLista = menu.leerInt("Seleccione opción: ");
                    
                    if (tipoLista == 2) {
                        Long idCatFiltro = menu.leerLong("Ingrese el ID de la Categoría: ");
                        System.out.println("\n--- PRODUCTOS DE LA CATEGORÍA #" + idCatFiltro + " ---");
                        GestorProductos.mostrarProductosPorCategoria(idCatFiltro);
                    } else {
                        System.out.println("\n--- LISTADO GENERAL ---");
                        GestorProductos.mostrarProductos();
                    }
                    break;
                    
                case 2:
                    System.out.println("\n--- ALTA DE PRODUCTO ---");
                    System.out.println("Categorías disponibles en el sistema:");
                    GestorCategorias.mostrarCategorias(); 
                    System.out.println("-------------------------------------");

                    Long idCatElegida = menu.leerLong("Seleccione el ID de la Categoría para el producto: ");
                    try {
                        Categoria catAsociada = GestorCategorias.buscarPorId(idCatElegida);
                        String nomProd = menu.leerTexto("Ingrese Nombre: ");

                        if (nomProd.isBlank()) {
                            System.out.println("[ERROR] El nombre del producto no puede estar vacío. Operación cancelada.");
                        } else {
                            String descProd = menu.leerTexto("Ingrese Descripción: ");
                            Double precioProd = menu.leerDouble("Ingrese Precio: ");
                            int stockProd = menu.leerInt("Ingrese Stock Inicial: ");
                            String imgProd = menu.leerTexto("Ingrese URL o Ruta de la Imagen: ");

                            System.out.println("¿Está disponible para la venta? 1. SÍ | 2. NO");
                            int dispOpcion = menu.leerInt("Seleccione opción: ");
                            Boolean dispProd = (dispOpcion == 1);

                            GestorProductos.crearProducto(nomProd, precioProd, descProd, stockProd, imgProd, dispProd, catAsociada);
                        }

                    } catch (EntidadNoEncontradaException e) {
                        System.out.println("[ERROR DE NEGOCIO] " + e.getMessage() + " No se permite continuar.");
                    } catch (PrecioInvalidoException | StockInvalidoException e) {
                        System.out.println("[ERROR DE VALIDACIÓN] " + e.getMessage());
                    }
                    break;
                    
                case 3:
                    modificarProducto(); 
                    break;
                    
                case 4:
                    System.out.println("\n--- BAJA DE PRODUCTO ---");
                    Long idEliminar = menu.leerLong("ID de Producto a eliminar: ");
                    try {
                        GestorProductos.buscarPorId(idEliminar);
                       
                        String confirma = menu.leerTexto("¿Está seguro que desea eliminar este producto lógicamente? (S/N): ");
                        if (confirma.equalsIgnoreCase("S")) {
                            GestorProductos.eliminarProducto(idEliminar);
                        } else {
                            System.out.println("Operación de baja cancelada.");
                        }
                    } catch (EntidadNoEncontradaException e) {
                        System.out.println("[ERROR] " + e.getMessage());
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
   
    private void modificarProducto() {
        System.out.println("\n--- MODIFICAR PRODUCTO ---");
        Long idProdMod = menu.leerLong("ID del Producto a editar: ");
        
        try {
            Producto prodMod = GestorProductos.buscarPorId(idProdMod);
            
            System.out.println("\nProducto encontrado: " + prodMod.getNombre());
            System.out.println("¿Qué campo desea modificar?");
            System.out.println("1. Nombre\n2. Precio\n3. Descripción\n4. Stock\n5. Categoría");
            int subOpcion = menu.leerInt("Seleccione una opción (1-5): ");
            
            
            String confirma = menu.leerTexto("¿Confirma aplicar este cambio sobre el producto? (S/N): ");
            if (!confirma.equalsIgnoreCase("S")) {
                System.out.println("Modificación cancelada por el usuario.");
                return; 
            }
            
            switch (subOpcion) {
                case 1:
                    String nuevoNombre = menu.leerTexto("Nuevo Nombre: ");
                    if (nuevoNombre.isBlank()) {
                        System.out.println("[ERROR] El nombre no puede ser vacío. Cambio rechazado.");
                    } else {
                        prodMod.setNombre(nuevoNombre);
                        System.out.println("Nombre modificado con éxito.");
                    }
                    break;
                    
                case 2:
                    double precio = menu.leerDouble("Nuevo Precio: ");
                    GestorProductos.actualizarPrecio(prodMod, precio);
                    break;
                    
                case 3:
                    prodMod.setDescripcion(menu.leerTexto("Nueva Descripción: "));
                    System.out.println("Descripción modificada con éxito.");
                    break;
                    
                case 4:
                    int stock = menu.leerInt("Nuevo Stock: ");
                    GestorProductos.actualizarStock(prodMod, stock);
                    break;
                    
                case 5:
                    System.out.println("\nCategorías disponibles:");
                    GestorCategorias.mostrarCategorias();
                    Long idNuevaCat = menu.leerLong("Seleccione el ID de la nueva Categoría: ");
                    
                    Categoria cat = GestorCategorias.buscarPorId(idNuevaCat);
                    prodMod.setCategoria(cat);
                    System.out.println("Categoría reasignada con éxito.");
                    break;
                    
                default:
                    System.out.println("Opción no válida.");
            } 
            
        } catch (EntidadNoEncontradaException e) {
            System.out.println("[ERROR] " + e.getMessage());
        } catch (PrecioInvalidoException | StockInvalidoException e) {
            
            System.out.println("[ERROR DE VALIDACIÓN] " + e.getMessage());
        }
    }
}
    

    
   