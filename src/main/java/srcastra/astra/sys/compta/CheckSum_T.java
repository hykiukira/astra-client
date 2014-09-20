/*
 * CheckSum_T.java
 *
 * Created on 12 février 2003, 15:44
 */

package srcastra.astra.sys.compta;

/**
 *
 * @author  thomas
 */
public class CheckSum_T implements java.io.Serializable{
    
    /** Creates a new instance of CheckSum_T */
    public CheckSum_T() {
    }
    
    /** Getter for property array.
     * @return Value of property array.
     */
    public java.util.ArrayList getArray() {
        return array;
    }
    
    /** Setter for property array.
     * @param array New value of property array.
     */
    public void setArray(java.util.ArrayList array) {
        this.array = array;
    }
    
    /** Getter for property totaldebit.
     * @return Value of property totaldebit.
     */
    public double getTotaldebit() {
        return totaldebit;
    }
    
    /** Setter for property totaldebit.
     * @param totaldebit New value of property totaldebit.
     */
    public void setTotaldebit(double totaldebit) {
        this.totaldebit = totaldebit;
    }
    
    /** Getter for property totalcredit.
     * @return Value of property totalcredit.
     */
    public double getTotalcredit() {
        return totalcredit;
    }
    
    /** Setter for property totalcredit.
     * @param totalcredit New value of property totalcredit.
     */
    public void setTotalcredit(double totalcredit) {
        this.totalcredit = totalcredit;
    }
    
    /** Getter for property arrayNc.
     * @return Value of property arrayNc.
     */
    public java.util.ArrayList getArrayNc() {
        return arrayNc;
    }
    
    /** Setter for property arrayNc.
     * @param arrayNc New value of property arrayNc.
     */
    public void setArrayNc(java.util.ArrayList arrayNc) {
        this.arrayNc = arrayNc;
    }
    
    /** Getter for property arrayAnc.
     * @return Value of property arrayAnc.
     */
    public java.util.ArrayList getArrayAnc() {
        return arrayAnc;
    }
    
    /** Setter for property arrayAnc.
     * @param arrayAnc New value of property arrayAnc.
     */
    public void setArrayAnc(java.util.ArrayList arrayAnc) {
        this.arrayAnc = arrayAnc;
    }
    
java.util.ArrayList array;
java.util.ArrayList arrayNc;
java.util.ArrayList arrayAnc;
double totaldebit;
double totalcredit;
}
