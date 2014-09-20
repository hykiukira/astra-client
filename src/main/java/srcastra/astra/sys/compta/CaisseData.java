/*
 * Caisse.java
 *
 * Created on 1 septembre 2003, 16:51
 */

package srcastra.astra.sys.compta;
import java.util.*;

/**
 *
 * @author  Thomas
 */
public class CaisseData implements java.io.Serializable{
    
    /** Creates a new instance of Caisse */
    public CaisseData() {
    }
    
    /** Getter for property caisse.
     * @return Value of property caisse.
     */
    public ArrayList getCaisse() {
        return caisse;
    }
    
    /** Setter for property caisse.
     * @param caisse New value of property caisse.
     */
    public void setCaisse(ArrayList caisse) {
        this.caisse = caisse;
    }
    
    /** Getter for property soldecash.
     * @return Value of property soldecash.
     */
    public double getSoldecash() {
        return soldecash;
    }
    
    /** Setter for property soldecash.
     * @param soldecash New value of property soldecash.
     */
    public void setSoldecash(double soldecash) {
        this.soldecash = soldecash;
    }
    
    /** Getter for property soldecc.
     * @return Value of property soldecc.
     */
    public double getSoldecc() {
        return soldecc;
    }
    
    /** Setter for property soldecc.
     * @param soldecc New value of property soldecc.
     */
    public void setSoldecc(double soldecc) {
        this.soldecc = soldecc;
    }
    
    /** Getter for property soldecheque.
     * @return Value of property soldecheque.
     */
    public double getSoldecheque() {
        return soldecheque;
    }
    
    /** Setter for property soldecheque.
     * @param soldecheque New value of property soldecheque.
     */
    public void setSoldecheque(double soldecheque) {
        this.soldecheque = soldecheque;
    }
    
    /** Getter for property soldecashPer.
     * @return Value of property soldecashPer.
     *
     */
    public double getSoldecashPer() {
        return soldecashPer;
    }
    
    /** Setter for property soldecashPer.
     * @param soldecashPer New value of property soldecashPer.
     *
     */
    public void setSoldecashPer(double soldecashPer) {
        this.soldecashPer = soldecashPer;
    }
    
    /** Getter for property soldeccPer.
     * @return Value of property soldeccPer.
     *
     */
    public double getSoldeccPer() {
        return soldeccPer;
    }
    
    /** Setter for property soldeccPer.
     * @param soldeccPer New value of property soldeccPer.
     *
     */
    public void setSoldeccPer(double soldeccPer) {
        this.soldeccPer = soldeccPer;
    }
    
    /** Getter for property soldechequePer.
     * @return Value of property soldechequePer.
     *
     */
    public double getSoldechequePer() {
        return soldechequePer;
    }
    
    /** Setter for property soldechequePer.
     * @param soldechequePer New value of property soldechequePer.
     *
     */
    public void setSoldechequePer(double soldechequePer) {
        this.soldechequePer = soldechequePer;
    }
    
    /** Getter for property solde.
     * @return Value of property solde.
     */
  
    
double soldecash;
double soldecc;
double soldecheque;
double soldecashPer;
double soldeccPer;
double soldechequePer;
ArrayList caisse;
}
