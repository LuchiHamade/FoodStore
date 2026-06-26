/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gestores;
import java.util.List;
import java.util.ArrayList;
import Entities.Categoria;
import Exceptions.EntidadNoEncontradaException;
import Exceptions.NombreDuplicadoException;
import Exceptions.ProductosAsociadosException;

/**
 
 * @author luciana
 */
public class GestorCategorias {
   
    
private static List<Categoria> categorias = new ArrayList<>();

    public static void mostrarCategorias() {
        boolean activas = false;
        for (Categoria c :categorias) {
            if (!c.isEliminado()) { 
                System.out.println(c); 
                activas = true;
            }
        }
        if (!activas) {
            System.out.println("No hay categorías cargadas.");
        }
    }
    
    
    public static void crearCategoria(String nombre, String descripcion) throws NombreDuplicadoException {
        if (existeNombreCategoria(nombre)) {
        throw new NombreDuplicadoException("Ya existe una categoría activa con el nombre '" + nombre + "'.");
        }     
        Categoria c = new Categoria(nombre, descripcion); 
        categorias.add(c);
        System.out.println("Categoría registrada correctamente.");
    }
    
    public static boolean existeNombreCategoria(String nombre) {
    boolean existe = false;
    int i = 0;
    
    if (nombre != null) {
        while (i < categorias.size() && !existe) {
            Categoria c = categorias.get(i);
            if (c != null && !c.isEliminado() && c.getNombre().equalsIgnoreCase(nombre.trim())) {
                existe = true;
            }
            i++;
        }
    }
    return existe;
}
    
   public static void modificarCategoria(Long id, String nombre, String descripcion) throws EntidadNoEncontradaException {
    Categoria c = buscarPorId(id);
    c.setNombre(nombre);
    c.setDescripcion(descripcion);
    System.out.println("Categoría modificada con éxito.");        
    }
   
   public static Categoria buscarPorId(Long id) throws EntidadNoEncontradaException {
        Categoria encontrado = null;
        int i = 0;
        
        if (id != null) {
            while (i < categorias.size() && encontrado == null) {
                Categoria c = categorias.get(i);
                if (c != null && c.getId().equals(id) && !c.isEliminado()) {
                    encontrado = c;
                }
                i++;
            }
        }
        if (encontrado == null) {
            throw new EntidadNoEncontradaException("La categoría con ID " + id + " no existe o fue eliminada.");
        }
        return encontrado;
    }
   
    public static void eliminarCategoria(Long id) throws EntidadNoEncontradaException,ProductosAsociadosException {
    Categoria c = buscarPorId(id);
    
    
    if (GestorProductos.tieneProductosEnCategoria(id)) {
        throw new Exceptions.ProductosAsociadosException("No se puede eliminar la categoría '" + c.getNombre() + "' porque tiene productos asociados.");
    }
    
    c.setEliminado(true); 
    System.out.println("Categoría eliminada del sistema.");
}
    


}

    
     

