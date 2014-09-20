/*
 * Solde.java
 *
 * Created on 11 septembre 2003, 12:06
 */

package srcastra.astra.sys.compta.command;
import srcastra.astra.sys.classetransfert.dossier.*;
/**
 *
 * @author  Thomas
 */
public class SoldeTot {
    
    /** Creates a new instance of Solde */
    public SoldeTot(int categorie, int cle, double montant,String dc, Payement_T payement) {
        this.categorie=categorie;
        this.cle=cle;
        this.montant=montant;
        this.payement=payement;
        this.dc=dc;
    }
    
    /** Getter for property caisse.
     * @return Value of property caisse.
     */
  
    
    /** Getter for property categorie.
     * @return Value of property categorie.
     */
    public int getCategorie() {
        return categorie;
    }
    
    /** Setter for property categorie.
     * @param categorie New value of property categorie.
     */
    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }
    
    /** Getter for property cle.
     * @return Value of property cle.
     */
    public int getCle() {
        return cle;
    }
    
    /** Setter for property cle.
     * @param cle New value of property cle.
     */
    public void setCle(int cle) {
        this.cle = cle;
    }
    
    /** Getter for property dc.
     * @return Value of property dc.
     */
    public java.lang.String getDc() {
        return dc;
    }
    
    /** Setter for property dc.
     * @param dc New value of property dc.
     */
    public void setDc(java.lang.String dc) {
        this.dc = dc;
    }
    
    /** Getter for property montant.
     * @return Value of property montant.
     */
    public double getMontant() {
        return montant;
    }
    
    /** Setter for property montant.
     * @param montant New value of property montant.
     */
    public void setMontant(double montant) {
        this.montant = montant;
    }
    
    /** Getter for property payement.
     * @return Value of property payement.
     */
    public srcastra.astra.sys.classetransfert.dossier.Payement_T getPayement() {
        return payement;
    }
    
    /** Setter for property payement.
     * @param payement New value of property payement.
     */
    public void setPayement(srcastra.astra.sys.classetransfert.dossier.Payement_T payement) {
        this.payement = payement;
    }
    
    /** Getter for property periode.
     * @return Value of property periode.
     */
    public long getPeriode() {
        return periode;
    }
    
    /** Setter for property periode.
     * @param periode New value of property periode.
     */
    public void setPeriode(long periode) {
        this.periode = periode;
    }
    
int categorie;
int cle;
double montant;
String dc;
Payement_T payement;
long periode;
}
