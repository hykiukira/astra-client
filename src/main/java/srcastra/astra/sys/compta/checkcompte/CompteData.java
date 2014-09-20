/*
 * CompteData.java
 *
 * Created on 22 août 2003, 9:00
 */

package srcastra.astra.sys.compta.checkcompte;
import java.util.*;

/**
 *
 * @author  thomas
 */
public class CompteData {
    
    /** Creates a new instance of CompteData */
    private ArrayList solde;
    private static CompteData comptedata;
    private static Object syn=CompteData.class;
    public static CompteData getCompteData(String key){
         synchronized (syn){
          if(table==null)
              table=new Hashtable();
          if(table.get(key)==null){
              table.put(key, new CompteData());               
          }
          return (CompteData)table.get(key);
        }      
    }
    
    /** Getter for property transaction.
     * @return Value of property transaction.
     */
    public int getTransaction() {
        return transaction;
    }
    public static void test(String key){
          if(table==null)
              table=new Hashtable();
          if(table.get(key)==null){
              table.put(key, new CompteData());               
          }
          CompteData data=(CompteData)table.get(key);     
    }
    
    /** Setter for property transaction.
     * @param transaction New value of property transaction.
     */
    public void setTransaction(int transaction) {
        this.transaction = transaction;
    }
    
    /** Getter for property jota_cleunik.
     * @return Value of property jota_cleunik.
     */
    public int getJota_cleunik() {
        return jota_cleunik;
    }
    
    /** Setter for property jota_cleunik.
     * @param jota_cleunik New value of property jota_cleunik.
     */
    public void setJota_cleunik(int jota_cleunik) {
        this.jota_cleunik = jota_cleunik;
    }
    
    /** Getter for property ce_cent_cleunik.
     * @return Value of property ce_cent_cleunik.
     */
    public int getCe_cent_cleunik() {
        return ce_cent_cleunik;
    }
    
    /** Setter for property ce_cent_cleunik.
     * @param ce_cent_cleunik New value of property ce_cent_cleunik.
     */
    public void setCe_cent_cleunik(int ce_cent_cleunik) {
        this.ce_cent_cleunik = ce_cent_cleunik;
    }
    
    /** Getter for property numerocompte.
     * @return Value of property numerocompte.
     */
    public String getNumerocompte() {
        return numerocompte;
    }
    
    /** Setter for property numerocompte.
     * @param numerocompte New value of property numerocompte.
     */
    public void setNumerocompte(String numerocompte) {
        this.numerocompte = numerocompte;
    }
    
    /** Getter for property ce_cleunik.
     * @return Value of property ce_cleunik.
     */
    public int getCe_cleunik() {
        return ce_cleunik;
    } 
    
    /** Setter for property ce_cleunik.
     * @param ce_cleunik New value of property ce_cleunik.
     */
    public void setCe_cleunik(int ce_cleunik) {
        this.ce_cleunik = ce_cleunik;
    }
    
    /** Getter for property periode.
     * @return Value of property periode.
     */
    public int getPeriode() {
        return periode;
    }
    
    /** Setter for property periode.
     * @param periode New value of property periode.
     */
    public void setPeriode(int periode) {
        this.periode = periode;
    }
    
    /** Getter for property typecentral.
     * @return Value of property typecentral.
     */
    public int getTypecentral() {
        return typecentral;
    }
    
    /** Setter for property typecentral.
     * @param typecentral New value of property typecentral.
     */
    public void setTypecentral(int typecentral) {
        this.typecentral = typecentral;
    }
    
    /** Getter for property solde.
     * @return Value of property solde.
     */
    public ArrayList getSolde() {
        if(solde==null)
            solde=new ArrayList();
        return solde;
    }
    
    /** Setter for property solde.
     * @param solde New value of property solde.
     */
    public void setSolde(ArrayList solde) {
        this.solde = solde;
    }
    
    /** Getter for property exle_cleunik.
     * @return Value of property exle_cleunik.
     *
     */
    public int getExle_cleunik() {
        return exle_cleunik;
    }
    
    /** Setter for property exle_cleunik.
     * @param exle_cleunik New value of property exle_cleunik.
     *
     */
    public void setExle_cleunik(int exle_cleunik) {
        this.exle_cleunik = exle_cleunik;
    }
    
//
    private CompteData() {
       
    }
int ce_cleunik;
int ce_cent_cleunik;
int periode;
int transaction;
String numerocompte;
int jota_cleunik;
int typecentral;
static Hashtable table;
int exle_cleunik;

}
