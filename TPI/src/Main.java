/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import Ui.Menu;
import Ui.MenuCategorias;
import Ui.MenuPedidos;
import Ui.MenuUsuarios;
import Ui.MenuProductos;


/**
 *
 * @author luciana
 */

public class Main {

    public static void main(String[] args) {
        
        Menu menuLector = new Menu();
        
        
        MenuCategorias menuCategorias = new MenuCategorias(menuLector);
        MenuProductos menuProductos = new MenuProductos(menuLector);
        MenuUsuarios menuUsuarios = new MenuUsuarios(menuLector);
        MenuPedidos menuPedidos = new MenuPedidos(menuLector); 
        int opcion;
        do {
            System.out.println("\n=============================================");
            System.out.println("      FOOD STORE - UTN     ");
            System.out.println("=============================================");
            System.out.println("1. Gestión de Categorías");
            System.out.println("2. Gestión de Productos");
            System.out.println("3. Gestión de Usuarios");
            System.out.println("4. Gestión de Pedidos");
            System.out.println("0. Salir del Sistema");
            System.out.println("=============================================");
            
            opcion = menuLector.leerInt("Seleccione una opción general (0-4): ");

            switch (opcion) {
                case 1:
                    menuCategorias.mostrar();
                    break;
                    
                case 2:                  
                    menuProductos.mostrar();
                    break;
                    
                case 3:                  
                    menuUsuarios.mostrar();
                    break;
                    
                case 4:
                    System.out.println("\n Accediendo a Gestión de Pedidos...");
                    menuPedidos.mostrar(); 
                    break;
                    
                case 0:
                    System.out.println("\n¡Gracias por utilizar el sistema! Finalizando aplicación...");
                    break;
                    
                default:
                    System.out.println("\n[ERROR] Opción fuera de rango. Intente de nuevo.");
            }
            
        } while (opcion != 0);
    }
}