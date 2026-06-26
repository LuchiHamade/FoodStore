/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

/**
 *
 * @author luciana
 */
public class DetallePedido extends Base {
    private static Long contadorId = 0L;  // agregamos un contador estático
    private int cantidad;
    private Double subtotal;
    private Producto producto;
   
    
    //sin subtotal para que sea determinado via calcular subtotal o via setter.
    public DetallePedido(int cantidad, Producto producto) {
        super(++contadorId);
        setProducto(producto);
        setCantidad(cantidad);
    }
    
   
    
    private Double calcularSubtotal() {
        if (producto != null && producto.getPrecio() != null) {
            this.subtotal = this.cantidad * producto.getPrecio();
        } else {
            this.subtotal = 0.0;    
        }
        return this.subtotal;
    }

   
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        if (this.producto != null) {
        this.subtotal = calcularSubtotal();
        
       }
    }    

    public Double getSubtotal() {
        return calcularSubtotal(); // De esta manera el getter llama al método privado y se calcula el subtotal.
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        if (producto != null) {
        this.producto = producto;
        calcularSubtotal();
        }
    }    
        
    @Override
    public String toString() {
        return String.format("DetallePedido [ID=%s, Producto=%s, Cantidad=%d, Subtotal=$%.2f]", 
            getId(), producto.getNombre(), cantidad, getSubtotal());
    }
}


