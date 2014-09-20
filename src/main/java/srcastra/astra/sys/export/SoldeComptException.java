/*
 * SoldeComptException.java
 *
 * Created on 13 mai 2004, 15:18
 */

package srcastra.astra.sys.export;
import java.util.*;
/**
 *
 * @author  Administrateur
 */
public class SoldeComptException extends Exception{
    
    /** Creates a new instance of SoldeComptException */
    public SoldeComptException() {
    }
    
    /** Getter for property erreur.
     * @return Value of property erreur.
     *
     */
    public java.util.ArrayList getErreur() {
        return erreur;
    }
    
    /** Setter for property erreur.
     * @param erreur New value of property erreur.
     *
     */
    public void setErreur(java.util.ArrayList erreur) {
        this.erreur = erreur;
    }
    
ArrayList erreur;
Exception exception;
}
