/*
 * Memo_T.java
 *
 * Created on 12 janvier 2004, 10:17
 */

package srcastra.astra.sys.classetransfert.dossier;

/**
 *
 * @author  Thomas
 */
public class Memo_T implements java.io.Serializable{
    
    /** Creates a new instance of Memo_T */
    public Memo_T() {
    }
    
    /** Getter for property memoOrg.
     * @return Value of property memoOrg.
     *
     */
    public java.lang.String getMemoOrg() {
         if(memoOrg==null)
            memoOrg="";
        return memoOrg;
    }
    
    /** Setter for property memoOrg.
     * @param memoOrg New value of property memoOrg.
     *
     */
    public void setMemoOrg(java.lang.String memoOrg) {
        this.memoOrg = memoOrg;
    }
    
    /** Getter for property memoPrint.
     * @return Value of property memoPrint.
     *
     */
    public java.lang.String getMemoPrint() {
        if(memoPrint==null)
            memoPrint="";
        return memoPrint;
    }
    
    /** Setter for property memoPrint.
     * @param memoPrint New value of property memoPrint.
     *
     */
    public void setMemoPrint(java.lang.String memoPrint) {
        this.memoPrint = memoPrint;
    }
    
    /** Getter for property empty.
     * @return Value of property empty.
     *
     */
    public boolean isEmpty() {
        return empty;
    }
    
    /** Setter for property empty.
     * @param empty New value of property empty.
     *
     */
    public void setEmpty(boolean empty) {
        this.empty = empty;
    }
    
    /** Getter for property memoFacture.
     * @return Value of property memoFacture.
     *
     */
    public java.lang.String getMemoFacture() {
        return memoFacture;
    }
    
    /** Setter for property memoFacture.
     * @param memoFacture New value of property memoFacture.
     *
     */
    public void setMemoFacture(java.lang.String memoFacture) {
        this.memoFacture = memoFacture;
    }
    
    /** Getter for property specialdossier.
     * @return Value of property specialdossier.
     *
     */
    public int getSpecialdossier() {
        return specialdossier;
    }
    
    /** Setter for property specialdossier.
     * @param specialdossier New value of property specialdossier.
     *
     */
    public void setSpecialdossier(int specialdossier) {
        this.specialdossier = specialdossier;
    }
    
    String memoOrg;
    String memoPrint;
    String memoFacture;
    boolean empty;
    int specialdossier;
    
}
