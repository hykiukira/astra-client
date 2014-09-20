/*
 * Cubic_T.java
 *
 * Created on 19 décembre 2003, 14:55
 */

package srcastra.astra.sys.export;
import java.util.*;
/**
 *
 * @author  Thomas
 */
public class Cubic_T implements java.io.Serializable{
    
    /** Creates a new instance of Cubic_T */
    ArrayList vente;
    ArrayList achat;
    ArrayList financier;
    ArrayList od;
    Hashtable fournisseur;
    Hashtable client;
    public Cubic_T() {
     
    }
    /** Getter for property achat.
     * @return Value of property achat.
     *
     */
    public java.util.ArrayList getAchat() {
        return achat;
    }
    
    /** Setter for property achat.
     * @param achat New value of property achat.
     *
     */
    public void setAchat(java.util.ArrayList achat) {
        this.achat = achat;
    }
    
    /** Getter for property client.
     * @return Value of property client.
     *
     */
    public java.util.Hashtable getClient() {
        return client;
    }
    
    /** Setter for property client.
     * @param client New value of property client.
     *
     */
    public void setClient(java.util.Hashtable client) {
        this.client = client;
    }
    
    /** Getter for property financier.
     * @return Value of property financier.
     *
     */
    public java.util.ArrayList getFinancier() {
        return financier;
    }
    
    /** Setter for property financier.
     * @param financier New value of property financier.
     *
     */
    public void setFinancier(java.util.ArrayList financier) {
        this.financier = financier;
    }
    
    /** Getter for property fournisseur.
     * @return Value of property fournisseur.
     *
     */
    public java.util.Hashtable getFournisseur() {
        return fournisseur;
    }
    
    /** Setter for property fournisseur.
     * @param fournisseur New value of property fournisseur.
     *
     */
    public void setFournisseur(java.util.Hashtable fournisseur) {
        this.fournisseur = fournisseur;
    }
    
    /** Getter for property od.
     * @return Value of property od.
     *
     */
    public java.util.ArrayList getOd() {
        return od;
    }
    
    /** Setter for property od.
     * @param od New value of property od.
     *
     */
    public void setOd(java.util.ArrayList od) {
        this.od = od;
    }
    
    /** Getter for property vente.
     * @return Value of property vente.
     *
     */
    public java.util.ArrayList getVente() {
        return vente;
    }
    
    /** Setter for property vente.
     * @param vente New value of property vente.
     *
     */
    public void setVente(java.util.ArrayList vente) {
        this.vente = vente;
    }
    
}
