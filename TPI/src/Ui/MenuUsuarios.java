/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ui;
import Gestores.GestorUsuarios;
import Entities.Usuario;
import Enums.Rol;
import Exceptions.EntidadNoEncontradaException;
import Exceptions.MailDuplicadoException;

/**
 *
 * @author luciana
 */
public class MenuUsuarios {
    
    private final Menu menu; 

    public MenuUsuarios(Menu menu) {
        this.menu = menu;
    }

    public void mostrar() {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE USUARIOS ---");
            System.out.println("1. Listar Usuarios");
            System.out.println("2. Crear Usuario");
            System.out.println("3. Editar Usuario");
            System.out.println("4. Eliminar Usuario");
            System.out.println("0. Volver al Menú Principal");
            System.out.println("-----------------------------");
            
            opcion = menu.leerInt("Seleccione una opción (0-4): ");

            switch (opcion) {
                case 1:
                    System.out.println("\n--- LISTADO DE USUARIOS ---");
                    GestorUsuarios.mostrarUsuarios();
                    break;
                    
                case 2:
                    
                  System.out.println("\n--- ALTA DE USUARIO ---");
                  String nombre = menu.leerTexto("Ingrese Nombre: ");
                  String apellido = menu.leerTexto("Ingrese Apellido: ");
                  String mail = menu.leerTexto("Ingrese Mail: ");
    
                  if (mail.isBlank() || !mail.contains("@")) {
                  System.out.println("[ERROR] El mail ingresado no es válido.");
                  } else {
                      String celular = menu.leerTexto("Ingrese Celular: ");
                      String contrasenia = menu.leerTexto("Ingrese Contraseña: ");
                      Rol rolPorDefecto = Rol.USUARIO; 
        
                           try {
                                 GestorUsuarios.crearUsuario(nombre, apellido, mail, celular, contrasenia, rolPorDefecto);
                                 } catch (MailDuplicadoException e) {
                                 System.out.println("[ERROR] " + e.getMessage());
                                 }
                                 }
   
                    break;
                    
                case 3:
                    editarUsuario(); // Método auxiliar debajo para no sobrecargar el switch 
                    break;
                    
                case 4:
                     System.out.println("\n--- BAJA DE USUARIO ---");
                    Long idBaja = menu.leerLong("ID del Usuario a eliminar: ");
                    try {
                        GestorUsuarios.eliminarUsuario(idBaja);
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
    
    private void editarUsuario() {
    System.out.println("\n--- MODIFICAR USUARIO ---");
    Long idOperador = menu.leerLong("Ingrese su ID: ");
    
    try {
        Usuario operador = GestorUsuarios.buscarPorId(idOperador);
        
        Long idMod = menu.leerLong("ID del Usuario a editar: "); 
        Usuario uMod = GestorUsuarios.buscarPorId(idMod);
        
        System.out.println("\nUsuario encontrado: " + uMod.getNombre() + " " + uMod.getApellido() + " [" + uMod.getRol() + "]");
        System.out.println("¿Qué desea modificar?");
        
        System.out.println("1. Nombre\n2. Apellido\n3. Celular\n4. Contraseña\n5. Mail");
        
        if (operador.getRol() == Rol.ADMIN) {
            System.out.println("6. Rol");
        }
        
        int subOpcion = menu.leerInt("Seleccione opción: "); 
        

        String confirma = menu.leerTexto("¿Está seguro que desea aplicar este cambio? (S/N): ");
        if (!confirma.equalsIgnoreCase("S")) {
            System.out.println("Operación cancelada por el usuario.");
            return; 
        }
        
        switch (subOpcion) {
            case 1:
            case 2:
            case 3:
            case 4:
                String nuevoValor = menu.leerTexto("Ingrese el nuevo valor: ");
                GestorUsuarios.modificarCampoUsuario(uMod, subOpcion, nuevoValor);
                break;
                
            case 5:
                String nuevoMail = menu.leerTexto("Ingrese el nuevo Mail: ");
                if (nuevoMail.isBlank() || !nuevoMail.contains("@")) {
                    System.out.println("[ERROR] Formato de mail inválido.");
                } else if (GestorUsuarios.existeMail(nuevoMail, uMod.getId())) {
                    System.out.println("[ERROR DE NEGOCIO] El mail ya está en uso por otro usuario activo.");
                } else {
                    uMod.setMail(nuevoMail);
                    System.out.println("Mail modificado con éxito.");
                }
                break;
                
            case 6:
                if (operador.getRol() == Rol.ADMIN) {
                    System.out.println("\nRoles disponibles: 1. ADMIN | 2. USUARIO");
                    int seleccionRol = menu.leerInt("Seleccione nuevo Rol (1-2): "); 
                    
                    Rol rolElegido;
                    if (seleccionRol == 1) {
                        rolElegido = Rol.ADMIN;
                    } else {
                        rolElegido = Rol.USUARIO;
                    }
                  
                    GestorUsuarios.modificarRolUsuario(uMod, rolElegido, idOperador);
                } else {
                    System.out.println("[Acceso Denegado] Solo los administradores pueden cambiar roles.");
                }
                break;
                
            default:
                System.out.println("Opción no válida.");
        } 
        
    } catch (EntidadNoEncontradaException e) {
        System.out.println("[ERROR] " + e.getMessage());
    }
}
    
    
} 



    
