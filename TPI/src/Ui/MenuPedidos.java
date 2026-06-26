/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ui;

import java.time.LocalDate;
import Gestores.GestorPedidos;
import Gestores.GestorProductos;
import Gestores.GestorUsuarios;
import Entities.Pedido;
import Entities.Producto;
import Entities.Usuario;
import Enums.Estado;
import Enums.FormaPago;
import Exceptions.EntidadNoEncontradaException;

/**
 *
 * @author luciana
 */
public class MenuPedidos {
    private final Menu menu;

    public MenuPedidos(Menu menu) {
        this.menu = menu;
    }
    
    public void mostrar() {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE PEDIDOS ---");
            System.out.println("1. Ver Historial de Pedidos");
            System.out.println("2. Hacer un Pedido");
            System.out.println("3. Cancelar un Pedido");
            System.out.println("4. Modificar un Pedido");
            System.out.println("0. Volver al Menú Principal");
            System.out.println("-----------------------------");
            
            opcion = menu.leerInt("Seleccione una opción (0-4): ");

            switch (opcion) {
                case 1:
                    System.out.println("\n--- HISTORIAL DE PEDIDOS ---");
                    GestorPedidos.mostrarPedidos();
                    break;
                    
                case 2:
                    try{
                    crearPedido(); 
                    }catch(EntidadNoEncontradaException e) {
                        System.out.println("[ERROR] " + e.getMessage());
                    }
                    break;
                    
                case 3:
                    System.out.println("\n--- CANCELAR / ELIMINAR PEDIDO ---");
                    Long idCancel = menu.leerLong("ID del Pedido a cancelar: ");
                    try {
                        GestorPedidos.eliminarPedido(idCancel);
                    } catch (EntidadNoEncontradaException e) {
                        System.out.println("[ERROR] " + e.getMessage());
                    }
                    break;
                    
                case 4:
                    try {
                    modificarPedido(); 
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
    
    private void crearPedido() throws EntidadNoEncontradaException {
        Long idUsuario = menu.leerLong("ID del Usuario: ");
        Usuario u = GestorUsuarios.buscarPorId(idUsuario);

        if (u != null) {
            Long idProd = menu.leerLong("ID del Producto: ");
            Producto prod = GestorProductos.buscarPorId(idProd);

            if (prod != null) {
                int cantidad = menu.leerInt("Cantidad: ");

                System.out.println("\n--- SELECCIONE FORMA DE PAGO ---");
                System.out.println("1. EFECTIVO\n2. TARJETA\n3. TRANSFERENCIA");
                int opPago = menu.leerInt("Seleccione opción (1-3): ");

                FormaPago pagoElegido;
                if (opPago == 1) {
                    pagoElegido = FormaPago.EFECTIVO;
                } else if (opPago == 2) {
                    pagoElegido = FormaPago.TARJETA;
                } else {
                    pagoElegido = FormaPago.TRANSFERENCIA;            
                }

                Pedido p = new Pedido(LocalDate.now(), Estado.PENDIENTE, 0.0, pagoElegido, u);
                p.addDetallePedido(cantidad, prod.getPrecio(), prod);

                GestorPedidos.registrarPedido(p);
                u.agregarPedido(p);

                System.out.println("\n Pedido #" + p.getId() + " creado [" + p.getFormaPago() + "]. Total: $" + p.getTotal());
            } else {
                System.out.println("Producto no encontrado.");
            }
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }
     
    private void modificarPedido() throws EntidadNoEncontradaException{
        System.out.println("\n--- MODIFICAR UN PEDIDO ---");
        Long idMod = menu.leerLong("ID del Pedido a modificar: ");
        Pedido pMod = GestorPedidos.buscarPorId(idMod);
        
        if (pMod != null) {
            int subOpcion;
            do {
                System.out.println("\n=======================================");
                System.out.println(">> MODIFICANDO PEDIDO #" + pMod.getId() + " <<");
                System.out.println("=======================================");
                System.out.println("Forma de Pago actual: " + pMod.getFormaPago());
                System.out.println("Total actual: $" + pMod.getTotal());
                System.out.println("-------------------------------------");
                System.out.println("1. Cambiar Forma de Pago");
                System.out.println("2. Agregar Producto al Detalle");
                System.out.println("3. Eliminar Producto del Detalle");
                System.out.println("4. Cambiar Estado del Pedido");
                System.out.println("0. Finalizar Modificación");
                System.out.println("=======================================");
                
                subOpcion = menu.leerInt("Seleccione qué desea hacer: ");
                
                switch (subOpcion) {
                    case 1:
                        System.out.println("\n--- SELECCIONE NUEVA FORMA DE PAGO ---");
                        System.out.println("1. EFECTIVO\n2. TARJETA\n3. TRANSFERENCIA");
                        int opPago = menu.leerInt("Seleccione opción (1-3): ");
                        
                        if (opPago == 1) {
                            pMod.setFormaPago(FormaPago.EFECTIVO);
                        } else if (opPago == 2) {
                            pMod.setFormaPago(FormaPago.TARJETA);
                        } else if (opPago == 3) {
                            pMod.setFormaPago(FormaPago.TRANSFERENCIA);
                        }
                        System.out.println("Forma de pago actualizada con éxito.");
                        break;
                        
                    case 2:
                        System.out.println("\n--- AGREGAR PRODUCTO ---");
                        Long idProdAdd = menu.leerLong("ID del Producto a agregar: ");
                        Producto prodAdd = GestorProductos.buscarPorId(idProdAdd);
                        
                        if (prodAdd != null) {
                            int cantidad = menu.leerInt("Cantidad: ");
                            pMod.addDetallePedido(cantidad, prodAdd.getPrecio(), prodAdd);
                            System.out.println("Producto agregado. El total se actualizó.");
                        } else {
                            System.out.println("Producto no encontrado.");
                        }
                        break;
                        
                    case 3:
                        System.out.println("\n--- ELIMINAR PRODUCTO DEL PEDIDO ---");
                        Long idProdDel = menu.leerLong("ID del Producto a borrar: ");
                        Producto prodDel = GestorProductos.buscarPorId(idProdDel);
                        
                        if (prodDel != null) {
                            pMod.deleteDetallePedidoByProducto(prodDel);
                            System.out.println("Pedido modificado exitosamente.");
                        } else {
                            System.out.println("Producto no encontrado.");
                        }
                        break;
                        
                    case 4:
                        System.out.println("\n--- CAMBIAR ESTADO ---");
                        System.out.println("1. PENDIENTE | 2. CONFIRMADO | 3. TERMINADO | 4. CANCELADO");
                        int opEst = menu.leerInt("Opción (1-4): ");
                        
                        if (opEst == 1) pMod.setEstado(Estado.PENDIENTE);
                        if (opEst == 2) pMod.setEstado(Estado.CONFIRMADO);
                        if (opEst == 3) pMod.setEstado(Estado.TERMINADO);
                        if (opEst == 4) pMod.setEstado(Estado.CANCELADO);
                        System.out.println("Estado actualizado con éxito.");
                        break;
                        
                    case 0:
                        System.out.println("Guardando cambios y saliendo al menú de pedidos...");
                        break;
                        
                    default:
                        System.out.println("Opción no válida.");
                }
            } while (subOpcion != 0);
            
        } else {
            System.out.println("Pedido no encontrado o inactivo.");
        }
    }
     
}