/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;
import Enums.Rol;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luciana
 */
public class Usuario extends Base {
    private static Long contadorId = 0L; //para ID autoincremental
    private String nombre; 
    private String apellido;
    private String mail; 
    private String celular;
    private String contrasenia; 
    private Rol rol;
    private List <Pedido> pedidos;

    public Usuario(String nombre, String apellido, String mail, String celular, String contrasenia, Rol rol) {
        super(++contadorId);
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.celular = celular;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.pedidos = new ArrayList <>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    
    public void agregarPedido(Pedido p){
        if(p!= null){
            this.pedidos.add(p);
            p.setUsuario(this);
        }
    }

    @Override
    public String toString() {
    String reporte = "====================================================================\n" +
                     "USUARIO: [" + this.getNombre() + " " + this.getApellido() + "] | Mail: [" + this.getMail() + "] | Rol: [" + this.getRol() + "]\n" +
                     "====================================================================\n";
    
    Double totalAcumulado = 0.0;
    for (Pedido p : this.pedidos) {
        reporte += "> Pedido #[" + p.getId() + "] | Fecha: [" + p.getFecha() + "] | Estado: [" + p.getEstado() + "] | FormaPago: [" + p.getFormaPago() + "]\n" +
                   "------------------------------------\n";
        
        for (Entities.DetallePedido dp : p.getDetalles()) {
            reporte += "  - DetallePedido #[" + dp.getId() + "]: [" + dp.getProducto().getNombre() + "] x [" + dp.getCantidad() + "] => Subtotal: $[" + dp.getSubtotal() + "]\n";
        }
        
        reporte += "TOTAL DEL PEDIDO: $[" + p.getTotal() + "]\n" +
                   "------------------------------------\n";
               
        totalAcumulado += p.getTotal();
    }
    
    reporte += "TOTAL ACUMULADO del usuario: $[" + totalAcumulado + "]\n" +
               "====================================================================";
           
    return reporte;
}
    }
    
    
    
    
    


