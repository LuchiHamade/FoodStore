/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exceptions;

/**
 *
 * @author luciana
 */
    public class NombreDuplicadoException extends Exception {

    public NombreDuplicadoException() {
        super("El nombre de la categoría ingresada ya existe en el sistema.");
    }

    public NombreDuplicadoException(String message) {
        super(message);
    }
}
    

