/*
 * InfoCompta.java
 *
 * Created on 12 juin 2003, 11:44
 */

package srcastra.astra.sys.compta;

/**
 *
 * @author  Thomas
 */
public class InfoCompta {
    
    /** Creates a new instance of InfoCompta */
    public InfoCompta() {
    }  
    /** Getter for property exle_cleunik.
     * @return Value of property exle_cleunik.
     */
    public int getExle_cleunik() {
        return exle_cleunik;
    }  
    /** Setter for property exle_cleunik.
     * @param exle_cleunik New value of property exle_cleunik.
     */
    public void setExle_cleunik(int exle_cleunik) {
        this.exle_cleunik = exle_cleunik;
    }
    /** Getter for property heperiode.
     * @return Value of property heperiode.
     */
    public int getHeperiode() {
        return heperiode;
    } 
    /** Setter for property heperiode.
     * @param heperiode New value of property heperiode.
     */
    public void setHeperiode(int heperiode) {
        this.heperiode = heperiode;
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
    /** Getter for property transaction.
     * @return Value of property transaction.
     */
    public long getTransaction() {
        return transaction;
    }
    /** Setter for property transaction.
     * @param transaction New value of property transaction.
     */
    public void setTransaction(long transaction) {
        this.transaction = transaction;
    }   
    long transaction;
    int heperiode;
    int exle_cleunik;
    int jota_cleunik;
    
}
