package Ui;

import java.util.Scanner;

public class Menu {
    private final Scanner sc = new Scanner(System.in);
    
    // Instanciamos los submenús especializados
    private final MenuCategorias menuCategorias = new MenuCategorias(this);
    private final MenuProductos menuProductos = new MenuProductos(this);
    private final MenuUsuarios menuUsuarios = new MenuUsuarios(this);
    private final MenuPedidos menuPedidos = new MenuPedidos(this);

    public void iniciarMenu() {
        int opcion;
        do {
            System.out.println("\n=======================================");
            System.out.println("=== SISTEMA DE PEDIDOS (FOOD STORE) ===");
            System.out.println("=======================================");
            System.out.println("1. Gestión de Categorías");
            System.out.println("2. Gestión de Productos");
            System.out.println("3. Gestión de Usuarios");
            System.out.println("4. Gestión de Pedidos");
            System.out.println("0. Salir del Sistema");
            System.out.println("=======================================");
             
            opcion = leerInt("Seleccione una opción (0-4): ");
            
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
                    menuPedidos.mostrar();
                    break;
                case 0:
                    System.out.println("\n=============================================");
                    System.out.println("¡Gracias por utilizar Food Store! Vuelva pronto.");
                    System.out.println("=============================================");
                    break;
                default:
                    System.out.println("\n[Error] Opción fuera de rango (0 a 4). Intente de nuevo.");
            }
        } while (opcion != 0);
    }

   //Métodos de lectura de scanner
    public String leerTexto(String mensaje) { 
        System.out.print(mensaje); 
        return sc.nextLine(); 
    }
 
    
    public int leerInt(String mensaje) { 
        int resultado;
        try { 
            resultado = Integer.parseInt(leerTexto(mensaje)); 
        } catch (Exception e) { 
            resultado = -1; 
        } 
        return resultado;
    }
    
    public Long leerLong(String mensaje) { 
        Long resultado;
        try { 
            resultado = Long.parseLong(leerTexto(mensaje)); 
        } catch (Exception e) { 
            resultado = -1L; 
        } 
        return resultado;
    }
    
    public double leerDouble(String mensaje) { 
        double resultado;
        try { 
            resultado = Double.parseDouble(leerTexto(mensaje)); 
        } catch (Exception e) { 
            resultado = -1.0; 
        } 
        return resultado;
    }
    
}

    
   