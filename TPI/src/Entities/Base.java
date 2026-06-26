


package Entities;

import java.time.LocalDateTime;


/**
 *
 * @author luciana
 */
public abstract class Base {
    private Long id;
    private boolean eliminado; 
    private LocalDateTime createdAt;

    public Base() {
        this.eliminado = false; //sería incoherente que el objeto inicie como eliminado
        this.createdAt = LocalDateTime.now(); // toma el momento exacto de la creación del objeto
    }

    public Base(Long id) {      //para que lo implementen las clases hijas 
        this();      
        this.id = id;//para que solo tengan que indicar el id al generar una nueva instancia
       
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

      
    
    @Override
    public  abstract String toString();
        
    
    
    
    
}
