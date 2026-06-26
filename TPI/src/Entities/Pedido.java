/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.time.LocalDate;
import Enums.Estado;
import Enums.FormaPago;
import java.util.ArrayList;
import java.util.List;
import Interfaces.Calculable;

/**
 *
 * @author luciana
 */
public class Pedido extends Base implements Calculable {
     private static Long contadorId = 0L;
    private LocalDate fecha;
    private Estado estado;
    private Double total;
    private FormaPago formaPago;
    private List <DetallePedido> detalles;
    private Usuario usuario;

    public Pedido(LocalDate fecha, Estado estado, Double total, FormaPago formaPago, Usuario usuario) {
        super(++contadorId);
        this.fecha = fecha;
        this.estado = estado;
        this.total = total;
        this.formaPago = formaPago;
        this.detalles = new ArrayList<>(); //relación de composición, el detalle del pedido nace con el pedido
        this.usuario = usuario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedido> detalles) {
        this.detalles = detalles;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
 
     @Override
    public void calcularTotal() {
        Double suma = 0.0;
        for (DetallePedido dp: detalles){
            if(dp!= null){
                suma += dp.getSubtotal();
            }
        }
        this.total=suma;
    }


    
    public void addDetallePedido(int cantidad, Double precio, Producto producto){
        DetallePedido detalle = new DetallePedido(cantidad, producto);
        this.detalles.add(detalle);
        calcularTotal();
    }
    
    
    
    //estimo que hubo un typo y no es finde si no find //
    public DetallePedido findDetallePedidoByProducto(Producto p){
        DetallePedido encontrado = null;
        int i = 0;
        
        if(p != null){
            while(i< detalles.size()&& encontrado == null){
                DetallePedido dp= detalles.get(i);
                
                if(dp.getProducto()!= null && dp.getProducto().getId().equals(p.getId())){
                    encontrado = dp;
                }
                
                i++;
            }
        }    
         if(encontrado == null){
             System.out.println("Ningún registro coincide con el producto indicado");
         }    
        
         return encontrado;   
    }    
    
    public void deleteDetallePedidoByProducto(Producto p){
        DetallePedido encontrado = findDetallePedidoByProducto(p);
        if (encontrado != null) {
            this.detalles.remove(encontrado);
            calcularTotal(); 
        }
    }
    

    @Override
    public String toString() {
       return String.format("Pedido [ID=%s, Fecha=%s, Estado=%s, FormaPago=%s, Total=$%.2f]",
            getId(),
            fecha,
            estado,
            formaPago,
            total);
    }

    
}    

    

  
    


