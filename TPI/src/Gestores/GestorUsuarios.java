/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gestores;
import java.util.List;
import java.util.ArrayList;
import Entities.Usuario;
import Enums.Rol;
import Exceptions.EntidadNoEncontradaException;
import Exceptions.MailDuplicadoException;

/**
 *
 * @author luciana
 */
public class GestorUsuarios {
    private static List<Usuario> usuarios = new ArrayList<>();
    
    public static void mostrarUsuarios() {
    boolean activos = false;

    for (Usuario u : usuarios) {
        if (!u.isEliminado()) { 
            System.out.println(u);
            activos = true; 
        }
    }

    if (!activos) {
        System.out.println("No hay usuarios activos registrados.");
    }
}
    
    public static void crearUsuario(String nombre, String apellido, String mail, String celular, String contrasenia, Rol rol) throws MailDuplicadoException {
        if (existeMail(mail)) {
            throw new MailDuplicadoException("El correo electrónico '" + mail + "' ya se encuentra registrado.");
        }
        
        Usuario u = new Usuario(nombre, apellido, mail, celular, contrasenia, rol);
        usuarios.add(u);
        System.out.println("Usuario creado correctamente. ID Generado: #" + u.getId());
    }
    
    public static Usuario buscarPorId(Long id) throws EntidadNoEncontradaException {
    Usuario encontrado = null;
    
    for (Usuario u : usuarios) {
        if (u.getId().equals(id) && !u.isEliminado()) {
            encontrado = u;
        }
    } 
    if (encontrado == null) {
            throw new EntidadNoEncontradaException("El usuario con ID " + id + " no existe o está inactivo.");
        }
    return encontrado; 
}
    
    public static void modificarCampoUsuario(Usuario uMod, int opcion, String nuevoValor) {
    switch (opcion) {
        case 1:
            uMod.setNombre(nuevoValor);
            System.out.println("Nombre modificado con éxito.");
            break;
        case 2:
            uMod.setApellido(nuevoValor);
            System.out.println("Apellido modificado con éxito.");
            break;
        case 3:
            uMod.setCelular(nuevoValor);
            System.out.println("Celular modificado con éxito.");
            break;
        case 4:
            uMod.setContrasenia(nuevoValor);
            System.out.println("Contraseña modificada con éxito.");
            break;
    }
}

    public static void modificarRolUsuario(Usuario uMod, Rol nuevoRol, Long idOperador) throws EntidadNoEncontradaException {
    Usuario operador = buscarPorId(idOperador);
    
    if (operador.getRol() == Rol.ADMIN) {
        uMod.setRol(nuevoRol); 
        System.out.println("Rol modificado con éxito a: " + uMod.getRol());
    } else {
        System.out.println("\n El ID ingresado no se corresponde con un Administrador.");
    }
}
    
    
    public static void eliminarUsuario(Long id) throws EntidadNoEncontradaException {
    
    Usuario u = buscarPorId(id);
    u.setEliminado(true); 
    System.out.println("Usuario dado de baja del sistema.");
    
}
    
    public static boolean existeMail(String mail) {
    boolean existe = false;
    int i = 0;
    
    if (mail != null) {
        while (i < usuarios.size() && !existe) {
            Usuario u = usuarios.get(i);
            if (u != null && !u.isEliminado() && u.getMail().equalsIgnoreCase(mail)) {
                existe = true; 
            }
            i++;
        }
    }
    return existe; 
}

//para ediciones de usuarios existentes
public static boolean existeMail(String mail, Long idRegistrado) { 
        boolean existe = false;
        int i = 0;
        
        if (mail != null) {
            while (i < usuarios.size() && !existe) {
                Usuario u = usuarios.get(i);
                if (u != null && !u.isEliminado() && u.getMail().equalsIgnoreCase(mail)) {
                    if (!u.getId().equals(idRegistrado)) {
                        existe = true;
                    }
                }
                i++;
            }
        }
        return existe; 
    }
}

    
