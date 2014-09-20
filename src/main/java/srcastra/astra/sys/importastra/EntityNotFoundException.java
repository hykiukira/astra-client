/*
 * EntityNotFoundException.java
 *
 * Created on 17 juin 2004, 13:29
 */

package srcastra.astra.sys.importastra;

/**
 *
 * @author  Administrateur
 */
public class EntityNotFoundException extends Exception{
    
    /** Creates a new instance of EntityNotFoundException */
    String entite;
    public EntityNotFoundException(String entite) {
        this.entite=entite;
    }
    
    /**
     * Getter for property entite.
     * @return Value of property entite.
     */
    public java.lang.String getEntite() {
        return entite;
    }
    
    /**
     * Setter for property entite.
     * @param entite New value of property entite.
     */
   
    
}
