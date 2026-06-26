/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gestores;
import java.util.List;
import java.util.ArrayList;
import Entities.Pedido;
import Enums.Estado;
import Exceptions.EntidadNoEncontradaException;

/**
 *
 * @author luciana 
 */
public class GestorPedidos {
    
    private static List<Pedido> pedidos = new ArrayList<>();
    
    public static void mostrarPedidos() {
        boolean activos = false;

        for (Pedido p : pedidos) {
            if (!p.isEliminado()) { 
                System.out.println(p);
                activos = true; 
            }
        }

        if (!activos) {
            System.out.println("No hay pedidos registrados en el sistema.");
        }
    }
 
    
    public static Pedido buscarPorId(Long id) throws EntidadNoEncontradaException {
    Pedido encontrado = null;
    for (Pedido p : pedidos) {
        if (p.getId().equals(id) && !p.isEliminado()) {
            encontrado = p;
        }
    }
    
    if (encontrado == null) {
            throw new EntidadNoEncontradaException("El pedido con ID " + id + " no existe en el sistema o fue cancelado.");
        }
    return encontrado;
}
    public static void registrarPedido(Pedido nuevoPedido) {
    if (nuevoPedido != null) {
        pedidos.add(nuevoPedido);
        System.out.println("Pedido registrado en el historial.");
    }
}
    
    public static boolean eliminarPedido(Long id) throws EntidadNoEncontradaException {
        boolean exito = false;
        Pedido p = buscarPorId(id);
        
        if (p != null) {
                        if (p.getEstado() != Estado.TERMINADO) {
                
                p.setEliminado(true);                       
                p.setEstado(Estado.CANCELADO);
                
                exito = true;
                System.out.println("El pedido #" + id + " fue dado de baja lógicamente con éxito.");
            } else {
                System.out.println(" No se puede eliminar un pedido que ya fue TERMINADO.");
            }
        } else {
            System.out.println(" No se encontró ningún pedido activo con el ID: " + id);
        }
        
        return exito; 
    }
}
    

    

