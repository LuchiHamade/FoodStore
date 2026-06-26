/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luciana
 */
public class Categoria extends Base{
    private static Long contadorId = 0L; // agregamos un contador para ID autoincremental
    private String nombre;
    private String descripcion;
    private List <Producto> productos;

    public Categoria(String nombre, String descripcion) {
        super(++contadorId);
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.productos = new ArrayList <>();
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    
    public void agregarProducto(Producto p){
        if(p !=null){
            this.productos.add(p);
            if(p.getCategoria() !=this){
                p.setCategoria(this);
            }
        }
    }
    
  
    @Override
    public String toString() {
        return String.format("Categoria [ID=%d, Nombre=%s, Descripcion=%s]", getId(), nombre, descripcion);
    }
    
    
}
