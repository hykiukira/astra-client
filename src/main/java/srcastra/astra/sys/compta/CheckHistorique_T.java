/*
 * CheckHistorique_T.java
 *
 * Created on 10 septembre 2003, 10:42
 */

package srcastra.astra.sys.compta;
import java.util.*;
/**
 *
 * @author  Thomas
 */
public class CheckHistorique_T implements java.io.Serializable{
    
    /** Creates a new instance of CheckHistorique_T */
    ArrayList solde;
    ArrayList historique;
    public CheckHistorique_T() {
    }
    
    /** Getter for property solde.
     * @return Value of property solde.
     */
    public java.util.ArrayList getSolde() {
        return solde;
    }
    
    /** Setter for property solde.
     * @param solde New value of property solde.
     */
    public void setSolde(java.util.ArrayList solde) {
        this.solde = solde;
    }
    
    /** Getter for property historique.
     * @return Value of property historique.
     */
    public java.util.ArrayList getHistorique() {
        return historique;
    }
    
    /** Setter for property historique.
     * @param historique New value of property historique.
     */
    public void setHistorique(java.util.ArrayList historique) {
        this.historique = historique;
    }
    
}
