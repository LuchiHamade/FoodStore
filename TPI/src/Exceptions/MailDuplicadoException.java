/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exceptions;

/**
 *
 * @author lucia
 */
public class MailDuplicadoException extends Exception {
    public MailDuplicadoException() {
        super("El correo electrónico ingresado ya existe en el sistema.");
    }
    
    //recibe el mensaje desde el gestor
    public MailDuplicadoException(String message) {
        super(message);
    }
}
