/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Gestores;

import java.util.ArrayList;
import java.util.List;
import Entities.Producto;
import Entities.Categoria;
import Exceptions.PrecioInvalidoException;
import Exceptions.StockInvalidoException;
import Exceptions.EntidadNoEncontradaException;



/**
 *
 * @author luciana
 */
public class GestorProductos {
    private static List<Producto> productos = new ArrayList<>();

    public static void mostrarProductos() {
        boolean activos = false;
        for (Producto p : productos) {
            if (!p.isEliminado()) { 
                System.out.println(p); 
                activos = true;
            }
        }
        if (!activos) {
            System.out.println("No hay productos registrados.");
        }
    }
    
    public static void crearProducto(String nombre, Double precio, String descripcion, int stock, String imagen, Boolean disponible, Categoria categoria) throws PrecioInvalidoException, StockInvalidoException{
    
    if (categoria == null) {
        System.out.println("No se puede crear el producto porque la categoría no existe.");
    } else if (precio < 0) {
        throw new PrecioInvalidoException("El precio ingresado no puede ser negativo.");
    } else if (stock < 0) {
        throw new StockInvalidoException("El stock ingresado no puede ser negativo."); 
    } else {
        Producto p = new Producto(nombre, precio, descripcion, stock, imagen, disponible, categoria);
        productos.add(p);
        categoria.agregarProducto(p);     
        System.out.println("Producto creado correctamente. ID Generado: #" + p.getId());
    }
    
}
    
    
    
    public static void actualizarPrecio(Producto p, Double precio) throws PrecioInvalidoException {
    if (precio < 0) {
        throw new PrecioInvalidoException("El precio ingresado no puede ser negativo.");
    }
    
    p.setPrecio(precio);
    System.out.println("Precio modificado con éxito.");
}


public static void actualizarStock(Producto p, int stock) throws StockInvalidoException {
    if (stock < 0) {
            throw new StockInvalidoException("El stock ingresado no puede ser negativo.");
        }
        
        p.setStock(stock);
        System.out.println("Stock modificado con éxito.");
    }

public static Producto buscarPorId(Long id) throws EntidadNoEncontradaException {
    Producto encontrado = null; 

    for (Producto p : productos) {
        if (p.getId().equals(id) && !p.isEliminado()) {
            encontrado = p; 
        }
    }
     if (encontrado == null) {
            throw new EntidadNoEncontradaException("El producto con ID " + id + " no existe o fue dado de baja.");
        }

    return encontrado; 
}

public static void eliminarProducto(Long id) throws EntidadNoEncontradaException {
    Producto p = buscarPorId(id);

    if (p != null) {
        p.setEliminado(true); 
        System.out.println("Producto eliminado del sistema.");
    } else {
        System.out.println("No se encontró ningún producto activo con ese ID.");
    }
}

public static boolean tieneProductosEnCategoria(Long idCategoria) {
    boolean tieneAsociados = false;
    int i = 0;
    
    if (idCategoria != null) {
        while (i < productos.size() && !tieneAsociados) {
            Producto p = productos.get(i);
            if (p != null && !p.isEliminado() && p.getCategoria().getId().equals(idCategoria)) {
                tieneAsociados = true;
            }
            i++;
        }
    }
    return tieneAsociados; 
}

public static void actualizarCategoria(Producto p, Categoria nuevaCategoria) {
    if (nuevaCategoria != null) {
        p.setCategoria(nuevaCategoria);
        System.out.println("Categoría del producto modificada con éxito.");
    }
}
 
public static void mostrarProductosPorCategoria(Long idCategoria) {
    boolean activos = false;
    
    for (Producto p : productos) {
        if (!p.isEliminado() && p.getCategoria().getId().equals(idCategoria)) { 
            System.out.println(p); 
            activos = true;
        }
    }
    
    if (!activos) {
        System.out.println("No hay productos registrados en esta categoría.");
    }
}

}

