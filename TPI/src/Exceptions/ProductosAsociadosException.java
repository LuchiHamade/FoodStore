/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exceptions;

/**
 *
 * @author luciana
 */

    public class ProductosAsociadosException extends Exception {

    public ProductosAsociadosException() {
        super("No se puede eliminar la entidad porque tiene productos asociados.");
    }

    public ProductosAsociadosException(String message) {
        super(message);
    }
}

